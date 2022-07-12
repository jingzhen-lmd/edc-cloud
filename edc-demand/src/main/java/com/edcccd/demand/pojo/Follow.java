package com.edcccd.demand.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 关注关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    private long id;
    private long userId;
    private long followId;
    private LocalDateTime createTime;

    public Follow(long userId, long followId) {
        this.userId = userId;
        this.followId = followId;
        this.createTime = LocalDateTime.now();
    }
}
