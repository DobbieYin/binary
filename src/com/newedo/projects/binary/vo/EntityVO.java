package com.newedo.projects.binary.vo;

import java.util.Date;
import java.util.List;

/**
 * 实体VO
 */
public class EntityVO {
    private String id;//唯一标识
    private String code;//实体编码
    private String name;//实体名称
    private String classname;//完全限定类名
    private String tablename;//表名
    private List<FieldVO> fields;//包含字段集合
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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public List<FieldVO> getFields() {
        return fields;
    }

    public void setFields(List<FieldVO> fields) {
        this.fields = fields;
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
