package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class Article implements Serializable {
    private static final long serialVersionUID = -6715923143168046891L;
    private Long id;

    private String title;

    private String quality;

    private String srcSite;

    private String intro;

    private String thumbnail;

    private String editor;

    private String refUrl;

    private String mainType;

    private String subType;

    private String labs;

    private String articleType;

    private String htmltext;

    private String comment;

    private String assLabs;

    private String info;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private Integer enable;
}