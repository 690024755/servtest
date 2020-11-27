package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 11:46
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/21日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class OnLineBO implements Serializable {
    /**
     * appId : 1.00002
     * token : q-R-s9wmfpuW78xhZBk2kYYtQ80-WK2VAYkDnK6OjF-YdtcVlaJTheWlRUQJ7xbMXjqKG3RigzBs0BoFq902UK7567_Z8HXfwQf51ls0T1df8RHPpgEgxrYpbTOBJcqfwpcGd4ATNMLdt5ZC1xAzDA==
     * type : online
     */
    private String appId;
    private String token;
    private String type;

}
