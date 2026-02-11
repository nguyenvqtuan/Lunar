FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app
# Create log directory
RUN mkdir -p /var/log/lunar && chmod 777 /var/log/lunar

COPY build/libs/Lunar-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
