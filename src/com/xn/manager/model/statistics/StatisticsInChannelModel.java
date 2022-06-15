package com.xn.manager.model.statistics;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @author yoko
 * @desc 统计代收渠道的每日记录的实体属性Bean
 * @create 2021-12-30 16:35
 **/
public class StatisticsInChannelModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 12931423301145L;

    public StatisticsInChannelModel(){

    }

    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 归属渠道ID
     */
    private long channelId;

    /**
     * 总额
     */
    private String totalMoney;

    /**
     * 余额
     */
    private String balance;

    /**
     * 锁定金额
     */
    private String lockMoney;

    /**
     * 跑量金额
     */
    private String totalAmount;

    /**
     * 实际金额
     */
    private String actualMoney;

    /**
     * 提现中的金额
     */
    private String withdrawIngMoney;

    /**
     * 提现成功金额
     */
    private String withdrawSucMoney;

    /**
     * 提现失败金额：驳回金额
     */
    private String withdrawFailMoney;

    /**
     * 加金额
     */
    private String addMoney;

    /**
     * 减金额
     */
    private String reduceMoney;

    /**
     * 创建日期：存的日期格式20160530
     */
    private int curday;

    /**
     * 创建所属小时：24小时制
     */
    private int curhour;

    /**
     * 创建所属分钟：60分钟制
     */
    private int curminute;


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
     * 汇总-总额
     */
    private String totalTotalMoney;

    /**
     * 汇总-余额
     */
    private String totalBalance;

    /**
     * 汇总-锁定金额
     */
    private String totalLockMoney;

    /**
     * 汇总-跑量金额
     */
    private String totalTotalAmount;

    /**
     * 汇总-实际金额
     */
    private String totalActualMoney;

    /**
     * 汇总-提现中的金额
     */
    private String totalWithdrawIngMoney;

    /**
     * 汇总-提现成功金额
     */
    private String totalWithdrawSucMoney;

    /**
     * 汇总-提现失败金额：驳回金额
     */
    private String totalWithdrawFailMoney;

    /**
     * 汇总-加金额
     */
    private String totalAddMoney;

    /**
     * 汇总-减金额
     */
    private String totalReduceMoney;


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

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLockMoney() {
        return lockMoney;
    }

    public void setLockMoney(String lockMoney) {
        this.lockMoney = lockMoney;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(String actualMoney) {
        this.actualMoney = actualMoney;
    }

    public String getWithdrawIngMoney() {
        return withdrawIngMoney;
    }

    public void setWithdrawIngMoney(String withdrawIngMoney) {
        this.withdrawIngMoney = withdrawIngMoney;
    }

    public String getWithdrawSucMoney() {
        return withdrawSucMoney;
    }

    public void setWithdrawSucMoney(String withdrawSucMoney) {
        this.withdrawSucMoney = withdrawSucMoney;
    }

    public String getWithdrawFailMoney() {
        return withdrawFailMoney;
    }

    public void setWithdrawFailMoney(String withdrawFailMoney) {
        this.withdrawFailMoney = withdrawFailMoney;
    }

    public String getAddMoney() {
        return addMoney;
    }

    public void setAddMoney(String addMoney) {
        this.addMoney = addMoney;
    }

    public String getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(String reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
    }

    public int getCurhour() {
        return curhour;
    }

    public void setCurhour(int curhour) {
        this.curhour = curhour;
    }

    public int getCurminute() {
        return curminute;
    }

    public void setCurminute(int curminute) {
        this.curminute = curminute;
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

    public String getTotalTotalMoney() {
        return totalTotalMoney;
    }

    public void setTotalTotalMoney(String totalTotalMoney) {
        this.totalTotalMoney = totalTotalMoney;
    }

    public String getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getTotalLockMoney() {
        return totalLockMoney;
    }

    public void setTotalLockMoney(String totalLockMoney) {
        this.totalLockMoney = totalLockMoney;
    }

    public String getTotalTotalAmount() {
        return totalTotalAmount;
    }

    public void setTotalTotalAmount(String totalTotalAmount) {
        this.totalTotalAmount = totalTotalAmount;
    }

    public String getTotalActualMoney() {
        return totalActualMoney;
    }

    public void setTotalActualMoney(String totalActualMoney) {
        this.totalActualMoney = totalActualMoney;
    }

    public String getTotalWithdrawIngMoney() {
        return totalWithdrawIngMoney;
    }

    public void setTotalWithdrawIngMoney(String totalWithdrawIngMoney) {
        this.totalWithdrawIngMoney = totalWithdrawIngMoney;
    }

    public String getTotalWithdrawSucMoney() {
        return totalWithdrawSucMoney;
    }

    public void setTotalWithdrawSucMoney(String totalWithdrawSucMoney) {
        this.totalWithdrawSucMoney = totalWithdrawSucMoney;
    }

    public String getTotalWithdrawFailMoney() {
        return totalWithdrawFailMoney;
    }

    public void setTotalWithdrawFailMoney(String totalWithdrawFailMoney) {
        this.totalWithdrawFailMoney = totalWithdrawFailMoney;
    }

    public String getTotalAddMoney() {
        return totalAddMoney;
    }

    public void setTotalAddMoney(String totalAddMoney) {
        this.totalAddMoney = totalAddMoney;
    }

    public String getTotalReduceMoney() {
        return totalReduceMoney;
    }

    public void setTotalReduceMoney(String totalReduceMoney) {
        this.totalReduceMoney = totalReduceMoney;
    }
}
