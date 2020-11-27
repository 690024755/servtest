package com.galaxyeye.websocket.test.galaxyeye.Article.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.DTO
 * @Date Create on 13:03
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/29日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SaveArticleDTO implements Serializable {
    private static final long serialVersionUID = -6996604753388093433L;
    /**
     * id : 1266231970380124160
     * msg : ok
     * result : 1
     * seq : abc
     * strId : 1266231970380124160
     */
    private Long id;
    private String msg;
    private Integer result;
    private String seq;
    private String strId;

}
