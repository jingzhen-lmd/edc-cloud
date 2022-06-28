package com.edcccd.basic.service.config;

import com.edcccd.basic.api.pojo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class StudentConfig {

  @Bean
  public Student student1() {
    return new Student(1, "xiaoming");
  }

}
