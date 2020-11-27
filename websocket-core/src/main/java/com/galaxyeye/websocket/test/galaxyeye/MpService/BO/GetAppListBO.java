package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:12
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/19日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class GetAppListBO implements Serializable {
    private static final long serialVersionUID = 8903825497345999650L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-x0-faJURTKcIVCjLgU_sKqalhPAzwVWUWc8RfzaTR6RuM4V_oWoD20RrYll5rIty3
     * bmAppid : 4.00090
     * appid : 4.00090
     * confAppid : 4.00086
     * count : 9
     * page : 9
     */
    private String uid;
    private String token;
    private String accessToken;
    private String bmAppid;
    private String appid;
    private String confAppid;
    private String env;
    private String version;
    private Integer count;
    private Integer page;
}

