package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class ArticleUserStatistics implements Serializable {
    private static final long serialVersionUID = 7268719131121215834L;
    private Long articleId;

    private Long uid;

    private Integer pushNum;

    private Integer readNum;

    private Integer shareNum;

    private String appid;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}