package com.galaxyeye.websocket.service.params;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by cmy on 2019/1/28.
 */
@Data
public class SxcItemSkuDTO implements Serializable{
	private static final long serialVersionUID = 4915765778779688275L;
	private Integer id;

    private Integer spuId;

    private Integer stockSkuId;

    private String spuName;

    private String localTitle;

    private String itemSpecies;

    private String sellCity;

    private Integer limitPer;

    private String sellerName;

    private Integer usingStatus;

    private String features;

    private Long price;

    private String mainImgUrl;

    private String saleTime;

    private Long currentPrice;

    private Integer cityCode;

    private Integer estimateStock;
    
    private Integer soldStock;

    private Integer status;
}
