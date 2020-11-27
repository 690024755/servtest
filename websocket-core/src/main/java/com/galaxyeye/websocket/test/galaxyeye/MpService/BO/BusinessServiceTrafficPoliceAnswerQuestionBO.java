package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 16:32
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/6日galaxyeye All Rights Reserved.
 */
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class BusinessServiceTrafficPoliceAnswerQuestionBO implements Serializable {
    private static final long serialVersionUID = 8620143935292309844L;
    /**
     * appid : 3.00014
     * bmAppid : 3.00014
     * seq : abc
     * token : 6DQNyP5-QoKyhwSrcflU29WPI86kSi5gmso7yb_O3qPzy-CORkbUNxD_1mDP_yu56gLJ3BDZ1l2M9FQs6elSdAha3Z0vOm8p3jc-rDzDe7-HjqtF-NlPMEUVllvXlnMJ9NKeZ_q8Nxd8UrM7wSkySg==
     * uid : 225858
     * id : 12345
     * answer : [0,1]
     */
    private String appid;
    private String bmAppid;
    private String seq;
    private String token;
    private Integer uid;
    private Integer id;
    private List<Integer> answer;
}
