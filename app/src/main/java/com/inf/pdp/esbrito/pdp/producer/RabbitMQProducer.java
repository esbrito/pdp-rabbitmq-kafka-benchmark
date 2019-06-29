package com.inf.pdp.esbrito.pdp.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer implements Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void produce(){
        amqpTemplate.convertAndSend("benchmark", "message-test", System.nanoTime());
    }
}