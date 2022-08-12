package com.edcccd.system.controller;

import com.edcccd.common.util.Result;
import com.edcccd.system.entity.Menu;
import com.edcccd.system.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (menu)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    /**
     * 查询指定项目的目录
     *
     * @param projectId 项目
     * @param partId    模块
     * @return 单条数据
     */
    @GetMapping("query")
    public Result<List<Menu>> query(@RequestParam("projectId") String projectId,
                                            @RequestParam("projectId") String partId) {

        return menuService.query(projectId, partId);
    }


}
