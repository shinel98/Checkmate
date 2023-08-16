package com.likelion.checkmate.config;

import com.likelion.checkmate.interceptor.AuthenticationInterceptor;
import com.likelion.checkmate.jwt.JwtService;
import com.likelion.checkmate.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final JwtService jwtService;
    private final PostRepository postRepository;

    @Value("${custom.origin.allowed}")
    private String client;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(jwtService, postRepository))
                .excludePathPatterns("/api/auth/**");
    }
//@Override
//public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(new KakaoInterceptor())
//            .excludePathPatterns("/api/auth/**");
//}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(client)
                .allowedMethods("GET", "POST", "DELETE", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
