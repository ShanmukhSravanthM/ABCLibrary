package com.tata.titan.libaray.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : Shanmukh Sravanth M 
 * 
 * Spring Boot Application Main/Starting point
 * 
 */

@SpringBootApplication
@EnableScheduling
public class AbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcApplication.class, args);
	}

}
 