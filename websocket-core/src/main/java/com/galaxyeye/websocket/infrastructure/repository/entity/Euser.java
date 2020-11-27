package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class Euser implements Serializable {
    private static final long serialVersionUID = -4530511580790656184L;
    private Long euid;

    private String euname;

    private String passwd;

    private String email;

    private String mobile;

    private String contactNumber;

    private String enterpriseName;

    private String addr;

    private String acctype;

    private Long feuid;

    private String fromAppid;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private Integer version;

}