package com.edcccd.common;

import com.edcccd.common.pojo.Product;
import com.edcccd.common.pojo.Student;
import com.edcccd.common.service.RedisLock;
import com.edcccd.common.util.IdGenerator;
import com.edcccd.common.util.RedisCacheUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class CommonTest {

    @Autowired
    StringRedisTemplate template;

    @Autowired
    IdGenerator wholeID;

    @Test
    public void test1() {
        template.opsForValue().set("chushi", "初始化", 40, TimeUnit.SECONDS);
        System.out.println(template.opsForValue().get("chushi"));
    }

    @Autowired
    RedisLock redisLock;

    @Test
    public void test2() throws InterruptedException {
        Product product = new Product();
        product.setNum(10);

        AtomicInteger sell = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(400);

        for (int i = 0; i < 400; i++) {
            Thread thread = new Thread(() -> {
                if (product.getNum() > 0) {
                    String key = "商品:" + product.getNum();

                    if (redisLock.getLock(key)) {
                        System.out.println("拿到锁了" + key);
                        int num = product.getNum() - 1;
//            try {
//              Thread.sleep(2);
//            } catch (InterruptedException e) {
//              e.printStackTrace();
//            }
                        sell.getAndIncrement();
                        product.setNum(num);

                        System.out.println("还回去了" + key);
                        redisLock.unlock(key);
                    }
                }
                latch.countDown();
            });
            thread.start();
        }

        latch.await();
//    Thread.sleep(2000);
        System.out.println("最后num：" + product.getNum());
        System.out.println("maile：" + sell);
    }

    @Autowired
    RedisCacheUtil redisCacheUtil;

    @Test
    public void test04() {
//    redisCache.addCache("wahahaha","asdsadas");

//        Student student = new Student().setId(545).setName("asd").setAge(64);
//        redisCache.addCacheLogic("asd", student, 30, TimeUnit.MINUTES);
//        redisCache.addCache("student", student);

        Student wahahaha = redisCacheUtil.getByCashThrow("student", Student.class);
//        Student asd = redisCache.getByCashLogic("asd", Student.class);


        System.out.println(wahahaha);


    }
}
