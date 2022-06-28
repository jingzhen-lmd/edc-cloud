package com.edcccd.basic.api.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseTable implements Serializable {
    private Long id;
}
