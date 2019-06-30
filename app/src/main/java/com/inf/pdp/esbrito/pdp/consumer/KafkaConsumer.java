package com.inf.pdp.esbrito.pdp.consumer;

import com.google.gson.Gson;
import com.inf.pdp.esbrito.pdp.configuration.AllConsumedLatch;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.atomic.AtomicLong;

public class KafkaConsumer extends BasicConsumer {

    public KafkaConsumer(Gson parser, AtomicLong totalDelay, AtomicLong totalMessages, AllConsumedLatch latch) {
        super(parser, totalDelay, totalMessages, latch, "Kafka");
    }

    @Override
    @KafkaListener(topics = "benchmark", groupId = "group_id")
    public void consume(String msg) {
        process(msg);
    }
}
