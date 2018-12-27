package com.future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FutureApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(FutureApplication.class, args);
		for (String name : appContext.getBeanDefinitionNames()) {
			System.out.println("name: " + name);
		}
	}
}
