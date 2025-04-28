#!/bin/sh

exec java \
  -Djava.security.egd=file:/dev/./urandom \
  ${JAVA_OPTS} \
  -jar /app/app.jar \
  --spring.profiles.active=${SPRING_PROFILES_ACTIVE:-dev} \
  "$@"