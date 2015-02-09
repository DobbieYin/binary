package com.newedo.projects.binary.dm;

import javafx.beans.property.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 字段DM
 */
public class FieldDM {
    public static String ID="id";//唯一标识
    public static String CODE="code";//字段编码
    public static String NAME="name";//字段名称
    public static String FIELDTYPE="fieldtype";//字段类型
    public static String ISPRIMARYKEY="isprimarykey";//是否主属性
    public static String ISNOTNULL="isnotnull";//是否必输
    public static String DEFAULTVAL="defaultval";//缺省值
    public static String LENGTH="length";//长度
    public static String MINVAL="minval";//最小值
    public static String MAXVAL="maxval";//最大值
    public static String ISACTIVE="isactive";//是否启用
    public static String CREATEDTIME="createdtime";//创建时间
    public static String CREATOR="creator";//创建人
    public static String MODIFITIME="modifitime";//修改时间
    public static String MODIFIER="modifier";//修改人
    public static String TS="ts";//时间戳

    private SimpleStringProperty id = new SimpleStringProperty();//唯一标识
    private SimpleStringProperty code = new SimpleStringProperty();//字段编码
    private SimpleStringProperty name = new SimpleStringProperty();//字段名称
    private SimpleStringProperty fieldtype = new SimpleStringProperty();//字段类型
    private SimpleBooleanProperty isprimarykey = new SimpleBooleanProperty();//是否主属性
    private SimpleBooleanProperty isnotnull = new SimpleBooleanProperty();//是否必输
    private SimpleStringProperty defaultval = new SimpleStringProperty();//缺省值
    private SimpleIntegerProperty length = new SimpleIntegerProperty();//长度
    private SimpleDoubleProperty minval = new SimpleDoubleProperty();//最小值
    private SimpleDoubleProperty maxval = new SimpleDoubleProperty();//最大值
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

    public String getFieldtype() {
        return fieldtype.get();
    }

    public SimpleStringProperty fieldtypeProperty() {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype) {
        this.fieldtype.set(fieldtype);
    }

    public boolean getIsprimarykey() {
        return isprimarykey.get();
    }

    public SimpleBooleanProperty isprimarykeyProperty() {
        return isprimarykey;
    }

    public void setIsprimarykey(boolean isprimarykey) {
        this.isprimarykey.set(isprimarykey);
    }

    public boolean getIsnotnull() {
        return isnotnull.get();
    }

    public SimpleBooleanProperty isnotnullProperty() {
        return isnotnull;
    }

    public void setIsnotnull(boolean isnotnull) {
        this.isnotnull.set(isnotnull);
    }

    public String getDefaultval() {
        return defaultval.get();
    }

    public SimpleStringProperty defaultvalProperty() {
        return defaultval;
    }

    public void setDefaultval(String defaultval) {
        this.defaultval.set(defaultval);
    }

    public int getLength() {
        return length.get();
    }

    public SimpleIntegerProperty lengthProperty() {
        return length;
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public double getMinval() {
        return minval.get();
    }

    public SimpleDoubleProperty minvalProperty() {
        return minval;
    }

    public void setMinval(double minval) {
        this.minval.set(minval);
    }

    public double getMaxval() {
        return maxval.get();
    }

    public SimpleDoubleProperty maxvalProperty() {
        return maxval;
    }

    public void setMaxval(double maxval) {
        this.maxval.set(maxval);
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

    public FieldDM(String code,String name) {
        this();
        setCode(code);
        setName(name);
    }


    public FieldDM(){
        setId(UUID.randomUUID().toString());
        setCreatedtime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        setModifitime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        setTs(String.valueOf(System.currentTimeMillis()));
    }
}
