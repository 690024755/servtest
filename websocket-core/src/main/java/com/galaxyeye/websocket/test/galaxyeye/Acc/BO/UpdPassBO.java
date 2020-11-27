package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 19:44
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/13日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UpdPassBO implements Serializable {
    private static final long serialVersionUID = -8325694943745534403L;
    /**
     * uname : daliang
     * passwd : password
     * oldpass : oldpassword
     * appid : 1.00002
     */
    private String uname;
    private String passwd;
    private String oldpass;
    private String appid;
}
