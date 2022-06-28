package com.edcccd.system.mapper.visit;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.system.pojo.generate.ActiveMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActiveMenuMapper extends BaseMapper<ActiveMenu> {

    @Select("select * from visit_menu_item v " +
            "left join table_type tt on v.tableType = tt.tableType " +
            "where v.visitMenuUuid = #{menuUuid} " +
            "order by v.sequence")
    List<ActiveMenu> queryActive(String menuUuid);
}
