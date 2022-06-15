package com.xn.manager.model.agent;


import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description: 代理提现记录的实体属性Bean
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class AgentWithdrawModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 120322330561149L;

    public AgentWithdrawModel(){

    }
    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 提现订单号
     */
    private String orderNo;

    /**
     * 归属代理ID
     */
    private long agentId;

    /**
     * 提现金额
     */
    private String money;

    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 提现银行名称
     */
    private String bankName;

    /**
     * 支行名称
     */
    private String subbranchName;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 提现银行卡卡号
     */
    private String bankCard;

    /**
     * 提现状态:1提现中，2驳回/提现失败，3提现成功
     */
    private int withdrawStatus;

    /**
     * 充值记录银行卡转账图片凭证
     */
    private String pictureAds;

    /**
     * 渠道提现当时的余额
     */
    private String balance;

    /**
     * 说明:提现失败的情况说明
     */
    private String withdrawExplain;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期
     */
    private int curday;

    /**
     *运行计算次数
     */
    private int runNum;

    /**
     * 运行计算状态：：0初始化，1锁定，2计算失败，3计算成功
     */
    private int runStatus;

    /**
     * 发送次数
     *
     * @mbggenerated
     */
    private int sendNum;

    /**
     * 发送状态：0初始化，1锁定，2计算失败，3计算成功
     *
     * @mbggenerated
     */
    private int sendStatus;

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

    private int curdayStart;
    private int curdayEnd;

    /**
     * 代理银行卡ID
     */
    private long agentBankId;

    /**
     * 审核状态
     */
    private int checkWithdrawStatus;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubbranchName() {
        return subbranchName;
    }

    public void setSubbranchName(String subbranchName) {
        this.subbranchName = subbranchName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public int getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(int withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public String getPictureAds() {
        return pictureAds;
    }

    public void setPictureAds(String pictureAds) {
        this.pictureAds = pictureAds;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getWithdrawExplain() {
        return withdrawExplain;
    }

    public void setWithdrawExplain(String withdrawExplain) {
        this.withdrawExplain = withdrawExplain;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public int getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(int runStatus) {
        this.runStatus = runStatus;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
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

    public int getCurdayStart() {
        return curdayStart;
    }

    public void setCurdayStart(int curdayStart) {
        this.curdayStart = curdayStart;
    }

    public int getCurdayEnd() {
        return curdayEnd;
    }

    public void setCurdayEnd(int curdayEnd) {
        this.curdayEnd = curdayEnd;
    }

    public long getAgentBankId() {
        return agentBankId;
    }

    public void setAgentBankId(long agentBankId) {
        this.agentBankId = agentBankId;
    }

    public int getCheckWithdrawStatus() {
        return checkWithdrawStatus;
    }

    public void setCheckWithdrawStatus(int checkWithdrawStatus) {
        this.checkWithdrawStatus = checkWithdrawStatus;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
    }
}
