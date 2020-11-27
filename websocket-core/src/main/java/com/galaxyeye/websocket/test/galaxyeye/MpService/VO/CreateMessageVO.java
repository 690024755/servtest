package com.galaxyeye.websocket.test.galaxyeye.MpService.VO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.VO
 * @Date Create on 14:41
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/29日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateMessageVO {

    /**
     * msg : ok
     * result : 1
     * id : 1189067524050784256
     * seq : 123
     */

    private String msg;
    private Integer result;
    private Long id;
    private Object seq;

}
