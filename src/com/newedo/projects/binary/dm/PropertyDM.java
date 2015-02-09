package com.newedo.projects.binary.dm;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

/**
 * 属性DM
 */
public class PropertyDM implements Comparable{
    public static String KEY="key";//属性键
    public static String VALUE="value";//属性值
    public static String ISREADONLY="isreadonly";//是否只读
    public static String VISIBLE="visible";//是否显示
    public static String DISPLAYKEY="displaykey";//显示key
    public static String valuecontrol="valueControl";//属性值控件

    private SimpleStringProperty key = new SimpleStringProperty();//属性键
    private SimpleObjectProperty value = new SimpleObjectProperty();//属性值
    private SimpleBooleanProperty isreadonly = new SimpleBooleanProperty();//是否只读
    private SimpleBooleanProperty visible = new SimpleBooleanProperty();//是否显示
    private SimpleStringProperty displaykey = new SimpleStringProperty();//显示key
    private Node valueNode = new TextField();//属性值控件：默认输入框

    public String getKey() {
        return key.get();
    }

    public SimpleStringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public Object getValue() {
        return value.get();
    }

    public SimpleObjectProperty valueProperty() {
        return value;
    }

    public void setValue(Object value) {
        this.value.set(value);
    }

    public boolean getIsreadonly() {
        return isreadonly.get();
    }

    public SimpleBooleanProperty isreadonlyProperty() {
        return isreadonly;
    }

    public void setIsreadonly(boolean isreadonly) {
        this.isreadonly.set(isreadonly);
    }

    public boolean getVisible() {
        return visible.get();
    }

    public SimpleBooleanProperty visibleProperty() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }

    public Node getValueNode() {
        return valueNode;
    }

    public void setValueNode(Node valueControl) {
        this.valueNode = valueControl;
    }

    public String getDisplaykey() {
        return displaykey.get();
    }

    public SimpleStringProperty displaykeyProperty() {
        return displaykey;
    }

    public void setDisplaykey(String displaykey) {
        this.displaykey.set(displaykey);
    }

    /**
     * 如果key相等就表示两个对象相等
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyDM that = (PropertyDM) o;
        if (key != null && that.key != null ? !key.get().equals(that.key.get()) : that != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if(this == o) return 0;
        if (o != null && o instanceof PropertyDM){
            PropertyDM that = (PropertyDM)o;
            return this.getKey().compareTo(that.getKey());
        }
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
                "key=" + key.get() +
                ", value=" + value.get() +
                '}';
    }

    public PropertyDM(String key,String displaykey, Object value, Boolean isreadonly, Boolean visible) {
        setKey(key);
        setDisplaykey(displaykey);
        setValue(value);
        setIsreadonly(isreadonly);
        setVisible(visible);
    }

    public PropertyDM(String key, Object value, Boolean isreadonly, Boolean visible) {
        this(key,key,value,isreadonly,visible);
    }

    public PropertyDM(String key,String displaykey, Object value) {
        this(key,displaykey,value,false,true);
    }
    public PropertyDM(String key, String value) {
        this(key,value,false,true);
    }
}