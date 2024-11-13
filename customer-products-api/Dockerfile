# Use a base image with OpenJDK 17
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built by your Spring Boot application
COPY build/libs/*.jar app.jar

# Expose the port your Spring Boot app listens on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "app.jar"]