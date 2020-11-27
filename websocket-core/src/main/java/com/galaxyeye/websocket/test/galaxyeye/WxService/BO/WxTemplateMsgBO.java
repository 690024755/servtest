package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;

@Data
@ToString
public class WxTemplateMsgBO implements Serializable {

    private static final long serialVersionUID = 7644312872584724276L;
    /**
     * templateMsg : {"title":"","msg":{"专家工号":"工号","问题描述":"xxx"},"page":"index/index"}
     * notifyAppid : appid
     * dstUid : 100001
     * srcUid : 100002
     * accessToken : xxx
     * appid : xxx
     * seq :
     */

    /*
    templateMsg的格式is
    {
        "templateMsg":{
            "msg":{
                "专家工号":"工号",
                "问题描述":"xxx"
            },
            "page":"index/index",
            "title":""
        }
    }
     */
    private HashMap<String,Object> templateMsg;
    private String notifyAppid;
    private Integer dstUid;
    private Integer srcUid;
    private String accessToken;
    private String appid;
    private String seq;


}
