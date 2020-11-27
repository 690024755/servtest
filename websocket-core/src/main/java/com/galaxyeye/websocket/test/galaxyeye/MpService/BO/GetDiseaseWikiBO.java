package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 14:05
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/20日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GetDiseaseWikiBO implements Serializable {
    private static final long serialVersionUID = -5399382215637802719L;
    /**
     * appid : 1.00002
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-x4PdYCyzCfJWwqvyoSacNjMuMQ5s30PQvZNNzeA-iVtmE8njQ4ywfaA4TldmdRtT1
     * seq :
     * name : aa
     */
    private String accessToken;
    private String appid;
    private String uid;
    private String token;
    private String seq;
    private String name;
}
