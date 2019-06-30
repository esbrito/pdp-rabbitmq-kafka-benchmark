package com.inf.pdp.esbrito.pdp.producer;

import com.google.gson.Gson;
import com.inf.pdp.esbrito.pdp.domain.BenchmarkMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Gson parser;

    @Override
    public void produce(int messageByteSize) {
        this.kafkaTemplate.send("benchmark",
                parser.toJson(new BenchmarkMessage(messageByteSize)));
    }
}
