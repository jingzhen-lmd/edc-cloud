package com.edcccd.system.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseTable implements Serializable {
    private long id;
}
