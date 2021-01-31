package org.bambrikii.examples.spring.cloud.secure.cert.client1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SecureCertClient1RestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/helloCertSecureRemote")
    public String helloSecureCert() {
        return restTemplate.getForObject("https://localhost:8084/helloCertSecure", String.class);
    }
}
