package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class AgentProfitDistributionDeployModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;
    /**
     * 类型表id
     */
    private Long relationTypeId;
    /**
     * 渠道id
     */
    private Long channelId;
    private Long bindingType;
    /**
     * 渠道id
     */
    private String channelName;
    /**
     * 平台类型名字
     */
    private String codeName;

    private String gewayCodeNames;


    /**
     * 通道名称和费率
     */
    private String agentNameOrServiceCharge;
    /**
     * 类型名称
     *
     * @mbggenerated
     */
    private String typeName;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * TS时间
     *
     * @mbggenerated
     */
    private Date tsTime;

    /**
     * 是否有效：0有效，1无效/删除
     *
     * @mbggenerated
     */
    private Integer yn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTsTime() {
        return tsTime;
    }

    public void setTsTime(Date tsTime) {
        this.tsTime = tsTime;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public Long getRelationTypeId() {
        return relationTypeId;
    }

    public void setRelationTypeId(Long relationTypeId) {
        this.relationTypeId = relationTypeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getGewayCodeNames() {
        return gewayCodeNames;
    }

    public void setGewayCodeNames(String gewayCodeNames) {
        this.gewayCodeNames = gewayCodeNames;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Long getBindingType() {
        return bindingType;
    }

    public void setBindingType(Long bindingType) {
        this.bindingType = bindingType;
    }

    public String getAgentNameOrServiceCharge() {
        return agentNameOrServiceCharge;
    }

    public void setAgentNameOrServiceCharge(String agentNameOrServiceCharge) {
        this.agentNameOrServiceCharge = agentNameOrServiceCharge;
    }
}