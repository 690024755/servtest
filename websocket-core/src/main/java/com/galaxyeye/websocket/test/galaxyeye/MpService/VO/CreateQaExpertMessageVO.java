package com.galaxyeye.websocket.test.galaxyeye.MpService.VO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.VO
 * @Date Create on 16:50
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/26日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateQaExpertMessageVO {

    /**
     * msg : ok
     * result : 1
     * id : 1199248784920416256
     * seq : abc
     */
    private String msg;
    private int result;
    /**
     * 留言新增的id
     */
    private String id;
    private String seq;

}
