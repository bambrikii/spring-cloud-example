package org.bambrikii.examples.spring.cloud.auth.basic.service1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationClient {
    private String username;
    private String password;
    private String[] roles;
}
