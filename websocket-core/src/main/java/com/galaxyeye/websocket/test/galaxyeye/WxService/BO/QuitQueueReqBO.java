package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 17:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/1日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class QuitQueueReqBO implements Serializable {
    private static final long serialVersionUID = -9054489226366246133L;
    /**
     * method : quitQueueReq
     * uid : 102887
     * bmAppid : 4.00002
     * wxAppid : wx16f2e50e562f4220
     */
    private String method;
    private Integer uid;
    private String bmAppid;
    private String wxAppid;
}
