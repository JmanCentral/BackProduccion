FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/backendproduccion-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_trivia.jar

EXPOSE 8862

ENTRYPOINT ["java" , "-jar", "app_trivia.jar"]