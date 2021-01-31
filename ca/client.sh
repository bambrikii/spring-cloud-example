#!/bin/bash

KEYSTORE_NAME=$1
STORE_PW=$2
HOST=localhost

openssl req -new -newkey rsa:4096 -keyout "${KEYSTORE_NAME}.key" -out "${KEYSTORE_NAME}.csr" \
 -subj "/CN=${HOST}/emailAddress=${KEYSTORE_NAME}@${HOST}/C=US/ST=NJ/L=Weehawken/O=Services/OU=${KEYSTORE_NAME}" -passin "pass:${STORE_PW}" \
 -passin "pass:${STORE_PW}" \
 -passout "pass:${STORE_PW}"


openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in "${KEYSTORE_NAME}.csr" -out "${KEYSTORE_NAME}".crt -days 365 -CAcreateserial \
 -extfile client.ext \
 -passin "pass:${STORE_PW}"

openssl pkcs12 -export -out "${KEYSTORE_NAME}".p12 -name "${KEYSTORE_NAME}" -inkey "${KEYSTORE_NAME}.key" -in "${KEYSTORE_NAME}.crt" \
 -passin "pass:${STORE_PW}" \
 -passout "pass:${STORE_PW}"

keytool -importcert -noprompt -keystore "${KEYSTORE_NAME}.p12" -alias rootCA -file rootCA.crt \
 -storepass "${STORE_PW}"

#keytool -importcert -noprompt -keystore "${KEYSTORE_NAME}.p12" -alias "${KEYSTORE_NAME}-signed" -file "${KEYSTORE_NAME}.crt" \
# -storepass "${STORE_PW}"
