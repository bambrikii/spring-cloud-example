package org.bambrikii.examples.spring.cloud.auth.cert.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class CertAuthService1App {
    public static void main(String[] args) {
        SpringApplication.run(CertAuthService1App.class);
    }

    @GetMapping("/helloCertSecure")
    public String helloSecureCert() {
        return "Hello, secure by certificated world! " + LocalDateTime.now().toString();
    }
}
