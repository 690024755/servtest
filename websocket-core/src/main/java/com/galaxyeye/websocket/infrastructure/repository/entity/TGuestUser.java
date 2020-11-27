package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class TGuestUser implements Serializable {
    private static final long serialVersionUID = -1489214016269078386L;
    private Long uid;

    private String uname;

    private String appid;

    private Integer status;

    private String ip;

    private Date createTime;

    private Date loginTime;

    private Date logoutTime;

    private Date deleteTime;

}