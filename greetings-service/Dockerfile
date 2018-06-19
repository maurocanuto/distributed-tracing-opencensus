FROM maven:3.5-jdk-8-alpine AS build

WORKDIR /greetings-service

COPY pom.xml /greetings-service/pom.xml
#RUN ["mvn", "dependency:resolve"]

# Adding source, compile and package into a fat jar
COPY ["src/main", "/greetings-service/src/main"]
RUN ["mvn", "package"]

FROM openjdk:8-jre-alpine

COPY --from=build /greetings-service/target/greetings-service.jar /
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/greetings-service.jar"]
EXPOSE 8080