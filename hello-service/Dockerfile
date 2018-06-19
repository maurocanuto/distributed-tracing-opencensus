FROM maven:3.5-jdk-8-alpine AS build

WORKDIR /hello-service

COPY pom.xml /hello-service/pom.xml
#RUN ["mvn", "dependency:resolve"]

# Adding source, compile and package into a fat jar
COPY ["src/main", "/hello-service/src/main"]
RUN ["mvn", "package"]

FROM openjdk:8-jre-alpine

COPY --from=build /hello-service/target/hello-service.jar /
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/hello-service.jar"]
EXPOSE 8888