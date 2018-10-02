package com.capitalone.batchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories({ "com.capitalone.batchservice"})
public class CleanupApplication {
	public static void main(String[] args) {
		SpringApplication.run(CleanupApplication.class, args);
	}
}
