FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-service ./common-service
COPY store-app ./store-app

RUN mvn -f common-service/pom.xml clean install -DskipTests
RUN mvn -f store-app/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/store-app/target/*.jar store-app.jar

ENTRYPOINT ["java","-jar","store-app.jar"]