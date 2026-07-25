package com.todomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.todomanager.presentation", "com.todomanager.application" })
public class TodomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodomanagerApplication.class, args);
	}

}
