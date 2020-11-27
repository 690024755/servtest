package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = 8176736999986983107L;
    private Integer id;

    private Long uid;

    private String uname;

    private String appid;

    private Date operateTime;

    private String operateIp;

    private String action;

    private String reqJsonStr;

    private String respJsonStr;

}