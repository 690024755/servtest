package com.galaxyeye.websocket.test.galaxyeye.Acc.BO;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class EUserLoginBO implements Serializable {
    private static final long serialVersionUID = 2345674220896359102L;
    /**
     * euid : 1103539011445592064
     * euname : galaxyeye
     * passwd : 111111
     * appid : 1.00002
     */
    private Long euid;
    private String euname;
    private String passwd;
    private String appid;
    private String bmAppid;
    private Long   uid;
    private String token;
    private String accessToken;
    private String seq;
}
