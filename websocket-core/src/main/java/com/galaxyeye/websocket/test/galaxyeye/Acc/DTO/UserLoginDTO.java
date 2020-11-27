package com.galaxyeye.websocket.test.galaxyeye.Acc.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.DTO
 * @Date Create on 16:43
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/12/4日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class UserLoginDTO {

    /**
     * msg :
     * result : 1
     * uid : 225778
     * uname : wx_pbkfs2dg
     * keys : {"unionid":{"value":[{"modifyTime":1566279154,"appid":"1.00003","value":"NBind_openid_0812_01","info":"bindNo1forOpenid"}],"info":"bindNo1forOpenid"},"openid":{"value":[{"modifyTime":1566203138,"appid":"1.00003","value":"openid"}]}}
     * newUser : false
     * seq : abc
     * token : STvKUZsYTlZ_Y_wnlQSBJ1WHNde-S7XTVtkaWtb53rpJHeq_9Dzvt50to2SX-n-wjqMz4zgdxG-8ZFzYH65uDYoFZhU73SQ9xA3J2DCILGXwsF9M0YwNGvj4KZVTKfYb
     */

    private String msg;
    private int result;
    private int uid;
    private String uname;
    private KeysBean keys;
    private boolean newUser;
    private String seq;
    private String token;

    public static class KeysBean {
        /**
         * unionid : {"value":[{"modifyTime":1566279154,"appid":"1.00003","value":"NBind_openid_0812_01","info":"bindNo1forOpenid"}],"info":"bindNo1forOpenid"}
         * openid : {"value":[{"modifyTime":1566203138,"appid":"1.00003","value":"openid"}]}
         */
        private UnionidBean unionid;
        private OpenidBean openid;

        public static class UnionidBean {
            /**
             * value : [{"modifyTime":1566279154,"appid":"1.00003","value":"NBind_openid_0812_01","info":"bindNo1forOpenid"}]
             * info : bindNo1forOpenid
             */
            private String info;
            private List<ValueBean> value;

            public static class ValueBean {
                /**
                 * modifyTime : 1566279154
                 * appid : 1.00003
                 * value : NBind_openid_0812_01
                 * info : bindNo1forOpenid
                 */
                private int modifyTime;
                private String appid;
                private String value;
                private String info;
            }
        }

        public static class OpenidBean {
            private List<ValueBeanX> value;
            public static class ValueBeanX {
                /**
                 * modifyTime : 1566203138
                 * appid : 1.00003
                 * value : openid
                 */
                private int modifyTime;
                private String appid;
                private String value;
            }
        }
    }
}
