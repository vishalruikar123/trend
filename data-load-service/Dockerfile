# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="vishalruikar@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8001 available to the world outside this container
EXPOSE 8001

# The application's jar file
ARG JAR_FILE=target/live-price-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} live-price-service.jar

# Run the jar file
ENTRYPOINT ["java", "-jar","/live-price-service.jar"]
