package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 11:28
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/10/27日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class TencentBO implements Serializable {
    private static final long serialVersionUID = -7299569821310123925L;
    /**
     * openid : {"Value":"","Info":"openid"}
     * unionid : {"Value":"TestLoginByWxcodeWithAllOpenidIsEmpty","Info":"unionid"}
     */
    private OpenidBean openid;
    private UnionidBean unionid;

    @Data
    @ToString
    public static class OpenidBean {
        /**
         * Value :
         * Info : openid
         */
        private String Value;
        private String Info;
    }

    @Data
    @ToString
    public static class UnionidBean {
        /**
         * Value : TestLoginByWxcodeWithAllOpenidIsEmpty
         * Info : unionid
         */
        private String Value;
        private String Info;
    }
}
