package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.VO
 * @Date Create on 10:43
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GetArticleBO implements Serializable {

    private static final long serialVersionUID = 8820300652918933952L;
    /**查询文章
     * id : 575243049722974208
     * title : xxx
     * dataFormat : list/body
     * uid : 100060
     * token : xxxxxx
     * bmAppid : 3.00007
     * accessToken : 72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9
     * appid : 1.00001
     * seq : abc
     */
    private String id;
    private String title;
    private String dataFormat;
    private String uid;
    private String token;
    private String bmAppid;
    private String accessToken;
    private String appid;
    private String seq;
}
