package org.bambrikii.examples.spring.cloud.secure.cert.registry;

import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Configuration
public class SecureCertRegistryConfig {
    @Value("${PASSWORD}")
    private String keyPw;

    @Value("${PASSWORD}")
    private String trustPw;

    @Value("${spring.application.name}")
    private String appName;

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
