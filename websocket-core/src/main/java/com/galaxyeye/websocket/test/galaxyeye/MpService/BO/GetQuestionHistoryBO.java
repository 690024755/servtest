package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:58
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/26日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class GetQuestionHistoryBO implements Serializable {
    private static final long serialVersionUID = 8250928750133713461L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-x0-faJURTKcIVCjLgU_sKqalhPAzwVWUWc8RfzaTR6RuM4V_oWoD20RrYll5rIty3
     * appid : 3.00014
     * date : 2020-05-11
     */
    private String uid;
    private String token;
    private String appid;
    private String date;
    private String bmAppid;
    private String accessToken;

}
