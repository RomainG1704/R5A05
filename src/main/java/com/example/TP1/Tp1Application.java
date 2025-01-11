package com.example.TP1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.example.TP1.accessingdatamysql")
@RestController
@EntityScan("com.example.TP1.Entite")
public class Tp1Application {

	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
	}

	@GetMapping("/bonjour")
	public String hello(){
		return "hello world";
	}
}
