package com.edcccd.account.service.serviceImpl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.account.common.Result;
import com.edcccd.account.mapper.UserMapper;
import com.edcccd.account.pojo.User;
import com.edcccd.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static com.edcccd.account.common.Constain.LOGIN_TOKEN;
import static com.edcccd.account.common.Constain.LOGIN_USER;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Result<String> login(HttpServletRequest request, User loginUser) {
        if (loginUser == null || StrUtil.isBlank(loginUser.getUserName()))
            return Result.fail("402", "输入为空！");

        User user = getUser(loginUser.getUserName(), loginUser.getPassword());

        if (user == null)
            return Result.fail("402", "用户名或密码错误！");
        if (user.getPower().equals("2"))
            return Result.fail("403", "用户已被禁用！");

//        登录成功
        user.setPassword("");
        String token = saveToken(user);
        return Result.success(token);
    }

    private User getUser(String userName, String passWord) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("password", passWord);
        return getOne(queryWrapper);
    }

    @Override
    public Result<String> getCheckCode(String phone) {
        if (StrUtil.isBlank(phone))
            return Result.fail("400", "电话为空！");

        String code = String.valueOf(RandomUtil.randomInt(100000, 999999));
        redisTemplate.opsForValue().set(LOGIN_USER + phone, code, 10, TimeUnit.MINUTES);

        return Result.success(code);
    }

    @Override
    public Result<String> phoneLogin(User inputUser) {
        if (StrUtil.isBlank(inputUser.getPhone()))
            return Result.fail("402", "手机号不能为空！");
        if (StrUtil.isBlank(inputUser.getCheckCode()))
            return Result.fail("402", "验证码不能为空！");

        String code = redisTemplate.opsForValue().get(LOGIN_USER + inputUser.getPhone());
        if (!inputUser.getCheckCode().equals(code))
            return Result.fail("403", "验证码错误");

        User user = queryUser(inputUser);

        if (user == null) {
            user = createUse(inputUser);
            save(user);
        }

        user.setPassword("");
        String token = saveToken(user);
        return Result.success(token);
    }

    private User queryUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", user.getPhone());
        return getOne(queryWrapper);
    }

    private User createUse(User user) {
        user.setPower("2");
        user.setPassword(user.getPhone().substring(5));
        String userName = "用户" + RandomUtil.randomString(6);
        user.setUserName(userName);
        return user;
    }

    private String saveToken(User user) {
        String token = RandomUtil.randomString(16);
        String user2Json = JSONUtil.toJsonStr(user);
        redisTemplate.opsForValue().set(LOGIN_TOKEN + token, user2Json, 70, TimeUnit.MINUTES);
        return token;
    }

}
