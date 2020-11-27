package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 11:16
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class KfChatBO implements Serializable {
    private static final long serialVersionUID = -2124455998467610910L;
    /**
     * method : kfchatReq
     * uid : 102887
     * msgType : pic|common
     * content : 图片url|聊天内容
     * chatPackSeq : xxxxxxx
     * bmAppid : 4.00002
     * wxAppid : wx16f2e50e562f4220
     */
    private String method;
    private Integer uid;
    private String msgType;
    private String content;
    private String chatPackSeq;
    private String bmAppid;
    private String wxAppid;

}
