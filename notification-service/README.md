# Prometheus Setup using Helm Chart #

### Add repo ###
* helm repo add prometheus-community https://prometheus-community.github.io/helm-charts

### Install Chart ###
#### This also installs the Service Monitor CRD which will be used by Rabbit MQ and apps to inform prometheus about with url to scrape for metrics ####
* helm install prometheus prometheus-community/kube-prometheus-stack -f .\kube-prometheus-stack-values.yaml

### Expose prometheus ui and grafana ###
* kubectl apply -f .\prometheus-nodeport-svc.yaml
* kubectl apply -f .\grafana-nodeport-svc.yaml


# Rabbit MQ Setup Using Helm Chart #

### Add repo ###
* helm repo add bitnami https://charts.bitnami.com/bitnami

### Install chart ###
#### This will also create a service monitor object that tells prometheus where to scrape MQ metrics from ####
* helm install -f .\rabbitmq-values.yaml rabbitmq bitnami/rabbitmq

### Create Exchange and Queues ###
* Goto http://localhost:15672/ login (admin / admin123)
* Primary Exchange - notif-sys
* Primary Queue - notif-sys-email 
* To queue add - ttl, x-dead-letter-exchange and x-dead-letter-routing-key properties to auto route to dlq when message is nacked / ttl expires
* Bind Primary Queue to Primary Exchange
* DL Exchange - notif-sys.dlx
* DL Queue - notif-sys-dlq
* Bind DLQ to DLX

### To access rabbit mq amqp port
* kubectl port-forward --namespace default svc/rabbitmq 5672:5672

### To access rabbit mq management interface
* kubectl port-forward --namespace default svc/rabbitmq 15672:15672

### To access prometheus metrics
* kubectl port-forward --namespace default svc/rabbitmq 9419:9419

### Rabbit MQ Metrics URL
* echo http://127.0.0.1:9419/metrics

# Run Notification Service App

### Build 
* mvn clean install

### Create image locally
* docker build -t notification-service:1.0.0 .

### Create deployment, service and servicemonitor (to let prometheus know where to scrape app metrics from)
* kubectl apply -f .\notif-svc-deployment.yaml
* kubectl apply -f .\notif-svc-nodeport.yaml
* kubectl apply -f .\notif-app-servicemonitor.yaml
