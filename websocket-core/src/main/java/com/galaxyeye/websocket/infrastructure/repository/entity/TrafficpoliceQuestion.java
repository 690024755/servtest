package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class TrafficpoliceQuestion {
    private Long id;

    private Byte type;

    private String statement;

    private String image;

    private String answer;

    private Date createdAt;

    private Date deletedAt;

    private String option;

}