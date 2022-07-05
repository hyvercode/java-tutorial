package com.solusione.day2;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class Day2Application  implements ApplicationRunner {

	@Value("${spring.application.name:demo}")
	private String name;

	public static void main(String[] args) {
		SpringApplication.run(Day2Application.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("Application Runner "+name);
	}

}
