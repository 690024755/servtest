package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class SysAccFlow implements Serializable {
    private static final long serialVersionUID = 558662604134631044L;
    private Integer id;

    private String sessionCode;

    private Long uid;

    private String appid;

    private String uname;

    private Integer loginType;

    private Integer logoutType;

    private Date loginTime;

    private Date logoutTime;

    private String clientIp;

    private String deviceCode;

    private Long onlineTime;

    private String channelNo;

}