FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-service ./common-service
COPY user-app ./user-app

RUN mvn -f common-service/pom.xml clean install -DskipTests
RUN mvn -f user-app/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/user-app/target/*.jar user-service.jar

ENTRYPOINT ["java","-jar","user-service.jar"]