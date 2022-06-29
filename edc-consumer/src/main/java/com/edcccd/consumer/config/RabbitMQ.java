package com.edcccd.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {

  @RabbitListener(queues = "simple.queue")
  public void listenSimpleQueueMessage(String msg) throws InterruptedException {
    System.out.println("消费者接收到消息：【" + msg + "】");
  }

  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange("itcast.fanout");
  }

  @Bean
  public Queue weatherQueue() {
    Queue queue = new Queue("tianQi");
    return queue;
  }

  @Bean
  public Binding banding1() {
    return BindingBuilder
        .bind(weatherQueue())
        .to(fanoutExchange());
  }


  @Bean
  public Queue areaQueue() {
    Queue queue = new Queue("area");
    return queue;
  }

  @Bean
  public Binding banding2() {
    return BindingBuilder
        .bind(areaQueue())
        .to(fanoutExchange());
  }

}
