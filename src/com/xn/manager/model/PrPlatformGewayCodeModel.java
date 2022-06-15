package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class PrPlatformGewayCodeModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 自增ID
     *
     * @mbggenerated
     */
    private Long idList;

    /**
     * 平台通道码名称
     *
     * @mbggenerated
     */
    private String codeName;

    /**
     * 平台通道编码
     *
     * @mbggenerated
     */
    private String pfGewayCode;

    /**
     * 费率类型：1固定费率，2额外费率
     *
     * @mbggenerated
     */
    private Integer serviceChargeType;

    /**
     * 费率
     *
     * @mbggenerated
     */
    private String serviceCharge;

    /**
     * 费率之额外费率：每单还要收取额外的手续费
     *
     * @mbggenerated
     */
    private String extraServiceCharge;

    /**
     * 平台通道码类型：1代收，2代付
     *
     * @mbggenerated
     */
    private Integer pfGewayCodeType;

    /**
     * 支持金额
     *
     * @mbggenerated
     */
    private String moneyRange;

    /**
     * 是否要进行跳转：1不跳转，2跳转
     */
    private Integer isJump;

    /**
     * 跳转到指定的地址：这里的值是策略管理里面的key值
     */
    private String jumpAds;

    /**
     * redis缓存时间（单位秒）：要跳转时token储存在redis的时长
     */
    private Long redisTime;

    /**
     * 是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
     *
     * @mbggenerated
     */
    private Integer isEnable;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * TS时间
     *
     * @mbggenerated
     */
    private Date tsTime;

    /**
     * 是否有效：0有效，1无效/删除
     *
     * @mbggenerated
     */
    private Integer yn;

    /**
     * SQL查询使用
     */
    private long notId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



    public String getMoneyRange() {
        return moneyRange;
    }

    public void setMoneyRange(String moneyRange) {
        this.moneyRange = moneyRange;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTsTime() {
        return tsTime;
    }

    public void setTsTime(Date tsTime) {
        this.tsTime = tsTime;
    }

    public Integer getServiceChargeType() {
        return serviceChargeType;
    }

    public void setServiceChargeType(Integer serviceChargeType) {
        this.serviceChargeType = serviceChargeType;
    }

    public Integer getPfGewayCodeType() {
        return pfGewayCodeType;
    }

    public void setPfGewayCodeType(Integer pfGewayCodeType) {
        this.pfGewayCodeType = pfGewayCodeType;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public Long getIdList() {
        return idList;
    }

    public void setIdList(Long idList) {
        this.idList = idList;
    }

    public long getNotId() {
        return notId;
    }

    public void setNotId(long notId) {
        this.notId = notId;
    }

    public Integer getIsJump() {
        return isJump;
    }

    public void setIsJump(Integer isJump) {
        this.isJump = isJump;
    }

    public String getJumpAds() {
        return jumpAds;
    }

    public void setJumpAds(String jumpAds) {
        this.jumpAds = jumpAds;
    }

    public Long getRedisTime() {
        return redisTime;
    }

    public void setRedisTime(Long redisTime) {
        this.redisTime = redisTime;
    }
}