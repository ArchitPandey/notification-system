# KEDA Setup using Helm Chart #

### Add repo ###
* helm repo add kedacore https://kedacore.github.io/charts

### Install Chart ###
#### This installs the Scaled Object CRD and controller which is used to scale the app based on Rabbit MQ length (Using Prometheus metrics) ####
#### https://keda.sh/docs/2.16/scalers/prometheus/ ####
* helm install keda kedacore/keda

# Run Email Sender Service App

### Build
* mvn clean install

### Create image locally
* docker build -t email-sender-service:1.0.0 .

### Create deployment, service, servicemonitor and scaled object (to let prometheus know where to scrape app metrics from)
* kubectl apply -f email-sender-svc-deploy.yaml \
* kubectl apply -f .\email-sender-svc-clusterip.yaml
* kubectl apply -f .\email-sender-autoscaler.yaml
* kubectl apply -f .\email-sender-servicemonitor.yaml

### Scaled Object is used to scale the email-sender-service based on Rabbit MQ length. 
