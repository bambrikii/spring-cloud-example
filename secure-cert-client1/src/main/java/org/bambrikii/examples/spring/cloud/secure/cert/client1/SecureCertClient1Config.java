package org.bambrikii.examples.spring.cloud.secure.cert.client1;

import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Configuration
@EnableWebSecurity
public class SecureCertClient1Config extends WebSecurityConfigurerAdapter {
    @Value("${PASSWORD}")
    private String keyPw;

    @Value("${PASSWORD}")
    private String trustPw;

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) throws KeyManagementException,
            UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        SSLContext sslContext = customSslContext();
        HttpClient client = HttpClients.custom().setSSLContext(sslContext).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(client);
        return new RestTemplate(requestFactory);
    }

    @Bean
    public SSLContext customSslContext() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException {
        return org.apache.http.ssl.SSLContextBuilder.create()
                .loadKeyMaterial(ResourceUtils.getFile("classpath:" + appName + ".p12"), keyPw.toCharArray(), // keystore
                        // password
                        keyPw.toCharArray() // key password
                ).loadTrustMaterial(ResourceUtils.getFile("classpath:" + appName + ".p12"), trustPw.toCharArray() // truststore
                        // password
                ).build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().requiresChannel().anyRequest().requiresSecure();
    }

    @Bean
    public DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, KeyManagementException {
        SSLContext sslContext = customSslContext();
        EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder()
                .withClientName(appName)
                .withCustomSSL(sslContext)
                .withMaxTotalConnections(10)
                .withMaxConnectionsPerHost(10);
        DiscoveryClientOptionalArgs args = new DiscoveryClientOptionalArgs();
        args.setEurekaJerseyClient(builder.build());
        return args;
    }
}
