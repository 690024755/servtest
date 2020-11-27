package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 16:17
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/24日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
public class BusinessServiceCallBackSrvPoliceFaceCertBO implements Serializable {
    private static final long serialVersionUID = 7104880155433422900L;
    private String verifiedCode;
    private String verifiedResult;
    private String verifiedId;
    private String identifier;
    private String headImage;
    private String openId;
}
