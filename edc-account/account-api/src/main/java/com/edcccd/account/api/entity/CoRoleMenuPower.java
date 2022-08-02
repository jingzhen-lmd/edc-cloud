package com.edcccd.account.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 中间表，角色菜单表，用于查权限
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "co_role_menu_power")
public class CoRoleMenuPower {
    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;

    /**
     * 菜单权限id
     */
    @TableId(value = "menu_power_id", type = IdType.INPUT)
    private Long menuPowerId;
}