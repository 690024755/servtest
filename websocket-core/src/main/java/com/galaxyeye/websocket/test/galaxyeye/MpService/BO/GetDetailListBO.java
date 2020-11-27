package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:36
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/19日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GetDetailListBO implements Serializable {
    private static final long serialVersionUID = 3690220298374202433L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-x0-faJURTKcIVCjLgU_sKqalhPAzwVWUWc8RfzaTR6RuM4V_oWoD20RrYll5rIty3
     * bmAppid : 9.00003
     * appid : 9.00003
     * confAppid : 8.00008
     * env : dev
     * version : 1.0.0
     * removeDup : 1
     */
    private String uid;
    private String token;
    private String accessToken;
    private String bmAppid;
    private String appid;
    private String confAppid;
    private String env;
    private String version;
    //是否去重（0否1是）
    private Integer removeDup;
}
