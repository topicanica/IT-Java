package com.Anica.vjezba4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class Vjezba4Application {

	public static void main(String[] args) {
		SpringApplication.run(Vjezba4Application.class, args);
	}

}
