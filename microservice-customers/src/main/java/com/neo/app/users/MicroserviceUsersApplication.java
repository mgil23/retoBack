package com.neo.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
public class MicroserviceUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceUsersApplication.class, args);
    }

}
