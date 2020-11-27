package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 9:29
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class ReplyQaExpertMessageBO implements Serializable {

    private static final long serialVersionUID = -8332070348259021381L;

    /**
     * uid : 100003
     * token : q8nhquUsFXdMNE3hHSnWZAQFD5o1ibnIABFfCIA8MGSuXQ_QsB4PT5dvWuB8ceOpIS7TWDGtyNrTtrV0SORzldWiOnbUz58UAGk-C4nAWQw0O8lzJwY9s2oISnT84w_2
     * bmAppid : 3.00014
     * appid : 3.00014
     * nickname : tester4
     * qaId : 1194817505588154368
     * content : 测试内容回答
     * replyType : 1
     */
    private String accessToken;
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    /**
     * 专家的昵称
     */
    private String nickname;
    private String qaId;
    private String content;
    /**
     * 1为专家
     * 2为普通用户
     */
    private Integer replyType;
    private String seq;

}
