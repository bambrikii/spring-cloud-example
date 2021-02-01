package org.bambrikii.examples.spring.cloud.secure.cert.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class SecureCertClient1App {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SecureCertClient1App.class);
        RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
        System.out.println(restTemplate.getForObject("https://localhost:8084/helloCertSecure", String.class));
    }
}
