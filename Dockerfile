# Etapa de build
FROM maven:3.9.6-eclipse-temurin-17 as builder

WORKDIR /app

COPY server/pom.xml .
COPY server/src ./src

RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
