package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 17:00
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/12日galaxyeye All Rights Reserved.
 */

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ToString
public class AddUserBO implements Serializable {
    private static final long serialVersionUID = 3390341050259225777L;
    /**
     * uname : 注册帐号名
     * passwd : 帐号密码
     * auto : false
     * prefix : guest
     * keys : [{"mobile":"13000000001"},{"deviceid":"46482168798654025"},{"<keytp>":"<key值>","info":"..."},"..."]
     * appid : 1.0001
     * seq : 123
     */
    private String uname;
    private String passwd;
    private boolean auto;
    private String prefix;
    private String appid;
    private String seq;
    private List<KeysBean> keys;

    private Map<String,String> statistics;

    @Data
    @ToString
    public static class KeysBean {
        /**
         * mobile : 13000000001
         * deviceid : 46482168798654025
         * <keytp> : <key值>
         * info : ...
         */
        //KeysBean对象设置deviceid、openid、unionid、cacc、mobile、nickname时候，设置一个就可以
        private String deviceid;
        private String openid;
        private String unionid;
        private String cacc;
        private String mobile;
        private String nickname;
        private String info;
    }
}
