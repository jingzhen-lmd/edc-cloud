package com.edcccd.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edcccd.account.pojo.Project;

import java.util.List;

public interface ProjectService extends IService<Project> {

    List<Project> queryByUser(long userId);
}
