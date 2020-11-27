package com.galaxyeye.websocket.test.galaxyeye.WxService.BO;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ToString
public class WxServiceReqLoginBO implements Serializable {
    private static final long serialVersionUID = -1308037462766332681L;
    /**
     * example
     * {"appid":"1.00001","method":"loginReq","passwd":"123456","uname":"hxw002"}
     */
    private String method;

    private String uname;

    private String passwd;

    private String keytp;

    private String wxAppid;

    private Object userInfoRes;

    /**
     * 该参数目前不在维护使用
     */
    private String appid;


    private String bmAppid;

    private Map<String,String> statistics;

    //增加appid订阅功能，获取哪些appid是正常状态，哪些是维护状态，属于非必填字段
    private List<String> maintenanceSubscribe;

}
