package com.edcccd.account.service.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.StrUtil;
import com.edcccd.account.api.feign.CaptchaFeign;
import com.edcccd.common.util.MyRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static com.edcccd.account.api.common.Constain.LOGIN_CAPTCHA;

/**
 * 验证码服务
 */
public class CaptchaController implements CaptchaFeign {

  @Autowired
  MyRedisUtil redisUtil;


  @Override
  public Boolean getCaptcha(String key) {
    if (!StrUtil.isBlank(key)) {
      throw new RuntimeException("输入key为空,无法生成验证码");
    }

    //定义图形验证码的长和宽
    LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
    //图形验证码写出，可以写出到文件，也可以写出到流
    lineCaptcha.write("d:/line.png");

    String captcha = lineCaptcha.getCode();
    redisUtil.addCache(LOGIN_CAPTCHA + key, captcha, 5, TimeUnit.MINUTES);

    return true;
  }


  @Override
  public Boolean verifyCaptcha(String code, String key) {
    return null;
  }
}
