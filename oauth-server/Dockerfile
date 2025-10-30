FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-service ./common-service
COPY oauth-server ./oauth-server

RUN mvn -f common-service/pom.xml clean install -DskipTests
RUN mvn -f oauth-server/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/oauth-server/target/*.jar oauth-server.jar

ENTRYPOINT ["java","-jar","oauth-server.jar"]