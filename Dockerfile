#immagine di base
FROM maven:3.8.6-eclipse-temurin-11

#creo nel container la cartella dove posizionerò il jar da eseguire
RUN mkdir /opt/app

#copio il jar creato nella cartella creata in precedenza
COPY src/ /opt/app/src/
COPY pom.xml /opt/app/

RUN ls -la /opt/app/*

#mi posiziono all'interno della cartella di lavoro
WORKDIR /opt/app

#avvio la build del progetto
RUN mvn clean package -DskipTests

ENTRYPOINT ["java", "-jar", "/opt/app/target/users-rest-api-docker-0.0.1-SNAPSHOT.jar"]