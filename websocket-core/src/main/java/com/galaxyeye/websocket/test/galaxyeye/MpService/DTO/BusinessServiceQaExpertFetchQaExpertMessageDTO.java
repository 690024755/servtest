package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 14:01
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/1日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class BusinessServiceQaExpertFetchQaExpertMessageDTO implements Serializable {
    /**
     * msg : ok
     * qaDetail : {"content":"咨询者问题","createdAt":"2020-07-01 14:25:05","nickname":"咨询者昵称","qaId":"1278213012766855168","repliedAt":"2020-07-01 14:25:10","reply":[{"content":"专家用户回复","createdAt":"2020-07-01 14:25:10","expert_no":"专家用户","id":"1278213034833088512","replyType":"1","uid":"0"},{"content":"专家用户回复","createdAt":"2020-07-01 14:25:05","expert_no":"专家用户","id":"1278213013379223552","replyType":"1","uid":"0"}],"type":"1","uid":225883}
     * result : 1
     * seq : null
     */
    private static final long serialVersionUID = -7978677719431229947L;
    private QaDetailBean qaDetail;
    private String msg;
    private Integer result;
    private Object seq;

    /**
     * {
     *         "content":"咨询者问题",
     *         "createdAt":"2020-07-01 13:52:00",
     *         "nickname":"咨询者昵称",
     *         "qaId":"1278204687073415168",
     *         "repliedAt":"2020-07-01 13:52:05",
     *         "reply":Array[2],
     *         "type":"1",
     *         "uid":225883
     *     }
     */
    @Data
    @ToString
    public static class QaDetailBean{
        private String content;
        private String createdAt;
        private String nickname;
        private String qaId;
        private String repliedAt;
        private String type;
        private String uid;
        private List<ReplyBean> reply;
    }

    /**
     * content : 专家用户回复
     * createdAt : 2020-07-01 13:52:05
     * expert_no : 专家用户
     * id : 1278204709571661824
     * replyType : 1
     * uid : 0
     */
    @Data
    @ToString
    public static class ReplyBean{
        private String content;
        private String createdAt;
        private String expert_no;
        private String id;
        private String replyType;
        private String uid;
    }
}
