FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/scientific-calculator-1.0-SNAPSHOT.jar app.jar

RUN echo "java -jar /app/app.jar" >> /root/.bashrc

CMD ["bash"]