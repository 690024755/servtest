package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 14:54
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/21日galaxyeye All Rights Reserved.
 */

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateQaExpertMessageBO implements Serializable {

    private static final long serialVersionUID = 306400569942866143L;
    /**
     * uid : 100003
     * token : q8nhquUsFXdMNE3hHSnWZAQFD5o1ibnIABFfCIA8MGSuXQ_QsB4PT5dvWuB8ceOpIS7TWDGtyNrTtrV0SORzldWiOnbUz58UAGk-C4nAWQw0O8lzJwY9s2oISnT84w_2
     * bmAppid : 3.00014
     * appid : 3.00014
     * nickname : tester4
     * type : 2
     * content : 测试内容
     */


    private String accessToken;
    @NonNull
    private String uid;
    private String token;
    private String bmAppid;
    @NonNull
    private String appid;
    //专家的appid,普通用户提问题给专家，微信推送订阅消息给专家
    private String expertAppid;
    /**
     * 提问题的工号，一般写业务员的工号
     */
    private String nickname;
    /**
     *提问类型
     */
    private String type;
    private String content;
    private String seq;
}
