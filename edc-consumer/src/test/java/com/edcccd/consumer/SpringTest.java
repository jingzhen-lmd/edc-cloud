package com.edcccd.consumer;

import cn.hutool.core.lang.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class SpringTest {

  @Autowired
  private RabbitTemplate rabbitTemplate;


  @Test
  public void test01() {
  }

  @Test
  public void testSimpleQueue() {
    // 队列名称
    String queueName = "simple.queue";
    // 消息
    String message = "你好啊，rabbit！";
    // 发送消息
    rabbitTemplate.convertAndSend(queueName, message);
  }

  @Test
  public void ttlSimpleQueue() {
    // 消息
    String message = "你好啊，lazyqueue！" + LocalDateTime.now();
    // 发送消息
    for (int i = 0; i < 10000; i++) {
      rabbitTemplate.convertAndSend("lazy.queue", message);
    }
  }

  @Test
  public void doubleQueue() {
    String exchange = "itcast.fanout";
    String message = "上海的天气是晴天！";
    // CorrelationData，放入唯一id
    CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
    // 放入回调方法1
    correlationData.getFuture().addCallback(success -> {
      System.out.println("消息发送成功到交换机");
//      这里成功不一定是全部成功，需校验交换机是否成功发送到队列
      if (success.isAck()) {
        System.out.println("交换机成功到队列");
      } else {
        System.out.println("交换机失败到队列");
      }
    }, fail -> {
      // 失败了业务处理
      System.out.println("消息发送失败" + fail);
    });

    // 发送消息，最后参数加上回调方法
    rabbitTemplate.convertAndSend(exchange, "12wahaha", message, correlationData);
  }
}
