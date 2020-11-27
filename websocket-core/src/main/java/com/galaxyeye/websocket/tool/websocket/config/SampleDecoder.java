package com.galaxyeye.websocket.tool.websocket.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

@Slf4j
public class SampleDecoder implements Decoder.Text<String> {

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }






    public String decode(String paramString) throws DecodeException {

        return paramString.toLowerCase();
    }

    public boolean willDecode(String paramString) {

        return true;
    }

}
