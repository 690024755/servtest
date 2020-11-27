package com.galaxyeye.websocket.tool.SpringContext; /*
 * Description:com.galaxyeye.websocket.tool.SpringContext
 * @Date Create on 13:11
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/26日galaxyeye All Rights Reserved.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@Slf4j
@Component
public class SpringContextHolder implements ApplicationContextAware{
    public static ApplicationContext contextHolder;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        contextHolder=applicationContext;
    }

    public ApplicationContext getcontextHolder(){
        return contextHolder;
    }
}
