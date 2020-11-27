package com.galaxyeye.websocket.tool.domain; /*
 * Description:com.galaxyeye.websocket
 * @Date Create on 10:41
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/11日galaxyeye All Rights Reserved.
 */

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseDomain {

    public BaseDomain() {
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
