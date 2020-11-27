package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 11:34
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/27日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class SendImageUrlBO implements Serializable {
    private static final long serialVersionUID = -3309591240931728925L;
    /**
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xupmOd4Veq-fMvNGkU-T96hszwDyOykY42gNUTSsL_W71Q3ep56lYzVJtJw8CULpt
     * appid : 3.00014
     * bmAppid : 3.00014
     * seq : 123
     * roomId : 1215945756511309824
     * urls : ["www.baidu.com","www.google.com"]
     * uid : 100005
     */
    private String token;
    private String accessToken;
    private String appid;
    private String bmAppid;
    private String seq;
    //trafficpolice_room.id或者trafficpolice_message.room_id
    private String roomId;
    private Long uid;
    //接口=BusinessService/trafficpolice/uploadimageform返回url地址
    private List<String> urls;
}
