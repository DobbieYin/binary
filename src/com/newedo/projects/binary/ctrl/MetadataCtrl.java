package com.newedo.projects.binary.ctrl;

import com.newedo.projects.binary.dm.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 元数据界面控制类
 */
public class MetadataCtrl {
    //=======================================================初始化=======================================================
    private TreeItem<MetadataDM> currSelectedMDTreeItem;//当前选中元数据树节点
    private MetadataDM currSelectedMD;//当前选中元数据
    private EntityDM currSelectedEntity;//当前选中实体
    private FieldDM currSelectedField;//当前选中字段
    private List<PropertyDM> currPros;//当前界面属性集合
    private MetadataDM currRootMD;//当前元数据树根节点
    public UserDM currUser;//当前用户
    private Integer propertyPanelStatus = -1;//0表示元数据、1表示实体、2表示字段
    /**
     * 初始化方法
     */
    @FXML
    void initialize() {
        System.out.println("元数据建模初始化：initialize");
        try {
            initTblCols();//初始化列
            initTblRows();//初始化行
            refreshMDsAction(null);//刷新元数据树
            addCurrSelectionListener();//添加选中监听
            //test();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化列失败：" + e.getMessage());
        }

    }

    private void test() {
        this.currUser = new UserDM("yinfx","尹飞翔");
//        List<PropertyDM> pros = new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            PropertyDM pro = new PropertyDM("key_"+i,"属性"+i,"value_"+i);
//
//            if(i == 10){
//                pro.setIsreadonly(true);
//            }else if(i == 13){
//                pro.setVisible(false);
//            }
//            pros.add(pro);
//        }
//        setProsToUI(pros);//设置到界面
    }

