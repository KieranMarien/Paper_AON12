package com.example.todobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToDoBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoBackEndApplication.class, args);
    }

}
