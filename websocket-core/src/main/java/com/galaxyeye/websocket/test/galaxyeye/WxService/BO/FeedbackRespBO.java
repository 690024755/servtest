package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 15:05
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/3日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class FeedbackRespBO implements Serializable {
    private static final long serialVersionUID = 5847887174785175404L;
    /**
     * method : feedbackReq
     * uid : 102887
     * evaluate : 1|2|3
     * chatPackSeq : xxxxxxx
     * bmAppid : 4.00002
     * wxAppid : wx16f2e50e562f4220
     */
    private String method;
    private Integer uid;
    private Integer evaluate;
    private String chatPackSeq;
    private String bmAppid;
    private String wxAppid;
}
