package com.xn.manager.model.channel;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description: 渠道账号实体属性Bean
 * <p>
 *     只做登录使用
 * </p>
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class AccountChannelModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1233223301100L;

    public AccountChannelModel(){

    }
    private long id; //账号ID
    private String accountNum; //账号
    private String passWd; //密码
    private long roleId; //所属角色ID
    private String acName;//账号昵称
    private String acContacts;//账号联系人
    private String acPhone;//联系电话
    private int acType;//类型:目前没用到
    private String remark;//备注
    private int isEnable;//是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
    private String createTime;//创建时间
    private String updateTime;//更新时间
    private int yn;//是否有效：0初始化，1失效/删除
    private String roleName; //所属的角色名称

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

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcContacts() {
        return acContacts;
    }

    public void setAcContacts(String acContacts) {
        this.acContacts = acContacts;
    }

    public String getAcPhone() {
        return acPhone;
    }

    public void setAcPhone(String acPhone) {
        this.acPhone = acPhone;
    }

    public int getAcType() {
        return acType;
    }

    public void setAcType(int acType) {
        this.acType = acType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
