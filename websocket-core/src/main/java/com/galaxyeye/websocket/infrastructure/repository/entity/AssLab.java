package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class AssLab implements Serializable {
    private static final long serialVersionUID = 4762062419853361897L;
    private Long id;

    private String assLabCode;

    private String assLabName;

    private String mainTypeCode;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;


}