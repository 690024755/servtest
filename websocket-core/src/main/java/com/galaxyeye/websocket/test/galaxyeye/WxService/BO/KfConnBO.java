package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 18:59
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/20日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class KfConnBO implements Serializable {

    private static final long serialVersionUID = -473814244740810471L;

    /**
     * method : kfConnReq
     * uid : 102887
     * context : [{"msg":["消息1"],"msgType":"common","type":"robot"},{"msg":["消息2图片1","消息2图片2"],"msgType":"pic","type":"robot"}]
     * bmAppid : 4.00002
     * wxAppid : wx16f2e50e562f4220
     */
    private String method;
    private Integer uid;
    private String bmAppid;
    private String wxAppid;
    private List<ContextBean> context;

    @Data
    @ToString
    public static class ContextBean {
        /**
         * msg : ["消息1"]
         * msgType : common
         * type : robot
         */
        private String msgType;
        private String type;
        private List<String> msg;
    }
}
