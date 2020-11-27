package com.galaxyeye.websocket.test.galaxyeye.IcService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.IcService.BO
 * @Date Create on 14:39
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/2日galaxyeye All Rights Reserved.
 */
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
@Data
@ToString
public class AppAccountAppLoginBO implements Serializable {
    private static final long serialVersionUID = 8157640610392305741L;
    /**
     * appid : 1.00003
     * cid : c1
     * cpwd : 123457
     * type : 0
     */
    private String appid;
    private String cid;
    private String cpwd;
    //0:普通账号登录,1:专家账号登录
    private Integer type;
}
