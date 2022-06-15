package com.xn.manager.model.frequently;


import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @author yoko
 * @desc 频繁请求：被抓取到的过于频繁的数据的实体Bean
 * @create 2022-04-12 19:18
 **/
public class FrequentlyModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1233152458309146L;

    public FrequentlyModel(){

    }

    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 渠道的主键ID
     */
    private long channelId;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道号（商铺号）
     */
    private String channel;

    /**
     * 客户端IP地址(用户IP)
     */
    private String clientIp;

    /**
     * 服务端IP
     */
    private String serviceIp;

    /**
     * 请求的json数据
     */
    private String jsonData;

    /**
     * 锁频繁请求的redisKey
     */
    private String lockRedisKey;

    /**
     * 锁释放时间
     */
    private String lockTime;

    /**
     * 锁定类型：1锁IP，2锁渠道号
     */
    private int lockType;

    /**
     * 数据来源的接口：来自那个接口检测到的
     */
    private String fromInterface;

    /**
     * 是否解锁：1被锁，2已解锁
     */
    private int isLock;

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
     * 失效状态：1未失效，2已失效，3未失效+已失效（全部）
     */
    private int invalidStatus;

    /**
     * 失效状态：SQL显示用
     */
    private String invalidStr;


    private int curdayStart;
    private int curdayEnd;

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

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getLockRedisKey() {
        return lockRedisKey;
    }

    public void setLockRedisKey(String lockRedisKey) {
        this.lockRedisKey = lockRedisKey;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public int getLockType() {
        return lockType;
    }

    public void setLockType(int lockType) {
        this.lockType = lockType;
    }

    public String getFromInterface() {
        return fromInterface;
    }

    public void setFromInterface(String fromInterface) {
        this.fromInterface = fromInterface;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
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

    public int getInvalidStatus() {
        return invalidStatus;
    }

    public void setInvalidStatus(int invalidStatus) {
        this.invalidStatus = invalidStatus;
    }

    public String getInvalidStr() {
        return invalidStr;
    }

    public void setInvalidStr(String invalidStr) {
        this.invalidStr = invalidStr;
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
}
