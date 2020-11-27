package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 15:43
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/27日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
public class BusinessServiceWzhrssStartTransactionCodeBO implements Serializable {
    private String uid;
    private String token;
    private String bmAppid;
    private String appid;
    private Object seq;
    //市民卡号
    private String cardno;
    //启用密码密文，对输入的明文密码要求：密码长度 >= 6 byte , 仅包含字母和数字，前端设置的数据强度为6等级
    private String pin;
}
