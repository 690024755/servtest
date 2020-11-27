package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:57
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/1/10日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class TrafficPoliceSendMessageBO implements Serializable {
    private static final long serialVersionUID = -5684165071202816097L;
    private Integer uid;
    //表trafficpolice_room.id
    private Long roomId;
    private String content;
    private String bmAppid;
    private String appid;
    private String accessToken;
    private String token;
    private String seq;
}
