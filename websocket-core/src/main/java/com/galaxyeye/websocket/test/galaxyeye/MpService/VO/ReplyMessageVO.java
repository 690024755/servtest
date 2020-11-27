package com.galaxyeye.websocket.test.galaxyeye.MpService.VO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.VO
 * @Date Create on 11:57
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/7日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReplyMessageVO {

    /**
     * msg : ok
     * result : 1
     * id : 1192289613192892416
     * seq : 123
     */

    private String msg;
    private int result;
    private long id;
    private String seq;

}