    /**
     * 初始化字段表格列
     * @throws Exception
     */
    private void initTblCols() throws Exception {
        System.out.println("初始化表格列：initTblCols");
        //实体表
        this.initTableColumn(this.tblc_enid, EntityDM.ID, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_encode, EntityDM.CODE, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_enname, EntityDM.NAME, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_enclassname, EntityDM.CLASSNAME, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_entablename, EntityDM.TABLENAME, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_enfields, EntityDM.FIELDS, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_encreator, EntityDM.CREATOR, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_encreatedtime, EntityDM.CREATEDTIME, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_enmodifier, EntityDM.MODIFIER, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_enmodifitime, EntityDM.MODIFITIME, EntityDM.class, String.class);
        this.initTableColumn(this.tblc_ents, EntityDM.TS, EntityDM.class, String.class);
        //字段表
        this.initTableColumn(this.tblc_fid, FieldDM.ID, FieldDM.class, String.class);
        this.initTableColumn(this.tblc_fcode, FieldDM.CODE, FieldDM.class, String.class);
        this.initTableColumn(this.tblc_fname, FieldDM.NAME, FieldDM.class, String.class);
        this.initTableColumn(this.tblc_ftype, FieldDM.FIELDTYPE, FieldDM.class, String.class);
        this.initTableColumn(this.tblc_fisprimarykey, FieldDM.ISPRIMARYKEY, FieldDM.class, Boolean.class);
        this.initTableColumn(this.tblc_fisnotnull, FieldDM.ISNOTNULL, FieldDM.class, Boolean.class);
        this.initTableColumn(this.tblc_fdefaultval, FieldDM.DEFAULTVAL, FieldDM.class, String.class);
        this.initTableColumn(this.tblc_flength, FieldDM.LENGTH, FieldDM.class, Integer.class);
        this.initTableColumn(this.tblc_fmaxval, FieldDM.MAXVAL, FieldDM.class, Double.class);
        this.initTableColumn(this.tblc_fminval, FieldDM.MINVAL, FieldDM.class, Double.class);
        this.initTableColumn(this.tblc_fisactive, FieldDM.ISACTIVE, FieldDM.class, Boolean.class);
        this.initTableColumn(this.tblc_fcreator, FieldDM.CREATOR, FieldDM.class, String.class);
        this.initTableColumn(this.tblc_fcreatedtime, FieldDM.CREATEDTIME, FieldDM.class, String.class);
        this.initTableColumn(this.tblc_fmodifier, FieldDM.MODIFIER, FieldDM.class, String.class);
        this.initTableColumn(this.tblc_fmodifitime, FieldDM.MODIFITIME, FieldDM.class, String.class);
        this.initTableColumn(this.tblc_fts, FieldDM.TS, FieldDM.class, String.class);
    }
    private void initTblRows() throws Exception{
        this.tbl_entities.setRowFactory((TableView<EntityDM> param) -> {
                TableRow<EntityDM> tableRow = new TableRow<>();
                tableRow.setOnMouseClicked((MouseEvent event) -> {
                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                        setEntityPropertyToUI(currSelectedEntity);//再设置当前实体属性到界面中
                    }
                });
                return tableRow;
        });
        this.tbl_fields.setRowFactory((TableView<FieldDM> param) -> {
                TableRow<FieldDM> tableRow = new TableRow<>();
                tableRow.setOnMouseClicked((MouseEvent event) ->{
                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                        setFieldPropertyToUI(currSelectedField);//再设置当前字段属性到界面中
                    }
                });
                return tableRow;
        });
    }
    /**
     * 添加当前选中项监听
     */
    private void addCurrSelectionListener() {
        System.out.println("添加选中项监听：addCurrSelectionListener");
        this.trv_mds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    System.out.println("上一个被选中的元数据：" + this.currSelectedMD);
                    System.out.println("上一个被选中的元数据树节点：" + this.currSelectedMDTreeItem);
                    updateObjectsProperty();//先更新上一个元数据属性
                    //设置树节点信息
                    if(this.currSelectedMDTreeItem != null) {
                        this.currSelectedMDTreeItem.setValue(null);
                        this.currSelectedMDTreeItem.setValue(this.currSelectedMD);
                    }
                    this.currSelectedMDTreeItem = newValue != null ? newValue : oldValue;//设置当前选中元数据树节点
                    this.currSelectedMD = this.currSelectedMDTreeItem != null ? this.currSelectedMDTreeItem.getValue() : null;//设置当前选中元数据项
                    setMDPropertyToUI(this.currSelectedMD);//再设置当前元数据属性到界面中
                    System.out.println("当前选中的元数据：" + this.currSelectedMD);
                    System.out.println("当前选中的元数据树节点：" + this.currSelectedMDTreeItem);
                    refreshEntitiesAction(null);
                }
        );
        this.tbl_entities.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    System.out.println("上一个被选中的实体：" + this.currSelectedEntity);
                    updateObjectsProperty();//先更新上一个实体属性
                    this.currSelectedEntity = newValue != null ? newValue : oldValue;
                    setEntityPropertyToUI(this.currSelectedEntity);//再设置当前实体属性到界面中
                    System.out.println("当前选中实体：" + this.currSelectedEntity);
                    refreshFieldsAction(null);
                }
        );
        this.tbl_fields.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    System.out.println("上一个被选中的字段：" + this.currSelectedField);
                    updateObjectsProperty();//先更新上一个字段属性
                    this.currSelectedField = newValue != null ? newValue : oldValue;
                    setFieldPropertyToUI(this.currSelectedField);//再设置当前字段属性到界面中
                    System.out.println("当前选中字段：" + this.currSelectedField);
                }
        );
    }
    /**
     * 初始化列
     * @param column 列对象
     * @param propertyName 列对应的属性子段名称
     * @param sClass TableView模型类型
     * @param tClass TableView对应列类型
     * @param <S> TableView模型类型
     * @param <T> TableView列类型
     * @throws Exception
     */
    private <S,T> void initTableColumn(TableColumn column, final String propertyName,Class<S> sClass,Class<T> tClass) throws Exception{
        if(column == null || propertyName == null) throw new Exception("[列对象]或[列对应的属性子段名称]为空！");
        column.setCellValueFactory(new PropertyValueFactory<S, T>(propertyName));//设置列值映射
        if(tClass == Boolean.class){
            column.setCellFactory(CheckBoxTableCell.forTableColumn(column));
        }else if(tClass == Integer.class){
            column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        }else if(tClass == Double.class){
            column.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        }else{
            column.setCellFactory(TextFieldTableCell.<S>forTableColumn());
        }
        column.setSortType(TableColumn.SortType.ASCENDING);
    }

    //=======================================================元数据=======================================================
    /**
     * 元数据关键字
     */
    @FXML // fx:id="txt_filterMDs"
    private TextField txt_filterMDs;
    /**
     * 菜单项：刷新元数据
     */
    @FXML // fx:id="mui_refreshMDs"
    private MenuItem mui_refreshMDs;
    /**
     * 菜单项：添加元数据
     */
    @FXML // fx:id="mui_addMD"
    private MenuItem mui_addMD;
    /**
     * 菜单项：删除实体
     */
    @FXML // fx:id="mui_delMD"
    private MenuItem mui_delMD;
    /**
     * 元数据树
     */
    @FXML // fx:id="trv_mds"
    private TreeView<MetadataDM> trv_mds;
    /**
     * 根据输入的元数据关键字过滤元数据的方法
     *
     * @param event
     */
    @FXML
    void filterMDsAction(ActionEvent event) {
        String[] split = this.txt_filterMDs.getText().split(",");
        MetadataDM root = new MetadataDM("root","元数据管理");
        root.getChildren().clear();
        for (String keyword : split) {
            for (MetadataDM metadataDM : this.currRootMD.getChildren()) {
                //TODO 关键字查询维护root
            }
        }
        setMetadatasToUI(new TreeItem<>(root), root.getChildren());
    }
    /**
     * 添加新元数据的方法
     *
     * @param event
     */
    @FXML
    void addMDAction(ActionEvent event) {
        if(this.currSelectedMD == null){
            //TODO 新增元数据时提示“请选择一个元数据节点”
            System.out.println("请选择一个节点");
            return;
        }
        //TODO 1.新增一个元数据对象
        MetadataDM md = new MetadataDM();
        //TODO 2.设置新增元数据father为当前选中元数据
        md.setFather(this.currSelectedMD);
        md.setCode("请输入编码");
        md.setName("请输入名称");
        TreeItem<MetadataDM> treeItem = new TreeItem<>(md);
        this.currSelectedMDTreeItem.getChildren().add(treeItem);
        this.currSelectedMD.getChildren().add(md);
        this.currSelectedMD = md;//TODO 设置当前选中项
        this.currSelectedMDTreeItem = treeItem;
        this.trv_mds.requestFocus();
        //TODO 3.设置新增元数据到属性面板
        this.setMDPropertyToUI(md);
    }
    /**
     * 保存元数据的方法
     *
     */
    void saveMD() {

    }
    /**
     * 删除当前选中元数据的方法
     *
     * @param event
     */
    @FXML
    void delMDAction(ActionEvent event) {
        if(this.currSelectedMD == this.currRootMD){//根节点
            System.out.println("不能删除根节点");
            return;//TODO 不能删除根节点
        }else{
            this.currSelectedMDTreeItem.getParent().getChildren().remove(this.currSelectedMDTreeItem);
        }
    }
    /**
     * 刷新元数据树的方法
     *
     * @param event
     */
    @FXML
    void refreshMDsAction(ActionEvent event) {
        this.currRootMD = new MetadataDM();//TODO 获取元数据根节点
        //TODO 测试代码 begin
        this.currRootMD.setCode("root");
        this.currRootMD.setName("元数据管理");
        MetadataDM dm1 = new MetadataDM("1","元数据1");
        MetadataDM dm1_1 = new MetadataDM("1-1","元数据1-1");
        dm1.getChildren().add(dm1_1);
        dm1_1.setFather(dm1);

        MetadataDM dm2 = new MetadataDM("2","元数据2");
        MetadataDM dm2_1 = new MetadataDM("2-1","元数据2-1");
        dm2.getChildren().add(dm2_1);
        dm2_1.setFather(dm2);
        //添加实体
        ObservableList<EntityDM> entityDMs = FXCollections.observableArrayList();
        for (int i = 1; i <= 2; i++) {
            EntityDM entityDM = new EntityDM("实体编码" + i, "实体名称" + i);
            if(i == 2){
                ObservableList<FieldDM> fieldDMs = FXCollections.observableArrayList();
                FieldDM fdm_1 = new FieldDM("字段编码1","字段名称1");
                FieldDM fdm_2 = new FieldDM("字段编码2","字段名称2");
                fieldDMs.addAll(fdm_1,fdm_2);
                entityDM.setFields(fieldDMs);
            }
            entityDMs.add(entityDM);
        }
        dm2_1.setEntities(entityDMs);

        MetadataDM dm2_1_1 = new MetadataDM("2-1-1","元数据2-1-1");
        dm2_1.getChildren().add(dm2_1_1);
        dm2_1_1.setFather(dm2_1);

        MetadataDM dm3 = new MetadataDM("3","元数据3");
        MetadataDM dm3_1 = new MetadataDM("3-1","元数据3-1");
        dm3.getChildren().add(dm3_1);
        dm3_1.setFather(dm3);
        MetadataDM dm3_1_1 = new MetadataDM("3-1-1","元数据3-1-1");
        dm3_1.getChildren().add(dm3_1_1);
        dm3_1_1.setFather(dm3_1);
        MetadataDM dm3_2 = new MetadataDM("3-2","元数据3-2");
        dm3.getChildren().add(dm3_2);
        dm3_2.setFather(dm3);

        this.currRootMD.getChildren().addAll(dm1, dm2, dm3);
        //TODO 测试代码 end
        if(this.currRootMD == null) return;
        TreeItem<MetadataDM> root = new TreeItem<>(this.currRootMD);
        setMetadatasToUI(root, this.currRootMD.getChildren());
        root.setExpanded(true);
        this.trv_mds.setRoot(root);
    }

    /**
     * 递归加载元数据树
     * @param parent 上级元数据
     * @param dms 子集元数据集合
     */
    private void setMetadatasToUI(TreeItem<MetadataDM> parent,List<MetadataDM> dms){
        if(dms == null || (dms != null && dms.isEmpty())) return;
        for (MetadataDM dm : dms) {
            TreeItem<MetadataDM> item = new TreeItem<>(dm);
            item.setExpanded(true);//默认展开
            parent.getChildren().add(item);
            if(dm.getChildren() != null && !dm.getChildren().isEmpty()){
                setMetadatasToUI(item, dm.getChildren());
            }
        }
    }

    /**
     * 设置元数据属性到属性界面
     * @param md 元数据VO
     */
    private void setMDPropertyToUI(MetadataDM md){
        if(md == null) {
            this.setProsToUI(null);
            return;
        }
        List<PropertyDM> pros = new ArrayList<>();
//        public static String ID="id";//唯一标识
//        public static String CODE="code";//元数据编码
//        public static String NAME="name";//元数据名称
//        public static String FATHER="father";//所属父级对象
//        public static String CHILDREN="children";//包含子级集合
//        public static String ENTITIES="entities";//包含实体集合
//        public static String CREATEDTIME="createdtime";//创建时间
//        public static String CREATOR="creator";//创建人
//        public static String MODIFITIME="modifitime";//修改时间
//        public static String MODIFIER="modifier";//修改人
//        public static String TS="ts";//时间戳
        pros.add(new PropertyDM(MetadataDM.ID,"唯一标识",md.getId(),true,false));
        pros.add(new PropertyDM(MetadataDM.CODE,"元数据编码",md.getCode(),false,true));
        pros.add(new PropertyDM(MetadataDM.NAME,"元数据名称",md.getName(),false,true));
        pros.add(new PropertyDM(MetadataDM.FATHER,"所属父级对象",md.getFather(),true,false));
        pros.add(new PropertyDM(MetadataDM.CHILDREN,"包含子级集合",md.getChildren(),true,false));
        pros.add(new PropertyDM(MetadataDM.ENTITIES,"包含实体集合",md.getEntities(),true,false));
        pros.add(new PropertyDM(MetadataDM.CREATOR,"创建人",md.getCreator(),true,true));
        pros.add(new PropertyDM(MetadataDM.CREATEDTIME,"创建时间", md.getCreatedtime(),true,true));
        pros.add(new PropertyDM(MetadataDM.MODIFIER, "修改人", md.getModifier(),true,true));
        pros.add(new PropertyDM(MetadataDM.MODIFITIME,"修改时间",md.getModifitime(),true,true));
        pros.add(new PropertyDM(MetadataDM.TS,"时间戳",md.getTs(),true,false));
        //Collections.sort(pros);
        this.currPros = pros;//设置当前属性
        this.setProsToUI(pros);
        this.propertyPanelStatus = 0;
    }



    //=======================================================实体=======================================================
    /**
     * 按钮：添加实体
     */
    @FXML // fx:id="btn_addEntity"
    private Button btn_addEntity;
    /**
     * 按钮：删除实体
     */
    @FXML // fx:id="btn_delEntity"
    private Button btn_delEntity;
    /**
     * 按钮：刷新实体
     */
    @FXML // fx:id="btn_refreshEntities"
    private Button btn_refreshEntities;
    /**
     * 实体表格
     */
    @FXML // fx:id="tbl_entities"
    private TableView<EntityDM> tbl_entities;
    /**
     * 实体表格列：唯一标识
     */
    @FXML // fx:id="tblc_enid"
    private TableColumn<EntityDM, String> tblc_enid;
    /**
     * 实体表格列：实体编码
     */
    @FXML // fx:id="tblc_encode"
    private TableColumn<EntityDM, String> tblc_encode;
    /**
     * 实体表格列：实体名称
     */
    @FXML // fx:id="tblc_enname"
    private TableColumn<EntityDM, String> tblc_enname;
    /**
     * 实体表格列：完全限定类名
     */
    @FXML // fx:id="tblc_enclassname"
    private TableColumn<EntityDM, String> tblc_enclassname;
    /**
     * 实体表格列：表名
     */
    @FXML // fx:id="tblc_entablename"
    private TableColumn<EntityDM, String> tblc_entablename;
    /**
     * 实体表格列：包含字段
     */
    @FXML // fx:id="tblc_enfields"
    private TableColumn<EntityDM, List<FieldDM>> tblc_enfields;
    /**
     * 实体表格列：创建人
     */
    @FXML // fx:id="tblc_encreator"
    private TableColumn<EntityDM, String> tblc_encreator;
    /**
     * 实体表格列：创建时间
     */
    @FXML // fx:id="tblc_encreatedtime"
    private TableColumn<EntityDM, Date> tblc_encreatedtime;
    /**
     * 实体表格列：修改人
     */
    @FXML // fx:id="tblc_enmodifier"
    private TableColumn<EntityDM, String> tblc_enmodifier;
    /**
     * 实体表格列：修改时间
     */
    @FXML // fx:id="tblc_enmodifitime"
    private TableColumn<FieldDM, Date> tblc_enmodifitime;
    /**
     * 实体表格列：时间戳
     */
    @FXML // fx:id="tblc_ents"
    private TableColumn<EntityDM, String> tblc_ents;
    /**
     * 添加新实体的方法
     *
     * @param event
     */
    @FXML
    void addEntityAction(ActionEvent event) {
        if(this.currSelectedMD == null){
            System.out.println("请选择一个元数据树节点");//TODO 请选择一个元数据树节点
            return;
        }
        //TODO 1.新增一个实体对象
        EntityDM entity = new EntityDM();
        //TODO 2.设置新增实体属性
        entity.setCode("请输入编码");
        entity.setName("请输入名称");
        this.currSelectedMD.getEntities().add(entity);//添加到元数据实体集合
        this.currSelectedEntity = entity;//TODO 设置当前选中项
        this.tbl_entities.requestFocus();
        //TODO 3.设置新增实体到属性面板
        this.setEntityPropertyToUI(entity);
    }
    /**
     * 保存实体的方法
     *
     */
    void saveEntity() {

    }
    /**
     * 删除当前选中实体的方法
     *
     * @param event
     */
    @FXML
    void delEntityAction(ActionEvent event) {
        if(this.tbl_entities.getItems() != null)
            this.tbl_entities.getItems().remove(this.currSelectedEntity);
    }
    /**
     * 根据当前选中元数据刷新实体的方法
     *
     * @param event
     */
    @FXML
    void refreshEntitiesAction(ActionEvent event) {
        if(this.currSelectedMD != null){//当前选中节点
            this.tbl_entities.setItems(this.currSelectedMD.getEntities());
        }else{
            tbl_entities.setItems(null);
        }
    }
    /**
     * 设置实体属性到属性界面
     * @param entity 实体VO
     */
    private void setEntityPropertyToUI(EntityDM entity){
        if(entity == null) {
            this.setProsToUI(null);
            return;
        }
        List<PropertyDM> pros = new ArrayList<>();
//        public static String ID="id";//唯一标识
//        public static String CODE="code";//实体编码
//        public static String NAME="name";//实体名称

//        public static String CLASSNAME="classname";//完全限定类名
//        public static String TABLENAME="tablename";//表名
//        public static String FIELDS="fields";//包含字段集合

//        public static String CREATOR="creator";//创建人
//        public static String CREATEDTIME="createdtime";//创建时间
//        public static String MODIFITIME="modifitime";//修改时间
//        public static String MODIFIER="modifier";//修改人
//        public static String TS="ts";//时间戳
        pros.add(new PropertyDM(EntityDM.ID,"唯一标识",entity.getId(),true,false));
        pros.add(new PropertyDM(EntityDM.CODE,"实体编码",entity.getCode(),false,true));
        pros.add(new PropertyDM(EntityDM.NAME,"实体名称",entity.getName(),false,true));

        pros.add(new PropertyDM(EntityDM.CLASSNAME,"完全限定类名",entity.getClassname(),false,true));
        pros.add(new PropertyDM(EntityDM.TABLENAME,"表名",entity.getTablename(),false,true));
        pros.add(new PropertyDM(EntityDM.FIELDS,"包含字段集合",entity.getFields(),false,false));

        pros.add(new PropertyDM(EntityDM.CREATOR,"创建人",entity.getCreator(),true,true));
        pros.add(new PropertyDM(EntityDM.CREATEDTIME,"创建时间", entity.getCreatedtime(),true,true));
        pros.add(new PropertyDM(EntityDM.MODIFIER, "修改人", entity.getModifier(),true,true));
        pros.add(new PropertyDM(EntityDM.MODIFITIME,"修改时间",entity.getModifitime(),true,true));
        pros.add(new PropertyDM(EntityDM.TS,"时间戳",entity.getTs(),true,false));
        //Collections.sort(pros);
        this.currPros = pros;//设置当前属性
        this.setProsToUI(pros);
        this.propertyPanelStatus = 1;
    }

    //=======================================================字段=======================================================
    /**
     * 字段关键字
     */
    @FXML // fx:id="txt_filterFields"
    private TextField txt_filterFields;
    /**
     * 按钮：添加字段
     */
    @FXML // fx:id="btn_addField"
    private Button btn_addField;
    /**
     * 按钮：删除实体
     */
    @FXML // fx:id="btn_delField"
    private Button btn_delField;
    /**
     * 按钮：刷新字段
     */
    @FXML // fx:id="btn_refreshFields"
    private Button btn_refreshFields;
    /**
     * 字段表格
     */
    @FXML // fx:id="tbl_fields"
    private TableView<FieldDM> tbl_fields;
    /**
     * 字段表格列：唯一标识
     */
    @FXML // fx:id="tblc_fid"
    private TableColumn<FieldDM, String> tblc_fid;
    /**
     * 字段表格列：字段编码
     */
    @FXML // fx:id="tblc_fcode"
    private TableColumn<FieldDM, String> tblc_fcode;
    /**
     * 字段表格列：字段名称
     */
    @FXML // fx:id="tblc_fname"
    private TableColumn<FieldDM, String> tblc_fname;
    /**
     * 字段表格列：
     */
    @FXML // fx:id="tblc_ftype"
    private TableColumn<FieldDM, String> tblc_ftype;
    /**
     * 字段表格列：是否主属性
     */
    @FXML // fx:id="tblc_fisprimarykey"
    private TableColumn<FieldDM, Boolean> tblc_fisprimarykey;
    /**
     * 字段表格列：是否必输
     */
    @FXML // fx:id="tblc_fisnotnull"
    private TableColumn<FieldDM, Boolean> tblc_fisnotnull;
    /**
     * 字段表格列：缺省值
     */
    @FXML // fx:id="tblc_fdefaultval"
    private TableColumn<FieldDM, String> tblc_fdefaultval;
    /**
     * 字段表格列：长度
     */
    @FXML // fx:id="tblc_flength"
    private TableColumn<FieldDM, Integer> tblc_flength;
    /**
     * 字段表格列：最小值
     */
    @FXML // fx:id="tblc_fminval"
    private TableColumn<FieldDM, String> tblc_fminval;
    /**
     * 字段表格列：最大值
     */
    @FXML // fx:id="tblc_fmaxval"
    private TableColumn<FieldDM, Double> tblc_fmaxval;
    /**
     * 字段表格列：是否启用
     */
    @FXML // fx:id="tblc_fisactive"
    private TableColumn<FieldDM, Boolean> tblc_fisactive;
    /**
     * 字段表格列：创建人
     */
    @FXML // fx:id="tblc_fcreator"
    private TableColumn<FieldDM, String> tblc_fcreator;
    /**
     * 字段表格列：创建时间
     */
    @FXML // fx:id="tblc_fcreatedtime"
    private TableColumn<FieldDM, Date> tblc_fcreatedtime;
    /**
     * 字段表格列：修改人
     */
    @FXML // fx:id="tblc_fmodifier"
    private TableColumn<FieldDM, String> tblc_fmodifier;
    /**
     * 字段表格列：修改时间
     */
    @FXML // fx:id="tblc_fmodifitime"
    private TableColumn<FieldDM, Date> tblc_fmodifitime;
    /**
     * 字段表格列：时间戳
     */
    @FXML // fx:id="tblc_fts"
    private TableColumn<FieldDM, String> tblc_fts;
    /**
     * 根据输入的字段关键字过滤字段的方法
     *
     * @param event
     */
    @FXML
    void filterFieldsAction(ActionEvent event) {
        List<PropertyDM> pros = new ArrayList<>();
        String[] keywords = this.txt_filterPros.getText().split(",");
        for (String keyword : keywords) {
            pros.addAll(this.currPros.stream().filter(pro -> (keyword == null || (keyword != null && keyword.trim().equals(""))) || pro.getKey().contains(keyword) || pro.getValue().toString().contains(keyword)).collect(Collectors.toList()));
        }
        pros = pros.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
        //Collections.sort(pros);
        setProsToUI(pros);
    }
    /**
     * 添加新字段的方法
     *
     * @param event
     */
    @FXML
    void addFieldAction(ActionEvent event) {
        //TODO 1.新增一个字段对象
        FieldDM field = new FieldDM();
        //TODO 2.设置新增字段father为当前选中元数据
        field.setCode("请输入编码");
        field.setName("请输入名称");
        this.currSelectedEntity.getFields().add(field);//添加到实体的字段集合中
        this.currSelectedField = field;//TODO 设置当前选中项
        this.tbl_fields.requestFocus();
        //TODO 3.设置新增字段到属性面板
        this.setFieldPropertyToUI(field);
    }
    /**
     * 保存字段的方法
     *
     */
    void saveField() {

    }
    /**
     * 删除当前选中字段的方法
     *
     * @param event
     */
    @FXML
    void delFieldAction(ActionEvent event) {
        if(this.tbl_fields.getItems() != null)
            this.tbl_fields.getItems().remove(this.currSelectedField);
    }
    /**
     * 根据当前选中实体刷新字段的方法
     *
     * @param event
     */
    @FXML
    void refreshFieldsAction(ActionEvent event) {
        if(this.currSelectedEntity != null){
            tbl_fields.setItems(this.currSelectedEntity.getFields());
        }else{
            tbl_fields.setItems(null);
        }
    }
    /**
     * 设置字段属性到属性界面
     * @param field 字段对象
     */
    private void setFieldPropertyToUI(FieldDM field){
        if(field == null) {
            this.setProsToUI(null);
            return;
        }
        List<PropertyDM> pros = new ArrayList<>();
//        public static String ID="id";//唯一标识
//        public static String CODE="code";//字段编码
//        public static String NAME="name";//字段名称
//        public static String FIELDTYPE="fieldtype";//字段类型
//        public static String ISPRIMARYKEY="isprimarykey";//是否主属性
//        public static String ISNOTNULL="isnotnull";//是否必输
//        public static String DEFAULTVAL="defaultval";//缺省值
//        public static String LENGTH="length";//长度
//        public static String MINVAL="minval";//最小值
//        public static String MAXVAL="maxval";//最大值
//        public static String ISACTIVE="isactive";//是否启用
//        public static String CREATOR="creator";//创建人
//        public static String CREATEDTIME="createdtime";//创建时间
//        public static String MODIFITIME="modifitime";//修改时间
//        public static String MODIFIER="modifier";//修改人
//        public static String TS="ts";//时间戳
        pros.add(new PropertyDM(FieldDM.ID,"唯一标识",field.getId(),true,false));
        pros.add(new PropertyDM(FieldDM.CODE,"字段编码",field.getCode(),false,true));
        pros.add(new PropertyDM(FieldDM.NAME,"字段名称",field.getName(),false,true));

        pros.add(new PropertyDM(FieldDM.FIELDTYPE,"字段类型",field.getFieldtype(),false,true));
        pros.add(new PropertyDM(FieldDM.ISPRIMARYKEY,"是否主属性",field.getIsprimarykey(),false,true));
        pros.add(new PropertyDM(FieldDM.ISNOTNULL,"是否必输",field.getIsnotnull(),false,true));
        pros.add(new PropertyDM(FieldDM.DEFAULTVAL,"缺省值",field.getDefaultval(),false,true));
        pros.add(new PropertyDM(FieldDM.LENGTH,"长度",field.getLength(),false,true));
        pros.add(new PropertyDM(FieldDM.MINVAL,"最小值",field.getMinval(),false,true));
        pros.add(new PropertyDM(FieldDM.MAXVAL,"最大值",field.getMaxval(),false,true));
        pros.add(new PropertyDM(FieldDM.ISACTIVE,"是否启用",field.getIsactive(),false,true));

        pros.add(new PropertyDM(FieldDM.CREATOR,"创建人",field.getCreator(),true,true));
        pros.add(new PropertyDM(FieldDM.CREATEDTIME,"创建时间", field.getCreatedtime(),true,true));
        pros.add(new PropertyDM(FieldDM.MODIFIER, "修改人", field.getModifier(),true,true));
        pros.add(new PropertyDM(FieldDM.MODIFITIME,"修改时间",field.getModifitime(),true,true));
        pros.add(new PropertyDM(FieldDM.TS,"时间戳",field.getTs(),true,false));
        //Collections.sort(pros);
        this.currPros = pros;//设置当前属性
        this.setProsToUI(pros);
        this.propertyPanelStatus = 2;
    }


    //=======================================================属性=======================================================
    /**
     * 属性关键字
     */
    @FXML // fx:id="txt_filterPros"
    private TextField txt_filterPros;
    /**
     * 属性面板
     */
    @FXML // fx:id="gp_pros"
    private GridPane gp_pros;
    /**
     * 根据输入的属性关键字过滤属性的方法
     *
     * @param event
     */
    @FXML
    void filterProsAction(ActionEvent event) {
        List<PropertyDM> pros = new ArrayList<>();
        String[] keywords = this.txt_filterPros.getText().split(",");
        for (String keyword : keywords) {
            pros.addAll(
                    this.currPros.stream()
                            .filter(pro -> (keyword == null || (keyword != null && keyword.trim().equals("")) || (pro.getKey() != null && pro.getDisplaykey() != null && pro.getValue() != null && (pro.getKey().contains(keyword) || pro.getDisplaykey().contains(keyword) || pro.getValue().toString().contains(keyword))))).collect(Collectors.toList()));
        }
        pros = pros.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
        Collections.sort(pros);
        setProsToUI(pros);
    }

    /**
     * 设置属性集合到界面上：只显示Visible为true的属性，Visible为false的属性临时保存在属性面板的UserData中
     * @param pros 所有属性集合
     */
    private void setProsToUI(List<PropertyDM> pros){
        pros = getVisiblePros(pros);//过滤需要显示的属性
        this.gp_pros.getChildren().clear();//清除子集
        if(pros == null || (pros != null && pros.isEmpty())) return;
        this.gp_pros.getRowConstraints().clear();
        for (int i = 0; i < pros.size(); i++) {
            if(pros.get(i) == null) continue;
            //<RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
//            if(pros.get(i).getVisible()) {
//                RowConstraints rowConstraints = new RowConstraints();
//                rowConstraints.setMinHeight(10);
//                rowConstraints.setPrefHeight(30);
//                rowConstraints.setVgrow(Priority.SOMETIMES);
//                this.gp_pros.getRowConstraints().add(rowConstraints);
//            }
            Label lbl_key = new Label(pros.get(i).getDisplaykey());
            lbl_key.setUserData(pros.get(i).getKey());
            if(pros.get(i).getValueNode() instanceof TextField && pros.get(i).getValue() != null) {
                ((TextField) pros.get(i).getValueNode()).setText(pros.get(i).getValue().toString());
            }
            this.gp_pros.setColumnIndex(lbl_key, 0);
            this.gp_pros.setColumnIndex(pros.get(i).getValueNode(), 1);
            this.gp_pros.setHalignment(lbl_key, HPos.RIGHT);
            this.gp_pros.setHalignment(pros.get(i).getValueNode(), HPos.LEFT);
            this.gp_pros.setRowIndex(lbl_key, i);
            this.gp_pros.setRowIndex(pros.get(i).getValueNode(), i);
            this.gp_pros.setValignment(lbl_key, VPos.CENTER);
            this.gp_pros.setValignment(pros.get(i).getValueNode(), VPos.CENTER);
            this.gp_pros.setMargin(lbl_key, new Insets(0, 10, 0, 0));//上右下左
            this.gp_pros.setMargin(pros.get(i).getValueNode(), new Insets(0, 10, 0, 0));//上右下左
            pros.get(i).getValueNode().setDisable(pros.get(i).getIsreadonly());
            this.gp_pros.add(lbl_key,0,i);
            this.gp_pros.add(pros.get(i).getValueNode(),1,i);
        }
        this.gp_pros.getChildren().get(0).requestFocus();//获取光标
    }

    /**
     * 过滤需要显示的属性
     * @param pros 所有的属性集合
     * @return 需要显示的属性集合
     */
    private List<PropertyDM> getVisiblePros(List<PropertyDM> pros){
        List<PropertyDM> visiblePros = new ArrayList<>();
        for (PropertyDM pro : pros) {
            if(pro.getVisible()){
                visiblePros.add(pro);
            }
        }
        return visiblePros;
    }
    /**
     * 从界面获取属性并更新currPros集合
     * @return 所有属性集合
     */
    private List<PropertyDM> getProsFromUI(){
        if(this.currPros == null || (this.currPros != null && this.currPros.isEmpty())) return null;
        ObservableList<Node> children = this.gp_pros.getChildren();
        if(children == null || (children != null && children.isEmpty())) return null;
        List<PropertyDM> pros = new ArrayList<>();
        //属性面板中的属性
        for (int i = 0; i < children.size()-1; i++) {
            Node keynode = children.get(i);
            Node valuenode = children.get(i + 1);
            Label lbl_key = (keynode != null && keynode instanceof Label) ? (Label)keynode : null;
            TextField txt_value = (valuenode != null && valuenode instanceof TextField) ? (TextField)valuenode : null;
            if(lbl_key == null || txt_value == null) continue;
            PropertyDM pro = new PropertyDM(lbl_key.getUserData().toString(),lbl_key.getText(),txt_value.getText());
            for (PropertyDM currPro : this.currPros) {
                if(pro.equals(currPro)){
                    currPro.setValue(pro.getValue());
                }
            }
            pros.add(pro);
        }
        return this.currPros;
    }



    //=======================================================公共=====================================================

    /**
     * 根据当前属性面板类型从界面获取并更新属性信息
     */
    private void updateObjectsProperty(){
        if(this.propertyPanelStatus == 0){//元数据
            updateObjectPropertyFromUI(this.currSelectedMD);
        }else if(this.propertyPanelStatus == 1){//实体
            updateObjectPropertyFromUI(this.currSelectedEntity);
        }else if(this.propertyPanelStatus == 2){//字段
            updateObjectPropertyFromUI(this.currSelectedField);
        }
    }
    /**
     * 从界面获取属性信息并保存到指定对象中
     * @param t 指定对象
     * @param <T> 指定对象类型
     */
    private <T> void updateObjectPropertyFromUI(T t){
        if(t == null) return;
        List<PropertyDM> prosFromUI = getProsFromUI();
        if(prosFromUI != null){
            for (PropertyDM pro : prosFromUI) {
                updateObjectByProperty(t, pro);
            }
        }
    }
    /**
     * 利用反射更新指定对象的属性
     * @param t 指定对象
     * @param pro 属性对象
     * @param <T> 指定对象类型
     */
    private <T> void updateObjectByProperty(T t, PropertyDM pro) {
        if(pro.getIsreadonly()) return;
        try {
            if(pro != null && pro.getKey() != null){
                Class<?> clazz = t.getClass();
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if(method != null && method.getName().toUpperCase().equals("SET" + pro.getKey().toUpperCase())){
                        Class<?> paramClazz = method.getParameterTypes()[0];
                        if(paramClazz == Integer.class || paramClazz == int.class){
                            method.invoke(t, Integer.valueOf(pro.getValue().toString()));
                            return;
                        }else if(paramClazz == Double.class || paramClazz == double.class){
                            method.invoke(t, Double.valueOf(pro.getValue().toString()));
                            return;
                        }else if(paramClazz == Boolean.class || paramClazz == boolean.class){
                            method.invoke(t, Boolean.valueOf(pro.getValue().toString()));
                            return;
                        }else if(paramClazz == String.class){
                            method.invoke(t, String.valueOf(pro.getValue().toString()));
                            return;
                        }else{
                            method.invoke(t, pro.getValue());
                            return;
                        }
                    }else if(method != null && method.getName().toUpperCase().equals("SETMODIFITIME")){
                        method.invoke(t,LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));//更新修改时间
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}