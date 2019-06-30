package com.inf.pdp.esbrito.pdp.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupQueueRabbitMQ {

    @Bean
    Queue queue() {
        return new Queue("benchmark-test-queue", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("benchmark");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("message-test");
    }


}
