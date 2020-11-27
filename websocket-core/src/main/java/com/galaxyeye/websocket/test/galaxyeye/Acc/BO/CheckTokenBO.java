package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 14:22
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CheckTokenBO implements Serializable {

    private static final long serialVersionUID = 627157035557261467L;
    /**
     * token : 123456
     * appid : bm11111111111111111111
     */
    private String token;
    private String appid;
    private String bmAppid;
    private Long   uid;
    private String accessToken;
}
