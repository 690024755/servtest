package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 14:01
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/1/14日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CreateAccessTokenBO implements Serializable {
    private static final long serialVersionUID = 6015991836775773395L;
    /**
     * bmAppid : 5.00801
     * appkey : V33C28e1UfopXLJE1
     * appid : xxx
     */
    private String accessToken;
    private String bmAppid;
    private Long uid;
    private String token;
    private String appkey;
    //sign=MD5(key)小写
    private String sign;
    private String appid;
    private Integer tokenExpire;
}
