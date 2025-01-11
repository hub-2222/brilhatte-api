package com.brilhatte.app.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("**").allowedMethods("*").allowedOrigins("*");
            }
        };
    }

//    private final RateLimitInterceptor rateLimitInterceptor;
//
//    @Autowired
//    public WebMvcConfig(RateLimitInterceptor rateLimitInterceptor) {
//        this.rateLimitInterceptor = rateLimitInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(rateLimitInterceptor);
//    }
}