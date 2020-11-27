package com.galaxyeye.websocket.tool.websocket.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WebsocketResLoginPara implements Serializable {
    private static final long serialVersionUID = -6439530486668856950L;

    private String method;

    private Integer uid;

    private String strUid;

    private String bmAppid;

    private Integer result;

    private String msg;

    private String token;

    private String upgradePath;

    private String txAiAPpid;

    private String txAiAppid;

    private String txAiAppkey;

    private String bdAiApiKey;

    private String bdAiSecretKey;
}
