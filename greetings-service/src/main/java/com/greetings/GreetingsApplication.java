package com.greetings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GreetingsApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(GreetingsApplication.class, args);
    }

}
