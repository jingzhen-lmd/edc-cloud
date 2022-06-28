package com.edcccd.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edcccd.system.common.Result;
import com.edcccd.system.mapper.visit.TesterMapper;
import com.edcccd.system.mapper.visit.VisitMapper;
import com.edcccd.system.pojo.Tester;
import com.edcccd.system.pojo.menu.Visit;
import com.edcccd.system.pojo.menu.VisitMenu;
import com.edcccd.system.service.menu.VisitMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导航窗
 * 受试者一览表
 */
@RestController
public class OverViewController {

    @Autowired
    TesterMapper testerMapper;
    @Autowired
    VisitMapper visitMapper;
    @Autowired
    VisitMenuService menuService;

    /**
     * 构造受试者目录，总览信息
     *
     * @return 返回表信息
     */
    @GetMapping("overview2")
    public Result<List<Map<String, Object>>> constructionTable2(@RequestParam("projectId") String projectId,
                                                                @RequestParam("centerId") String centerId) {
        List<Map<String, Object>> table = new ArrayList<>();

//        1、访问所有的目录
        List<VisitMenu> visitMenus = menuService.getMenu(projectId, centerId);

//        2、查询所有受试者
        QueryWrapper<Tester> testerWrapper = new QueryWrapper<>();
        testerWrapper.eq("project_id", projectId);
        testerWrapper.eq("center_id", centerId);
        testerWrapper.orderByAsc("id");
        List<Tester> testers = testerMapper.selectList(testerWrapper);

//        3、查询该受试者在目录下的所有状态
        for (Tester t : testers) {
            QueryWrapper<Visit> query = new QueryWrapper<>();
            query.eq("tester_id", t.getId());
            //查询该受试者的所有状态，放入map中
            List<Visit> visits = visitMapper.selectList(query);
            Map<Long, Visit> stateMap = new HashMap<>();
            for (Visit state : visits) {
                stateMap.put(state.getVisitMenuId(), state);
            }

//          4、转换为行数据，取出map中对应的状态按列名顺序排列
            Map<String, Object> hang = new HashMap<>();
            hang.put("testerId", t.getId());
            for (VisitMenu menu : visitMenus) {

                hang.put(menu.getMenuName(), stateMap.get(menu.getId()) == null ?
                        stateMap.get(menu.getId()) : new Visit());
            }

            table.add(hang);
        }

        return Result.success(table);
    }
}
