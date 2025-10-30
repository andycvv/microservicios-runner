FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-service ./common-service
COPY ubicacion-service ./ubicacion-service

RUN mvn -f common-service/pom.xml clean install -DskipTests
RUN mvn -f ubicacion-service/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/ubicacion-service/target/*.jar ubicacion-service.jar

ENTRYPOINT ["java","-jar","ubicacion-service.jar"]