package com.newedo.projects.binary.dm;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 用户数据模型类
 */
public class UserDM {

    public static String ID="id";//唯一标识
    public static String CODE="code";//用户编码
    public static String NAME="name";//用户名称
    public static String ISACTIVE="isactive";//是否启用
    public static String CREATEDTIME="createdtime";//创建时间
    public static String CREATOR="creator";//创建人
    public static String MODIFITIME="modifitime";//修改时间
    public static String MODIFIER="modifier";//修改人
    public static String TS="ts";//时间戳

    private SimpleStringProperty id = new SimpleStringProperty();//唯一标识
    private SimpleStringProperty code = new SimpleStringProperty();//用户编码
    private SimpleStringProperty name = new SimpleStringProperty();//用户名称
    private SimpleBooleanProperty isactive = new SimpleBooleanProperty();//是否启用
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

    public boolean getIsactive() {
        return isactive.get();
    }

    public SimpleBooleanProperty isactiveProperty() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive.set(isactive);
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

    public UserDM(String code,String name) {
        this();
        setCode(code);
        setName(name);
    }

    public UserDM(){
        setId(UUID.randomUUID().toString());
        setCreatedtime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        setModifitime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        setTs(String.valueOf(System.currentTimeMillis()));
    }
}
