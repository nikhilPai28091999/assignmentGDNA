package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.example.demo","com.example.utilities"})
public class App2Application {

	public static void main(String[] args) {
		SpringApplication.run(App2Application.class, args);
	}

}
