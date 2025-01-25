//package com.brilhatte.app.infra.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class WebMvcConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//
//		registry.addMapping("/auth/**")
//			.allowedOrigins("**")
//			.allowedMethods("GET","POST", "OPTIONS","PUT", "DELETE")
//			.allowedHeaders("*")
//			.exposedHeaders("*")
//			.allowCredentials(true);
//	}
//}