package com.edcccd.account.service.service.serviceImpl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.edcccd.account.api.entity.User;
import com.edcccd.account.service.entity.UserDetail;
import com.edcccd.account.service.service.LoginService;
import com.edcccd.account.service.util.MyRedisUtil;
import com.edcccd.account.service.util.MyTokenUtil;
import com.edcccd.common.util.Result;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.edcccd.account.api.common.Constain.LOGIN_USER;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    AuthenticationManager manager;
    @Resource
    MyRedisUtil redisUtil;
    @Resource
    MyTokenUtil tokenUtil;
    @Resource
    CaptchaServiceImpl captchaService;

    @Override
    public Result<String> login(User user) {
        // 1、调用模块的认证授权方法，在security模块中登录，
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user
                .getUserName(), user.getPassword());

        // 2、进行认证，登陆错误由过滤器处理
        Authentication authenticate = manager.authenticate(authenticationToken);

        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();
        // 3、登录成功，使用redis记录用户数据
        redisUtil.addCache(LOGIN_USER + userDetail.getUser().getId(), JSONUtil.toJsonStr(userDetail));

        // 3、将用户id打包为token，返回
        String token = tokenUtil.generateToken(userDetail.getUser().getId());

        return Result.success(token);
    }

    @Override
    public Result<Boolean> loginCaptcha(String captcha, User user) {
        if (StrUtil.isBlank(captcha)) {
            return Result.fail(400, "验证码为空");
        }
        if (user == null || StrUtil.isBlank(user.getPhone())) {
            return Result.fail(400, "手机号为空");
        }
        if (!Validator.isMobile(user.getPhone())) {
            return Result.fail(400, "请输入正确的手机号");
        }

        Boolean isSuccess = captchaService.verifyCaptcha(captcha, user.getPhone());
        if (isSuccess) {
            return Result.success(isSuccess);
        }
        return Result.fail(400, "验证码错误！");
    }
}

