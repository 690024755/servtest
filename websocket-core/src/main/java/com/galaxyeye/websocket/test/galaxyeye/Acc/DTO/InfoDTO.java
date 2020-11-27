package com.galaxyeye.websocket.test.galaxyeye.Acc.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.DTO
 * @Date Create on 15:10
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InfoDTO {

    /**
     * AuthMethods : {}
     * RequestEncode : utf8
     * OpenMethods : {"deletearticle":{},"queryusercountbydate":{},"fetchqaexpertmessage":{},"addeuser":{},"addappid":{},"getappid":{},"getarticle":{},"userlogin":{},"createmessage":{},"logout":{},"savearticle":{},"createappidtoken":{},"getdiscountcoupon":{},"checkaccesstoken":{},"createqaexpertmessage":{},"refreshtoken":{},"setimages":{},"uploadimageform":{},"updappid":{},"getusercollect":{},"getqaexpertmessagelist":{},"refreshaccesstoken":{},"updeuser":{},"checktoken":{},"getuser":{},"getarticleaddinfo":{},"auditarticle":{},"msgtomq":{},"createaccesstoken":{},"login":{},"matcharticle":{},"resetpass":{},"euserlogin":{},"getappidaccess":{},"articlelistquery":{},"geterobotpkg":{},"articlelistquerybysession":{},"userlogout":{},"querytotalusercount":{},"replyqaexpertmessage":{},"authtoken":{},"fetcharticle":{},"delappid":{},"articlestatistics":{},"registerqaexpert":{},"adderobotpkg":{},"queryloginusercountbydate":{},"geterobot":{},"uploadimage":{}}
     * WXAppid :
     * Name : 1.00002
     * AllowIps : [[{"Min":0,"Max":255},{"Min":0,"Max":255},{"Min":0,"Max":255},{"Min":0,"Max":255}]]
     * Desc :
     * SignKey : 1234567812345678
     * MaskMode : {}
     * BmAppid : {"euid":1103539011445592064,"apptype":"1","erid":0,"pid":0,"pkgs":[],"fromAppid":"1.00002","groupTag":"1.00002yy","identity":"1.00002","bmAppid":"1.00002","appkey":"1234567812345678","callbackUrl":"http://127.0.0.1:28080/aiChatResp","desc":"1.00002yy","info":{"openMethods":["createappidtoken","queryusercountbydate","getdiscountcoupon","matcharticle","getarticle","getusercollect","fetcharticle","articlestatistics","getappid","geterobotpkg","adderobotpkg","geterobot","userlogin","userlogout","login","logout","uploadimage","savearticle","articlelistquery","articlelistquerybysession","getarticleaddinfo","auditarticle","deletearticle","setimages","createmessage","querytotalusercount","queryloginusercountbydate","registerqaexpert","createqaexpertmessage","replyqaexpertmessage","getqaexpertmessagelist","fetchqaexpertmessage","msgtomq","uploadimageform","euserlogin","checktoken","addeuser","updeuser","addappid","updappid","delappid","getuser","resetpass","authtoken","refreshtoken","createaccesstoken","refreshaccesstoken","checkaccesstoken","getappidaccess"],"allowIps":["*.*.*.*"],"privilegeLevel":1},"status":0}
     * AllowTopics : {}
     * ResponseEncode : utf8
     * WXSecret :
     */

    private AuthMethodsBean AuthMethods;
    private String RequestEncode;
    private OpenMethodsBean OpenMethods;
    private String WXAppid;
    private String Name;
    private String Desc;
    private String SignKey;
    private MaskModeBean MaskMode;
    private BmAppidBean BmAppid;
    private AllowTopicsBean AllowTopics;
    private String ResponseEncode;
    private String WXSecret;
    private java.util.List<java.util.List<AllowIpsBean>> AllowIps;

    public static class AuthMethodsBean {

    }
    public static class OpenMethodsBean {

    }
    public static class MaskModeBean {

    }
    public static class BmAppidBean {

    }
    public static class AllowTopicsBean {

    }
    public static class AllowIpsBean {

    }
}
