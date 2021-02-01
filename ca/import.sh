#!/bin/bash

PW=${1}

for p12 in secure-*.p12; do
  for crt in secure-*.crt; do
    p12Filename="${p12%.*}"
    crtFilename="${crt%.*}"
    if [ "$p12Filename" != "$crtFilename" ]; then
      echo "     ${crt} -> ${crtFilename}-signed -> ${p12Filename}"
      keytool -importcert -noprompt -keystore "${p12}" -alias "${crtFilename}-signed" -file "${crt}" -storepass "${PW}"
    fi
  done
done
