package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 14:44
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/28日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BusinessServiceWzhrssWithDrawAccountDTO implements Serializable {
    private static final long serialVersionUID = -7649703216720796548L;
    /**
     * balance : 72.4
     * bankcardno : 999988887777
     * bankno : 1001
     * msg : ok
     * result : 1
     * seq : null
     */
    private Double balance;
    private String bankcardno;
    private String bankno;
    private String msg;
    private Integer result;
    private Object seq;
}
