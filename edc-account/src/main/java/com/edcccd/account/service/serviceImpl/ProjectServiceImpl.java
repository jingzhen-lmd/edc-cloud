package com.edcccd.account.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.account.mapper.ProjectMapper;
import com.edcccd.account.pojo.Project;
import com.edcccd.account.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    ProjectMapper mapper;

    @Override
    public List<Project> queryByUser(long userId) {
        return mapper.queryByUser(userId);
    }
}
