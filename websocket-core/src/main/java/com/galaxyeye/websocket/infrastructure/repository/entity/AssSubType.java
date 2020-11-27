package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class AssSubType implements Serializable {
    private static final long serialVersionUID = 6383032370128722506L;

    private Long id;

    private String assSubTypeCode;

    private String assSubTypeName;

    private String mainTypeCode;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
}