package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.CheckAccessTokenBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
@Slf4j
@Component
public class CheckAccessTokenTest extends BaseTest {

    /**
     * 1、检查accesstoken有效，重新生效accesstoken
     * @throws Exception
     */
    @Test
    public void checkaccesstokenTestByValidAccessTokenIsNotExpire() throws Exception {
        String checkAccessTokenUrl =null;
        CheckAccessTokenBO checkAccessTokenBO =null;
        String checkaccesstokenResult ="";
        try{
            checkAccessTokenUrl = url+"/AccService/checkaccesstoken";
            checkAccessTokenBO = new CheckAccessTokenBO();
            checkAccessTokenBO.setAppid("1.00002");
            checkAccessTokenBO.setBmAppid("1.00002");
            checkAccessTokenBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            log.info("checkAccessTokenUrl 请求的参数=" + checkAccessTokenUrl);
            log.info("checkAccessTokenBO 请求的参数=" + JSON.toJSONString(checkAccessTokenBO));
            checkaccesstokenResult = HttpUtil.postGeneralUrl(checkAccessTokenUrl, "application/json", JSON.toJSONString(checkAccessTokenBO), "UTF-8");
            log.info("checkaccesstokenResult 返回结果=" + JSON.parseObject(checkaccesstokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("检查accesstoken有效，生效accesstoken则返回tokenExpire的过期时间");
            recordhttp.setUrl(checkAccessTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkAccessTokenBO));
            recordhttp.setResponse(checkaccesstokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checkaccesstokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checkaccesstokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checkaccesstokenResult.contains("tokenExpire"),true);
        }


    }

    /**
     * 1、参数AccessToken的值为过期值，失效accesstoken则返回token_error
     * @throws Exception
     */
    @Test
    public void checkaccesstokenTestByValidAccessTokenIsExpire() throws Exception {
        String checkAccessTokenUrl =null;
        CheckAccessTokenBO checkAccessTokenBO =null;
        String checkaccesstokenResult ="";
        try{
            checkAccessTokenUrl =  url+"/AccService/checkaccesstoken";
            checkAccessTokenBO = new CheckAccessTokenBO();
            checkAccessTokenBO.setAppid("1.00002");
            checkAccessTokenBO.setBmAppid("1.00002");
            checkAccessTokenBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8edacefe75957bf3bcbe94b903c348697");
            log.info("checkAccessTokenUrl 请求的参数=" + checkAccessTokenUrl);
            log.info("checkAccessTokenBO 请求的参数=" + JSON.toJSONString(checkAccessTokenBO));
            checkaccesstokenResult = HttpUtil.postGeneralUrl(checkAccessTokenUrl, "application/json", JSON.toJSONString(checkAccessTokenBO), "UTF-8");
            log.info("checkaccesstokenResult 返回结果=" + JSON.parseObject(checkaccesstokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数AccessToken的值为过期值，失效accesstoken则返回token_error");
            recordhttp.setUrl(checkAccessTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkAccessTokenBO));
            recordhttp.setResponse(checkaccesstokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checkaccesstokenResult.contains("\"msg\":\"token_error\""),true);
        }
    }

    /**
     * 1、参数AccessToken的值随机，则返回token_error
     * @throws Exception
     */
    @Test
    public void checkaccesstokenTestByInvalidAccessToken() throws Exception {
        String checkAccessTokenUrl =null;
        CheckAccessTokenBO checkAccessTokenBO =null;
        String checkaccesstokenResult ="";
        try{
            checkAccessTokenUrl =  url+"/AccService/checkaccesstoken";
            checkAccessTokenBO = new CheckAccessTokenBO();
            checkAccessTokenBO.setAppid("1.00002");
            checkAccessTokenBO.setBmAppid("1.00002");
            checkAccessTokenBO.setAccessToken("14e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            log.info("checkAccessTokenUrl 请求的参数=" + checkAccessTokenUrl);
            log.info("checkAccessTokenBO 请求的参数=" + JSON.toJSONString(checkAccessTokenBO));
            checkaccesstokenResult = HttpUtil.postGeneralUrl(checkAccessTokenUrl, "application/json", JSON.toJSONString(checkAccessTokenBO), "UTF-8");
            log.info("checkaccesstokenResult 返回结果=" + JSON.parseObject(checkaccesstokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数AccessToken的值随机，则返回token_error");
            recordhttp.setUrl(checkAccessTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkAccessTokenBO));
            recordhttp.setResponse(checkaccesstokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checkaccesstokenResult.contains("\"msg\":\"token_error\""),true);
        }
    }

    /**
     * 1、使用AccessToken与BmAppid验证接口名称=checkaccesstoken权限
     * @throws Exception
     */
    @Test
    public void checkaccesstokenTest1() throws Exception {
        String checkAccessTokenUrl =null;
        CheckAccessTokenBO checkAccessTokenBO =null;
        String checkaccesstokenResult ="";
        try{
            checkAccessTokenUrl =  url+"/AccService/checkaccesstoken";
            checkAccessTokenBO = new CheckAccessTokenBO();
            checkAccessTokenBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            checkAccessTokenBO.setAppid("100.00002");
            checkAccessTokenBO.setBmAppid("1.00002");
            log.info("checkAccessTokenUrl 请求的参数=" + checkAccessTokenUrl);
            log.info("checkAccessTokenBO 请求的参数=" + JSON.toJSONString(checkAccessTokenBO));
            checkaccesstokenResult = HttpUtil.postGeneralUrl(checkAccessTokenUrl, "application/json", JSON.toJSONString(checkAccessTokenBO), "UTF-8");
            log.info("checkaccesstokenResult 返回结果=" + JSON.parseObject(checkaccesstokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用AccessToken与BmAppid验证接口名称=checkaccesstoken权限");
            recordhttp.setUrl(checkAccessTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkAccessTokenBO));
            recordhttp.setResponse(checkaccesstokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checkaccesstokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checkaccesstokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checkaccesstokenResult.contains("tokenExpire"),true);
        }
    }

    @Test
    public void test() throws Exception {
        String checkAccessTokenUrl =null;
        CheckAccessTokenBO checkAccessTokenBO =null;
        String checkaccesstokenResult ="";
        try{
            checkAccessTokenUrl =  url+"/AccService/checkaccesstoken";
            checkAccessTokenBO = new CheckAccessTokenBO();
            checkAccessTokenBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            checkAccessTokenBO.setAppid("100.00002");
            checkAccessTokenBO.setBmAppid("1.00002");
            log.info("checkAccessTokenUrl 请求的参数=" + checkAccessTokenUrl);
            log.info("checkAccessTokenBO 请求的参数=" + JSON.toJSONString(checkAccessTokenBO));
            checkaccesstokenResult = HttpUtil.postGeneralUrl(checkAccessTokenUrl, "application/json", JSON.toJSONString(checkAccessTokenBO), "UTF-8");
            log.info("checkaccesstokenResult 返回结果=" + JSON.parseObject(checkaccesstokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法checkaccesstoken配置在authMethod段里，使用请求参数BmAppid、AccessToken检查checkaccesstoken是否生效");
            recordhttp.setUrl(checkAccessTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkAccessTokenBO));
            recordhttp.setResponse(checkaccesstokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checkaccesstokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checkaccesstokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checkaccesstokenResult.contains("tokenExpire"),true);
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
