package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.List;


public class PrGewayCodeModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private long id;

    /**
     * 归属通道ID
     *
     * @mbggenerated
     */
    private long gewayId;
    /**
     * 自增ID
     *
     * @mbggenerated
     */
    private List<Long> idList;
    /**
     * 通道码名称
     *
     * @mbggenerated
     */
    private String gewayCodeName;

    /**
     * 我方通道码：我方给定编号的通道码，因为如果直接根据上游通道码拉单，不排除上游通道码有重复。所以这里设定了我方通道码
     *
     * @mbggenerated
     */
    private String myGewayCode;
    private String gewayName;

    /**
     * 通道拼接接受字符串
     */
    private String gewayNameStr;

    /**
     * 通道码/上游通道码
     *
     * @mbggenerated
     */
    private String gewayCode;

    /**
     * 接口地址
     *
     * @mbggenerated
     */
    private String interfaceAds;

    /**
     * 同步的接口地址:我方的同步地址
     *
     * @mbggenerated
     */
    private String notifyUrl;

    /**
     * 请求标识
     *
     * @mbggenerated
     */
    private String sendTag;

    /**
     * 成功数据回传标识
     *
     * @mbggenerated
     */
    private String sucTag;

    /**
     * 总额不扣费率
     *
     * @mbggenerated
     */
    private String bigTotalMoney;

    /**
     * 总额扣费率
     *
     * @mbggenerated
     */
    private String totalMoney;

    /**
     * 当日跑量金额不扣费率
     *
     * @mbggenerated
     */
    private String bigTadayMoney;

    /**
     * 当日跑量金额扣费率
     *
     * @mbggenerated
     */
    private String tadayMoney;

    /**
     * 上游费率类型：1固定费率，2额外费率
     *
     * @mbggenerated
     */
    private int  upServiceChargeType;

    /**
     * 上游费率
     *
     * @mbggenerated
     */
    private String upServiceCharge;

    /**
     * 上游费率之额外费率：每单还要收取额外的手续费；当字段up_service_charge_type等于2时，则要用到此字段
     *
     * @mbggenerated
     */
    private String upExtraServiceCharge;

    /**
     * 平台费率类型（只做参考）：1固定费率，2额外费率
     *
     * @mbggenerated
     */
    private int serviceChargeType;

    /**
     * 平台费率（只做参考）
     *
     * @mbggenerated
     */
    private String serviceCharge;

    /**
     * 平台费率之额外费率（只做参考）：每单还要收取额外的手续费
     *
     * @mbggenerated
     */
    private String extraServiceCharge;

    /**
     * 支持金额类型：1固定的，2单一范围，3多个范围
     *
     * @mbggenerated
     */
    private int moneyType;

    /**
     * 支持金额:money_type=1则50多个则以英文逗号风格，money_type=2则100-1000；money_type=3则100-1000,200-2000多个以英文逗号分割
     *
     * @mbggenerated
     */
    private String moneyRange;

    /**
     * 放量时间：值1表示全天关闭；具体时间段则表示在此时间段属于放量时间；值3表示全天开放
     *
     * @mbggenerated
     */
    private String openTime;

    /**
     * 每日限制放量的金额
     *
     * @mbggenerated
     */
    private String dayLimitMoney;

    /**
     * 属性类型：1全类型，2Android，3IOS
     */
    private int codeAttributeType;

    /**
     * 白名单IP：多个以英文逗号分割
     */
    private String whiteListIp;

    /**
     * 接收同步数据的身份key
     */
    private String identityKey;

    /**
     * 请求身份：手动接通道的请求标识
     */
    private String sendIdentity;

    /**
     * 是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
     *
     * @mbggenerated
     */
    private int isEnable;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * TS时间
     *
     * @mbggenerated
     */
    private String tsTime;

    /**
     * 是否有效：0有效，1无效/删除
     *
     * @mbggenerated
     */
    private int yn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGewayId() {
        return gewayId;
    }

    public void setGewayId(long gewayId) {
        this.gewayId = gewayId;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getGewayCodeName() {
        return gewayCodeName;
    }

    public void setGewayCodeName(String gewayCodeName) {
        this.gewayCodeName = gewayCodeName;
    }

    public String getMyGewayCode() {
        return myGewayCode;
    }

    public void setMyGewayCode(String myGewayCode) {
        this.myGewayCode = myGewayCode;
    }

    public String getGewayName() {
        return gewayName;
    }

    public void setGewayName(String gewayName) {
        this.gewayName = gewayName;
    }

    public String getGewayCode() {
        return gewayCode;
    }

    public void setGewayCode(String gewayCode) {
        this.gewayCode = gewayCode;
    }

    public String getInterfaceAds() {
        return interfaceAds;
    }

    public void setInterfaceAds(String interfaceAds) {
        this.interfaceAds = interfaceAds;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSendTag() {
        return sendTag;
    }

    public void setSendTag(String sendTag) {
        this.sendTag = sendTag;
    }

    public String getSucTag() {
        return sucTag;
    }

    public void setSucTag(String sucTag) {
        this.sucTag = sucTag;
    }

    public String getBigTotalMoney() {
        return bigTotalMoney;
    }

    public void setBigTotalMoney(String bigTotalMoney) {
        this.bigTotalMoney = bigTotalMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getBigTadayMoney() {
        return bigTadayMoney;
    }

    public void setBigTadayMoney(String bigTadayMoney) {
        this.bigTadayMoney = bigTadayMoney;
    }

    public String getTadayMoney() {
        return tadayMoney;
    }

    public void setTadayMoney(String tadayMoney) {
        this.tadayMoney = tadayMoney;
    }

    public int getUpServiceChargeType() {
        return upServiceChargeType;
    }

    public void setUpServiceChargeType(int upServiceChargeType) {
        this.upServiceChargeType = upServiceChargeType;
    }

    public String getUpServiceCharge() {
        return upServiceCharge;
    }

    public void setUpServiceCharge(String upServiceCharge) {
        this.upServiceCharge = upServiceCharge;
    }

    public String getUpExtraServiceCharge() {
        return upExtraServiceCharge;
    }

    public void setUpExtraServiceCharge(String upExtraServiceCharge) {
        this.upExtraServiceCharge = upExtraServiceCharge;
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

    public int getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(int moneyType) {
        this.moneyType = moneyType;
    }

    public String getMoneyRange() {
        return moneyRange;
    }

    public void setMoneyRange(String moneyRange) {
        this.moneyRange = moneyRange;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getDayLimitMoney() {
        return dayLimitMoney;
    }

    public void setDayLimitMoney(String dayLimitMoney) {
        this.dayLimitMoney = dayLimitMoney;
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

    public String getTsTime() {
        return tsTime;
    }

    public void setTsTime(String tsTime) {
        this.tsTime = tsTime;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }


    public int getCodeAttributeType() {
        return codeAttributeType;
    }

    public void setCodeAttributeType(int codeAttributeType) {
        this.codeAttributeType = codeAttributeType;
    }

    public String getWhiteListIp() {
        return whiteListIp;
    }

    public void setWhiteListIp(String whiteListIp) {
        this.whiteListIp = whiteListIp;
    }

    public String getIdentityKey() {
        return identityKey;
    }

    public void setIdentityKey(String identityKey) {
        this.identityKey = identityKey;
    }

    public String getSendIdentity() {
        return sendIdentity;
    }

    public void setSendIdentity(String sendIdentity) {
        this.sendIdentity = sendIdentity;
    }

    public String getGewayNameStr() {
        return gewayNameStr;
    }

    public void setGewayNameStr(String gewayNameStr) {
        this.gewayNameStr = gewayNameStr;
    }
}