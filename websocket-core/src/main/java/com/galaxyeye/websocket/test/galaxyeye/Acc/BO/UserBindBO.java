package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 15:28
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/15日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class UserBindBO implements Serializable {
    private static final long serialVersionUID = 7016991051050128229L;
    /**
     * uname : !235080
     * appid : 1.00002
     * keys : [{"unionid":"daliang"},{"deviceid":"daliang test deviceid"},{"openid":"dada"}]
     */
    private String uname;
    private String appid;
    private List<KeysBean> keys;

    @Data
    @ToString
    public static class KeysBean {
        /**
         * unionid : daliang
         * deviceid : daliang test deviceid
         * openid : dada
         */
        private String unionid;
        private String deviceid;
        private String openid;
        private String cacc;
        private String nickname;
        private String email;
        private String mobile;

    }
}
