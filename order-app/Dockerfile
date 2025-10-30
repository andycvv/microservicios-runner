FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-service ./common-service
COPY order-app ./order-app

RUN mvn -f common-service/pom.xml clean install -DskipTests
RUN mvn -f order-app/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/order-app/target/*.jar order-app.jar

ENTRYPOINT ["java","-jar","order-app.jar"]