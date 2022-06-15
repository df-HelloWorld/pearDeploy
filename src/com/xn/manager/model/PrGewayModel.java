package com.xn.manager.model;

import com.xn.common.page.BasePage;


public class PrGewayModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private long id;

    /**
     * 通道名称
     *
     * @mbggenerated
     */
    private String gewayName;

    /**
     * 秘钥key：分配给我方的秘钥
     *
     * @mbggenerated
     */
    private String secretKey;

    /**
     * 商铺号（商家号）
     *
     * @mbggenerated
     */
    private String payId;

    /**
     * 通道属性：1代收，2代付
     *
     * @mbggenerated
     */
    private int attributeType;

    /**
     * 总账
     *
     * @mbggenerated
     */
    private String totalMoney;

    /**
     * 保底金额：预付款通道，如果余额少于保底金额，就不出码
     *
     * @mbggenerated
     */
    private String leastMoney;

    /**
     * 余额
     *
     * @mbggenerated
     */
    private String balance;

    /**
     * 锁定金额
     *
     * @mbggenerated
     */
    private String lockMoney;

    /**
     * 通道类型：1普通通道，2预付款通道
     *
     * @mbggenerated
     */
    private int gewayType;

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
     */
    private int yn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGewayName() {
        return gewayName;
    }

    public void setGewayName(String gewayName) {
        this.gewayName = gewayName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public int getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(int attributeType) {
        this.attributeType = attributeType;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getLeastMoney() {
        return leastMoney;
    }

    public void setLeastMoney(String leastMoney) {
        this.leastMoney = leastMoney;
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

    public int getGewayType() {
        return gewayType;
    }

    public void setGewayType(int gewayType) {
        this.gewayType = gewayType;
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
}