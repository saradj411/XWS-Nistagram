FROM maven:3.6.3-ibmjava-8-alpine AS media-dock

WORKDIR /usr/src/media-microservice
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /media-microservice
COPY --from=media-dock /usr/src/media-microservice/target/media-microservice-0.0.1-SNAPSHOT.jar ./
EXPOSE 8085
CMD ["java", "-jar", "media-microservice-0.0.1-SNAPSHOT.jar"]