package com.inf.pdp.esbrito.pdp.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void produce() {
        this.kafkaTemplate.send("benchmark", String.valueOf(System.nanoTime()));
    }
}
