package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 15:38
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/30日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@ToString
public class WxServiceHeartBeatBO implements Serializable {
    private static final long serialVersionUID = 1149728849184423206L;
    /**
     * method : heartbeat
     * uid : 100000
     * wxAppid : wx16f2e50e562f4220
     */
    private String method;
    private String uid;
    private String wxAppid;
    private Map<String,String> statistics;

}
