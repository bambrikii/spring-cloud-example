package org.bambrikii.examples.spring.cloud.auth.basic.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class Service1App {
    public static void main(String[] args) {
        SpringApplication.run(Service1App.class);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world! " + LocalDateTime.now().toString();
    }
}
