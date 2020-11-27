package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class CustomerServiceStaff implements Serializable {
    private static final long serialVersionUID = -4592043377930038439L;
    private Long id;

    private String appId;

    private Long staffId;

    private String staffName;
//客服在线状态(1:离线（默认）；2：在线；3：挂起)
    private Integer staffStatus;

    private Integer avatar;

    private Date createTime;

    private Date updateTime;
}