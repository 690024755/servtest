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
import java.util.UUID;

@Data
@ToString
public class TrafficPoliceCreateRoomBO implements Serializable {
    private static final long serialVersionUID = -5472579746810152236L;
    /**
     * accessToken : PlWoRt8I5vMeQ5lony2wORWwsFsYeEz8FxK0iiDvY9jW-hnBq_NI6VyxCt4FI5FxdC56gC9fTYs_bp3z4DL0K57pJk3VUTKH_hMKPrfOUG2YR-zHhiDCF3vKSy-NvCgz
     * appid : 3.00014
     * bmAppid : 3.00014
     * seq : 123
     * name : ai小助手测试
     * contact : 13688888888
     * uid : 100005
     * unionId : AAADFSJHSKS
     * content : 回复内容
     */
    private String accessToken;
    private String token;
    private String appid;
    private String bmAppid;
    private String seq;
    private String name;
    private String contact;
    private Integer uid;
    private String unionId;
    private String content;
    private UUID uuid;
}
