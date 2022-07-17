package com.solusione.day2;

import com.hyvercode.starter.annotations.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableSwagger
@EnableConfigurationProperties(SecurityProperties.class)
public class Day2Application{
	public static void main(String[] args) {
		SpringApplication.run(Day2Application.class, args);
	}
}
