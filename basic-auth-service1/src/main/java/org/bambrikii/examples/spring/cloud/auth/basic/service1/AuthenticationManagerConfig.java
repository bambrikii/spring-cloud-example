package org.bambrikii.examples.spring.cloud.auth.basic.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ApplicationClients.class)
public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    ApplicationClients application;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = auth.inMemoryAuthentication();
        for (ApplicationClient client : application.getClients()) {
            configurer
                    .withUser(client.getUsername())
                    .password("{noop}" + client.getPassword())
                    .roles(client.getRoles());
        }
    }

}
