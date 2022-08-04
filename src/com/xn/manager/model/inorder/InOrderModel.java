package com.xn.manager.model.inorder;



import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @Description 任务订单的实体属性Bean
 * @Author yoko
 * @Date 2020/5/21 19:27
 * @Version 1.0
 */
public class InOrderModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1203223201121L;

    public InOrderModel(){

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
     * 上游订单号
     */
    private String tradeNo;

    /**
     * 渠道的主键ID
     */
    private long channelId;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 平台通道编码ID：对应tb_pr_platform_geway_code表的主键ID
     */
    private long pfGewayCodeId;

    /**
     * 平台通道编码
     */
    private String pfGewayCode;

    /**
     * 平台通道码名称
     */
    private String codeName;

    /**
     * 通道ID
     */
    private long gewayId;

    /**
     * 通道名称
     */
    private String gewayName;

    /**
     * 通道码ID：对应tb_pr_geway_code表的主键ID
     */
    private long gewayCodeId;

    /**
     * 通道码
     */
    private String gewayCode;

    /**
     * 通道码名称
     */
    private String gewayCodeName;

    /**
     * 渠道号/商铺号
     */
    private String channel;

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
     * 拉单码类型：1根据平台通道码拉单，2根据通道码拉单
     */
    private int pullOrderCodeType;

    /**
     * 异步通知地址/渠道
     */
    private String notifyUrl;

    /**
     * 我方异步通知地址
     */
    private String myNotifyUrl;

    /**
     * 接口版本
     */
    private String interfaceVer;

    /**
     * 参数名称：页面跳转同步通知地址；支付成功后，通过页面跳转的方式跳转到商家网站
     */
    private String returnUrl;

    /**
     * 参数名称：回传参数商户如果支付请求是传递了该参数，则通知商户支付成功时会回传该参数
     */
    private String extraReturnParam;

    /**
     * 发起拉单服务端IP
     */
    private String serviceIp;

    /**
     * 客户端IP地址
     */
    private String clientIp;

    /**
     * 参数名称：平台返回签名数据该参数用于验签
     */
    private String sign;

    /**
     * 提交时间
     */
    private String subTime;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * productCode
     */
    private String productCode;

    /**
     * 订单的回执时间
     */
    private String tradeTime;

    /**
     * 是否是补单：1初始化不是补单，2是补单
     */
    private int replenishType;

    /**
     * 是否用于计算收益：1不计算收益，2计算收益
     */
    private int isProfit;

    /**
     * 数据说明：做解说用的
     */
    private String dataExplain;

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
     *运行计算次数
     */
    private int runNum;

    /**
     * 运行计算状态：0初始化，1锁定，2计算失败，3计算成功
     */
    private int runStatus;

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

    private String createTimeStart;
    private String createTimeEnd;




    private String orderStatusStr;// 订单状态：1初始化，2超时/失败，3有质疑，4成功
    private String pullOrderStatusStr;// 订单拉单状态：1拉单成功，2拉单失败
    private String pullOrderCodeTypeStr;// 拉单码类型：1根据平台通道码拉单，2根据通道码拉单
    private String replenishTypeStr;// 是否是补单：1初始化不是补单，2是补单
    private String isProfitStr;// 是否用于计算收益：1不计算收益，2计算收益
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

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public long getPfGewayCodeId() {
        return pfGewayCodeId;
    }

    public void setPfGewayCodeId(long pfGewayCodeId) {
        this.pfGewayCodeId = pfGewayCodeId;
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

    public long getGewayId() {
        return gewayId;
    }

    public void setGewayId(long gewayId) {
        this.gewayId = gewayId;
    }

    public String getGewayName() {
        return gewayName;
    }

    public void setGewayName(String gewayName) {
        this.gewayName = gewayName;
    }

    public long getGewayCodeId() {
        return gewayCodeId;
    }

    public void setGewayCodeId(long gewayCodeId) {
        this.gewayCodeId = gewayCodeId;
    }

    public String getGewayCode() {
        return gewayCode;
    }

    public void setGewayCode(String gewayCode) {
        this.gewayCode = gewayCode;
    }

    public String getGewayCodeName() {
        return gewayCodeName;
    }

    public void setGewayCodeName(String gewayCodeName) {
        this.gewayCodeName = gewayCodeName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public int getPullOrderCodeType() {
        return pullOrderCodeType;
    }

    public void setPullOrderCodeType(int pullOrderCodeType) {
        this.pullOrderCodeType = pullOrderCodeType;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMyNotifyUrl() {
        return myNotifyUrl;
    }

    public void setMyNotifyUrl(String myNotifyUrl) {
        this.myNotifyUrl = myNotifyUrl;
    }

    public String getInterfaceVer() {
        return interfaceVer;
    }

    public void setInterfaceVer(String interfaceVer) {
        this.interfaceVer = interfaceVer;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getExtraReturnParam() {
        return extraReturnParam;
    }

    public void setExtraReturnParam(String extraReturnParam) {
        this.extraReturnParam = extraReturnParam;
    }

    public String getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSubTime() {
        return subTime;
    }

    public void setSubTime(String subTime) {
        this.subTime = subTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public int getIsProfit() {
        return isProfit;
    }

    public void setIsProfit(int isProfit) {
        this.isProfit = isProfit;
    }

    public String getDataExplain() {
        return dataExplain;
    }

    public void setDataExplain(String dataExplain) {
        this.dataExplain = dataExplain;
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

    public String getPullOrderCodeTypeStr() {
        return pullOrderCodeTypeStr;
    }

    public void setPullOrderCodeTypeStr(String pullOrderCodeTypeStr) {
        this.pullOrderCodeTypeStr = pullOrderCodeTypeStr;
    }

    public String getReplenishTypeStr() {
        return replenishTypeStr;
    }

    public void setReplenishTypeStr(String replenishTypeStr) {
        this.replenishTypeStr = replenishTypeStr;
    }

    public String getIsProfitStr() {
        return isProfitStr;
    }

    public void setIsProfitStr(String isProfitStr) {
        this.isProfitStr = isProfitStr;
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

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
