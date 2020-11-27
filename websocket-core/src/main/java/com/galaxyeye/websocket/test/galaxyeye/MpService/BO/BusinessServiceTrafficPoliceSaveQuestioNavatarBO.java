package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 16:45
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/6日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BusinessServiceTrafficPoliceSaveQuestioNavatarBO implements Serializable {
    private static final long serialVersionUID = -5959285884140285928L;
    private String token;
    private String uid;
    private String appid;
    //用户昵称
    private String nickname;
    //用户微信头像链接
    private String avatar;
}
