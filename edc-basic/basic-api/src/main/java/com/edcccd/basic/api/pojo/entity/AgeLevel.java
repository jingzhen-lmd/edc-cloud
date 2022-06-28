package com.edcccd.basic.api.pojo.entity;

public enum AgeLevel {
    zeroTo60("0-60"),
    sixtyTo75("60-75"),
    moreThan75(">75");

    private String caption;

    AgeLevel(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
