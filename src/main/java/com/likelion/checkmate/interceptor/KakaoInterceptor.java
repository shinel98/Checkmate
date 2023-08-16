//package com.likelion.checkmate.interceptor;
//
//import com.likelion.checkmate.post.domain.entity.Post;
//import com.likelion.checkmate.post.domain.repository.PostRepository;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.cors.CorsUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//@RequiredArgsConstructor
//public class KakaoInterceptor implements HandlerInterceptor {
//
//    private final PostRepository postRepository;
//    private final String KAKAO_TOKEN_VALIDATION_URL = "https://kapi.kakao.com/v1/user/access_token_info";
//
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        String accessToken = request.getHeader("Authorization");
//
//        String uri = request.getRequestURI();
//        String[] parts = uri.split("/");
//
//        Long postId = null;
//
//        try {
//            if (parts.length > 0) {
//                postId = Long.parseLong(parts[parts.length - 1]);
//            }
//        } catch (NumberFormatException e) {
//
//        }
//
//        if (postId != null) {
//            Post post = postRepository.findById(postId).orElse(null);
//
//            if (post != null && post.getScope() == 2) {
//
//                return true;
//            }
//        }
//        if (CorsUtils.isPreFlightRequest(request)){
//            return true;
//        }
//
//
//        if (accessToken == null || !isValidToken(accessToken)) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//        return true;
//    }
//
//    private boolean isValidToken(String token) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + token);
//
//        ResponseEntity<String> response = restTemplate.exchange(KAKAO_TOKEN_VALIDATION_URL, HttpMethod.GET, new HttpEntity<>(headers), String.class);
//
//        return response.getStatusCodeValue() == 200;
//    }
//}
