package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class ManualConfig implements Serializable {
    private static final long serialVersionUID = 8681886538487201825L;
    private Long id;

    private String appid;

    private String confEnv;

    private String ver;

    private String confKey;

    private Date createdAt;

    private Date updatedAt;

    private String comment;

    private Integer enable;

    private Integer verify;

    private String editor;

    private String data;

}