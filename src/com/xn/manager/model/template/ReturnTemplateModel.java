package com.xn.manager.model.template;


import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description: 返回模板的实体属性Bean
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class ReturnTemplateModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1203223201523L;

    public ReturnTemplateModel(){

    }

    /**
     * 主键ID
     */
    private long id;

    /**
     * 通道码ID：对应tb_pr_geway_code表的主键ID
     */
    private long gewayCodeId;

    /**
     * 模板名称
     */
    private String template;

    /**
     *返回数据类型：1直接返回，2返回一层json，3返回二层json
     */
    private int dataType;

    /**
     * check拉单状态的参数名
     */
    private String parameterName;

    /**
     * check拉单成功的参数值：如果有多个值代表成功，则以英文逗号分割
     */
    private String parameterValue;

    /**
     * check拉单成功参数值的数据类型：1String类型，2Int类型，3long类型
     */
    private int parameterValueType;

    /**
     * 返回数据案例
     */
    private String returnCase;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
     */
    private int isEnable;

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

    /**
     * 通道码名称
     */
    private String gewayCodeName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGewayCodeId() {
        return gewayCodeId;
    }

    public void setGewayCodeId(long gewayCodeId) {
        this.gewayCodeId = gewayCodeId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public int getParameterValueType() {
        return parameterValueType;
    }

    public void setParameterValueType(int parameterValueType) {
        this.parameterValueType = parameterValueType;
    }

    public String getReturnCase() {
        return returnCase;
    }

    public void setReturnCase(String returnCase) {
        this.returnCase = returnCase;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
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

    public String getGewayCodeName() {
        return gewayCodeName;
    }

    public void setGewayCodeName(String gewayCodeName) {
        this.gewayCodeName = gewayCodeName;
    }
}
