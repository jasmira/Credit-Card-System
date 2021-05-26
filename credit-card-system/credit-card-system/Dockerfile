FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/credit-card-system-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} credit-card-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/credit-card-server-1.0.0.jar"]