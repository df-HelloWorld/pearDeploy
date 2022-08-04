package com.xn.manager.model.channel.vo;



import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @Description 渠道资金流水的实体属性Bean
 * @Author yoko
 * @Date 2020/5/21 19:27
 * @Version 1.0
 */
public class ChannelFlowingWaterVo extends BasePage implements Serializable {
    private static final long   serialVersionUID = 89120322320112994L;

    public ChannelFlowingWaterVo(){

    }


    /**
     * 我方订单号
     */
    private String myTradeNo;

    /**
     * 参数名称：商家订单号
     */
    private String outTradeNo;


    /**
     * 渠道名称
     */
    private String channelName;


    /**
     * 平台通道码名称
     */
    private String codeName;


    /**
     * 涉及金额
     */
    private String totalAmount;

    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 原金额
     */
    private String oldMoney;

    /**
     * 要变更的金额
     */
    private String changeMoney;

    /**
     * 新金额：变更后的金额
     */
    private String newMoney;


    /**
     * 变更类型：1付款订单，2提现，3提现驳回，4手动增加，5手动减少
     */
    private int changeType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期：存的日期格式20160530
     */
    private int curday;

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

    private int curdayStart;
    private int curdayEnd;


    /**
     * 导出Excel使用
     */
    private String changeTypeStr;

    public String getMyTradeNo() {
        return myTradeNo;
    }

    public void setMyTradeNo(String myTradeNo) {
        this.myTradeNo = myTradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
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

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(String oldMoney) {
        this.oldMoney = oldMoney;
    }

    public String getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(String changeMoney) {
        this.changeMoney = changeMoney;
    }

    public String getNewMoney() {
        return newMoney;
    }

    public void setNewMoney(String newMoney) {
        this.newMoney = newMoney;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
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

    public String getChangeTypeStr() {
        return changeTypeStr;
    }

    public void setChangeTypeStr(String changeTypeStr) {
        this.changeTypeStr = changeTypeStr;
    }
}
