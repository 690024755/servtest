package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 19:03
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/27日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UploadImageFormDTO implements Serializable {
    private static final long serialVersionUID = 7939996198911125300L;
    /**
     * msg : ok
     * result : 1
     * seq : abc
     * url : http://jumpserver-storage.oss-cn-hangzhou.aliyuncs.com/wzjj/advice/img/yy20200427/1254727625344028672.png
     */
    private String msg;
    private int result;
    private String seq;
    private String url;
}
