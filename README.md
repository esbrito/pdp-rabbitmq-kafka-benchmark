# Trabalho final da cadeira de PDP


Trata-se de uma aplicação que efetua um simples benchmark entre o RabbitMQ e Kafka. 

Para subir a infra é necessário docker e docker-compose. Verificar README.md localizado na pasta infra/ para ver detalhes de como subir a infraestrutura

Para executar o programa execute o seguinte:


```
cd app
mvn clean install
java -jar target/pdp-0.0.1-SNAPSHOT.jar kafka # se deseja rodar o benchmark do Kafka
java -jar target/pdp-0.0.1-SNAPSHOT.jar rabbit # se deseja rodar o benchmark do RabbitMQ

```
