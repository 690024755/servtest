package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 16:34
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/26日galaxyeye All Rights Reserved.
 */
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
public class BusinessServiceWzhrssWithDrawWithSmsBO implements Serializable {
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
     * smsno : 2802801
     * smscode : 123456
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private Object seq;
//    市民卡号
    private String cardno;
//    01=脱机（电子钱包查询） 、02=联机（市民卡账户查询）、为空查询所有、06=车改户
    private String acckind;
    //提现金额
    private Double amount;
    //银行代码
    private String bankno;
    //银行卡号
    private String bankcardno;
    //短信流水号
    private String smsno;
    //短信验证码
    private String smscode;
}
