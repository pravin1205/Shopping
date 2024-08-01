# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/*.jar apigateway.jar

# Make port 8080 available to the world outside this container
EXPOSE 9001

# Run the jar file
ENTRYPOINT ["java", "-jar", "apigateway.jar"]
