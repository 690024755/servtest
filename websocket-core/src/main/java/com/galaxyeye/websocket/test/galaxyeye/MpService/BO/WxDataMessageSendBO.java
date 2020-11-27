package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 14:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/10日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@ToString
public class WxDataMessageSendBO implements Serializable {
    private static final long serialVersionUID = -2236130106826184569L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xJspbKdfGsNItHNni6Lx5SQ3Evl1PGnyZ_ZFznxHJ6xi0q15bRgm4K37fVJWbpId0
     * bmAppid : 3.00014
     * appid : 3.00014
     * notifyAppid : 4.00024
     * dstUid : 716489
     * templateMsg : {"title":"留言审核通知","msg":{"留言内容":"我是留言内容","回复内容":"我是回复内容","文章标题":"我是文章标题"}}
     * miniState : developer
     */
    private String uid;
    private String token;
    private String accessToken;
    private String bmAppid;
    private String appid;
    private String notifyAppid;
    private Integer dstUid;
    private Map<String,Object> templateMsg;
//    跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
    private String miniState;

}
