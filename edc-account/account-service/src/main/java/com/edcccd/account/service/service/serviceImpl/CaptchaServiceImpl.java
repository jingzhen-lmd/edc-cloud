package com.edcccd.account.service.service.serviceImpl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.StrUtil;
import com.edcccd.account.api.feign.CaptchaFeign;
import com.edcccd.account.service.util.MyRedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.edcccd.account.api.common.Constain.CAPTCHA;

@Service
public class CaptchaServiceImpl implements CaptchaFeign {
    @Resource
    MyRedisUtil redisUtil;

    @Override
    public Boolean getCaptcha(String path, String key) {
        if (StrUtil.isBlank(key)) {
            throw new RuntimeException("输入key为空,无法生成验证码");
        }
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 80);

        //图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write(new File(path));

        String captcha = lineCaptcha.getCode();
        redisUtil.addCache(CAPTCHA + key, captcha, 5, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public Boolean verifyCaptcha(String code, String key) {
        String cache = redisUtil.getCache(CAPTCHA + key);
        return StrUtil.isNotBlank(cache) && cache.equals(code);
    }
}
