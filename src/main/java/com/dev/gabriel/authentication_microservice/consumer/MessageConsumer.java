package com.dev.gabriel.authentication_microservice.consumer;

import com.dev.gabriel.authentication_microservice.consumer.dto.NewUserRegisteredMessage;
import com.dev.gabriel.authentication_microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageConsumer {
  private final UserService userService;

  @RabbitListener(queues = "${rabbitmq.queue.new-registered-user}")
  public void registerNewUser(NewUserRegisteredMessage message) {
    this.userService.registerUser(message);
  }
}
