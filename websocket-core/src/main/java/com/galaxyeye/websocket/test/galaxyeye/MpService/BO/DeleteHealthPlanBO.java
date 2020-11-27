package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 14:09
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/20日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DeleteHealthPlanBO implements Serializable {
    private static final long serialVersionUID = -293158722760979198L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-x7MqTP1_7DQce5MNYIYzRo9LQbQPhKYHEaenef_T9bzIC5M08gtQ47XZmxmJMGwSV
     * bmAppid : 4.00090
     * appid : 4.00090
     * id : 2
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private String id;
}
