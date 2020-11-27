package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class ArticleCatAssSubType implements Serializable {
    private static final long serialVersionUID = 2804268258178639459L;
    private Long articleId;
    private String assSubTypeCode;
    private Date createdAt;
    private Date updatedAt;

}