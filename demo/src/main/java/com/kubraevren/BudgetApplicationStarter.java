package com.kubraevren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.kubraevren"})
@ComponentScan(basePackages = {"com.kubraevren"})
@EnableJpaRepositories(basePackages = {"com.kubraevren"})
@SpringBootApplication
public class BudgetApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(BudgetApplicationStarter.class, args);
	}

}
