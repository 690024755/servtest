package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class TrafficpoliceQuestionUser {
    private Long uid;

    private String nickname;

    private String avatar;

    private Integer qid;

    private Integer qidToanswer;

    private Integer qcount;

    private Integer right;

    private Date answerAt;

    private String appid;

    private Date createdAt;

}