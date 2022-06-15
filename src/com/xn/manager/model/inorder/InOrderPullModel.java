package com.xn.manager.model.inorder;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description: 代收拉单的实体属性Bean
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class InOrderPullModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1203223201821L;

    public InOrderPullModel(){

    }

    /**
     * 渠道主键ID
     */
    private long channelId;

    /**
     * 平台通道ID
     */
    private long pfGewayCodeId;

    /**
     * 通道码ID
     */
    private long gewayCodeId;

    /**
     *是否用于计算收益：1不计算收益，2计算收益
     */
    private String isProfit;

    /**
     * 拉单金额
     */
    private String totalAmount;

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

    public long getGewayCodeId() {
        return gewayCodeId;
    }

    public void setGewayCodeId(long gewayCodeId) {
        this.gewayCodeId = gewayCodeId;
    }

    public String getIsProfit() {
        return isProfit;
    }

    public void setIsProfit(String isProfit) {
        this.isProfit = isProfit;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
