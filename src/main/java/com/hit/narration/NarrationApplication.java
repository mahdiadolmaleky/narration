package com.hit.narration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude = {SecurityAutoConfiguration.class}
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NarrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NarrationApplication.class, args);
    }
}
