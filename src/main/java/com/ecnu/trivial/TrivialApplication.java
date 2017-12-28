package com.ecnu.trivial;

import com.ecnu.trivial.webSocket.WebSocketServer;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.security.auth.login.Configuration;

@SpringBootApplication
public class TrivialApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(TrivialApplication.class, args);
		WebSocketServer.setApplicationContext(configurableApplicationContext);
	}
}
