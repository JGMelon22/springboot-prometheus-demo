# Build Stage
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY . .

RUN mvn clean install

# Production Stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/prometheus-example-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]