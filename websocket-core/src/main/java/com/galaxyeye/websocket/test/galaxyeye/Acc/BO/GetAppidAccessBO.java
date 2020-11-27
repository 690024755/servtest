package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 14:54
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class GetAppidAccessBO implements Serializable {
    private static final long serialVersionUID = 774769113791423814L;
    /**
     * appid : 3.00014
     * onlyCheckTime : false
     */
    private String appid;
    private Boolean onlyCheckTime;

}
