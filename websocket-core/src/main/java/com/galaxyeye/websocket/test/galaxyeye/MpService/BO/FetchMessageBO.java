package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.BO
 * @Date Create on 15:12
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/29日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class FetchMessageBO implements Serializable {

    private static final long serialVersionUID = -657239482991831379L;

    /**
     * uid : 100060
     * accessToken : ae6013a81c61813b3d31a0d54ca8948fb4135216952d40cedc95bda8c5224452
     * appid : 3.00014
     * bmAppid : 3.00014
     * id : 1185108641909313536
     */

    private String uid;
    private String accessToken;
    private String token;
    private String appid;
    private String bmAppid;
    private String id;

}

