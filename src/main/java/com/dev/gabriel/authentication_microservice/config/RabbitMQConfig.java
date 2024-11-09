package com.dev.gabriel.authentication_microservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  @Value("${rabbitmq.queue.new-registered-user}")
  private String newUserRegisteredQueue;

  @Bean
  public Queue newUserRegistered() {
    return new Queue(newUserRegisteredQueue, true);
  }

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
