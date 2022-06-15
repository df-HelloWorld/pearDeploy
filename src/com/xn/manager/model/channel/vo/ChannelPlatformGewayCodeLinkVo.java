package com.xn.manager.model.channel.vo;


import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description: 客户端：渠道与平台通道码关联的实体属性Bean
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class ChannelPlatformGewayCodeLinkVo extends BasePage implements Serializable {
    private static final long   serialVersionUID = 12332343312302147L;

    public ChannelPlatformGewayCodeLinkVo(){

    }



    /**
     * 费率
     *
     * @mbggenerated
     */
    private String serviceCharge;

    /**
     * 平台通道码
     */
    private String pfGewayCode;


    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 平台通道码名称
     */
    private String codeName;

    /**
     * 是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
     */
    private int isEnable;


    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
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

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }
}
