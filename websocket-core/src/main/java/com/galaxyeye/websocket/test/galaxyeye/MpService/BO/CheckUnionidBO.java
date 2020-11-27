package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 18:03
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/1/14日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class CheckUnionidBO implements Serializable {
    private static final long serialVersionUID = -6844621231329932447L;
    /**
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xupmOd4Veq-fMvNGkU-T96hszwDyOykY42gNUTSsL_W71Q3ep56lYzVJtJw8CULpt
     * appid : 3.00014
     * bmAppid : 3.00014
     * seq : 123
     * unionId : o8Xn91RNHVnMzUoN9msOAhF2CFZY
     * uid : 100005
     */
    private String token;
    private String accessToken;
    private String appid;
    private String bmAppid;
    private String seq;
    private String unionId;
    private Integer uid;
}
