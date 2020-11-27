package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 16:11
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/14日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class LogOutBO implements Serializable {
    private static final long serialVersionUID = 4580522091762837648L;
    /**
     * uid : 100001
     * appid : 1.1001
     * seq : abc
     */
    private Long uid;
    private String appid;
    private String seq;
    private String sessionCode;
    private String keytp;
}
