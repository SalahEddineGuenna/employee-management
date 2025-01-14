FROM openjdk:17-jdk-slim

COPY target/employee-management.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]