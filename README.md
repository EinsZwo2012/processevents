# Processevents

Integrates Camunda BPM historic process events with Elasticsearch via kafka

## How to use

1. Start Zookeper and Kafka under default ports
2. Start Elasticsearch (must be version 6.5 at the moment)
3. Start event-consumer (Spring Boot Application)
4. Start event-producer-sample-app (Spring Boot Application)
5. Go to localhost:8082. Login in with demo:demo and start a new process instance in tasklist
6. Produced events will be written to Apache Kafka topic and consumed by event-consumer which populates them to Elasticsearch under index 'camunda-*' (for example camunda-historic-process-instance)
7. Start Kibana (must be version 6.5 at the moment)
8. Go to localhost:5601 and import vizualizations.json and dashboard.json for some sample vizualizations of data


Example:

![KPIs](kpis.png)

![KPIs2](kpis2.png)

