package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class ArticleCatAssLab implements Serializable {
    private static final long serialVersionUID = 4073028313371048092L;
    private Long articleId;
    private String assLabCode;
    private Date createdAt;
    private Date updatedAt;
}