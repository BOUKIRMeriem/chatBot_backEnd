package com.example.authentification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;


@Configuration
@CrossOrigin("*")
@SpringBootApplication
public class AuthentificationApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(AuthentificationApplication.class, args);
	}


}