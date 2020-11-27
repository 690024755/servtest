package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.BO
 * @Date Create on 15:36
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/1日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class SetImagesBO implements Serializable {
    private static final long serialVersionUID = -4123308153513593608L;
    /**
     * urls : ["https://7niu-article.galaxyeye-tech.com/img/msgboard/191018/1185022241557975040.jpg","https://7niu-article.galaxyeye-tech.com/img/msgboard/191018/1185022477248499712.jpg"]
     * enable : false
     * source : messageboard
     * uid : 100060
     * token : xxxxxx
     * bmAppid : 3.00007
     * accessToken : 72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9
     * appid : 1.00001
     * seq : abc
     */
    private Boolean enable;
    private String source;
    private String uid;
    private String token;
    private String bmAppid;
    private String accessToken;
    private String appid;
    private String seq;
    private List<String> urls;
}
