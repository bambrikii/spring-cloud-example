1. https://start.spring.io/

### Spring Cloud

1. https://cloud.spring.io/spring-cloud-netflix/multi/multi__service_discovery_eureka_clients.html
1. https://www.baeldung.com/spring-cloud-configuration

### Two-way SSL

1. https://www.neovasolutions.com/2020/03/19/steps-to-implement-2-way-mutual-ssl-authentication/
1. https://medium.com/@niral22/2-way-ssl-with-spring-boot-microservices-2c97c974e83
1. https://piotrminkowski.wordpress.com/2018/05/21/secure-discovery-with-spring-cloud-netflix-eureka/

##### Client Certificate Request Validation
openssl req -text -noout -verify -in ./secure-cert-client1.csr
##### Certificate Details
openssl x509 -noout -text -in secure-cert-client1.crt
