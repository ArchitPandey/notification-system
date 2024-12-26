FROM eclipse-temurin:17-jre-ubi9-minimal
LABEL authors="Archit"
RUN mkdir /opt/app
COPY /target/email-sender-service-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/email-sender-service-0.0.1-SNAPSHOT.jar"]