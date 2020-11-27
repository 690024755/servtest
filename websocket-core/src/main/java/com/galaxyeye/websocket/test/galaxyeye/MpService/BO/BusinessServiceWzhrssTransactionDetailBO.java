package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 13:48
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/25日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BusinessServiceWzhrssTransactionDetailBO implements Serializable {
    private static final long serialVersionUID = 8942892453139028890L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-xnfmC01QQX6txsES8LW7h0NbAryj24AfqhMiZqzWiPDUah0lsL97N7va3E01bdsil
     * bmAppid : 4.00047
     * appid : 4.00047
     * seq : 1
     * cardno : 12345678901234567890
     * rectype : 0
     * acckind : 01
     * startdate : 200521
     * enddate : 200821
     * index : 1
     * count : 5
     */
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private Object seq;
    //市民卡卡号
    private String cardno;
    /**
     *  0：全部 、 1：消费 、
     *  2：退货  、 3：充值  、
     *  4：提现  、 5：退现 （不填为全部）
     */
    private Integer rectype;
    //01=脱机（点击钱包查询）、 02=联机（市民卡账户查询），为空查询所有、06=车改户
    private String acckind;
    private String startdate;
    private String enddate;
    private Integer index;
    private Integer count;

}
