FROM maven:3.8.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY pom.xml ./
COPY ./src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 8881
COPY --from=builder /app/target/*.jar /app/nos-app.jar
ENTRYPOINT ["java", "-jar", "/app/nos-app.jar"]