package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class PrChannelModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 归属账号ID
     *
     * @mbggenerated
     */
    private String accountNum;

    /**
     * 账号密码
     *
     * @mbggenerated
     */
    private String passWd;

    /**
     * 提款密码
     *
     * @mbggenerated
     */
    private String withdrawPassWd;

    /**
     * 渠道名称
     *
     * @mbggenerated
     */
    private String channelName;

    /**
     * 渠道号（商铺号）
     *
     * @mbggenerated
     */
    private String channel;

    /**
     * 公司名称
     *
     * @mbggenerated
     */
    private String companyName;

    /**
     * 联系人
     *
     * @mbggenerated
     */
    private String contacts;

    /**
     * 联系人电话
     *
     * @mbggenerated
     */
    private String phoneNum;

    /**
     * 总账
     *
     * @mbggenerated
     */
    private String totalMoney;

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
     * 秘钥key
     *
     * @mbggenerated
     */
    private String secretKey;

    /**
     * 是否需要谷歌验证：1不需要，2需要
     *
     * @mbggenerated
     */
    private Integer isGoogle;

    /**
     * 谷歌唯一码
     *
     * @mbggenerated
     */
    private String googleKey;

    /**
     * 同步的接口地址
     *
     * @mbggenerated
     */
    private String lowerUrl;

    /**
     * 返回的成功标识
     *
     * @mbggenerated
     */
    private String lowerSuc;

    /**
     * 白名单IP：多个以英文逗号分割
     *
     * @mbggenerated
     */
    private String whiteListIp;

    /**
     * 是否需要数据同步:1需要同步，2不需要同步
     *
     * @mbggenerated
     */
    private Integer isSynchro;

    /**
     * 提现类型：1默认在支付平台操作，2发送下发数据到蛋糕平台
     *
     * @mbggenerated
     */
    private Integer withdrawType;

    /**
     * 渠道类型：1代收，2代付，3其它
     *
     * @mbggenerated
     */
    private Integer channelType;

    /**
     * 所属角色ID
     *
     * @mbggenerated
     */
    private Long roleId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public String getWithdrawPassWd() {
        return withdrawPassWd;
    }

    public void setWithdrawPassWd(String withdrawPassWd) {
        this.withdrawPassWd = withdrawPassWd;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public String getGoogleKey() {
        return googleKey;
    }

    public void setGoogleKey(String googleKey) {
        this.googleKey = googleKey;
    }

    public String getLowerUrl() {
        return lowerUrl;
    }

    public void setLowerUrl(String lowerUrl) {
        this.lowerUrl = lowerUrl;
    }

    public String getLowerSuc() {
        return lowerSuc;
    }

    public void setLowerSuc(String lowerSuc) {
        this.lowerSuc = lowerSuc;
    }

    public String getWhiteListIp() {
        return whiteListIp;
    }

    public void setWhiteListIp(String whiteListIp) {
        this.whiteListIp = whiteListIp;
    }

    public Integer getIsGoogle() {
        return isGoogle;
    }

    public void setIsGoogle(Integer isGoogle) {
        this.isGoogle = isGoogle;
    }

    public Integer getIsSynchro() {
        return isSynchro;
    }

    public void setIsSynchro(Integer isSynchro) {
        this.isSynchro = isSynchro;
    }

    public Integer getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(Integer withdrawType) {
        this.withdrawType = withdrawType;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
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

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}