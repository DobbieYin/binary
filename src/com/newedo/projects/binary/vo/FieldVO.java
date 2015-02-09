package com.newedo.projects.binary.vo;

import java.util.Date;

/**
 * 字段VO
 */
public class FieldVO {
    private String id;//唯一标识
    private String code;//字段编码
    private String name;//字段名称
    private String fieldtype;//字段类型
    private Boolean isprimarykey;//是否主属性
    private Boolean isnotnull;//是否必输
    private String defaultval;//缺省值
    private Integer length;//长度
    private String minval;//最小值
    private String maxval;//最大值
    private Boolean isactive;//是否启用
    private Date createdtime;//创建时间
    private String creator;//创建人
    private Date modifitime;//修改时间
    private String modifier;//修改人
    private String ts;//时间戳

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype;
    }

    public Boolean getIsprimarykey() {
        return isprimarykey;
    }

    public void setIsprimarykey(Boolean isprimarykey) {
        this.isprimarykey = isprimarykey;
    }

    public Boolean getIsnotnull() {
        return isnotnull;
    }

    public void setIsnotnull(Boolean isnotnull) {
        this.isnotnull = isnotnull;
    }

    public String getDefaultval() {
        return defaultval;
    }

    public void setDefaultval(String defaultval) {
        this.defaultval = defaultval;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getMinval() {
        return minval;
    }

    public void setMinval(String minval) {
        this.minval = minval;
    }

    public String getMaxval() {
        return maxval;
    }

    public void setMaxval(String maxval) {
        this.maxval = maxval;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getModifitime() {
        return modifitime;
    }

    public void setModifitime(Date modifitime) {
        this.modifitime = modifitime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
