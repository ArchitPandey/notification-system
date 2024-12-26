# Notification System #
### A simple notification system that exposes REST APIs to send notifications to external users ###

## Components ##
* Notification Service - Exposes REST Api to send notifications to external users *
* Rabbit MQ - Gets notification messages from Notification service (for eg, for email notifications). Each notification type has a separate queue with consumer app consuming from it *
* Email Sender Service - A scalable service that consumes the notifications of type email from Rabbit MQ and does a mock send to users *
* Prometheus / Grafana - Collect metrics from Rabbit MQ, Email Sender service *
* KEDA - Kubernetes Event Driver Auto scaler reads Rabbit MQ metrics from Prometheus and scales email-sender-service based on Rabbit MQ Queue length *


