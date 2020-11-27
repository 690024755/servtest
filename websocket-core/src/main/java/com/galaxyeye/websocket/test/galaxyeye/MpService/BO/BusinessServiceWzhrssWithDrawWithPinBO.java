package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 16:02
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/27日galaxyeye All Rights Reserved.
 */
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
public class BusinessServiceWzhrssWithDrawWithPinBO implements Serializable {
    private static final long serialVersionUID = 1695830837392577893L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xnfmC01QQX6txsES8LW7h0NbAryj24AfqhMiZqzWiPDUah0lsL97N7va3E01bdsil
     * bmAppid : 4.00047
     * appid : 4.00047
     * seq : 1
     * cardno : 123456789012345678
     * acckind : 06
     * amount : 123.4
     * bankno : 1001
     * bankcardno : 62261234567897125211
     * pin : nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xnfmC01QQX6txsES8LW7h0N
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private Object seq;
    private String cardno;
    private String acckind;
    private Double amount;
    private String bankno;
    private String bankcardno;
    //
    private String pin;
}
