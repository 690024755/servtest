package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:04
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/19日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GetBriefBO implements Serializable {

    private static final long serialVersionUID = 6455475634739186252L;

    /**
     * appid : 4.00090
     * bmAppid : 4.00090
     * env : prd
     * version : 1.1.1
     * keys : ["indexAuditMask","yiqingUrl"]
     */
    private String appid;
    private String bmAppid;
    private String env;
    private String version;
    private String uid;
    private String token;
    private String accessToken;
    private List<String> keys;

}
