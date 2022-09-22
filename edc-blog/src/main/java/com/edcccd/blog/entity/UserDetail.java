package com.edcccd.blog.entity;

import com.edcccd.account.api.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDetail {
    /**
     * 用户信息
     */
    private User user;
    /**
     * 权限信息
     */
    private List<String> powers;
}
