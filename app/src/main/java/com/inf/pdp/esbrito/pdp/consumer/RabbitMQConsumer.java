package com.inf.pdp.esbrito.pdp.consumer;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class RabbitMQConsumer extends BasicConsumer  {

    public RabbitMQConsumer(Gson parser) {
        super(parser, "RabbitMQ");
    }

    @RabbitListener(queues="${pdp.rabbitmq.queue}")
    @Override
    public void consume(String msg) {
        process(msg);
    }

}