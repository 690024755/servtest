package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class HttpLog implements Serializable {
    private static final long serialVersionUID = -3940405852892135910L;
    private Long id;

    private String caseDescribe;

    private String url;

    private String request;

    private String response;

    private Date createTime;
}