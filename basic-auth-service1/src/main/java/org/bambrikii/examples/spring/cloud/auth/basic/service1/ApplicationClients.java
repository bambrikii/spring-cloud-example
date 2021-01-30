package org.bambrikii.examples.spring.cloud.auth.basic.service1;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("authentication")
public class ApplicationClients {
    private final List<ApplicationClient> clients = new ArrayList<>();

    public List<ApplicationClient> getClients() {
        return this.clients;
    }
}

