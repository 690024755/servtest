package com.galaxyeye.websocket.test.galaxyeye.IcService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 14:54
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/21日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AppAccountGetProUidBO implements Serializable {

    private static final long serialVersionUID = 7903372857503693260L;

    /**
     * appid : 3.00014
     */
    private String appid;
}
