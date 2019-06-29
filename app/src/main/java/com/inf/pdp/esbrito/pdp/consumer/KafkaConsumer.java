package com.inf.pdp.esbrito.pdp.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer implements Consumer {

    private long totalDelay;
    private long totalMessages;

    @KafkaListener(topics = "benchmark", groupId = "group_id")
    public void consume(String msg) {
        long delay = System.nanoTime() - Long.parseLong(msg);
        System.out.println("Delay da mensagem eh de " + delay/1000.0 + " ms (Kafka)");
        totalDelay = totalDelay + delay;
        totalMessages++;
        System.out.println("Delay medio eh de " + (totalDelay/totalMessages)/1000.0 + " ms (Kafka)" );
    }
}
