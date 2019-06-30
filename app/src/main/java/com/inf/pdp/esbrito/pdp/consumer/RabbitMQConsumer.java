package com.inf.pdp.esbrito.pdp.consumer;

import com.google.gson.Gson;
import com.inf.pdp.esbrito.pdp.configuration.AllConsumedLatch;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class RabbitMQConsumer extends BasicConsumer  {

    public RabbitMQConsumer(Gson parser, AllConsumedLatch latch) {
        super(parser, latch, "RabbitMQ");
    }

    @RabbitListener(queues="${pdp.rabbitmq.queue}")
    @Override
    public void consume(String msg) {
        process(msg);
    }

}