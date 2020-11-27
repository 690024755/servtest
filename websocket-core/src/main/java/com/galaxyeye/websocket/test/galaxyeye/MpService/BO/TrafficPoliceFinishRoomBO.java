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
public class TrafficPoliceFinishRoomBO implements Serializable {
    private static final long serialVersionUID = -4328104971951729061L;
    /**
     * accessToken : PlWoRt8I5vMeQ5lony2wORWwsFsYeEz8FxK0iiDvY9jW-hnBq_NI6VyxCt4FI5FxdC56gC9fTYs_bp3z4DL0K57pJk3VUTKH_hMKPrfOUG2YR-zHhiDCF3vKSy-NvCgz
     * bmAppid : 3.00014
     * seq : 123
     * roomId : 199279
     */
    private String accessToken;
    private String bmAppid;
    private String uid;
    private String token;
    private String appid;
    //trafficpolice_room.remote_room_id
    private Long roomId;
}
