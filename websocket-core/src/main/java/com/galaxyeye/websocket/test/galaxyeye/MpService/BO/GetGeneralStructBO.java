package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 18:02
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/16日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class GetGeneralStructBO implements Serializable {
    private static final long serialVersionUID = -6229327011110725726L;
    private String token;
    private String accessToken;
    private String appid;
    private String bmAppid;
    private String uid;
    private String seq;
    private String env;
    private String version;
    private String type;
    private String key;
}
