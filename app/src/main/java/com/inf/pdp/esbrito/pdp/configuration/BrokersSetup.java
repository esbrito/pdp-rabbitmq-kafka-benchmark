package com.inf.pdp.esbrito.pdp.configuration;

import com.google.gson.Gson;
import com.inf.pdp.esbrito.pdp.consumer.KafkaConsumer;
import com.inf.pdp.esbrito.pdp.consumer.RabbitMQConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokersSetup {

    @Autowired
    private Gson parser;

    @Bean
    public Queue queue() {
        return new Queue("benchmark-test-queue", false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("benchmark");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("message-test");
    }

    @Bean
    public RabbitMQConsumer rabbitMQConsumer1() {
        return new RabbitMQConsumer(parser);
    }

    @Bean
    public RabbitMQConsumer rabbitMQConsumer2() {
        return new RabbitMQConsumer(parser);
    }

    @Bean
    public RabbitMQConsumer rabbitMQConsumer3() {
        return new RabbitMQConsumer(parser);
    }

    @Bean
    public KafkaConsumer kafkaConsumer1() {
        return new KafkaConsumer(parser);
    }

    @Bean
    public KafkaConsumer kafkaConsumer2() {
        return new KafkaConsumer(parser);
    }

    @Bean
    public KafkaConsumer kafkaConsumer3() {
        return new KafkaConsumer(parser);
    }


}
