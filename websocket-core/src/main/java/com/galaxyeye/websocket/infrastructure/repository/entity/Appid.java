package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class Appid implements Serializable {
    private static final long serialVersionUID = -5587019172073229595L;
    private Long id;

    private String appid;

    private String appkey;

    private String apptype;

    private String desc;

    private String groupTag;

    private String identity;

    private String callbackUrl;

    private Long erid;

    private Long euid;

    private Long pid;

    private String info;

    private String fromAppid;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private Integer version;

}