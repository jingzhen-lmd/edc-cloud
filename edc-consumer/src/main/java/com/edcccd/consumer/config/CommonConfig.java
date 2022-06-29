package com.edcccd.consumer.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，配置返回消息对象
 */
@Configuration
public class CommonConfig implements ApplicationContextAware {
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//    获取template对象
    RabbitTemplate template = applicationContext.getBean(RabbitTemplate.class);
//    配置ReturnCallback
    template.setReturnsCallback(returnedMessage -> {
      Message message = returnedMessage.getMessage();// 消息
      int replyCode = returnedMessage.getReplyCode(); //响应码
      String replyText = returnedMessage.getReplyText();//失败原因
      String exchange = returnedMessage.getExchange();// 交换机
      String routingKey = returnedMessage.getRoutingKey();//路由key

      System.out.println(returnedMessage);
      // todo 这里可以执行重发业务
    });

  }
}
