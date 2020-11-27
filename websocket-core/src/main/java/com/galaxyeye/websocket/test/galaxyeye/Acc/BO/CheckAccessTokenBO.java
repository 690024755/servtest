package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 10:13
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/21日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CheckAccessTokenBO implements Serializable {
    private static final long serialVersionUID = 8495115351140516368L;
    /**
     * bmAppid : 3.00007
     * accessToken : 72b25378ce643cf691fb69c03c2ffbcede9fa4efe2b0e45fa15ef805301358bc
     * appid : 1.00002
     */
    private String bmAppid;
    private String accessToken;
    private String appid;
}
