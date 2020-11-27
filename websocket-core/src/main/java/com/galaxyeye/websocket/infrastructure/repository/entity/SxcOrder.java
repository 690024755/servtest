package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class SxcOrder implements Serializable {
	private static final long serialVersionUID = 2308659104867336262L;
	private String orderNo;

    private Integer num;

    private Long totalFee;

    private Integer buyerId;

    private String buyerName;

    private Integer sellerId;

    private String sellerName;

    private Integer payType;

    private Integer orderStatus;

    private Date createTime;

    private Integer state;

    private Integer operatorUserId;

    private String areaCode;

    private Integer pickhouseId;

    private Integer centerhouseId;

    private Date picktime;

    private Integer buyerPickhouseId;

    private Date lastUpdateTime;

    private Date payTime;

    private Integer refundNum;

    private Long discountedFee;

    private Integer deliveryStatus;

    private Integer sellStatus;

    private Long cashBack;

    private Date cashBackTime;

    private Integer isCashBack;

    private Integer orderType;

    private Integer cityCode;

    private String features;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public Integer getPickhouseId() {
        return pickhouseId;
    }

    public void setPickhouseId(Integer pickhouseId) {
        this.pickhouseId = pickhouseId;
    }

    public Integer getCenterhouseId() {
        return centerhouseId;
    }

    public void setCenterhouseId(Integer centerhouseId) {
        this.centerhouseId = centerhouseId;
    }

    public Date getPicktime() {
        return picktime;
    }

    public void setPicktime(Date picktime) {
        this.picktime = picktime;
    }

    public Integer getBuyerPickhouseId() {
        return buyerPickhouseId;
    }

    public void setBuyerPickhouseId(Integer buyerPickhouseId) {
        this.buyerPickhouseId = buyerPickhouseId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Long getDiscountedFee() {
        return discountedFee;
    }

    public void setDiscountedFee(Long discountedFee) {
        this.discountedFee = discountedFee;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    public Long getCashBack() {
        return cashBack;
    }

    public void setCashBack(Long cashBack) {
        this.cashBack = cashBack;
    }

    public Date getCashBackTime() {
        return cashBackTime;
    }

    public void setCashBackTime(Date cashBackTime) {
        this.cashBackTime = cashBackTime;
    }

    public Integer getIsCashBack() {
        return isCashBack;
    }

    public void setIsCashBack(Integer isCashBack) {
        this.isCashBack = isCashBack;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
    }
}