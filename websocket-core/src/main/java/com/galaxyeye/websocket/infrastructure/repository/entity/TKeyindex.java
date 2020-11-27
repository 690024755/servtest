package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class TKeyindex implements Serializable {
    private static final long serialVersionUID = -5060503831558301569L;
    private Integer keyid;

    private String keyvalue;

    private String appid;

    private Long uid;
}