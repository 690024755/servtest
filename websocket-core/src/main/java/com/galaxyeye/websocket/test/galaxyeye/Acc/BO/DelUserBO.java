package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 13:29
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/18日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class DelUserBO implements Serializable {
    private static final long serialVersionUID = -3374327102378351988L;
    /**
     * uid : 1108669183396155392
     * appid : 1.1001
     */
    private Long uid;
    private String appid;
    private String seq;

}
