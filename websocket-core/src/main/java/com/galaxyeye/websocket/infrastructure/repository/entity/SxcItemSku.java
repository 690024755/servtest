package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Data
public class SxcItemSku implements Serializable {

	private static final long serialVersionUID = 7071610506236341792L;
	/**
     * 商品 id
     */
    private Integer id;

    /**
     * spu id
     */
    private Integer spuId;

    /**
     * 销售城市名称
     */
    private String sellCity;

    /**
     * 商品上下架状态：0: 没开始销售 1:上架 2:下架 3: 不再销售
     */
    private Integer status;

    /**
     * 商品售价
     */
    private Long price;

    /**
     * 0:删除 1:可用
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    private Date gmtModified;

    /**
     * 预估库存
     */
    private Integer estimateStock;

    /**
     * 已经销售多少件商品
     */
    private Integer soldStock;

    /**
     * 商品每人每天限购数量
     */
    private Integer limitPer;

    /**
     * 商品是否具有关联销售     0:否    1:是
     */
    private Integer isRelationSale;

    /**
     * 商品销售的城市编码
     */
    private Integer cityCode;

    /**
     * sku属性
     */
    private String features;

    /**
     * 商品的包装方式,默认为件:0, 袋装:1,纸箱装:2,泡沫箱装:3,塑料框装:4
     */
    private Integer packagingType;

    /**
     * 商品产地
     */
    private String itemArea;

    /**
     * 叶子类目id
     */
    private Integer catId;

    /**
     * 本地称谓
     */
    private String localTitle;

    /**
     * 商品包装名称,默认为件:0,袋:1,箱:2
     */
    private Integer itemUnit;

    /**
     * 商品净重,单位为斤
     */
    private Double productWeight;

    /**
     * 商品等级,例如一级品、二级品
     */
    private Integer grade;

    /**
     * 商品品种,如土豆分 山东荷兰土豆，本地小土豆
     */
    private String itemSpecies;

    /**
     * 商品标准名称
     */
    private String spuName;

    /**
     * 商品运费,单位为分,默认为0
     */
    private Integer shippingCost;

    /**
     * 当前sku的版本
     */
    private Integer version;

    /**
     * 0,表示当前商品库存没有锁定
1,表示当前商品库存锁定
     */
    private Integer lockStatus;

    /**
     * 商品类型：0：普通销售商品，1：预售商品
     */
    private Integer itemType;

    /**
     * properties:前台商品特有商品属性
     */
    private String properties;

    /**
     * 商品产地城市编码
     */
    private Integer prodAreaCode;

    /**
     * 后台sku_id
     */
    private Integer stockSkuId;

    /**
     * 售卖渠道id
     */
    private Integer channelId;

    /**
     * 售卖渠道名称
     */
    private String channelName;


}