package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.BO
 * @Date Create on 14:52
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/29日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class GetMessageBoxListBO implements Serializable {

    /**
     * uid : 100060
     * token : xxxxxx
     * bmAppid : 3.00007
     * accessToken : 72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9
     * appid : 1.00001
     * seq : abc
     */

    private String uid;
    private String token;
    private String bmAppid;
    private String accessToken;
    private String appid;
    private String seq;
}
