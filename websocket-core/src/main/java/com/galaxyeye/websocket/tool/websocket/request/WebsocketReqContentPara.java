package com.galaxyeye.websocket.tool.websocket.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WebsocketReqContentPara {

    private String method;

    private int uid;

    private String content;


    private String appid;

    private String bmAppid;
}
