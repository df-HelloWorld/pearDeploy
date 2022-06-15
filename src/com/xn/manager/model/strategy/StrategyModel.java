package com.xn.manager.model.strategy;



import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @Description 策略表：关于一些策略配置的部署的实体属性Bean
 * @Author yoko
 * @Date 2020/5/19 11:49
 * @Version 1.0
 */
public class StrategyModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1233223301149L;

    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 策略名称
     */
    private String stgName;

    /**
     * 策略类型：1注册分享链接，2
     */
    private int stgType;

    /**
     * 策略的key：等同于策略类型
     */
    private String stgKey;

    /**
     * 策略整形值
     */
    private int stgNumValue;

    /**
     * 策略值
     */
    private String stgValue;

    /**
     * 策略大的值： 存储json数据
     */
    private String stgBigValue;

    /**
     * 数据类型：1普通数据，2英文逗号数据（多字段，英文逗号分割），3json数据
     */
    private int dataType;

    /**
     * 数据说明：描述，简介
     */
    private String stgExplain;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否有效：0有效，1无效/删除
     */
    private int yn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStgName() {
        return stgName;
    }

    public void setStgName(String stgName) {
        this.stgName = stgName;
    }

    public int getStgType() {
        return stgType;
    }

    public void setStgType(int stgType) {
        this.stgType = stgType;
    }

    public String getStgKey() {
        return stgKey;
    }

    public void setStgKey(String stgKey) {
        this.stgKey = stgKey;
    }

    public int getStgNumValue() {
        return stgNumValue;
    }

    public void setStgNumValue(int stgNumValue) {
        this.stgNumValue = stgNumValue;
    }

    public String getStgValue() {
        return stgValue;
    }

    public void setStgValue(String stgValue) {
        this.stgValue = stgValue;
    }

    public String getStgBigValue() {
        return stgBigValue;
    }

    public void setStgBigValue(String stgBigValue) {
        this.stgBigValue = stgBigValue;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getStgExplain() {
        return stgExplain;
    }

    public void setStgExplain(String stgExplain) {
        this.stgExplain = stgExplain;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }
}
