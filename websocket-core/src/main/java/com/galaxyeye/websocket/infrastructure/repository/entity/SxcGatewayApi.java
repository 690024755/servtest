package com.galaxyeye.websocket.infrastructure.repository.entity;/* * Description:com.galaxyeye.websocket.infrastructure.repository.entity * @Date Create on 13:35 * @author <a href="mailto:yangyi@galaxyeye-tech.com">yangyi</a> * @Version JDK 1.8 * @since version 1.0 Copyright 2019-04-03日SXC All Rights Reserved. */import lombok.Data;import lombok.ToString;import java.io.Serializable;import java.util.Date;@ToString@Datapublic class SxcGatewayApi implements Serializable {	private static final long serialVersionUID = -8931992660724526336L;	private Long id;	private String apiName;	private Long apiTag;	private String apiCallPermission;	private String apiDefine;	private Date gmtCreate;	private Date gmtModified;}