FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests
LABEL authors="ramilhagverdiyev"

FROM openjdk:21-jdk-slim
COPY --from=build /target/product-inventory-0.0.1-SNAPSHOT.jar product-inventory.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar","product-inventory.jar"]