package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class HealthQuestionHistory implements Serializable {
    private static final long serialVersionUID = -9013537786062395606L;
    private Long id;
    private Long uid;
    private Long questionId;
    private Integer answer;
    private Date createdAt;

}