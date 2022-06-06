package com.webapp.foobar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class FoobarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoobarApplication.class, args);
	}

}
