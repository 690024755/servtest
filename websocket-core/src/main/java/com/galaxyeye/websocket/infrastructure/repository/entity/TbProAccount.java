package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class TbProAccount implements Serializable {
    private static final long serialVersionUID = 7344226002450934693L;
    private Long id;

    private String cid;

    private String cname;

    private String cpwd;

    private String euid;

    private String appid;

    private Integer status;

    private Long uid;

    private Long sendnum;

    private Integer type;

    private Date createTime;

    private Date updateTime;

}