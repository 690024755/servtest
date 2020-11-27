package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 10:47
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class UserLoginBO implements Serializable {

    private static final long serialVersionUID = -6857659044910929785L;
    /**
     * uname : 071cRmVP1NMWua12AwVP1NHnVP1cRmVx
     * passwd : xxxxxx
     * userInfoRes : object
     * keytp : wxcode
     * bmAppid : 4.00002
     * wxAppid : wx16f2e50e562f4220
     */

    private String uname;
    private String passwd;
    private String userInfoRes;
    /**
     *微信小程序登录需要设置值为wxcode，其他可以传空或者不传参数
     */
    private String keytp;
    private String bmAppid;
    private String wxAppid;

}
