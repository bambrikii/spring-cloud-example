package org.bambrikii.examples.spring.cloud.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class BasicAuthService1App {
    public static void main(String[] args) {
        SpringApplication.run(BasicAuthService1App.class);
    }

    @GetMapping("/helloSecure")
    public String helloSecure() {
        return "Hello, secure world! " + LocalDateTime.now().toString();
    }
}
