package com.edcccd.account.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String userName;
    private String password;
    private String phone;

    // 权限
    private String power;
    // 验证码
    @TableField(exist = false)
    private String checkCode;

}
