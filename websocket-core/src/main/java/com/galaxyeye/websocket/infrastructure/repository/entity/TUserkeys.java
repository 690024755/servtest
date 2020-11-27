package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class TUserkeys implements Serializable {
    private static final long serialVersionUID = 2197187727984262243L;
    private Long uid;

    private Integer keyid;

    private String value;

    private String info;

    private Long modifyTime;

    private String appid;

    private Long uc;

}