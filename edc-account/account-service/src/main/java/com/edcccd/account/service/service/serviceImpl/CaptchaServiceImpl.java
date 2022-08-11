package com.edcccd.account.service.service.serviceImpl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.edcccd.account.api.feign.CaptchaFeign;
import com.edcccd.common.util.RedisUtil;
import com.edcccd.common.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.edcccd.account.api.common.Constain.CAPTCHA;

@Service
public class CaptchaServiceImpl implements CaptchaFeign {
    @Resource
    RedisUtil redisUtil;

    @Override
    public Result<String> getCaptcha(String key) {
        if (StrUtil.isBlank(key)) {
            throw new RuntimeException("输入key为空,无法生成验证码");
        }
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40);

        String captcha = lineCaptcha.getCode();
        redisUtil.pushCache(CAPTCHA + key, captcha, 5, TimeUnit.MINUTES);
        return Result.success(lineCaptcha.getImageBase64Data());
    }

    @Override
    public Result<Boolean> verifyCaptcha(String code, String key) {
        String cache = redisUtil.getCache(CAPTCHA + key);
        Boolean isMach = StrUtil.isNotBlank(cache) && cache.equals(code);
        return Result.success(isMach);
    }

    @Override
    public Result<String> getCaptchaByPhone(String phone) {
        if (!Validator.isMobile(phone)) {
            return Result.fail(400, "请输入正确的手机号");
        }
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40);
        String captcha = lineCaptcha.getCode();
        redisUtil.pushCache(CAPTCHA + phone, captcha, 5, TimeUnit.MINUTES);

        // todo 以后改为短信发送
        return Result.success(captcha);
    }


}
