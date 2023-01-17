FROM maven:3.8.6-eclipse-temurin-11

RUN mkdir /opt/app

COPY src/ /opt/app/src/
COPY pom.xml /opt/app/

RUN ls -la /opt/app/*

WORKDIR /opt/app

RUN mvn clean package -DskipTest

ENTRYPOINT ["java", "-jar", /opt/app/target/users-rest-api-docker-0.0.1-SNAPSHOT.jar]