package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 11:42
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/1/6日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class AddAppidBO implements Serializable {
    private static final long serialVersionUID = 1679933053675641318L;
    /**
     * euid : 1103539011445592064
     * erid : 1105651125404569600
     * apptype : 6
     * identity : 省公众号微信appid
     * groupTag :
     * desc : 省公众号
     * callbackUrl : http://xxxxxx/xxx
     * info : {"allowIps":["*.*.*.*"],"openMethods":["login","logout"],"privilegeLevel":0}
     * appid : 1.00001
     */
    private Long euid;

    private Long erid;
    private String apptype;

    private String identity;
    private String groupTag;
    private String desc;
    private String callbackUrl;
    private InfoBean info;
    private String appid;
    private String bmAppid;
    //1为超管权限，非1为普通权限，可选字段，不填写默认为0
    private Integer ___super;
    private Integer protVer;
    @Data
    @ToString
    public static class InfoBean {
        /**
         * allowIps : ["*.*.*.*"]
         * openMethods : ["login","logout"]
         * privilegeLevel : 0
         */
        private Integer privilegeLevel;
        private Integer guestUpperLimit;
        private Integer guestPeriodValid;
        private Integer normalUpperLimit;
        private List<String> allowIps;
        private List<String> openMethods;
    }
}
