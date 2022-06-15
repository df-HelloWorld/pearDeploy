package com.xn.manager.model.template;


import com.xn.common.page.BasePage;
import com.xn.manager.model.strategy.StrategyData;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:
 * @Description: 请求字段的实体属性Bean
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class SendFieldModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1203223201522L;

    public SendFieldModel(){

    }

    /**
     * 主键ID
     */
    private long id;

    /**
     * 归属请求模板ID：对应tb_pr_send_template表的主键ID
     */
    private long sendTemplateId;

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
     * 是否要加密：1加密，2不加密
     */
    private int isEncryption;

    /**
     * 是否允许为空：1不允许为空，2允许为空
     */
    private int isVacant;

    /**
     * 顺序/位置
     */
    private int seat;

    /**
     * 字段类型：1其它，2订单号/商家订单号，3商铺号，4通道码，5订单金额（单位：元），6订单金额（单位：分），7异步同步地址，8支付成功后跳转地址，9签名，10客户端IP，11时间：yyyy-MM-dd HH:mm:ss，12时间：yyyyMMddHHmmss，13时间：13位时间戳，14时间：10位时间戳，15秘钥变量名
     */
    private int fieldType;

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
     * 模板名称
     */
    private String template;

    /**
     * 是否有效：0有效，1无效/删除
     */
    private int yn;

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

    public long getSendTemplateId() {
        return sendTemplateId;
    }

    public void setSendTemplateId(long sendTemplateId) {
        this.sendTemplateId = sendTemplateId;
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

    public int getIsEncryption() {
        return isEncryption;
    }

    public void setIsEncryption(int isEncryption) {
        this.isEncryption = isEncryption;
    }

    public int getIsVacant() {
        return isVacant;
    }

    public void setIsVacant(int isVacant) {
        this.isVacant = isVacant;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
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
