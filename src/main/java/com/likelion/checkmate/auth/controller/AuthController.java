package com.likelion.checkmate.auth.controller;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.likelion.checkmate.jwt.*;
import com.likelion.checkmate.user.application.service.UserService;
import com.likelion.checkmate.user.application.dto.UserJwtDto;
import com.likelion.checkmate.user.domain.entity.User;
import com.likelion.checkmate.user.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Optional;

@CrossOrigin(
        origins = "${custom.origin.allowed}",
        allowedHeaders = "POST, GET, DELETE, PATCH, OPTIONS",
        allowCredentials = "true")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Value("${custom.jwt.secret}")
    private String jwtSecretKey;  // .yml 파일의 secretKey 값이 주입됩니다.
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public ResponseEntity<UserJwtDto> kakaoCallback(@RequestParam String code) {
        HashMap<String, Object> userInfo;
//        System.out.println("code = " + request.getCode());
        System.out.println("code = " + code);
//        String accessToken = getKakaoAccessToken(request.getCode());
        String accessToken = getKakaoAccessToken(code);
        userInfo = getUserInfo(accessToken);
        System.out.println("userInfo = " + userInfo);
        System.out.println("userkakaoId = " + userInfo.get("userKakaoId"));

        Optional<User> userOr = userService.isPresent(Long.parseLong(userInfo.get("userKakaoId").toString()));

        if(userOr.isPresent()) {
            User user = userOr.get();
            JwtPair tokens = jwtService.issueToken(user.getEmail(), user.getName(), accessToken);
            userService.saveUser(userInfo, tokens);
            System.out.println("tokens = " + tokens);

            return ResponseEntity.ok(
                    UserJwtDto.builder()
                            .isRegistered(true)
                            .tokenType("Bearer ")
//                            .accessToken(accessToken)
                            .tokens(tokens)
                            .build());
        }

        userService.saveUser(userInfo);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(UserJwtDto.builder()
                        .isRegistered(false)
                        .build());

    }



        @PostMapping("/logout")
        public String logout(@RequestHeader("Authorization") String jwtToken) {
            final String KAKAO_LOGOUT_URL = "https://kapi.kakao.com/v1/user/logout";
            try {
                // JWT 토큰 파싱
                String jwtSecret = jwtSecretKey;  // 실제 사용하는 JWT secret key로 교체해주세요.
                Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken.replace("Bearer ", "")).getBody();
                String accessToken = claims.get("accessToken", String.class);
                // Kakao 로그아웃 로직
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(KAKAO_LOGOUT_URL))
                        .header("Authorization", "Bearer " + accessToken)
                        .POST(HttpRequest.BodyPublishers.noBody())
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    return "Failed to log out from Kakao: " + response.body();
                }


//                System.out.println("claims = " + claims.getSubject());
                String userEmail = claims.getSubject();

                // DB에서 refreshToken 만료
                User user = userRepository.findUserByEmail(userEmail);  // 해당 사용자 정보 조회

                if (user != null) {
                    user.setRefreshToken(null);
                    user.setAccessToken(null);
                    userRepository.save(user);
                }

                return "Logged out successfully!";

            } catch (Exception e) {
                return "Error during logout: " + e.getMessage();
            }
        }

    @PostMapping("/token")
    public ResponseEntity<TokenInfo> issueAccessToken(@RequestBody TokenForm tokenForm) {
        Optional<String> refreshTokenOr = Optional.ofNullable(tokenForm.getRefreshToken());
        Optional<Claims> claimsOr = jwtService.validate(refreshTokenOr);

        if (refreshTokenOr.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        } else if (claimsOr.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
        Claims claims = claimsOr.get();
        String email = claims.get("email", String.class);
        String name = claims.get("name", String.class);
        String kaccessToken = claims.get("accessToken", String.class);
        String accessToken = jwtService.issueToken(email, name , kaccessToken, GrantType.ACCESS_TOKEN);

        return ResponseEntity.ok(new TokenInfo(GrantType.ACCESS_TOKEN, accessToken));
    }
    public String getKakaoAccessToken(String code) {

        String accessToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로 설정
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            // POST 요처에 필요로 요구하는 파라미터를 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(conn.getOutputStream())));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=4f5d143ab557ac31113b376147f3676e");
            sb.append("&redirect_uri=http://localhost:8080/api/auth/login");
//            sb.append("&redirect_uri=http://localhost:3000/login/kakaojs");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String line;
                StringBuilder responseError = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    responseError.append(line);
                }
                System.out.println("Response Error: " + responseError.toString());
            }
            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("result = " + result);

            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();

            System.out.println("accessToken = " + accessToken);

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<>();

        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            Long userId = element.getAsJsonObject().get("id").getAsLong();

            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
            String name = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();

            userInfo.put("nickname", name);
            userInfo.put("email", email);
            userInfo.put("userKakaoId", userId);
            userInfo.put("accessToken", accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}
