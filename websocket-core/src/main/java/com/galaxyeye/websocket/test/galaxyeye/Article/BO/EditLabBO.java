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
public class EditLabBO implements Serializable {
    private static final long serialVersionUID = -7771746142637470834L;
    /**
     * appid : 1.00002
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xi_fRnncorRrnEKmp006gPImsEqJRLXN1Hati7K8-NvTlWvtyU5TJY3SmZsbtdQU2
     * code : 03000001
     * name : 德国测试改3
     */
    private String appid;
    private String uid;
    private String token;
    private String code;
    private String name;
}
