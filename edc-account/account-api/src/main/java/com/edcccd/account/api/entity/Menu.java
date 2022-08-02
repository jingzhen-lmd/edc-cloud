package com.edcccd.account.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "menu")
public class Menu {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 菜单用于哪个项目
     */
    @TableField(value = "project_id")
    private String projectId;

    /**
     * 菜单用于哪个部分
     */
    @TableField(value = "part_id")
    private String partId;

    /**
     * 菜单名字
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 路由地址
     */
    @TableField(value = "`path`")
    private String path;

    /**
     * 组件地址
     */
    @TableField(value = "component")
    private String component;

    /**
     * 1=显示(默认)
     */
    @TableField(value = "visible")
    private String visible;

    @TableField(value = "icon")
    private String icon;

    /**
     * 可设置的权限
     */
    @TableField(value = "power_key")
    private String powerKey;

    /**
     * 菜单级别
     */
    @TableField(value = "`level`")
    private Integer level;

    /**
     * 菜单顺序
     */
    @TableField(value = "`rank`")
    private Integer rank;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 1=删除
     */
    @TableField(value = "deleted")
    private String deleted;
}