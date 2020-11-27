package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.BO
 * @Date Create on 15:45
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/7日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class LabListBO implements Serializable {
    private static final long serialVersionUID = -6021858682302359060L;
    private String appid;
    private String uid;
    private String token;
    //标签code
    private String code;
    /**
     * count : 10
     * name : 市
     * maintype : 01000004
     * page : 1
     */
    private Integer count;
    //标签名称
    private String name;
    //主类code
    private String maintype;
    private Integer page;
}
