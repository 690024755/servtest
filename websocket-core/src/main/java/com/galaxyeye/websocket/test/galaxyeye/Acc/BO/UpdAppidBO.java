package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 11:46
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
public class UpdAppidBO implements Serializable {
    private static final long serialVersionUID = 1635866430451225256L;

    /**
     * bmAppid : bm1108626337150537728
     * euid :
     * erid :
     * identity : 1.00002
     * groupTag :
     * desc : 1.00002
     * apptype : 6
     * callbackUrl : www.baidu.com
     * info : {"allowIps":["172.16.0.5","10.10.*.*","172.16.*.*"],"openMethods":["login","logout","userget","getappid"],"privilegeLevel":1}
     * appid : 1.00001
     * seq : aaa
     */
    private String bmAppid;
    //企业账号
    private String euid;
    //机器人账号
    private String erid;
    private String identity;
    private String groupTag;
    private String desc;
    private String apptype;
    private String callbackUrl;
    private InfoBean info;
    private String appid;
    private String seq;
    private String appkey;
    //1为超管权限，非1为普通权限，可选字段，不填写默认为0
    private Integer ___super;
    private Integer protVer;
    @Data
    @ToString
    public static class InfoBean {
        /**
         * allowIps : ["172.16.0.5","10.10.*.*","172.16.*.*"]
         * openMethods : ["login","logout","userget","getappid"]
         * privilegeLevel : 1
         */
        private Integer privilegeLevel;
        private Integer guestUpperLimit;
        private Integer guestPeriodValid;
        private Integer normalUpperLimit;
        private List<String> allowIps;
        private List<String> openMethods;
    }
}
