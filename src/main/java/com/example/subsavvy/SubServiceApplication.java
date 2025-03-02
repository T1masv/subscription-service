package com.example.subsavvy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// http://localhost:8080/context-path/swagger-ui.html

@SpringBootApplication
public class SubServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubServiceApplication.class, args);
    }

}
