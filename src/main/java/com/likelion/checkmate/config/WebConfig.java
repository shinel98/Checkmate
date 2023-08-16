//package com.likelion.checkmate.config;
//
//import com.likelion.checkmate.interceptor.AuthenticationInterceptor;
//import com.likelion.checkmate.jwt.JwtService;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@RequiredArgsConstructor
//public class WebConfig implements WebMvcConfigurer {
//    private final JwtService jwtService;
//
//    @Value("${custom.origin.allowed}")
//    private String client;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthenticationInterceptor(jwtService))
//                .excludePathPatterns("/api/auth/**");
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns(client)
//                .allowedMethods("GET", "POST", "DELETE", "PATCH")
//                .allowCredentials(true);
//    }
//}
