package com.tml.log4j2demo.log4j2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class CommonConfig {

    @Bean
    public Executor threadPoolExecutor() {
        return Executors.newFixedThreadPool(10);
    }

}
