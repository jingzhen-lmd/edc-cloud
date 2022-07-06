package com.edcccd.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * redis对象
 * 用于逻辑过期
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisData<T> implements Serializable {
    private LocalDateTime date;
    private T object;
}
