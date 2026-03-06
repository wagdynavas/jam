# Build stage
FROM eclipse-temurin:25 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:25-jre
WORKDIR /opt/app

# Create non-root user
RUN addgroup --system javauser && adduser --system --group javauser
USER javause

# JVM settings for container
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Expose port and define entrypoint
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

COPY --from=builder /build/target/jam.jar .
CMD ["java", "-jar", "jam.jar"]
