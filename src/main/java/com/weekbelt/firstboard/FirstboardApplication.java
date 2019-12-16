package com.weekbelt.firstboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FirstboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstboardApplication.class, args);
    }

}
