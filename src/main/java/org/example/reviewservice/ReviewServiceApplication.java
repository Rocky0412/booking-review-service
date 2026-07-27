package org.example.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication(
		scanBasePackages = {
				"org.example.reviewservice",
				"org.example.entityservices"
		}
)
@EnableJpaRepositories(basePackages = {
		"org.example.reviewservice.repositories",
		"org.example.entityservices.repositories"
})
@EntityScan(basePackages = {
		"org.example.reviewservice.models",
		"org.example.entityservices.models"
})
@EnableDiscoveryClient
public class ReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewServiceApplication.class, args);
	}

}
