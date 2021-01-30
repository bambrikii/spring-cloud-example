package org.bambrikii.examples.spring.cloud.service1;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("authentication")
public class ApplicationClients {
    private final List<ApplicationClient> clients = new ArrayList<>();

    public List<ApplicationClient> getClients() {
        return this.clients;
    }
}

