package com.inf.pdp.esbrito.pdp.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer implements Consumer {

    private long totalDelay;
    private long totalMessages;

    @Override
    @RabbitListener(queues="${pdp.rabbitmq.queue}")
    public void consume(String msg) {
        long delay = System.nanoTime() - Long.parseLong(msg);
        System.out.println("Delay da mensagem eh de " + delay + " ms (RabbitMQ)");
        totalDelay = totalDelay + delay;
        totalMessages++;
        System.out.println("Delay medio eh de " + (totalDelay/totalMessages)/1000.0 + " ms (RabbitMQ)" );
    }
}