package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class XmlCase implements Serializable {
    private static final long serialVersionUID = -1510717048023980554L;
    private Long id;

    private String moduleName;

    private String caseName;

    private Integer type;

    private Date createTime;

}