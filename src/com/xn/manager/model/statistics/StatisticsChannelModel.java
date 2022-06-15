package com.xn.manager.model.statistics;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @author yoko
 * @desc 统计：渠道金额的统计
 * @create 2021-12-27 12:01
 **/
public class StatisticsChannelModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 12331523397146L;

    public StatisticsChannelModel(){

    }

    private long id;

    /**
     * 渠道
     */
    private long channelId;

    /**
     * 通道码ID
     */
    private long gewayCodeId;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 日期
     */
    private int curday;

    /**
     * 订单数量
     */
    private int orderNum;

    /**
     * 订单金额
     */
    private String orderMoney;

    /**
     * 成功订单数
     */
    private int sucOrderNum;

    /**
     * 成功订单金额
     */
    private String sucOrderMoney;

    /**
     * 单数成功率
     */
    private String sucOrderNumRatio;

    /**
     * 金额成功率
     */
    private String sucOrderMoneyRatio;

    /**
     * 实际金额
     */
    private String sucActualMoney;

    /**
     * 汇总订单数
     */
    private int totalNum;

    /**
     * 汇总成功订单数
     */
    private int totalSucNum;

    /**
     * 汇总单数成功率
     */
    private String totalNumRatio;

    /**
     * 汇总订单金额
     */
    private String totalMoney;

    /**
     * 汇总成功订单金额
     */
    private String totalSucMoney;

    /**
     * 汇总金额成功率
     */
    private String totalMoneyRatio;

    /**
     * 汇总实际金额
     */
    private String totalSucActualMoney;

    /**
     * 是否用于计算收益：1不计算收益，2计算收益
     */
    private int isProfit;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public long getGewayCodeId() {
        return gewayCodeId;
    }

    public void setGewayCodeId(long gewayCodeId) {
        this.gewayCodeId = gewayCodeId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public int getSucOrderNum() {
        return sucOrderNum;
    }

    public void setSucOrderNum(int sucOrderNum) {
        this.sucOrderNum = sucOrderNum;
    }

    public String getSucOrderMoney() {
        return sucOrderMoney;
    }

    public void setSucOrderMoney(String sucOrderMoney) {
        this.sucOrderMoney = sucOrderMoney;
    }

    public String getSucOrderNumRatio() {
        return sucOrderNumRatio;
    }

    public void setSucOrderNumRatio(String sucOrderNumRatio) {
        this.sucOrderNumRatio = sucOrderNumRatio;
    }

    public String getSucOrderMoneyRatio() {
        return sucOrderMoneyRatio;
    }

    public void setSucOrderMoneyRatio(String sucOrderMoneyRatio) {
        this.sucOrderMoneyRatio = sucOrderMoneyRatio;
    }

    public String getSucActualMoney() {
        return sucActualMoney;
    }

    public void setSucActualMoney(String sucActualMoney) {
        this.sucActualMoney = sucActualMoney;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalSucNum() {
        return totalSucNum;
    }

    public void setTotalSucNum(int totalSucNum) {
        this.totalSucNum = totalSucNum;
    }

    public String getTotalNumRatio() {
        return totalNumRatio;
    }

    public void setTotalNumRatio(String totalNumRatio) {
        this.totalNumRatio = totalNumRatio;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTotalSucMoney() {
        return totalSucMoney;
    }

    public void setTotalSucMoney(String totalSucMoney) {
        this.totalSucMoney = totalSucMoney;
    }

    public String getTotalMoneyRatio() {
        return totalMoneyRatio;
    }

    public void setTotalMoneyRatio(String totalMoneyRatio) {
        this.totalMoneyRatio = totalMoneyRatio;
    }

    public String getTotalSucActualMoney() {
        return totalSucActualMoney;
    }

    public void setTotalSucActualMoney(String totalSucActualMoney) {
        this.totalSucActualMoney = totalSucActualMoney;
    }

    public int getIsProfit() {
        return isProfit;
    }

    public void setIsProfit(int isProfit) {
        this.isProfit = isProfit;
    }
}
