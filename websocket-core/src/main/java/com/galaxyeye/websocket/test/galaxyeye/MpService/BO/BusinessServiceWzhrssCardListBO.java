package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 14:31
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/24日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BusinessServiceWzhrssCardListBO implements Serializable {
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xnfmC01QQX6txsES8LW7h0NbAryj24AfqhMiZqzWiPDUah0lsL97N7va3E01bdsil
     * bmAppid : 4.00047
     * appid : 4.00047
     * seq : 1
     * certno : 123456789012345678
     * certtype : 01
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private Integer seq;
    //证件号码
    private String certno;
    //身份证类型
    private String certtype;
}
