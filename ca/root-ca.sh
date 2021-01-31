#!/bin/bash

STORE_PW=$1
HOST=localhost.localdomain

openssl req -x509 -sha256 -days 3650 -newkey rsa:4096 -keyout rootCA.key -out rootCA.crt \
  -subj "/CN=${HOST}/emailAddress=email@${HOST}/C=US/ST=NJ/L=Weehawken/O=${HOST}-CA/OU=CA" \
  -passin "pass:${STORE_PW}" \
  -passout "pass:${STORE_PW}"

openssl pkcs12 -export -out rootCA.p12 -name "${HOST}" -inkey rootCA.key -in rootCA.crt \
  -passin "pass:${STORE_PW}" \
  -passout "pass:${STORE_PW}"
