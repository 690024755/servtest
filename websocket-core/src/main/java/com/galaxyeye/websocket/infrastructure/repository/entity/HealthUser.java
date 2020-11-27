package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class HealthUser implements Serializable {
    private static final long serialVersionUID = -4282325963225960490L;
    private Long uid;

    private Integer answer;

    private Integer right;

    private Integer signDays;

    private Short todayAnswer;

    private Short todayRight;

    private Date answerAt;

    private Date signAt;

    private String appid;

    private Date createdAt;

}