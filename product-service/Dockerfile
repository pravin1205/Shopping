# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

VOLUME /tmp
# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/*.jar productservice.jar

# Make port 8080 available to the world outside this container
EXPOSE 9004

# Run the jar file
ENTRYPOINT ["java", "-jar", "productservice.jar"]
