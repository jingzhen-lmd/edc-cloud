package com.edcccd.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 角色表，用于管理权限
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`role`")
public class Role {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "`name`")
    private String name;

    /**
     * 角色权限字符串
     */
    @TableField(value = "role_key")
    private String roleKey;

    /**
     * 状态1禁用
     */
    @TableField(value = "`status`")
    private String status;

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

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}