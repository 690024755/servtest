package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class UserLabStatistics implements Serializable {
    private static final long serialVersionUID = -1447494151779837671L;
    private Long uid;

    private String subType;

    private String lab;

    private Integer count;

    private Integer status;

    private String appid;

    private Date readAt;

    private Date falloffAt;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}