package com.newedo.projects.binary.dm;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 元数据DM
 */
public class MetadataDM{
    public static String ID="id";//唯一标识
    public static String CODE="code";//元数据编码
    public static String NAME="name";//元数据名称
    public static String FATHER="father";//所属父级对象
    public static String CHILDREN="children";//包含子级集合
    public static String ENTITIES="entities";//包含实体集合
    public static String CREATEDTIME="createdtime";//创建时间
    public static String CREATOR="creator";//创建人
    public static String MODIFITIME="modifitime";//修改时间
    public static String MODIFIER="modifier";//修改人
    public static String TS="ts";//时间戳

    private SimpleStringProperty id = new SimpleStringProperty();//唯一标识
    private SimpleStringProperty code = new SimpleStringProperty();//元数据编码
    private SimpleStringProperty name = new SimpleStringProperty();//元数据名称
    private SimpleObjectProperty<MetadataDM> father = new SimpleObjectProperty<>();//所属父级对象
    private SimpleListProperty<MetadataDM> children = new SimpleListProperty<>(FXCollections.observableArrayList());//包含子级集合
    private SimpleListProperty<EntityDM> entities = new SimpleListProperty<>(FXCollections.observableArrayList());//包含实体集合
    private SimpleStringProperty createdtime = new SimpleStringProperty();//创建时间
    private SimpleStringProperty creator = new SimpleStringProperty();//创建人
    private SimpleStringProperty modifitime = new SimpleStringProperty();//修改时间
    private SimpleStringProperty modifier = new SimpleStringProperty();//修改人
    private SimpleStringProperty ts = new SimpleStringProperty();//时间戳

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public MetadataDM getFather() {
        return father.get();
    }

    public SimpleObjectProperty<MetadataDM> fatherProperty() {
        return father;
    }

    public void setFather(MetadataDM father) {
        this.father.set(father);
    }

    public ObservableList<MetadataDM> getChildren() {
        return children.get();
    }

    public SimpleListProperty<MetadataDM> childrenProperty() {
        return children;
    }

    public void setChildren(ObservableList<MetadataDM> children) {
        this.children.set(children);
    }

    public ObservableList<EntityDM> getEntities() {
        return entities.get();
    }

    public SimpleListProperty<EntityDM> entitiesProperty() {
        return entities;
    }

    public void setEntities(ObservableList<EntityDM> entities) {
        this.entities.set(entities);
    }

    public String getCreatedtime() {
        return createdtime.get();
    }

    public SimpleStringProperty createdtimeProperty() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime.set(createdtime);
    }

    public String getCreator() {
        return creator.get();
    }

    public SimpleStringProperty creatorProperty() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator.set(creator);
    }

    public String getModifitime() {
        return modifitime.get();
    }

    public SimpleStringProperty modifitimeProperty() {
        return modifitime;
    }

    public void setModifitime(String modifitime) {
        this.modifitime.set(modifitime);
    }

    public String getModifier() {
        return modifier.get();
    }

    public SimpleStringProperty modifierProperty() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier.set(modifier);
    }

    public String getTs() {
        return ts.get();
    }

    public SimpleStringProperty tsProperty() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts.set(ts);
    }

    public MetadataDM(String code,String name) {
        this();
        setCode(code);
        setName(name);
    }

    public MetadataDM(String name) {
        this(null,name);
    }

    public MetadataDM() {
        setId(UUID.randomUUID().toString());
        setCreatedtime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        setModifitime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        setTs(String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public String toString() {
        return code != null && name != null ? code.get()+" "+name.get() : "";
    }

}
