FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src

RUN mvn clean install

# Estágio 2: Execução
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

RUN apk upgrade && apk update && \
    apk add --no-cache tzdata curl && \
    cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    echo "America/Sao_Paulo" > /etc/timezone && \
    apk del tzdata && \
    rm -rf /var/cache/apk/*

COPY --from=build /app/target/kubernetes-labs-*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]