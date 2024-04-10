package com.neo.app.transactionsaccounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
public class MicroserviceTransactionsaccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTransactionsaccountsApplication.class, args);
	}

}
