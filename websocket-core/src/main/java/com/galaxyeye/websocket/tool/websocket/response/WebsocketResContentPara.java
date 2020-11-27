package com.galaxyeye.websocket.tool.websocket.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WebsocketResContentPara implements Serializable {
    private static final long serialVersionUID = 6687403375659806899L;
    private String method;

    private String bmAppid;

    private String strUid;

    private String content;

    private String action;

    private String contentType;

    private int result;

    private String msg;
}
