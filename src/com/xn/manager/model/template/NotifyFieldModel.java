package com.xn.manager.model.template;

import com.xn.common.page.BasePage;
import com.xn.manager.model.strategy.StrategyData;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:
 * @Description: 接收字段的实体属性Bean
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class NotifyFieldModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1203223201523L;

    public NotifyFieldModel(){

    }

    /**
     * 主键ID
     */
    private long id;

    /**
     * 归属接收模板ID：对应tb_pr_notify_template表的主键ID
     */
    private long notifyTemplateId;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     *参数名称
     */
    private String parameterName;

    /**
     * 参数值
     */
    private String parameterValue;

    /**
     * 参数值类型：1String类型，2Int类型,3long类型
     */
    private int parameterValueType;

    /**
     * 字段类型：1支付连接地址，2图片二维码地址，3json第二层的data
     */
    private int fieldType;

    /**
     * 存放位置：1在第一层JSON，2在第二层JSON
     */
    private int seat;

    /**
     * 备注
     */
    private String remark;

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
     * 模板名称
     */
    private String template;


    /**
     * 请求字段的字段类型集合
     */
    private List<StrategyData> strategyDataList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNotifyTemplateId() {
        return notifyTemplateId;
    }

    public void setNotifyTemplateId(long notifyTemplateId) {
        this.notifyTemplateId = notifyTemplateId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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


    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<StrategyData> getStrategyDataList() {
        return strategyDataList;
    }

    public void setStrategyDataList(List<StrategyData> strategyDataList) {
        this.strategyDataList = strategyDataList;
    }
}
