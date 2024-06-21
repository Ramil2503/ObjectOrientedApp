FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests
LABEL authors="ramilhagverdiyev"

FROM openjdk:21-jdk-slim
COPY --from=build /target/web-client-0.0.1-SNAPSHOT.jar web-client.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","web-client.jar"]