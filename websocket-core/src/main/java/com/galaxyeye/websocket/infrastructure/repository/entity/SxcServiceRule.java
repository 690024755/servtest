package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class SxcServiceRule implements Serializable {
	private static final long serialVersionUID = 8573596627914889507L;
	private Integer id;

    private Integer cityCode;

    private Integer pickhouseId;

    private String goodsGroupIds;

    private String catIds;

    private String chargeStandard;

    private Date gmtStartTime;

    private Date gmtEndTime;

    private Boolean enabled;

    private String ruleGroupId;

    private Date gmtCreateTime;

    private Date gmtModifyTime;

    private Integer creatorId;

    private Integer modifierId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getPickhouseId() {
        return pickhouseId;
    }

    public void setPickhouseId(Integer pickhouseId) {
        this.pickhouseId = pickhouseId;
    }

    public String getGoodsGroupIds() {
        return goodsGroupIds;
    }

    public void setGoodsGroupIds(String goodsGroupIds) {
        this.goodsGroupIds = goodsGroupIds == null ? null : goodsGroupIds.trim();
    }

    public String getCatIds() {
        return catIds;
    }

    public void setCatIds(String catIds) {
        this.catIds = catIds == null ? null : catIds.trim();
    }

    public String getChargeStandard() {
        return chargeStandard;
    }

    public void setChargeStandard(String chargeStandard) {
        this.chargeStandard = chargeStandard == null ? null : chargeStandard.trim();
    }

    public Date getGmtStartTime() {
        return gmtStartTime;
    }

    public void setGmtStartTime(Date gmtStartTime) {
        this.gmtStartTime = gmtStartTime;
    }

    public Date getGmtEndTime() {
        return gmtEndTime;
    }

    public void setGmtEndTime(Date gmtEndTime) {
        this.gmtEndTime = gmtEndTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRuleGroupId() {
        return ruleGroupId;
    }

    public void setRuleGroupId(String ruleGroupId) {
        this.ruleGroupId = ruleGroupId == null ? null : ruleGroupId.trim();
    }

    public Date getGmtCreateTime() {
        return gmtCreateTime;
    }

    public void setGmtCreateTime(Date gmtCreateTime) {
        this.gmtCreateTime = gmtCreateTime;
    }

    public Date getGmtModifyTime() {
        return gmtModifyTime;
    }

    public void setGmtModifyTime(Date gmtModifyTime) {
        this.gmtModifyTime = gmtModifyTime;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }
}