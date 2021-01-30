#!/bin/bash

# https://www.baeldung.com/x-509-authentication-in-spring-security
# Generate root key and public certificate
openssl req -x509 -sha256 -days 3650 -newkey rsa:4096 -keyout rootCA.key -out rootCA.crt
openssl x509 -in rootCA.crt -text
openssl pkcs12 -export -out rootCA.p12 -name "example.com" -inkey rootCA.key -in rootCA.crt

# Export root keypair to P12
openssl pkcs12 -export -out localhost.p12 -name "localhost" -inkey localhost.key -in localhost.crt

# Create certificate signing request
openssl req -new -newkey rsa:4096 -keyout localhost.key -out localhost.csr

# Sign certificate request
openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in localhost.csr -out localhost.crt -days 365 -CAcreateserial -extfile localhost.ext
openssl x509 -in localhost.crt -text

# Export client keypair to P12
openssl pkcs12 -export -out localhost.p12 -name "localhost" -inkey localhost.key -in localhost.crt
#keytool -importkeystore -srckeystore localhost.p12 -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS

# Export root public key
# openssl rsa -in rootCA.key -pubout > rootCA.pub
