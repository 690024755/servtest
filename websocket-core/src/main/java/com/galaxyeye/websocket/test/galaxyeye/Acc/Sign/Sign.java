package com.galaxyeye.websocket.test.galaxyeye.Acc.Sign;

import com.alibaba.dubbo.common.URL;
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.encr.MD5Utils;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Log4j
public class Sign {

    /**
     * 腾讯的appId
     */
    private final static Integer appId=2119005254;

    public static Integer getAppId() {
        return appId;
    }

    public static String getSign(Map<String, String> param,String appKey) {
        /**
         * 腾讯的、appKey
         */

        //String appKey = "3GIpmWVgwi7Kynyo";

        //ocr
        //String appKey = "zUSieIF192cndpeR";
        return getSign(appKey,param);
    }

    public static String getSign(Map<String, String> param,String appKeyName,String appKeyValue,boolean isLower) {
        return getSign(appKeyName,appKeyValue,param,isLower);
    }

    /**
     * 获取sign
     */
    public static String getSign(String appKey,Map<String, String> param) {
        String sign="";
        try{
        sign= SortUtils.formatUrlParam(param,"UTF-8",true)+"&app_key="+appKey;
            log.info("字典排序且对map中的value做URLEncoder后结果:"+sign);
            return sign;
        } catch (Exception e) {
            log.error("获取sign失败！" + e);
        }
        return null;
    }

    public static String getSign(String appKeyName,String appKeyValue,Map<String, String> param,boolean isLower) {
        String sign="";
        try{
            sign= SortUtils.formatUrlParam(param,"UTF-8",isLower)+"&"+appKeyName+"="+appKeyValue;
            log.info("字典排序且对map中的value做URLEncoder后结果:"+sign);
            return sign;
        } catch (Exception e) {
            log.error("获取sign失败！" + e);
        }
        return null;
    }

}
