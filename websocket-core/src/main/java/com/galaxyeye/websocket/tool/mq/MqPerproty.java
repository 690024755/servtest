package com.galaxyeye.websocket.tool.mq; /*
 * Description:com.galaxyeye.websocket.test.mqTest
 * @Date Create on 12:39
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/21日galaxyeye All Rights Reserved.
 */

import lombok.Data;

public class MqPerproty {

    public void setMQ_QUEUE(String MQ_QUEUE) {
        this.MQ_QUEUE = MQ_QUEUE;
    }

    private   String MQ_QUEUE;

    public static String queue=null;

    public void intiMqPerproty(){
        queue=this.MQ_QUEUE;
    }

}
