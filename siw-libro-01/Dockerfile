# Build stage: compile the Spring Boot jar
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src src
RUN mvn -q -DskipTests package

# Run stage: only the JRE and the jar
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/siw-libro-01-1.0.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
