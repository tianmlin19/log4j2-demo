package com.tml.log4j2demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Log4j2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Log4j2DemoApplication.class, args);
    }

}
