package com.edcccd.blog.service;

import com.edcccd.blog.dto.DtHomeInfo;

/**
 * 配置服务，用于页面配置查询
 */
public interface ConfigService {

    /**
     * 首页配置信息
     */
    DtHomeInfo homeInfo();
}
