package com.edcccd.blog.config;

import lombok.Getter;

@Getter
public enum Model {
    TALK("说说"),
    ARTICLE("文章"),
    COMMENT("评论"),
    PICTURE("照片");

    private final String caption;

    Model(String caption) {
        this.caption = caption;
    }
}
