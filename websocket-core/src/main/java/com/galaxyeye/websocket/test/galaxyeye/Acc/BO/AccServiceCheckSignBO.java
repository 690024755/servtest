package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 14:47
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/10/9日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
@Data
@ToString
public class AccServiceCheckSignBO implements Serializable {
    /**
     * appid : 3.00016
     * bmAppid : 3.00016
     * accessToken : 021094da1b80fbfdfbd2adc9bca933f8614386b2cd5d1e6dac74874e5c6af2f
     * signAppid : 1.00003
     * sign : 632a362c1c0f11af8f8fb3573fec9d5a
     * randomSeed : 19930317
     */
    private String appid;
    private String bmAppid;
    private String uid;
    private String accessToken;
    private String token;
    private String signAppid;
    private String sign;
    private String randomSeed;
}
