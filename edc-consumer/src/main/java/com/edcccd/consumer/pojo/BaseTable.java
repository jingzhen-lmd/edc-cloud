package com.edcccd.consumer.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseTable implements Serializable {
    private Long id;
}
