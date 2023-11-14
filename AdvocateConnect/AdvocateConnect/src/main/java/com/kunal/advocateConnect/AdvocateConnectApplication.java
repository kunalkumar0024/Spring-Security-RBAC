package com.kunal.advocateConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.kunal.advocateConnect.repo" })
public class AdvocateConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvocateConnectApplication.class, args);
	}

}
