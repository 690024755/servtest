package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 10:51
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SendFormidBO implements Serializable {

    private static final long serialVersionUID = -8967734953556185093L;
    /**
     * uid : uid
     * formid : xxx
     * bmAppid : 4.00012
     * wxAppid : wxAppid
     * openid :
     * seq :
     */
    private String uid;
    private String formid;
    private String bmAppid;
    private String wxAppid;
    private String openid;
    private String seq;
}
