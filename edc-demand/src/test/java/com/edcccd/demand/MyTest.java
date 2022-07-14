package com.edcccd.demand;


import com.edcccd.common.util.Const;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SpringBootTest
public class MyTest {


  @Test
  public void ceshi() {
    Date date = new Date(Long.MAX_VALUE);
    LocalDateTime time = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

    System.out.println(date);
    System.out.println(time);
  }
}
