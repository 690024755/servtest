package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class UserCollectStatistics implements Serializable {
    private static final long serialVersionUID = -484698286094942207L;
    private Long uid;

    private String appid;

    private Long articleId;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}