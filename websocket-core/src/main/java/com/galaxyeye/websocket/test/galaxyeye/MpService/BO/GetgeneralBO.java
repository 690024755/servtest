package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 16:38
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/21日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GetgeneralBO implements Serializable {
    private static final long serialVersionUID = -4674526783293985681L;
    /**
     * token : 5IC9s6WcHg0kp3Hv4EIPRb0cou7cc-d93Afr_dU3saQWYWcb_JiN_aE4ZVmCyDQtWy3Q1MZGoQfkimgPlKh3Y8UZM1XYusgAVLUs_e8TvofRiV3B1RrjuCAcKEJ7v0_S
     * appid : 4.00090
     * bmAppid : 4.00090
     * uid : 631604
     * env : dev
     * version : 5.0.0
     * type : object
     * key : healthPlanAbs
     */
    private String token;
    private String appid;
    private String bmAppid;
    private String uid;
    private String env;
    private String version;
    private String type;
    private String key;
}
