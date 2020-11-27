package com.galaxyeye.websocket.web.controller.web.test; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.AIRecognition
 * @Date Create on 10:55
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/28日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.XmlCaseRepository;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.tool.domain.Result;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import static java.util.Arrays.asList;


@Slf4j
@Controller
@RequestMapping("/api/test/web")
public class TestController  {

    @Autowired
    private JedisTemplate jedisTemplate;

    Lock lock=new ReentrantLock();

    /**
     *获取token操作
     * @return
     */
    @RequestMapping(value ="/gettoken",method = RequestMethod.POST)
    @ResponseBody
    public Result<?> gettoken(String  uid) {
        log.info("打印gettoken方法,接口请求参数：uid="+uid);
        Result<String> result = new Result<>();
        if( uid == null  )
        {
            Map<Object,Object> map=new HashMap<>();
            map.put(uid,uid);
            result.setSuccess(false);
            result.setErrorMessage("请求参数为空！"+ JSON.toJSONString(map));
            log.info("请求参数为空！"+ JSON.toJSONString(map));
            return result;
        }
        try{
            lock.lock();
            if(jedisTemplate.hasKey(uid)){
                result.setData((String)jedisTemplate.get(uid));
                result.setSuccess(true);
            }else {
                UUID token=UUID.randomUUID();
                jedisTemplate.set(uid,token.toString(),3600);
                result.setData(token.toString());
                result.setSuccess(true);
            }
            lock.unlock();
        }catch (Exception e){
            log.error("gettoken: {}", e);
            result.setE(e);
            result.setSuccess(false);
        }
        log.info("打印gettoken方法,接口返回参数："+JSON.toJSONString(result));
        return result;
    }


    @RequestMapping(value ="/authtoken",method = RequestMethod.POST)
    @ResponseBody
    public Result<?> authtoken(String uid,String token) {
        log.info("打印authtoken方法,接口请求参数：uid="+uid+",token="+token);
        Result<String> result = new Result<>();
        if( uid == null  && token==null)
        {
            Map<Object,Object> map=new HashMap<>();
            map.put(uid,uid);
            map.put(token,token);
            result.setSuccess(false);
            result.setErrorMessage("请求参数为空！"+ JSON.toJSONString(map));
            log.info("请求参数为空！"+ JSON.toJSONString(map));
            return result;
        }
        try{
            String token_in=(String)jedisTemplate.get(uid);
            if(token_in != null && token_in.equals(token) ){
                result.setData("token验证通过");
                result.setSuccess(true);
            }else {
                result.setData("token验证不通过");
                result.setSuccess(false);
            }
        }catch (Exception e){
            log.error("authtoken: {}", e);
            result.setE(e);
            result.setSuccess(false);
        }
        log.info("打印authtoken方法,接口返回参数："+JSON.toJSONString(result));
        return result;
    }


    /**
     * 加法接口
     * @param a
     * @param b
     * @return
     */
    @RequestMapping(value ="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result<?> add(Integer a, Integer b) {
        log.info("打印add方法,接口请求参数：a="+a+",b="+b);
        Result<Integer> result = new Result<>();
        if( a == null  && b==null )
        {
            Map<Integer,Integer> map=new HashMap<>();
            map.put(a,a);
            map.put(b,b);
            result.setSuccess(false);
            result.setErrorMessage("请求参数为空！"+ JSON.toJSONString(map));
            log.info("请求参数为空！"+ JSON.toJSONString(map));
            return result;
        }
        try{
            result.setData(a+b);
            result.setSuccess(true);
        }catch (Exception e){
            log.error("add: {}", e);
            result.setE(e);
            result.setSuccess(false);
        }
        log.info("打印add方法,接口返回参数："+JSON.toJSONString(result));
        return result;
    }

    /**
     * 乘法接口
     * @param a
     * @param b
     * @return
     */
    @RequestMapping(value ="/muti",method = RequestMethod.POST)
    @ResponseBody
    public Result<?> muti(Integer a, Integer b) {
        log.info("打印muti方法,接口请求参数：a="+a+",b="+b);
        Result<Integer> result = new Result<>();
        if( a == null  && b==null )
        {
            Map<Integer,Integer> map=new HashMap<>();
            map.put(a,a);
            map.put(b,b);
            result.setSuccess(false);
            result.setErrorMessage("请求参数为空！"+ JSON.toJSONString(map));
            log.info("请求参数为空！"+ JSON.toJSONString(map));
            return result;
        }
        try{
            result.setData(a*b);
            result.setSuccess(true);
        }catch (Exception e){
            log.error("add: {}", e);
            result.setE(e);
            result.setSuccess(false);
        }
        log.info("打印muti方法,接口返回参数："+JSON.toJSONString(result));
        return result;
    }


    @RequestMapping(value ="/get",method = RequestMethod.GET)
    @ResponseBody
    public Result<?> get() {
        Result<String> result = new Result<>();
        log.info("服务端接收到locust请求");
        try{
            result.setData("locust 测试");
            result.setSuccess(true);
        }catch (Exception e){
            log.error("locust 测试异常", e);
            result.setE(e);
            result.setSuccess(false);
        }
        return result;
    }


}
