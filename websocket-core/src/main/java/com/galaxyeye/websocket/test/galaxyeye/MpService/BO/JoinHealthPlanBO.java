package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 13:54
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/20日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class JoinHealthPlanBO implements Serializable {

    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xJ1AZdk3Whouu3Ys-HbLkyqmLSwy79P6nzfghkTDN-Sxh9is5GhL4q17svkyxMJf1
     * bmAppid : 3.00014
     * appid : 3.00014
     * id : 1
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private String id;
}
