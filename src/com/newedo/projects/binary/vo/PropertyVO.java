package com.newedo.projects.binary.vo;

/**
 * 属性VO
 */
public class PropertyVO {
    private String key;//属性键
    private String value;//属性值
    private Boolean isreadonly;//是否只读
    private Boolean visible;//是否显示

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getIsreadonly() {
        return isreadonly;
    }

    public void setIsreadonly(Boolean isreadonly) {
        this.isreadonly = isreadonly;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
