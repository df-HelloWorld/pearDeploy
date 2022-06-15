package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class PrPlatformGewayCodeLinkModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;
    private String relationType;
    /**
     * 别名
     *
     * @mbggenerated
     */
    private String alias;
    /**
     * 平台通道码名称
     *
     * @mbggenerated
     */
    private String gewayCodeNames;
    private String gewayName;
    private String myGewayCode;
    /**
     * 通道码名称
     *
     * @mbggenerated
     */
    private String codeNames;

    /**
     * 平台通道编码ID：对应tb_pr_platform_geway_code表的主键ID
     *
     * @mbggenerated
     */
    private Long pfGewayCodeId;

    /**
     * 通道码ID：对应tb_pr_geway_code表的主键ID
     *
     * @mbggenerated
     */
    private Long gewayCodeId;

    /**
     * 类型ID：relationTypeId
     *
     * @mbggenerated
     */
    private Long relationTypeId;
    private String pfGewayCodeName;
    private String gewayCodeName;
    private String codeName;

    /**
     * 权重
     *
     * @mbggenerated
     */
    private Integer ratio;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getPfGewayCodeId() {
        return pfGewayCodeId;
    }

    public void setPfGewayCodeId(Long pfGewayCodeId) {
        this.pfGewayCodeId = pfGewayCodeId;
    }

    public Long getGewayCodeId() {
        return gewayCodeId;
    }

    public void setGewayCodeId(Long gewayCodeId) {
        this.gewayCodeId = gewayCodeId;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
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

    public String getGewayCodeNames() {
        return gewayCodeNames;
    }

    public void setGewayCodeNames(String gewayCodeNames) {
        this.gewayCodeNames = gewayCodeNames;
    }

    public String getCodeNames() {
        return codeNames;
    }

    public void setCodeNames(String codeNames) {
        this.codeNames = codeNames;
    }

    public Long getRelationTypeId() {
        return relationTypeId;
    }

    public void setRelationTypeId(Long relationTypeId) {
        this.relationTypeId = relationTypeId;
    }

    public String getPfGewayCodeName() {
        return pfGewayCodeName;
    }

    public void setPfGewayCodeName(String pfGewayCodeName) {
        this.pfGewayCodeName = pfGewayCodeName;
    }

    public String getGewayCodeName() {
        return gewayCodeName;
    }

    public void setGewayCodeName(String gewayCodeName) {
        this.gewayCodeName = gewayCodeName;
    }

    public String getGewayName() {
        return gewayName;
    }

    public void setGewayName(String gewayName) {
        this.gewayName = gewayName;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getMyGewayCode() {
        return myGewayCode;
    }

    public void setMyGewayCode(String myGewayCode) {
        this.myGewayCode = myGewayCode;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}