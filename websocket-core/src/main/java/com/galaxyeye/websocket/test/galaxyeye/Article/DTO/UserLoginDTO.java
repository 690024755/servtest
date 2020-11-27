package com.galaxyeye.websocket.test.galaxyeye.Article.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginDTO {

    private String uname;

    private String passwd;

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

    public static class Keys{
        private String unionid;
        private String info;

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


}
