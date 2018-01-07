package com.ecnu.trivial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//???
@SpringBootApplication
public class TrivialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrivialApplication.class, args);
	}
}
