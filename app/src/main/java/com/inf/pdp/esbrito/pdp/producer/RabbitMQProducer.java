package com.inf.pdp.esbrito.pdp.producer;

import com.google.gson.Gson;
import com.inf.pdp.esbrito.pdp.domain.BenchmarkMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer implements Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private Gson parser;

    @Override
    public void produce(int messageByteSize){
        amqpTemplate.convertAndSend("benchmark", "message-test",
                parser.toJson(new BenchmarkMessage(messageByteSize)));
    }
}