package com.likelion.checkmate.auth.controller;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.likelion.checkmate.auth.request.LoginRequest;
import com.likelion.checkmate.jwt.*;
import com.likelion.checkmate.user.application.UserService.UserService;
import com.likelion.checkmate.user.application.dto.UserJwtDto;
import com.likelion.checkmate.user.domain.entity.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/login")
    public ResponseEntity<UserJwtDto> kakaoCallback(@RequestParam String code) {
        HashMap<String, Object> userInfo;
//        System.out.println("code = " + request.getCode());
        System.out.println("code = " + code);
//        String accessToken = getKakaoAccessToken(request.getCode());
        String accessToken = getKakaoAccessToken(code);
        userInfo = getUserInfo(accessToken);
        System.out.println("userInfo = " + userInfo);


        Optional<User> userOr = userService.isPresent(Long.parseLong(userInfo.get("userKakaoId").toString()));

        if(userOr.isPresent()) {
            User user = userOr.get();
            JwtPair tokens = jwtService.issueToken(user.getEmail(), user.getName());
            userService.saveUser(userInfo, tokens);
            System.out.println("tokens = " + tokens);

            return ResponseEntity.ok(
                    UserJwtDto.builder()
                            .isRegistered(true)
                            .tokenType("Bearer ")
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
        String nickname = claims.get("nickname", String.class);
        String accessToken = jwtService.issueToken(email, name ,GrantType.ACCESS_TOKEN);

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

            // POST 요처에 필요로 요구하는 파라미터를 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(conn.getOutputStream())));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=4f5d143ab557ac31113b376147f3676e");
            sb.append("&redirect_uri=http://localhost:8080/api/auth/login");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}
