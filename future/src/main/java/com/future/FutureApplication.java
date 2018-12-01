package com.future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.future.repository")
public class FutureApplication {
	public static void main(String[] args) {
		SpringApplication.run(FutureApplication.class, args); }
	public void run(){ }
}

