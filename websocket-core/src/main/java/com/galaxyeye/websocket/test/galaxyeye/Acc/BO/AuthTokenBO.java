package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 10:56
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AuthTokenBO implements Serializable {

    private static final long serialVersionUID = -3255697335712219364L;
    /**
     * bmAppid :
     * accessToken :
     * uid : 100002
     * token :
     * appid : xxxx
     * seq :
     */

    private String bmAppid;
    private String accessToken;
    private Long uid;
    private String token;
    private String appid;
    private String seq;

}
