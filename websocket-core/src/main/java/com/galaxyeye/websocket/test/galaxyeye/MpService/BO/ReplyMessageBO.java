package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.BO
 * @Date Create on 14:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/29日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ReplyMessageBO implements Serializable {
    private static final long serialVersionUID = -6917536354329100156L;

    /**
     * uid : 100060
     * accessToken : ae6013a81c61813b3d31a0d54ca8948fb4135216952d40cedc95bda8c5224452
     * appid : 3.00014
     * bmAppid : 3.00014
     * nickname : traffic police1
     * messageboardId : 1185108641909313536
     * replyType : 1
     * content : testtesttestsetsessetstese
     * seq : abc
     */

    private String uid;
    private String accessToken;
    private String appid;
    private String bmAppid;
    private String nickname;
    private String messageboardId;
    private String replyType;
    private String content;
    private String seq;
    private String token;

}
