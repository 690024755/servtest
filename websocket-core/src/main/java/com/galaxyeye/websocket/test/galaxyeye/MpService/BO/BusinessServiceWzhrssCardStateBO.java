package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 9:37
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/27日galaxyeye All Rights Reserved.
 */
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class BusinessServiceWzhrssCardStateBO implements Serializable {
    private static final long serialVersionUID = 7566900443055446972L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xnfmC01QQX6txsES8LW7h0NbAryj24AfqhMiZqzWiPDUah0lsL97N7va3E01bdsil
     * bmAppid : 4.00047
     * appid : 4.00047
     * seq : 1
     * cardno : 12345678901234567890
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private Object seq;
    //市民卡卡号
    private String cardno;
}
