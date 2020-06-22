package com.wrd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceUserApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApp.class,args);
    }
}
