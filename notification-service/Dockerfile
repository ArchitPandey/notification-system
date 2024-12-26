FROM eclipse-temurin:17
LABEL authors="Archit"
RUN mkdir /opt/app
COPY /target/notification-service-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/notification-service-0.0.1-SNAPSHOT.jar"]