package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 9:51
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/10/27日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GetEuserBO implements Serializable {
    private static final long serialVersionUID = -8649540897546526639L;
    /**
     * euid : 1103539011445592064
     * appid : 1.00002
     */
    private Long euid;
    private String appid;

}
