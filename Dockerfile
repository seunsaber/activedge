FROM openjdk:11-jre-slim

COPY target/activedge-1.0-SNAPSHOT.jar /activedge.jar

CMD ["java", "-jar", "/activedge.jar"]