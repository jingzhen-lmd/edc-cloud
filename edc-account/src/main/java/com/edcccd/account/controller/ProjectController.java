package com.edcccd.account.controller;

import com.edcccd.account.common.Result;
import com.edcccd.account.interceptor.UserHolder;
import com.edcccd.account.pojo.Project;
import com.edcccd.account.pojo.User;
import com.edcccd.account.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目导航
 */
@RestController
public class ProjectController {

    @Autowired
    ProjectService service;

    @GetMapping("projects")
    public Result<List<Project>> queryProject() {
        User user = UserHolder.getUser();
        List<Project> projects = service.queryByUser(user.getId());

        return Result.success(projects);
    }
}
