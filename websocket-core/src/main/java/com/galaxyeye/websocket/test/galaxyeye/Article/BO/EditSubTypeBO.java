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
public class EditSubTypeBO implements Serializable {
    private static final long serialVersionUID = 4798959360761846L;
    /**
     * appid : 1.00002
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-x1Z8HbHFYRXPo6zBWnc92ZlfoYm2jsu6wSkPhF1O3Z7f6xwg_mEJzYO5zcuKgWg8e
     * name : 测试20
     * code : 02000791
     */
    private String appid;
    private String uid;
    private String token;
    private String name;
    private String code;
}
