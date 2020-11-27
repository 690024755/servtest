package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 17:04
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/25日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
public class BusinessServiceWzhrssBankListBO implements Serializable {
    private static final long serialVersionUID = 6347806843165309216L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xnfmC01QQX6txsES8LW7h0NbAryj24AfqhMiZqzWiPDUah0lsL97N7va3E01bdsil
     * bmAppid : 4.00047
     * appid : 4.00047
     * seq : 1
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String accessToken;
    private String appid;
    private Object seq;
}
