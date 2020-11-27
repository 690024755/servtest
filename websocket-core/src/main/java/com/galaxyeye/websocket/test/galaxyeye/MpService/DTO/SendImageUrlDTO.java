package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 13:33
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/28日galaxyeye All Rights Reserved.
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class SendImageUrlDTO implements Serializable {
    private static final long serialVersionUID = -5346693605168314864L;

    /**
     * msg : urls is empty
     * result : 101
     * seq : null
     */
    private String msg;
    private int result;
    private Object seq;

}
