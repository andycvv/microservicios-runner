FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-service ./common-service
COPY product-app ./product-app

RUN mvn -f common-service/pom.xml clean install -DskipTests
RUN mvn -f product-app/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/product-app/target/*.jar product-app.jar

ENTRYPOINT ["java","-jar","product-app.jar"]