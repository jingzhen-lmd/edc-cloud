package com.edcccd.account.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "menu_power")
public class MenuPower {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 菜单id
     */
    @TableField(value = "menu_id")
    private Long menuId;

    /**
     * 权限key
     */
    @TableField(value = "power_key")
    private String powerKey;

    /**
     * 权限名
     */
    @TableField(value = "power_name")
    private String powerName;
}