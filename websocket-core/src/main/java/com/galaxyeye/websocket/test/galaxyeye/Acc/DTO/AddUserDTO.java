package com.galaxyeye.websocket.test.galaxyeye.Acc.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.DTO
 * @Date Create on 13:37
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/18日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddUserDTO {
    /**
     * msg :
     * result : 1
     * seq : null
     * uid : 237393
     * uname : test123456
     */
    private String msg;
    private Integer result;
    private String seq;
    private Long uid;
    private String uname;
}
