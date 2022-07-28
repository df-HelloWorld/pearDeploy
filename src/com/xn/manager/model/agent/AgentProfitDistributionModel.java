package com.xn.manager.model.agent;


import com.xn.common.page.BasePage;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:
 * @Description: 代理利益分配的实体属性Bean
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class AgentProfitDistributionModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1233233302187L;

    public AgentProfitDistributionModel(){

    }

    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 别名
     */
    private String alias;

    /**
     * 归属代理ID
     */
    private long agentId;

    /**
     * 平台通道码ID
     */
    private long pfGewayCodeId;

    /**
     * 通道码ID
     */
    private long gewayCodeId;

    /**
     * 归属渠道ID
     */
    private long channelId;

    /**
     * 归属渠道ID
     */
    private long deployId;

    /**
     * 绑定类型：1与通道码绑定，2与渠道绑定，3与渠道+通道码绑定
     */
    private int bindingType;


    /**
     * 利益类型/费率类型：1固定费率，2额外费率
     */
    private int serviceChargeType;

    /**
     * 利益分成/费率
     */
    private String serviceCharge;

    /**
     * 利益值额外利益/费率之额外费率
     */
    private String extraServiceCharge;

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
     * 代理名称
     */
    private String agentName;

    /**
     * 代理类型：1针对渠道，2针对通道，3两者针对
     */
    private int agentType;

    /**
     * 通道码名称
     */
    private String gewayCodeName;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * id集合
     */
    private List<Long> idList;

    /**
     * 通道名称
     */
    private String gewayName;

    /**
     * 平台通道名称
     */
    private String codeName;

    /**
     * 平台通道码
     */
    private String pfGewayCode;

    /**
     * 渠道对应平台通道码的费率
     */
    private String pfGewayServiceCharge;


    /**
     * 拼接字符串使用
     */
    private String splicing;

    /**
     * 页面传的复选框的值-代理ID集合
     */
    private String agentArr;

    /**
     * 页面传的通道ID，分润
     * <p>
     *     数据传输格式
     *     平台通道ID_1,分润值_1#平台通道ID_2,分润值_2#
     * </p>
     */
    private String pfGewayServiceChargeArr;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public long getGewayCodeId() {
        return gewayCodeId;
    }

    public void setGewayCodeId(long gewayCodeId) {
        this.gewayCodeId = gewayCodeId;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public int getBindingType() {
        return bindingType;
    }

    public void setBindingType(int bindingType) {
        this.bindingType = bindingType;
    }

    public int getServiceChargeType() {
        return serviceChargeType;
    }

    public void setServiceChargeType(int serviceChargeType) {
        this.serviceChargeType = serviceChargeType;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getExtraServiceCharge() {
        return extraServiceCharge;
    }

    public void setExtraServiceCharge(String extraServiceCharge) {
        this.extraServiceCharge = extraServiceCharge;
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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getGewayCodeName() {
        return gewayCodeName;
    }

    public void setGewayCodeName(String gewayCodeName) {
        this.gewayCodeName = gewayCodeName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getAgentType() {
        return agentType;
    }

    public void setAgentType(int agentType) {
        this.agentType = agentType;
    }

    public long getDeployId() {
        return deployId;
    }

    public void setDeployId(long deployId) {
        this.deployId = deployId;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getGewayName() {
        return gewayName;
    }

    public void setGewayName(String gewayName) {
        this.gewayName = gewayName;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getPfGewayCode() {
        return pfGewayCode;
    }

    public void setPfGewayCode(String pfGewayCode) {
        this.pfGewayCode = pfGewayCode;
    }

    public String getPfGewayServiceCharge() {
        return pfGewayServiceCharge;
    }

    public void setPfGewayServiceCharge(String pfGewayServiceCharge) {
        this.pfGewayServiceCharge = pfGewayServiceCharge;
    }

    public String getSplicing() {
        return splicing;
    }

    public void setSplicing(String splicing) {
        this.splicing = splicing;
    }

    public long getPfGewayCodeId() {
        return pfGewayCodeId;
    }

    public void setPfGewayCodeId(long pfGewayCodeId) {
        this.pfGewayCodeId = pfGewayCodeId;
    }

    public String getAgentArr() {
        return agentArr;
    }

    public void setAgentArr(String agentArr) {
        this.agentArr = agentArr;
    }

    public String getPfGewayServiceChargeArr() {
        return pfGewayServiceChargeArr;
    }

    public void setPfGewayServiceChargeArr(String pfGewayServiceChargeArr) {
        this.pfGewayServiceChargeArr = pfGewayServiceChargeArr;
    }
}
