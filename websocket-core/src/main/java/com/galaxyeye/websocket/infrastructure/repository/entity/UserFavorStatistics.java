package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class UserFavorStatistics implements Serializable {
    private static final long serialVersionUID = 2079189468967739200L;
    private Long uid;

    private Long articleId;

    private Integer favor;

    private String appid;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}