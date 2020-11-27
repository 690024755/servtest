package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 11:49
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/1/6日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class DelAppidBO implements Serializable {
    private static final long serialVersionUID = 6261977854931164719L;
    /**
     * bmAppid : bm1108626337150537728
     * appid : 1.00001
     * seq : aaa
     */
    private String bmAppid;
    private String appid;
    private String seq;
}
