FROM maven:3.6.3-ibmjava-8-alpine AS profile-dock

WORKDIR /usr/src/profile-microservice
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /profile-microservice
COPY --from=profile-dock /usr/src/profile-microservice/target/profile-microservice-0.0.1-SNAPSHOT.jar ./
EXPOSE 8084
CMD ["java", "-jar", "profile-microservice-0.0.1-SNAPSHOT.jar"]