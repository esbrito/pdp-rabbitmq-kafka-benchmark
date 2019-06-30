package com.inf.pdp.esbrito.pdp.consumer;

import com.google.gson.Gson;
import com.inf.pdp.esbrito.pdp.configuration.AllConsumedLatch;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer extends BasicConsumer {

    public KafkaConsumer(Gson parser, AllConsumedLatch latch) {
        super(parser, latch, "Kafka");
    }

    @Override
    @KafkaListener(topics = "benchmark", groupId = "group_id")
    public void consume(String msg) {
        process(msg);
    }
}
