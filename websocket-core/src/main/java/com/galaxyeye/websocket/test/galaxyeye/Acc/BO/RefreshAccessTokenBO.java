package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 17:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/20日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RefreshAccessTokenBO implements Serializable {
    private static final long serialVersionUID = 5551096091074290109L;
    /**
     * bmAppid : 3.00007
     * accessToken : 72b25378ce643cf691fb69c03c2ffbce0cdeeff1b08342e0b185bd5803a7cd4a
     * uid : 100002
     * token : xxxxxx
     * tokenExpire : 31536000
     * appid : 1.00002
     * seq : abc
     */
    private String bmAppid;
    private String accessToken;
    private String token;
    private Long uid;
    private String appid;
    private String seq;

}
