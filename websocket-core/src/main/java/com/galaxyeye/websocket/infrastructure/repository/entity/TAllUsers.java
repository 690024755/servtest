package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class TAllUsers implements Serializable {
    private static final long serialVersionUID = 8356474915110757880L;
    private Integer id;

    private Long uid;

    private String appid;

    private String channelNo;

    private Date createTime;

}