package com.edcccd.account.service.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.StrUtil;
import com.edcccd.account.api.feign.CaptchaFeign;
import com.edcccd.account.service.util.MyRedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.edcccd.account.api.common.Constain.LOGIN_CAPTCHA;

/**
 * 验证码服务
 */
@RestController
@RequestMapping("captcha")
public class CaptchaController implements CaptchaFeign {

    @Resource
    MyRedisUtil redisUtil;

    @GetMapping
    @Override
    public Boolean getCaptcha(@RequestParam("path") String path, @RequestParam("key") String key) {
        if (StrUtil.isBlank(key)) {
            throw new RuntimeException("输入key为空,无法生成验证码");
        }
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 80);

        //图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write(new File(path));

        String captcha = lineCaptcha.getCode();
        redisUtil.addCache(LOGIN_CAPTCHA + key, captcha, 5, TimeUnit.MINUTES);
        return true;
    }

    @GetMapping("verify")
    @Override
    public Boolean verifyCaptcha(@RequestParam("code") String code, @RequestParam("key") String key) {
        String cache = redisUtil.getCache(LOGIN_CAPTCHA + key);
        return StrUtil.isNotBlank(cache) && cache.equals(code);
    }
}
