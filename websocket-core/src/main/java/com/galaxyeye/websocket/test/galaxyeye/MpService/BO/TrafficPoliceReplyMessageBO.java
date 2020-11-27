package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:57
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/1/10日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class TrafficPoliceReplyMessageBO implements Serializable {
    private static final long serialVersionUID = 2410361520830167770L;
    /**
     * accessToken : PlWoRt8I5vMeQ5lony2wORWwsFsYeEz8FxK0iiDvY9jW-hnBq_NI6VyxCt4FI5FxdC56gC9fTYs_bp3z4DL0K57pJk3VUTKH_hMKPrfOUG2YR-zHhiDCF3vKSy-NvCgz
     * bmAppid : 3.00014
     * seq : 123
     * roomId : 199279
     * content : 回复内容
     */
    private String accessToken;
    private String bmAppid;
    private String appid;
    private String token;
    private Integer uid;
    /**
     * 会话id,RoomId=trafficpolice_room.remote_room_id设置remote room id
     */
    private Long roomId;
    private String content;
}
