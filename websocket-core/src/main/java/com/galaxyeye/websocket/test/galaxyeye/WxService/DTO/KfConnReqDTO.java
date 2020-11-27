package com.galaxyeye.websocket.test.galaxyeye.WxService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.DTO
 * @Date Create on 0:46
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/4日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class KfConnReqDTO implements Serializable {

    private static final long serialVersionUID = -3982804459221224486L;
    /**
     * method : kfConnResp
     * bmAppid : 1.00002
     * uid : 239601
     * result : 1
     * msg :
     * chatPackSeq : 751240517819301888
     * kfNickname : yangyi
     * kfId :
     * status : link
     */

    private String method;
    private String bmAppid;
    private String uid;
    private Integer result;
    private String msg;
    private String chatPackSeq;
    private String kfNickname;
    private String kfId;
    private String status;

}
