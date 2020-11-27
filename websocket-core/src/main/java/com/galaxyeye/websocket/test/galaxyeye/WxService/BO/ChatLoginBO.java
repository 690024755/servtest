package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 16:18
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/21日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Data
@ToString

public class ChatLoginBO implements Serializable {
    private static final long serialVersionUID = -4998117949686122865L;
    /**
     * type : chatLogin
     * appId : 1.00002
     * token : q-R-s9wmfpuW78xhZBk2kYYtQ80-WK2VAYkDnK6OjF-YdtcVlaJTheWlRUQJ7xbMXjqKG3RigzBs0BoFq902UFK1gVk_BVEUZNTnjBanl4NsWMejcr6NsDbpaXRjfbjP08qtdjLIiCJj6RRr3w2Izw==
     */
    private String type;
    private String appId;
    private String token;




}
