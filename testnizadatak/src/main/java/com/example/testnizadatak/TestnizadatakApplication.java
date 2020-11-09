package com.example.testnizadatak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestnizadatakApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestnizadatakApplication.class, args);
    }

}
