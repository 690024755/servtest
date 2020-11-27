package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.DTO
 * @Date Create on 10:32
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GetArticleAddInfoBO implements Serializable {
    private static final long serialVersionUID = -1726450497555620061L;
    /**
     * 获取所有文章附加信息
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String accessToken;
    private String appid;
    private String seq;

}
