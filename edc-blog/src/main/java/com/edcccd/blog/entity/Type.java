package com.edcccd.blog.entity;

/**
 * 文本类，类型
 */
public enum Type {
    ARTICLE("文章"),
    TALK("说说"),
    COMMENT("评论"),
    IMAGE("图片"),
    MOVE("视屏");

    private final String caption;

    Type(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}
