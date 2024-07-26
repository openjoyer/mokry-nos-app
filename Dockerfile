FROM openjdk:24-jdk-slim
WORKDIR /app
COPY /target/mokriy-nos-app-1.0.0-SNAPSHOT.jar /app/nos-app.jar

EXPOSE 8881
ENTRYPOINT ["java", "-jar", "nos-app.jar"]