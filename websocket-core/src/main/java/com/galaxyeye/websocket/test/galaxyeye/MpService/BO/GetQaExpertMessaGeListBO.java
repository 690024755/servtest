package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:18
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class GetQaExpertMessaGeListBO implements Serializable {

    /**
     * uid : 100003
     * token : q8nhquUsFXdMNE3hHSnWZAQFD5o1ibnIABFfCIA8MGSuXQ_QsB4PT5dvWuB8ceOpIS7TWDGtyNrTtrV0SORzldWiOnbUz58UAGk-C4nAWQw0O8lzJwY9s2oISnT84w_2
     * bmAppid : 3.00014
     * appid : 3.00014
     * nickname : tester1
     * character : 2
     * replied : 0
     * begin : 2019-11-14 11:19:00
     * end : 2019-11-14 11:20:00
     */
    private String accessToken;
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private String nickname;
    //character=1为专家用户
    //character=2为普通用户
    private Integer character;
    //replied=0为返回回复里包括未回复与已回复消息
    //replied=1为返回未回复
    //replied=2为返回已回复
    private Integer replied;
    //查询表qa_expert_board.created_at留言起始时间
    private String begin;
    //查询表qa_expert_board.created_at留言结束时间
    private String end;
    private String seq;
}
