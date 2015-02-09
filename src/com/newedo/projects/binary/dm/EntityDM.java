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
 * 实体DM
 */
public class EntityDM {
    public static String ID="id";//唯一标识
    public static String CODE="code";//实体编码
    public static String NAME="name";//实体名称
    public static String CLASSNAME="classname";//完全限定类名
    public static String TABLENAME="tablename";//表名
    public static String FIELDS="fields";//包含字段集合
    public static String CREATEDTIME="createdtime";//创建时间
    public static String CREATOR="creator";//创建人
    public static String MODIFITIME="modifitime";//修改时间
    public static String MODIFIER="modifier";//修改人
    public static String TS="ts";//时间戳

    private SimpleStringProperty id = new SimpleStringProperty();//唯一标识
    private SimpleStringProperty code = new SimpleStringProperty();//实体编码
    private SimpleStringProperty name = new SimpleStringProperty();//实体名称
    private SimpleStringProperty classname = new SimpleStringProperty();//完全限定类名
    private SimpleStringProperty tablename = new SimpleStringProperty();//表名
    private SimpleListProperty<FieldDM> fields = new SimpleListProperty<>(FXCollections.observableArrayList());//包含字段集合
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

    public String getClassname() {
        return classname.get();
    }

    public SimpleStringProperty classnameProperty() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname.set(classname);
    }

    public String getTablename() {
        return tablename.get();
    }

    public SimpleStringProperty tablenameProperty() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename.set(tablename);
    }

    public ObservableList<FieldDM> getFields() {
        return fields.get();
    }

    public SimpleListProperty<FieldDM> fieldsProperty() {
        return fields;
    }

    public void setFields(ObservableList<FieldDM> fields) {
        this.fields.set(fields);
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

    public EntityDM(String code,String name) {
        this();
        setCode(code);
        setName(name);
    }
    public EntityDM(){
        setId(UUID.randomUUID().toString());
        setCreatedtime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        setModifitime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        setTs(String.valueOf(System.currentTimeMillis()));
    }
}
