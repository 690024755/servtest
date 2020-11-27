package com.galaxyeye.websocket.infrastructure.repository.entity;


import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
public class SxcFixPriceBaseInfo implements Serializable {
	private static final long serialVersionUID = -6813634565319507089L;
	private Integer id;

    private Integer itemId;

    private Integer skuId;

    private Integer spuId;

    private String spuName;

    private Integer cityCode;

    private Long purchasePrice;

    private Date purchaseTime;

    private Integer purchaserId;

    private String purchaserName;

    private String purchaserMobilePhone;

    private Long purchaseInquiryPrice;

    private Long marketInquiryPrice;

    private Date inquiryTime;

    private Integer inquirierId;

    private String inquirierName;

    private String inquirierMobilePhone;

    private String remark;

    private Integer spareStockNum;

    private Double spareSalesTime;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer state;

    private Long inquiryTaxFee;

    private Integer purchaseOrderType;

    private String marketingActivityInfo;

    private String salesInfo;

    private Integer marketInquiryPriceFlag;

    private Long freight;

    private String marketSalesInfo;

    private String features;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName == null ? null : spuName.trim();
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Integer getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Integer purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName == null ? null : purchaserName.trim();
    }

    public String getPurchaserMobilePhone() {
        return purchaserMobilePhone;
    }

    public void setPurchaserMobilePhone(String purchaserMobilePhone) {
        this.purchaserMobilePhone = purchaserMobilePhone == null ? null : purchaserMobilePhone.trim();
    }

    public Long getPurchaseInquiryPrice() {
        return purchaseInquiryPrice;
    }

    public void setPurchaseInquiryPrice(Long purchaseInquiryPrice) {
        this.purchaseInquiryPrice = purchaseInquiryPrice;
    }

    public Long getMarketInquiryPrice() {
        return marketInquiryPrice;
    }

    public void setMarketInquiryPrice(Long marketInquiryPrice) {
        this.marketInquiryPrice = marketInquiryPrice;
    }

    public Date getInquiryTime() {
        return inquiryTime;
    }

    public void setInquiryTime(Date inquiryTime) {
        this.inquiryTime = inquiryTime;
    }

    public Integer getInquirierId() {
        return inquirierId;
    }

    public void setInquirierId(Integer inquirierId) {
        this.inquirierId = inquirierId;
    }

    public String getInquirierName() {
        return inquirierName;
    }

    public void setInquirierName(String inquirierName) {
        this.inquirierName = inquirierName == null ? null : inquirierName.trim();
    }

    public String getInquirierMobilePhone() {
        return inquirierMobilePhone;
    }

    public void setInquirierMobilePhone(String inquirierMobilePhone) {
        this.inquirierMobilePhone = inquirierMobilePhone == null ? null : inquirierMobilePhone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSpareStockNum() {
        return spareStockNum;
    }

    public void setSpareStockNum(Integer spareStockNum) {
        this.spareStockNum = spareStockNum;
    }

    public Double getSpareSalesTime() {
        return spareSalesTime;
    }

    public void setSpareSalesTime(Double spareSalesTime) {
        this.spareSalesTime = spareSalesTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getInquiryTaxFee() {
        return inquiryTaxFee;
    }

    public void setInquiryTaxFee(Long inquiryTaxFee) {
        this.inquiryTaxFee = inquiryTaxFee;
    }

    public Integer getPurchaseOrderType() {
        return purchaseOrderType;
    }

    public void setPurchaseOrderType(Integer purchaseOrderType) {
        this.purchaseOrderType = purchaseOrderType;
    }

    public String getMarketingActivityInfo() {
        return marketingActivityInfo;
    }

    public void setMarketingActivityInfo(String marketingActivityInfo) {
        this.marketingActivityInfo = marketingActivityInfo == null ? null : marketingActivityInfo.trim();
    }

    public String getSalesInfo() {
        return salesInfo;
    }

    public void setSalesInfo(String salesInfo) {
        this.salesInfo = salesInfo == null ? null : salesInfo.trim();
    }

    public Integer getMarketInquiryPriceFlag() {
        return marketInquiryPriceFlag;
    }

    public void setMarketInquiryPriceFlag(Integer marketInquiryPriceFlag) {
        this.marketInquiryPriceFlag = marketInquiryPriceFlag;
    }

    public Long getFreight() {
        return freight;
    }

    public void setFreight(Long freight) {
        this.freight = freight;
    }

    public String getMarketSalesInfo() {
        return marketSalesInfo;
    }

    public void setMarketSalesInfo(String marketSalesInfo) {
        this.marketSalesInfo = marketSalesInfo == null ? null : marketSalesInfo.trim();
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
    }
}