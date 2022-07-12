package com.edcccd.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQ {

//  @RabbitListener(queues = "simple.queue")
//  public void listenSimpleQueueMessage(String msg) throws InterruptedException {
//    System.out.println("消费者接收到消息：【" + msg + "】 " + LocalTime.now());
//    System.out.println(1 / 0);
//    System.out.println("消息处理成功");
//  }

//  惰性队列
//  @Bean
//  public Queue lazyQueue() {
//    Queue queue =  QueueBuilder.durable("lazy.queue").lazy().build();
//    return queue;
//  }

  @RabbitListener(queues = "tianQi")
  public void dieMessageConsumer4(String msg) {
    System.out.println("消费者接收到消息：【" + msg + "】 " + LocalTime.now());
    System.out.println(1 / 0);
    System.out.println("消息处理成功");
  }

  @Bean
  public DirectExchange fanoutExchange() {
    return new DirectExchange("itcast.fanout");
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
        .to(fanoutExchange())
        .with("wahaha");
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
        .to(fanoutExchange())
        .with("wahaha");
  }

  /**
   * 异常交换机及队列
   */
  @Bean
  public FanoutExchange errorExchange() {
    return new FanoutExchange("error.message");
  }

  @Bean
  public Queue errorQueue() {
    Queue queue = new Queue("error.queue");
    return queue;
  }

  @Bean
  public Binding errorBanding() {
    return BindingBuilder
        .bind(errorQueue())
        .to(errorExchange());
  }

  /**
   * 错误信息交换机配置
   */
  @Bean
  public RepublishMessageRecoverer errorMessage(RabbitTemplate template) {
    String errorExchange = "error.message";
// 直接注入RabbitTemplate
    return new RepublishMessageRecoverer(template, errorExchange, null);
  }

  /**
   * 死信交换机，队列，消费者
   */
  @RabbitListener(bindings = @QueueBinding(
      exchange = @Exchange("die.direct"),
      value = @org.springframework.amqp.rabbit.annotation.Queue("die.queue")
  ))
  public void dieMessageConsumer(String msg) {
    System.out.println("死信消费者接收到消息：【" + msg + "】 " + LocalTime.now());
  }

  @RabbitListener(bindings = @QueueBinding(
      exchange = @Exchange("die.direct"),
      value = @org.springframework.amqp.rabbit.annotation.Queue("die.queue")
  ))
  public void dieMessageConsumer2(String msg) {
    System.out.println("死信消费者接收到消息2：【" + msg + "】 " + LocalTime.now());
  }

  @RabbitListener(bindings = @QueueBinding(
      exchange = @Exchange("die.direct"),
      value = @org.springframework.amqp.rabbit.annotation.Queue("die.queue")
  ))
  public void dieMessageConsumer3(String msg) {
    System.out.println("死信消费者接收到消息3：【" + msg + "】 " + LocalTime.now());
  }


  /**
   * ttl交换机，队列，绑定
   */
  @Bean
  public FanoutExchange ttlExchange() {
    return new FanoutExchange("ttl.message");
  }

  @Bean
  public Queue ttlQueue() {
    Map<String, Object> arguments = new HashMap<>();
    arguments.put("x-message-ttl", 10000);
    arguments.put("x-dead-letter-exchange", "die.direct");

    Queue queue = new Queue("ttl.queue", true,
        false, false, arguments);

//    也可以使用建造者建造，都是一样的
//    Queue queue = QueueBuilder.durable("ttl.queue")
//        .ttl(10000)
//        .deadLetterExchange("die.direct")
//        .deadLetterRoutingKey("wahaha")
//        .build();
    return queue;
  }

  @Bean
  public Binding ttlBanding() {
    return BindingBuilder
        .bind(ttlQueue())
        .to(ttlExchange());
  }
}
