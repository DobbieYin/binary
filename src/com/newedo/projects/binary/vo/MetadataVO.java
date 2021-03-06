package com.newedo.projects.binary.vo;

import java.util.Date;
import java.util.List;

/**
 * 元数据VO
 */
public class MetadataVO {
    private String id;//唯一标识
    private String code;//元数据编码
    private String name;//元数据名称
    private MetadataVO father;//所属父级对象
    private List<MetadataVO> children;//包含子级集合
    private List<EntityVO> entities;//包含实体集合
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

    public MetadataVO getFather() {
        return father;
    }

    public void setFather(MetadataVO father) {
        this.father = father;
    }

    public List<MetadataVO> getChildren() {
        return children;
    }

    public void setChildren(List<MetadataVO> children) {
        this.children = children;
    }

    public List<EntityVO> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityVO> entities) {
        this.entities = entities;
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
