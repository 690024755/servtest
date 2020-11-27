package com.galaxyeye.websocket.tool.websocket.client.message; /*
 * Description:com.galaxyeye.websocket.tool.websocket.client.message
 * @Date Create on 14:16
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/16日galaxyeye All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.List;

public class WebsocketMessage {
    private static List<String> MultipleMessage=new ArrayList<>();
    public static void saveMultipleMessage(String message){
        MultipleMessage.add(message);
    }
    public static List<String> getMultipleMessage(){
        List<String> multipleMessageTmp=new ArrayList<>();
        multipleMessageTmp.addAll(MultipleMessage);
        MultipleMessage.clear();
        return multipleMessageTmp;
    }
}
