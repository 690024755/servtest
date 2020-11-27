package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class TUser implements Serializable {
    private static final long serialVersionUID = -6884004263815397215L;
    private Long uid;

    private String uname;

    private String password;

    private String channelNo;

    private String activityno;

    private Long createtime;

    private Long modifytime;

    private Integer blocked;

    private Long blockedtimeout;

    private Integer status;

    private String appid;

    private Long uc;

    private String ip;

    private Long lastlogintime;

    private Long lastlogouttime;

}