package com.galaxyeye.websocket.test.galaxyeye.Acc.BO;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ToString
public class UserLoginBO implements Serializable {

    private static final long serialVersionUID = 2830183061471991276L;
    private String uname;
    private String passwd;
    private Long uid;
    private String token;
    private String accessToken;
    private String bmAppid;
    private Map<String,String> statistics;



    /**
     * 4种形式值：deviceid、openid、unionid、cacc
     */
    private String keytp;

    /**
     * 是否三方登录
     */
    private boolean thirdlogin;
    private List<Keys> keys ;

    /**
     * value is unionid、openid、cacc
     */
    private String appid;
    private String seq;
    @Data
    @ToString
    public static class Keys{
        private String email;
        private String mobile;
        private String nickname;
        private String turename;
        private String idcard;
        private String scene;
        private String robot;
        private String deviceid;
        private String openid;
        private String unionid;
        private String cacc;
        private String info;
    }




}
