# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the jar file from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (uses $PORT env variable for cloud platforms)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}"]