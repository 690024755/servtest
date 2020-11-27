package com.galaxyeye.websocket.test.galaxyeye.Acc.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.VO
 * @Date Create on 13:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class UploadImageDTO {
    /**
     * msg :
     * result : 1
     * url : http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     * seq : null
     */
    private String msg;
    private int result;
    private String url;
    private Object seq;

}
