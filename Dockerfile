FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src

RUN mvn clean install

FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ARG TZDATA_VERSION=2025b-r0
ARG CURL_VERSION=8.14.1-r1

RUN apk upgrade && apk update && \
    apk add --no-cache tzdata=$TZDATA_VERSION curl=$CURL_VERSION  && \
    cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    echo "America/Sao_Paulo" > /etc/timezone && \
    apk del tzdata && \
    rm -rf /var/cache/apk/*

COPY --from=build /app/target/kubernetes-labs-*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]