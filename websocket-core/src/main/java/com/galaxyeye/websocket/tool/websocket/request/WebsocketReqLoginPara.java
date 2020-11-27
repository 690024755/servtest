package com.galaxyeye.websocket.tool.websocket.request;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class WebsocketReqLoginPara {

    private String method;

    private String uname;

    private String passwd;


    private String appid;


    private String bmAppid;

    private Map<String,String> statistics;


}
