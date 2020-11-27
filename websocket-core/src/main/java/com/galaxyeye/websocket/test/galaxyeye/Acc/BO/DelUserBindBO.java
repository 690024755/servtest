package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 15:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/15日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;
import sun.rmi.transport.Transport;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@ToString
public class DelUserBindBO implements Serializable {
    private static final long serialVersionUID = -7336846532441179291L;
    /**
     * keys : [{"keytp":"value"},{"openid":"o3_0727_01"},{"unionid":"u3_0727_01"}]
     * uname : !225676
     * appid : 1.00003
     */
    private String uname;
    private String appid;
    private List<KeysBean> keys;

    @Data
    @ToString
    public static class KeysBean {
        /**
         * keytp : value
         * openid : o3_0727_01
         * unionid : u3_0727_01
         */
        private String unionid;
        private String deviceid;
        private String openid;
        private String cacc;
        private String nickname;
        private String email;
        private String mobile;
        //test为不存在的信息,在表查询不到t_keyinfo
        private String test;

    }

}
