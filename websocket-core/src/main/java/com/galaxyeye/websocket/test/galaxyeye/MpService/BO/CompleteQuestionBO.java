package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 13:48
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/20日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CompleteQuestionBO implements Serializable {
    private static final long serialVersionUID = 3991256455217317532L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-x2MtAJKUBTRrggu-WCTFoEC_p8CSTtUgx7VSU7882zFRLXX3YEYlKMiEcwNsSLxQb
     * bmAppid : 3.00014
     * appid : 3.00014
     */
    private String accessToken;
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
}
