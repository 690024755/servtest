package com.galaxyeye.websocket.service.params;/* * Description:com.galaxyeye.websocket.service.params * @Date Create on 14:21 * @author <a href="mailto:yangyi@galaxyeye-tech.com">yangyi</a> * @Version JDK 1.8 * @since version 1.0 Copyright 2019-04-03日SXC All Rights Reserved. */import lombok.Data;import lombok.ToString;import java.util.Date;@Data@ToStringpublic class SxcGatewayApiInfoDTO {	private Long id;	private String apiName;	private Long apiTag;	private String apiCallPermission;	private String apiDefine;	private Date gmtCreate;	private Date gmtModified;}