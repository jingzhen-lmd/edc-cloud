package com.edcccd.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体：受试者
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tester extends BaseTable {
    private String projectId;
    private String centerId;

    private String name;
}
