package com.xn.manager.model.inorder.vo;



import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @Description 视图层：代收订单的实体属性Bean
 * @Author yoko
 * @Date 2020/5/21 19:27
 * @Version 1.0
 */
public class InOrderVo extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1203223201121L;

    public InOrderVo(){

    }

    /**
     * 主键ID
     */
    private long id;


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
     * 平台通道编码
     */
    private String pfGewayCode;

    /**
     * 平台通道码名称
     */
    private String codeName;

    /**
     * 参数名称：商家订单金额，订单总金额
     */
    private String totalAmount;

    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 实际金额
     */
    private String actualMoney;

    /**
     * 订单状态：1初始化，2超时/失败，3有质疑，4成功
     */
    private int orderStatus;

    /**
     * 订单拉单状态：1拉单成功，2拉单失败
     */
    private int pullOrderStatus;

    /**
     * 订单的回执时间
     */
    private String tradeTime;

    /**
     * 是否是补单：1初始化不是补单，2是补单
     */
    private int replenishType;


    /**
     * 创建日期：存的日期格式20160530
     */
    private int curday;


    /**
     *发送次数
     */
    private int sendNum;

    /**
     * 发送状态：0初始化，1锁定，2计算失败，3计算成功
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

    private int curdayStart;
    private int curdayEnd;


    private String orderStatusStr;// 订单状态：1初始化，2超时/失败，3有质疑，4成功
    private String pullOrderStatusStr;// 订单拉单状态：1拉单成功，2拉单失败
    private String replenishTypeStr;// 是否是补单：1初始化不是补单，2是补单
    private String sendStatusStr;// 发送状态：0初始化，1锁定，2计算失败，3计算成功

    /**
     * 总的订单金额
     */
    private String totalMoney;

    /**
     * 总的成功订单金额
     */
    private String totalSucMoney;

    /**
     * 总的成功金额比例
     * totalSucMoney/totalMoney=totalMoneyRatio
     */
    private String totalMoneyRatio;

    /**
     * 总的实际金额
     */
    private String totalActualMoney;

    /**
     * 总的成功实际金额
     */
    private String totalSucActualMoney;

    /**
     * 总的实际金额手续费
     * totalActualMoney-totalSucActualMoney=totalActualServiceCharge
     */
    private String totalActualServiceCharge;

    /**
     * 总的实际金额比例
     * totalSucActualMoney/totalActualMoney=totalActualRatio
     */
    private String totalActualRatio;

    /**
     * 总数据条数
     */
    private int totalNum;

    /**
     * 总成功数据条数
     */
    private int totalSucNum;

    /**
     * 订单条数的成功率
     * totalSucNum/totalNum=totalNumRatio
     */
    private String totalNumRatio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPfGewayCode() {
        return pfGewayCode;
    }

    public void setPfGewayCode(String pfGewayCode) {
        this.pfGewayCode = pfGewayCode;
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

    public String getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(String actualMoney) {
        this.actualMoney = actualMoney;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPullOrderStatus() {
        return pullOrderStatus;
    }

    public void setPullOrderStatus(int pullOrderStatus) {
        this.pullOrderStatus = pullOrderStatus;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public int getReplenishType() {
        return replenishType;
    }

    public void setReplenishType(int replenishType) {
        this.replenishType = replenishType;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
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

    public String getOrderStatusStr() {
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getPullOrderStatusStr() {
        return pullOrderStatusStr;
    }

    public void setPullOrderStatusStr(String pullOrderStatusStr) {
        this.pullOrderStatusStr = pullOrderStatusStr;
    }

    public String getReplenishTypeStr() {
        return replenishTypeStr;
    }

    public void setReplenishTypeStr(String replenishTypeStr) {
        this.replenishTypeStr = replenishTypeStr;
    }

    public String getSendStatusStr() {
        return sendStatusStr;
    }

    public void setSendStatusStr(String sendStatusStr) {
        this.sendStatusStr = sendStatusStr;
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

    public String getTotalActualMoney() {
        return totalActualMoney;
    }

    public void setTotalActualMoney(String totalActualMoney) {
        this.totalActualMoney = totalActualMoney;
    }

    public String getTotalSucActualMoney() {
        return totalSucActualMoney;
    }

    public void setTotalSucActualMoney(String totalSucActualMoney) {
        this.totalSucActualMoney = totalSucActualMoney;
    }

    public String getTotalActualServiceCharge() {
        return totalActualServiceCharge;
    }

    public void setTotalActualServiceCharge(String totalActualServiceCharge) {
        this.totalActualServiceCharge = totalActualServiceCharge;
    }

    public String getTotalActualRatio() {
        return totalActualRatio;
    }

    public void setTotalActualRatio(String totalActualRatio) {
        this.totalActualRatio = totalActualRatio;
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
}
