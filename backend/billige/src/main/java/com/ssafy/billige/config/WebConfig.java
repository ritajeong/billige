package com.ssafy.billige.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.exposedHeaders("Authorization")
			.allowedHeaders("Authorization");
		// .allowCredentials(true)
		// .allowedMethods(
		//         HttpMethod.GET.name(),
		//         HttpMethod.POST.name(),
		//         HttpMethod.DELETE.name(),
		//         HttpMethod.PUT.name(),
		//         HttpMethod.HEAD.name());
	}
}