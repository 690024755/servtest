package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:30
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class MsgToMqBO implements Serializable {

    private static final long serialVersionUID = -69015780836978540L;
    /**
     * msg : {"a":"123","b":456}
     * topic : mplog
     * uid : 100001
     * token : y-zxo5CH-3yW1EIdh1_iCNOJL-6Lt85yGdk78P6oVlAbpIJEmQygEtFrMM8CDoX2RhDGzV7Fpe7Z0o0u7HvKHBXYzOBVQmX2kzNfu_hq8oNFA5W_75byoHoIXLUBXZVd
     * bmAppid : 3.00014
     * appid : 3.00014
     * seq : null
     */

    /**
     * map的json字符串
     */
    private String msg;
    private String topic;
    private Integer uid;
    private String token;
    private String bmAppid;
    private String appid;
    private Object seq;
    private String accessToken;

}
