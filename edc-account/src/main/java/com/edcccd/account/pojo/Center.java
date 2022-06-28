package com.edcccd.account.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 中心信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Center extends Entity {
    private String name;
    private String code;
    private String address;
    private String phone;
}
