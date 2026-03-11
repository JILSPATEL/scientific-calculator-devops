FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/scientific-calculator-1.0-SNAPSHOT.jar app.jar

CMD ["bash", "-c", "java -jar app.jar"]