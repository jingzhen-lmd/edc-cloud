package com.edcccd.system.mapper.visit;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.system.pojo.menu.VisitMenuItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VisitMenuItemMapper extends BaseMapper<VisitMenuItem> {


    @Delete("delete from visit_menu_item where visit_menu_id = #{menuId}")
    void deleteByVisitMenuId(@Param("menuId")long menuId);
}
