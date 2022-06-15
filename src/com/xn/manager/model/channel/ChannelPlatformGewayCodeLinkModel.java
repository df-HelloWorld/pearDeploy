package com.xn.manager.model.channel;


import com.xn.common.page.BasePage;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:
 * @Description: 渠道与平台通道码关联的实体属性Bean
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class ChannelPlatformGewayCodeLinkModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 12332343312302147L;

    public ChannelPlatformGewayCodeLinkModel(){

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
     * 归属渠道ID
     */
    private long channelId;

    /**
     * 平台通道编码ID：对应tb_pr_platform_geway_code表的主键ID
     */
    private long pfGewayCodeId;

    /**
     * 费率类型：1固定费率，2额外费率
     *
     * @mbggenerated
     */
    private int serviceChargeType;

    /**
     * 费率
     *
     * @mbggenerated
     */
    private String serviceCharge;

    /**
     * 费率之额外费率：每单还要收取额外的手续费
     *
     * @mbggenerated
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
     * 渠道名称
     */
    private String channelName;

    /**
     * 平台通道码名称
     */
    private String codeName;

    /**
     * 平台通道码
     */
    private String pfGewayCode;

    /**
     * id集合
     */
    private List<Long> idList;

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

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public long getPfGewayCodeId() {
        return pfGewayCodeId;
    }

    public void setPfGewayCodeId(long pfGewayCodeId) {
        this.pfGewayCodeId = pfGewayCodeId;
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

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
