#!/bin/bash

PW=$1

./clean-all.sh
./root-ca.sh "${PW}"
./client.sh secure-cert-registry "${PW}"
./client.sh secure-cert-client1 "${PW}"
./client.sh secure-cert-service1 "${PW}"
