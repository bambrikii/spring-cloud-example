package org.bambrikii.examples.spring.cloud.secure.cert.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SecureCertRegistryApp {
    public static void main(String[] args) {
        SpringApplication.run(SecureCertRegistryApp.class, args);
    }
}
