package com.example.AtharamanBackend1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.example.AtharamanBackend1.entity")
@EnableJpaRepositories("com.example.AtharamanBackend1.repository")
@SpringBootApplication
public class AtharamanBackend1Application {

	public static void main(String[] args) {
		SpringApplication.run(AtharamanBackend1Application.class, args);
	}

}
