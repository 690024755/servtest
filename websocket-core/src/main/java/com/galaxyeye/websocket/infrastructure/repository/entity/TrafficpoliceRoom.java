package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class TrafficpoliceRoom {
    private Long id;

    private String unionId;

    private Long remoteRoomId;

    private String name;

    private String contact;

    private String content;

    private Date createdAt;

    private Date updatedAt;

    private Integer unreadCount;

    private Date readAt;

    private Date repliedAt;

    private String appid;

    private Integer isFinished;

}