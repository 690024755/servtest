package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.BO
 * @Date Create on 15:21
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/7日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class MainTypeListBO implements Serializable {
    private static final long serialVersionUID = 6514561785582280020L;
    /**
     * appid : 1.00002
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xYSOUeeUAAyDs2sJzYGo6SNhB3Dk5lf0OzrK73I3ClZ8Q7Ka8xB7SsuzhwFj9q-71
     * count : 30
     * name : 宗教
     * code : 01000010
     * page : 2
     * maintain : 0
     */
    private String appid;
    private String uid;
    private String token;
    private Integer count;
    private String name;
    private String code;
    private Integer page;
    //0为查询所有、1为查询正常状态、2为查询维护状态
    private Integer maintain;
}
