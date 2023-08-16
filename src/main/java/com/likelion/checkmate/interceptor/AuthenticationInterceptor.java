package com.likelion.checkmate.interceptor;

import com.likelion.checkmate.jwt.JwtService;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.repository.PostRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final static String BEARER = "Bearer ";
    private final JwtService jwtService;

    private final PostRepository postRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();  // 예: /posts/123
        String[] parts = uri.split("/");

        Long postId = null;

        try {
            if (parts.length > 0) {
                postId = Long.parseLong(parts[parts.length - 1]);  // 마지막 부분을 post ID로 추출
            }
        } catch (NumberFormatException e) {
            // URL의 마지막 부분이 숫자가 아닌 경우, 처리
        }

        if (postId != null) {
            Post post = postRepository.findById(postId).orElse(null);

            if (post != null && post.getScope() == 2) {
                // scope 값이 2일 때만 Interceptor 로직을 건너뛰기
                return true;
            }
        }
        if (CorsUtils.isPreFlightRequest(request)){
            return true;
        }
        Optional<String> token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(value -> value.startsWith(BEARER))
                .map(value -> value.substring(BEARER.length()));
        Optional<Claims> claims = jwtService.validate(token);

        if(claims.isPresent()) {
            request.setAttribute("claims", claims.get());
            return true;
        }
        response.sendError(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}
