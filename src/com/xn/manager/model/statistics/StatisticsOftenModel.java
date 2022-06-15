package com.xn.manager.model.statistics;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @author yoko
 * @desc 统计：时时的统计
 * @create 2021-12-27 12:01
 **/
public class StatisticsOftenModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 12331923307146L;

    public StatisticsOftenModel(){

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
     * 通道名称
     */
    private String gewayName;

    /**
     * 通道码名称
     */
    private String gewayCodeName;

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
     * 开始时间
     */
    private String startCreateTime;

    /**
     * 结束时间
     */
    private String endCreateTime;

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

    public String getGewayCodeName() {
        return gewayCodeName;
    }

    public void setGewayCodeName(String gewayCodeName) {
        this.gewayCodeName = gewayCodeName;
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

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public int getIsProfit() {
        return isProfit;
    }

    public void setIsProfit(int isProfit) {
        this.isProfit = isProfit;
    }

    public String getGewayName() {
        return gewayName;
    }

    public void setGewayName(String gewayName) {
        this.gewayName = gewayName;
    }
}
