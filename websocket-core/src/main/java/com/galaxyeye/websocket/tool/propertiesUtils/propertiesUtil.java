package com.galaxyeye.websocket.tool.propertiesUtils; /*
 * Description:com.galaxyeye.websocket.tool.propertiesLoaderUtils
 * @Date Create on 10:05
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/9日galaxyeye All Rights Reserved.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
public class propertiesUtil {


    public static Properties readProperty(String file) {
        Properties properties = new Properties();
        try {
            properties = PropertiesLoaderUtils.loadAllProperties(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    public static Set<String> readProperty2(String file) {
        Properties properties = new Properties();
        Set<String> objects =null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties(file);
            objects = properties.stringPropertyNames();
            for (String object : objects) {
                System.out.println(new String(properties.getProperty((String) object).getBytes("utf-8"), "utf-8"));
            }
        } catch (IOException e) {
            log.error("IOException :"+e);
        }
        return objects;
    }



    public static String readProperty3(String file) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(file);
        Enumeration enumeration = resourceBundle.getKeys();
        String result=null;
        while (enumeration.hasMoreElements()) {
            try {
                String value = resourceBundle.getString((String) enumeration.nextElement());
                result=new String(value.getBytes("iso-8859-1"), "gbk");
            } catch (UnsupportedEncodingException e) {
                log.error("UnsupportedEncodingException :"+e);
            }
        }
        return result;
    }


    public static Properties readProperty1(String file) {
        if(file.length()<=0 || file ==null){
            log.error("file is not exit"+file);
        }
        Properties properties = new Properties();
        InputStream inputStream = Object.class.getResourceAsStream(file);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("IOException :"+e);
        }
        return properties;
    }

}
