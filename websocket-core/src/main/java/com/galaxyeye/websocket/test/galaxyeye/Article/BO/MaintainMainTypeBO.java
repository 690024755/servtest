package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.BO
 * @Date Create on 15:41
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/7日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class MaintainMainTypeBO implements Serializable {
    /**
     * appid : 1.00002
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-x1Z8HbHFYRXPo6zBWnc92ZlfoYm2jsu6wSkPhF1O3Z7f6xwg_mEJzYO5zcuKgWg8e
     * code :  01000016
     * maintain : 1
     */
    private String appid;
    private String uid;
    private String token;
    private String code;
    private Integer maintain;
}
