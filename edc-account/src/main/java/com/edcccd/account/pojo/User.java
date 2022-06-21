package com.edcccd.account.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private String id;
  private String userName;
  private String password;
  private String power;
  private String phone;
}
