package com.edcccd.blog.controller;

import com.edcccd.blog.dto.DtHomeInfo;
import com.edcccd.blog.service.ConfigService;
import com.edcccd.common.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询配置
 * 用于查看网站的配置信息
 */
@Api(tags = "查询配置")
@RestController
@RequestMapping("config")
public class ConfigController {

    @Autowired
    ConfigService service;

    /**
     * 首页配置信息
     */
    @GetMapping("homeInfo")
    public Result<DtHomeInfo> homeInfo() {
        DtHomeInfo info = service.homeInfo();
        return Result.success(info);
    }


}
