package com.galaxyeye.websocket.client.result;/* * Description:com.galaxyeye.websocket.client.result * @Date Create on 17:19 * @author <a href="mailto:yangyi@galaxyeye-tech.com">yangyi</a> * @Version JDK 1.8 * @since version 1.0 Copyright 2019-02-14日SXC All Rights Reserved. */import lombok.Data;import java.io.Serializable;import java.util.Date;@Datapublic class SxcAccountInfoDTO {	private Integer id;	private Integer userId;	private Integer accountId;	private Long balance;	private Date createTime;	private Integer state;	private Long subsidyBalance;	private Long cashBalance;	private Long baseCashBalance;}