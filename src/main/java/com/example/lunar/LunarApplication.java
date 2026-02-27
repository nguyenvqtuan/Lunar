package com.example.lunar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LunarApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunarApplication.class, args);
    }
}
