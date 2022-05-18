FROM maven:3.8.5-jdk-8 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jdk-alpine
COPY --from=build /usr/src/app/target/foobar-1.0.0-SNAPSHOT.jar /usr/app/foobar-1.0.0-SNAPSHOT.jar
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/foobar-1.0.0-SNAPSHOT.jar"] 