package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 10:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class FetchQaExpertMessageBO implements Serializable {

    private static final long serialVersionUID = -496354669807684122L;
    /**
     * uid : 100003
     * token : q8nhquUsFXdMNE3hHSnWZAQFD5o1ibnIABFfCIA8MGSuXQ_QsB4PT5dvWuB8ceOpIS7TWDGtyNrTtrV0SORzldWiOnbUz58UAGk-C4nAWQw0O8lzJwY9s2oISnT84w_2
     * bmAppid : 3.00014
     * appid : 3.00014
     * id : 1194817505588154368
     */

    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private String id;
    private String accessToken;
    private String seq;
}
