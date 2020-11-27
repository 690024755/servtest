package com.galaxyeye.websocket.test.galaxyeye.AutoTest.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase.GetAppidTest;
import com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase.LoginTest;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Slf4j
@Component
public class autotestyy extends BaseTest {

    @Test
    public void test1(){
        try{
            log.info("autotestyy test1" );
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }


    @Test
    public void test2(){
        try{
            log.info("autotestyy test2" );
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }


    @Test
    public void test3(){
        try{
            log.info("autotestyy test3" );
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    @Test
    public void test4(){
        try{
            log.info("autotestyy test4" );
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    @Test
    public void test5(){
        try{
            log.info("autotestyy test5" );
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    @Test
    public void test6(){
        try{
            log.info("autotestyy test6" );
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void test7(){
        try{
            UUID token=UUID.randomUUID();
            jedisCluster.set("1",token.toString());
            String token_in=jedisCluster.get("1");
            log.info("token_in =" +token_in);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    @Autowired
    private JedisTemplate jedisTemplate;
    @Test
    public void test8(){
        try{
            UUID token=UUID.randomUUID();
            jedisTemplate.set("1",token.toString());
            String token_in=(String)jedisTemplate.get("1");
            log.info("token_in =" +token_in);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }



    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }
}
