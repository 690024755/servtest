package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class SxcBuyer implements Serializable {
	private static final long serialVersionUID = 5883102454487329709L;
	private Integer id;

    private Integer buyerUserId;

    private String buyerName;

    private Integer marketSurveyStatus;

    private Integer maxBuyerLevel;

    private Integer buyerLevel;

    private Integer buyerStorehouseId;

    private Integer buyerStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer cityCode;

    private Integer state;

    private String supplyChannel;

    private Long dayTransactionFee;

    private String downstreamCustomers;

    private String address;

    private String mobilePhone;

    private Integer valid;


}