package com.xn.manager.model.channel;



import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @Description 渠道的实体属性Bean
 * @Author yoko
 * @Date 2020/3/2 17:24
 * @Version 1.0
 */
public class ChannelModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 12331523301145L;

    public ChannelModel(){

    }

    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 账号
     */
    private String accountNum;

    /**
     * 账号密码
     */
    private String passWd;

    /**
     * 提款密码
     */
    private String withdrawPassWd;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     *渠道号（商铺号）
     */
    private String channel;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系人电话
     */
    private String phoneNum;

    /**
     * 总账
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
     * 秘钥key
     */
    private String secretKey;

    /**
     * 是否需要谷歌验证：1不需要，2需要
     */
    private int isGoogle;

    /**
     * 谷歌唯一码
     */
    private String googleKey;

    /**
     * 同步的接口地址
     */
    private String lowerUrl;

    /**
     * 返回的成功标识
     */
    private String lowerSuc;

    /**
     * 白名单IP：多个以英文逗号分割
     */
    private String whiteListIp;

    /**
     * 是否需要数据同步:1需要同步，2不需要同步
     */
    private int isSynchro;


    /**
     * 数据发送类型:1get，2post/form，3post/json
     */
    private Integer sendDataType;


    /**
     * 提现类型：1默认在支付平台操作，2发送下发数据到蛋糕平台
     */
    private int withdrawType;

    /**
     * 渠道类型：1代收，2代付，3其它
     */
    private int channelType;

    /**
     * 所属角色ID
     */
    private long roleId;

    /**
     * 是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
     */
    private int isEnable;

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
     * 重置登录密码
     */
    private String resetPassWd;

    /**
     * 重置提现密码
     */
    private String resetWithdrawPassWd;

    private String roleName;

    /**
     * 求和的总账
     */
    private String totalTotalMoney;

    /**
     * 求和的总余额
     */
    private String totalBalance;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getIsGoogle() {
        return isGoogle;
    }

    public void setIsGoogle(int isGoogle) {
        this.isGoogle = isGoogle;
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

    public int getIsSynchro() {
        return isSynchro;
    }

    public void setIsSynchro(int isSynchro) {
        this.isSynchro = isSynchro;
    }

    public int getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(int withdrawType) {
        this.withdrawType = withdrawType;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public String getResetPassWd() {
        return resetPassWd;
    }

    public void setResetPassWd(String resetPassWd) {
        this.resetPassWd = resetPassWd;
    }

    public String getResetWithdrawPassWd() {
        return resetWithdrawPassWd;
    }

    public void setResetWithdrawPassWd(String resetWithdrawPassWd) {
        this.resetWithdrawPassWd = resetWithdrawPassWd;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getSendDataType() {
        return sendDataType;
    }

    public void setSendDataType(Integer sendDataType) {
        this.sendDataType = sendDataType;
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
}
