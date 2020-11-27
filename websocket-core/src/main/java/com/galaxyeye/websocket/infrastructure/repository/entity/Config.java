package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class Config implements Serializable {
    private static final long serialVersionUID = 6633816628016013031L;
    private Integer id;

    private String name;

    private String value;

    private String info;

    private String desc;

    private Date timestamp;

}