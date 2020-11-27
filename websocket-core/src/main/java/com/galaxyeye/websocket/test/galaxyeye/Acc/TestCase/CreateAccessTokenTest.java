package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 13:59
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/1/14日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.CreateAccessTokenBO;
import com.galaxyeye.websocket.tool.encr.MD5Utils;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class CreateAccessTokenTest extends BaseTest {


    @Test
    public void getAccessToken() throws Exception {
        String createaccesstokenUrl =null;
        CreateAccessTokenBO createAccessTokenBO =null;
        String createAccessTokenResult ="";
        try{
            createaccesstokenUrl = url+"/AccService/createaccesstoken";
            createAccessTokenBO = new CreateAccessTokenBO();
            createAccessTokenBO.setBmAppid("1.00002");
            createAccessTokenBO.setAppid("1.00002");
            createAccessTokenBO.setAppkey("1234567812345678");
            //参数TokenExpire不设置，默认创建有效期为2小时
            //参数TokenExpire设置，默认创建有效期为设置时间
            createAccessTokenBO.setTokenExpire(999999999);
            log.info("createaccesstokenUrl 请求的参数=" + createaccesstokenUrl);
            log.info("createAccessTokenBO 请求的参数=" + JSON.toJSONString(createAccessTokenBO));
            createAccessTokenResult = HttpUtil.postGeneralUrl(createaccesstokenUrl, "application/json", JSON.toJSONString(createAccessTokenBO), "UTF-8");
            log.info("createAccessTokenResult 返回结果=" + createAccessTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("生成永久的accesstoken");
            recordhttp.setUrl(createAccessTokenResult);
            recordhttp.setRequest(JSON.toJSONString(createAccessTokenBO));
            recordhttp.setResponse(createAccessTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createAccessTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(createAccessTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createAccessTokenResult.contains("accessToken"),true);
            Assert.assertEquals(createAccessTokenResult.contains("tokenExpire"),true);
            /**
             * 请求使用AccessToken、BmAppid或者Uid、token
             *         uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
             *         uploadImageBO.setBmAppid("1.00002");
             *         or
             *         uploadImageBO.setUid("417941");
             *         uploadImageBO.setToken("1.00002");
             */
        }
    }

    @Test
    public void generateAccessToken() throws Exception {
        String createaccesstokenUrl =null;
        CreateAccessTokenBO createAccessTokenBO =null;
        String createAccessTokenResult ="";
        try{
            createaccesstokenUrl = url+"/AccService/createaccesstoken";
            createAccessTokenBO = new CreateAccessTokenBO();
            createAccessTokenBO.setBmAppid("3.00016");
            createAccessTokenBO.setAppid("1.00002");
            createAccessTokenBO.setAppkey("q3cdz3Yt5K4PKO4V");
            //参数TokenExpire不设置，默认创建有效期为2小时
            //参数TokenExpire设置，默认创建有效期为设置时间
            createAccessTokenBO.setTokenExpire(999999999);
            log.info("createaccesstokenUrl 请求的参数=" + createaccesstokenUrl);
            log.info("createAccessTokenBO 请求的参数=" + JSON.toJSONString(createAccessTokenBO));
            createAccessTokenResult = HttpUtil.postGeneralUrl(createaccesstokenUrl, "application/json", JSON.toJSONString(createAccessTokenBO), "UTF-8");
            log.info("createAccessTokenResult 返回结果=" + createAccessTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("生成永久的accesstoken");
            recordhttp.setUrl(createAccessTokenResult);
            recordhttp.setRequest(JSON.toJSONString(createAccessTokenBO));
            recordhttp.setResponse(createAccessTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createAccessTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(createAccessTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createAccessTokenResult.contains("accessToken"),true);
            Assert.assertEquals(createAccessTokenResult.contains("tokenExpire"),true);
        }
    }


    /**
     * 根据appid 、BmAppid、Appkey获取accessToken
     * return accessToken
     * @throws Exception
     */
    @Test
    public void createaccesstokenTestByAppkey() throws Exception {
        String createaccesstokenUrl =null;
        CreateAccessTokenBO createAccessTokenBO =null;
        String createAccessTokenResult ="";
        try{
            createaccesstokenUrl = url+"/AccService/createaccesstoken";
            createAccessTokenBO = new CreateAccessTokenBO();
            createAccessTokenBO.setBmAppid("1.00002");
            createAccessTokenBO.setAppid("1.00002");
            createAccessTokenBO.setAppkey("1234567812345678");
            //参数TokenExpire不设置，默认创建有效期为2小时
            //参数TokenExpire设置，默认创建有效期为设置时间
            createAccessTokenBO.setTokenExpire(999999999);
            log.info("createaccesstokenUrl 请求的参数=" + createaccesstokenUrl);
            log.info("createAccessTokenBO 请求的参数=" + JSON.toJSONString(createAccessTokenBO));
            createAccessTokenResult = HttpUtil.postGeneralUrl(createaccesstokenUrl, "application/json", JSON.toJSONString(createAccessTokenBO), "UTF-8");
            log.info("createAccessTokenResult 返回结果=" + createAccessTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据appid 、BmAppid、Appkey获取accessToken");
            recordhttp.setUrl(createAccessTokenResult);
            recordhttp.setRequest(JSON.toJSONString(createAccessTokenBO));
            recordhttp.setResponse(createAccessTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createAccessTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(createAccessTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createAccessTokenResult.contains("accessToken"),true);
            Assert.assertEquals(createAccessTokenResult.contains("tokenExpire"),true);
        }

    }

    /**
     * 根据appid 、BmAppid、sign获取accessToken
     * @throws Exception
     */
    @Test
    public void createaccesstokenTestBySign() throws Exception {
        String createaccesstokenUrl =null;
        CreateAccessTokenBO createAccessTokenBO =null;
        String createAccessTokenResult ="";
        try{
            createaccesstokenUrl = url+"/AccService/createaccesstoken";
            createAccessTokenBO = new CreateAccessTokenBO();
            createAccessTokenBO.setBmAppid("1.00002");
            createAccessTokenBO.setAppid("1.00002");
            String key="1234567812345678";
            String sign= MD5Utils.getMD5(key,"utf-8").toLowerCase();
            createAccessTokenBO.setSign(sign);
            //参数TokenExpire不设置，默认创建有效期为2小时
            //参数TokenExpire设置，默认创建有效期为设置时间
            createAccessTokenBO.setTokenExpire(999999999);
            log.info("createaccesstokenUrl 请求的参数=" + createaccesstokenUrl);
            log.info("createAccessTokenBO 请求的参数=" + JSON.toJSONString(createAccessTokenBO));
            createAccessTokenResult = HttpUtil.postGeneralUrl(createaccesstokenUrl, "application/json", JSON.toJSONString(createAccessTokenBO), "UTF-8");
            log.info("createAccessTokenResult 返回结果=" + createAccessTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据appid 、BmAppid、sign获取accessToken");
            recordhttp.setUrl(createAccessTokenResult);
            recordhttp.setRequest(JSON.toJSONString(createAccessTokenBO));
            recordhttp.setResponse(createAccessTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createAccessTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(createAccessTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createAccessTokenResult.contains("accessToken"),true);
            Assert.assertEquals(createAccessTokenResult.contains("tokenExpire"),true);
        }

    }

    /**
     * 获取默认的AccessToken的有效期为4小时，则请求参数TokenExpire不传
     * @throws Exception
     */
    @Test
    public void createaccesstokenTestByDefaultExpire() throws Exception {
        String createaccesstokenUrl =null;
        CreateAccessTokenBO createAccessTokenBO =null;
        String createAccessTokenResult ="";
        try{
            createaccesstokenUrl = url+"/AccService/createaccesstoken";
            createAccessTokenBO = new CreateAccessTokenBO();
            createAccessTokenBO.setBmAppid("1.00002");
            createAccessTokenBO.setAppid("1.00002");
            String key="1234567812345678";
            createAccessTokenBO.setAppkey(key);
            //参数TokenExpire不设置，默认创建有效期为2小时
            //参数TokenExpire设置，默认创建有效期为设置时间
//        createAccessTokenBO.setTokenExpire(999999999);
            log.info("createaccesstokenUrl 请求的参数=" + createaccesstokenUrl);
            log.info("createAccessTokenBO 请求的参数=" + JSON.toJSONString(createAccessTokenBO));
            createAccessTokenResult = HttpUtil.postGeneralUrl(createaccesstokenUrl, "application/json", JSON.toJSONString(createAccessTokenBO), "UTF-8");
            log.info("createAccessTokenResult 返回结果=" + createAccessTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取默认的AccessToken的有效期为4小时，则请求参数TokenExpire不传");
            recordhttp.setUrl(createAccessTokenResult);
            recordhttp.setRequest(JSON.toJSONString(createAccessTokenBO));
            recordhttp.setResponse(createAccessTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createAccessTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(createAccessTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createAccessTokenResult.contains("accessToken"),true);
            Assert.assertEquals(createAccessTokenResult.contains("tokenExpire"),true);
        }

    }


    /**
     * 普通用户登录，使用uid与token验证
     * @throws Exception
     */
    @Test
    public void createaccesstokenTest() throws Exception {
        String createaccesstokenUrl =null;
        CreateAccessTokenBO createAccessTokenBO =null;
        String createAccessTokenResult ="";
        try{
            createaccesstokenUrl = url+"/AccService/createaccesstoken";
            createAccessTokenBO = new CreateAccessTokenBO();
            HashMap<String,String> hs=userLoginTest();
            createAccessTokenBO.setUid(Long.valueOf(hs.get("uid")));
            createAccessTokenBO.setToken(String.valueOf(hs.get("token")));
            createAccessTokenBO.setBmAppid("1.00002");
            createAccessTokenBO.setAppid("100.00002");
            createAccessTokenBO.setAppkey("1234567812345678");
            //参数TokenExpire不设置，默认创建有效期为2小时
            //参数TokenExpire设置，默认创建有效期为设置时间
            createAccessTokenBO.setTokenExpire(999999999);
            log.info("createaccesstokenUrl 请求的参数=" + createaccesstokenUrl);
            log.info("createAccessTokenBO 请求的参数=" + JSON.toJSONString(createAccessTokenBO));
            createAccessTokenResult = HttpUtil.postGeneralUrl(createaccesstokenUrl, "application/json", JSON.toJSONString(createAccessTokenBO), "UTF-8");
            log.info("createAccessTokenResult 返回结果=" + createAccessTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户登录，使用uid与token验证");
            recordhttp.setUrl(createAccessTokenResult);
            recordhttp.setRequest(JSON.toJSONString(createAccessTokenBO));
            recordhttp.setResponse(createAccessTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createAccessTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(createAccessTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createAccessTokenResult.contains("accessToken"),true);
            Assert.assertEquals(createAccessTokenResult.contains("tokenExpire"),true);
        }

    }


    /**
     * 企业用户登录，使用uid与token验证
     * @throws Exception
     */
    @Test
    public void createaccesstokenTest1() throws Exception {
        String createaccesstokenUrl =null;
        CreateAccessTokenBO createAccessTokenBO =null;
        String createAccessTokenResult ="";
        try{
            createaccesstokenUrl = url+"/AccService/createaccesstoken";
            createAccessTokenBO = new CreateAccessTokenBO();
            HashMap<String,String> hs=EUserLoginTest();
            createAccessTokenBO.setUid(Long.valueOf(hs.get("euid")));
            createAccessTokenBO.setToken(String.valueOf(hs.get("token")));
            createAccessTokenBO.setBmAppid("1.00002");
            createAccessTokenBO.setAppid("100.00002");
            createAccessTokenBO.setAppkey("1234567812345678");
            //参数TokenExpire不设置，默认创建有效期为2小时
            //参数TokenExpire设置，默认创建有效期为设置时间
            createAccessTokenBO.setTokenExpire(999999999);
            log.info("createaccesstokenUrl 请求的参数=" + createaccesstokenUrl);
            log.info("createAccessTokenBO 请求的参数=" + JSON.toJSONString(createAccessTokenBO));
            createAccessTokenResult = HttpUtil.postGeneralUrl(createaccesstokenUrl, "application/json", JSON.toJSONString(createAccessTokenBO), "UTF-8");
            log.info("createAccessTokenResult 返回结果=" + createAccessTokenResult);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("企业用户登录，使用uid与token验证");
            recordhttp.setUrl(createAccessTokenResult);
            recordhttp.setRequest(JSON.toJSONString(createAccessTokenBO));
            recordhttp.setResponse(createAccessTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createAccessTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(createAccessTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createAccessTokenResult.contains("accessToken"),true);
            Assert.assertEquals(createAccessTokenResult.contains("tokenExpire"),true);
        }

    }

    /**
     * 使用bmappid与accesstoken创建
     * @throws Exception
     */
    @Test
    public void createaccesstokenTest2() throws Exception {
        String createaccesstokenUrl =null;
        CreateAccessTokenBO createAccessTokenBO =null;
        String createAccessTokenResult ="";
        try{
            createaccesstokenUrl = url+"/AccService/createaccesstoken";
            createAccessTokenBO = new CreateAccessTokenBO();
            createAccessTokenBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8950bdf5c828e02fff2e87511787c3c36");
            createAccessTokenBO.setBmAppid("1.00002");
            createAccessTokenBO.setAppid("100.00002");
            createAccessTokenBO.setAppkey("1234567812345678");

            //参数TokenExpire不设置，默认创建有效期为2小时
            //参数TokenExpire设置，默认创建有效期为设置时间
//        createAccessTokenBO.setTokenExpire(999999999);
            log.info("createaccesstokenUrl 请求的参数=" + createaccesstokenUrl);
            log.info("createAccessTokenBO 请求的参数=" + JSON.toJSONString(createAccessTokenBO));
            createAccessTokenResult = HttpUtil.postGeneralUrl(createaccesstokenUrl, "application/json", JSON.toJSONString(createAccessTokenBO), "UTF-8");
            log.info("createAccessTokenResult 返回结果=" + createAccessTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用bmappid与accesstoken创建");
            recordhttp.setUrl(createAccessTokenResult);
            recordhttp.setRequest(JSON.toJSONString(createAccessTokenBO));
            recordhttp.setResponse(createAccessTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createAccessTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(createAccessTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createAccessTokenResult.contains("accessToken"),true);
            Assert.assertEquals(createAccessTokenResult.contains("tokenExpire"),true);
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
