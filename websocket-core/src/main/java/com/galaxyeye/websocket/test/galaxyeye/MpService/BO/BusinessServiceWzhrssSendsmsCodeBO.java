package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 16:14
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/26日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BusinessServiceWzhrssSendsmsCodeBO implements Serializable {
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xnfmC01QQX6txsES8LW7h0NbAryj24AfqhMiZqzWiPDUah0lsL97N7va3E01bdsil
     * bmAppid : 4.00047
     * appid : 4.00047
     * seq : 1
     * cardno : 12345678901234567890
     * smstype : 02
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private Object seq;
    //市民卡号
    private String cardno;
    //发送短信类型：01登录、02支付、03启用为启用交易密码、
    // 目前只有车改账户余额转出功能使用短信验证码，为02,只测试02，其他暂时不测
    private String smstype;

    private String phone;
}
