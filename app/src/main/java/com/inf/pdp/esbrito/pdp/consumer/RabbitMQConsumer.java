package com.inf.pdp.esbrito.pdp.consumer;

import com.google.gson.Gson;
import com.inf.pdp.esbrito.pdp.configuration.AllConsumedLatch;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.concurrent.atomic.AtomicLong;


public class RabbitMQConsumer extends BasicConsumer  {

    public RabbitMQConsumer(Gson parser, AtomicLong totalDelay, AtomicLong totalMessages, AllConsumedLatch latch) {
        super(parser, totalDelay, totalMessages, latch, "RabbitMQ");
    }

    @RabbitListener(queues="${pdp.rabbitmq.queue}")
    @Override
    public void consume(String msg) {
        process(msg);
    }

}