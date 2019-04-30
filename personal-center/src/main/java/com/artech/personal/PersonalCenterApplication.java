package com.artech.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.artech")
public class PersonalCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalCenterApplication.class, args);
	}

}
