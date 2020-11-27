package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 18:08
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/2日galaxyeye All Rights Reserved.
 */
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
public class QuitKfReqBO implements Serializable {
    private static final long serialVersionUID = -5865132728520023884L;
    /**
     * method : quitKfReq
     * uid : 102887
     * chatPackSeq : xxxxxxx
     * bmAppid : 4.00002
     * wxAppid : wx16f2e50e562f4220
     */
    private String method;
    private Integer uid;
    private String chatPackSeq;
    private String bmAppid;
    private String wxAppid;
}
