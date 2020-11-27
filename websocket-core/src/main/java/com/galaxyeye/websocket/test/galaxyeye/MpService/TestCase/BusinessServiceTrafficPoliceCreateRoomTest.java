package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/24日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceCreateRoomBO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

@Slf4j
@Component
public class BusinessServiceTrafficPoliceCreateRoomTest extends BaseTest {
    public static HashMap<String, String> hs=null;

    public final static String  unionId ="o8Xn91cwv1EU_yAh_2GK9xyLTxRs";


    public String BusinessServiceTrafficPoliceCreateRoomTestByGernal() throws Exception {
        String createroomUrl =null;
        TrafficPoliceCreateRoomBO trafficPoliceCreateRoomBO =null;
        String createroomResult ="";
        try{
            createroomUrl = url+"/BusinessService/trafficpolice/createroom";
            trafficPoliceCreateRoomBO = new TrafficPoliceCreateRoomBO();
            UUID uUID=UUID.randomUUID();
            String filePath=getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceCreateRoomBO.setUuid(uUID);
            trafficPoliceCreateRoomBO.setBmAppid("1.00002");
            trafficPoliceCreateRoomBO.setAppid("1.00002");
            trafficPoliceCreateRoomBO.setContact("13093863510");
            trafficPoliceCreateRoomBO.setName("ai小助手测试");
            //uid与UnionId需要存在映射关系，在表trafficpolice_user
            trafficPoliceCreateRoomBO.setUid(Integer.valueOf(hs.get("uid")));
            trafficPoliceCreateRoomBO.setUnionId(unionId);
            trafficPoliceCreateRoomBO.setContent("ai小助手测试");
            trafficPoliceCreateRoomBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            log.info("createroomUrl 请求的参数=" + createroomUrl);
            log.info("trafficPoliceCreateRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceCreateRoomBO));
            createroomResult = HttpUtil.postGeneralUrl(createroomUrl, "application/json", JSON.toJSONString(trafficPoliceCreateRoomBO), "UTF-8");
            log.info("createroomResult 返回结果=" + createroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("咨询者创建通用会话");
            recordhttp.setUrl(createroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceCreateRoomBO));
            recordhttp.setResponse(createroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
            Assert.assertEquals(createroomResult.contains("id"),true);
            Assert.assertEquals(createroomResult.contains("idStr"),true);
            Assert.assertEquals(createroomResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
            String idStr =JsonPath.read(createroomResult,"$.idStr");
            return idStr;
        }
    }


    @Test
    public void createroom() throws Exception {
        String createroomUrl =null;
        TrafficPoliceCreateRoomBO trafficPoliceCreateRoomBO =null;
        String createroomResult ="";
        try{
            createroomUrl = url+"/BusinessService/trafficpolice/createroom";
            trafficPoliceCreateRoomBO = new TrafficPoliceCreateRoomBO();
            UUID uUID=UUID.randomUUID();
            String filePath=getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceCreateRoomBO.setUuid(uUID);
            trafficPoliceCreateRoomBO.setBmAppid("1.00002");
            trafficPoliceCreateRoomBO.setAppid("1.00002");
            trafficPoliceCreateRoomBO.setContact("13093863510");
            trafficPoliceCreateRoomBO.setName("ai小助手测试");
            //uid与UnionId需要存在映射关系，在表trafficpolice_user
            trafficPoliceCreateRoomBO.setUid(Integer.valueOf(hs.get("uid")));
            trafficPoliceCreateRoomBO.setUnionId(unionId);
            trafficPoliceCreateRoomBO.setContent("ai小助手测试");
            trafficPoliceCreateRoomBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            log.info("createroomUrl 请求的参数=" + createroomUrl);
            log.info("trafficPoliceCreateRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceCreateRoomBO));
            createroomResult = HttpUtil.postGeneralUrl(createroomUrl, "application/json", JSON.toJSONString(trafficPoliceCreateRoomBO), "UTF-8");
            log.info("createroomResult 返回结果=" + createroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用于创建会话trafficpolice_room");
            recordhttp.setUrl(createroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceCreateRoomBO));
            recordhttp.setResponse(createroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
            Assert.assertEquals(createroomResult.contains("id"),true);
            Assert.assertEquals(createroomResult.contains("idStr"),true);
            Assert.assertEquals(createroomResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 方法createroom配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void createRoomByOpenMethodAndUid() throws Exception {
        String createroomUrl =null;
        TrafficPoliceCreateRoomBO trafficPoliceCreateRoomBO =null;
        String createroomResult ="";
        try{
            createroomUrl = url+"/BusinessService/trafficpolice/createroom";
            trafficPoliceCreateRoomBO = new TrafficPoliceCreateRoomBO();
            UUID uUID=UUID.randomUUID();
            String filePath=getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceCreateRoomBO.setUuid(uUID);
            trafficPoliceCreateRoomBO.setBmAppid("1.00002");
            trafficPoliceCreateRoomBO.setAppid("1.00002");
            trafficPoliceCreateRoomBO.setContact("13093863510");
            trafficPoliceCreateRoomBO.setName("ai小助手测试");
            //uid与UnionId需要存在映射关系，在表trafficpolice_user
            trafficPoliceCreateRoomBO.setUid(Integer.valueOf(hs.get("uid")));
            trafficPoliceCreateRoomBO.setUnionId(unionId);
            trafficPoliceCreateRoomBO.setContent("ai小助手测试");
            log.info("createroomUrl 请求的参数=" + createroomUrl);
            log.info("trafficPoliceCreateRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceCreateRoomBO));
            createroomResult = HttpUtil.postGeneralUrl(createroomUrl, "application/json", JSON.toJSONString(trafficPoliceCreateRoomBO), "UTF-8");
            log.info("createroomResult 返回结果=" + createroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法createroom配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(createroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceCreateRoomBO));
            recordhttp.setResponse(createroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
            Assert.assertEquals(createroomResult.contains("id"),true);
            Assert.assertEquals(createroomResult.contains("idStr"),true);
            Assert.assertEquals(createroomResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 方法createroom配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void createRoomByOpenMethodAndBmAppid() throws Exception {
        String createroomUrl =null;
        TrafficPoliceCreateRoomBO trafficPoliceCreateRoomBO =null;
        String createroomResult ="";
        try{
            createroomUrl = url+"/BusinessService/trafficpolice/createroom";
            trafficPoliceCreateRoomBO = new TrafficPoliceCreateRoomBO();
            UUID uUID=UUID.randomUUID();
            String filePath=getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceCreateRoomBO.setUuid(uUID);
            trafficPoliceCreateRoomBO.setBmAppid("1.00002");
            trafficPoliceCreateRoomBO.setAppid("1.00002");
            trafficPoliceCreateRoomBO.setContact("13093863510");
            trafficPoliceCreateRoomBO.setName("ai小助手测试");
            //uid与UnionId需要存在映射关系，在表trafficpolice_user
            trafficPoliceCreateRoomBO.setUid(Integer.valueOf(hs.get("uid")));
            trafficPoliceCreateRoomBO.setUnionId(unionId);
            trafficPoliceCreateRoomBO.setContent("ai小助手测试");
            log.info("createroomUrl 请求的参数=" + createroomUrl);
            log.info("trafficPoliceCreateRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceCreateRoomBO));
            createroomResult = HttpUtil.postGeneralUrl(createroomUrl, "application/json", JSON.toJSONString(trafficPoliceCreateRoomBO), "UTF-8");
            log.info("createroomResult 返回结果=" + createroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法createroom配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(createroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceCreateRoomBO));
            recordhttp.setResponse(createroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
            Assert.assertEquals(createroomResult.contains("id"),true);
            Assert.assertEquals(createroomResult.contains("idStr"),true);
            Assert.assertEquals(createroomResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 方法createroom配置在authMethod当中，参数Token传递
     * @throws Exception
     */
    @Test
    public void createRoomByAuthMethodAndUid() throws Exception {
        String createroomUrl =null;
        TrafficPoliceCreateRoomBO trafficPoliceCreateRoomBO =null;
        String createroomResult ="";
        try{
            createroomUrl = url+"/BusinessService/trafficpolice/createroom";
            trafficPoliceCreateRoomBO = new TrafficPoliceCreateRoomBO();
            UUID uUID=UUID.randomUUID();
            String filePath=getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceCreateRoomBO.setUuid(uUID);
            trafficPoliceCreateRoomBO.setUid(Integer.valueOf(hs.get("uid")));
            trafficPoliceCreateRoomBO.setToken(hs.get("token"));
            trafficPoliceCreateRoomBO.setBmAppid("100.00002");
            trafficPoliceCreateRoomBO.setAppid("100.00002");
            trafficPoliceCreateRoomBO.setContact("13093863510");
            trafficPoliceCreateRoomBO.setName("ai小助手测试");
            //uid与UnionId需要存在映射关系，在表trafficpolice_user
            trafficPoliceCreateRoomBO.setUnionId(unionId);
            trafficPoliceCreateRoomBO.setContent("ai小助手测试");
            log.info("createroomUrl 请求的参数=" + createroomUrl);
            log.info("trafficPoliceCreateRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceCreateRoomBO));
            createroomResult = HttpUtil.postGeneralUrl(createroomUrl, "application/json", JSON.toJSONString(trafficPoliceCreateRoomBO), "UTF-8");
            log.info("createroomResult 返回结果=" + createroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法createroom配置在authMethod当中，参数Token传递");
            recordhttp.setUrl(createroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceCreateRoomBO));
            recordhttp.setResponse(createroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
            Assert.assertEquals(createroomResult.contains("id"),true);
            Assert.assertEquals(createroomResult.contains("idStr"),true);
            Assert.assertEquals(createroomResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 方法createroom配置在authMethod当中，参数Token不传递
     * @throws Exception
     */
    @Test
    public void createRoomByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String createroomUrl =null;
        TrafficPoliceCreateRoomBO trafficPoliceCreateRoomBO =null;
        String createroomResult ="";
        try{
            createroomUrl = url+"/BusinessService/trafficpolice/createroom";
            trafficPoliceCreateRoomBO = new TrafficPoliceCreateRoomBO();
            UUID uUID=UUID.randomUUID();
            String filePath=getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceCreateRoomBO.setUuid(uUID);
            trafficPoliceCreateRoomBO.setUid(Integer.valueOf(hs.get("uid")));
//            trafficPoliceCreateRoomBO.setToken(hs.get("token"));
            trafficPoliceCreateRoomBO.setBmAppid("100.00002");
            trafficPoliceCreateRoomBO.setAppid("100.00002");
            trafficPoliceCreateRoomBO.setContact("13093863510");
            trafficPoliceCreateRoomBO.setName("ai小助手测试");
            //uid与UnionId需要存在映射关系，在表trafficpolice_user
            trafficPoliceCreateRoomBO.setUnionId(unionId);
            trafficPoliceCreateRoomBO.setContent("ai小助手测试");
            log.info("createroomUrl 请求的参数=" + createroomUrl);
            log.info("trafficPoliceCreateRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceCreateRoomBO));
            createroomResult = HttpUtil.postGeneralUrl(createroomUrl, "application/json", JSON.toJSONString(trafficPoliceCreateRoomBO), "UTF-8");
            log.info("createroomResult 返回结果=" + createroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法createroom配置在authMethod当中，参数Token不传递");
            recordhttp.setUrl(createroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceCreateRoomBO));
            recordhttp.setResponse(createroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createroomResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(createroomResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 方法createroom配置在authMethod当中，参数AccessToken传递
     * @throws Exception
     */
    @Test
    public void createRoomByAuthMethodAndBmAppid() throws Exception {
        String createroomUrl =null;
        TrafficPoliceCreateRoomBO trafficPoliceCreateRoomBO =null;
        String createroomResult ="";
        try{
            createroomUrl = url+"/BusinessService/trafficpolice/createroom";
            trafficPoliceCreateRoomBO = new TrafficPoliceCreateRoomBO();
            UUID uUID=UUID.randomUUID();
            String filePath=getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceCreateRoomBO.setUuid(uUID);
            trafficPoliceCreateRoomBO.setUid(Integer.valueOf(hs.get("uid")));
            trafficPoliceCreateRoomBO.setAccessToken(bmAppids.get("100.00002"));
            trafficPoliceCreateRoomBO.setBmAppid("100.00002");
            trafficPoliceCreateRoomBO.setAppid("100.00002");
            trafficPoliceCreateRoomBO.setContact("13093863510");
            trafficPoliceCreateRoomBO.setName("ai小助手测试");
            //uid与UnionId需要存在映射关系，在表trafficpolice_user
            trafficPoliceCreateRoomBO.setUnionId(unionId);
            trafficPoliceCreateRoomBO.setContent("ai小助手测试");
            log.info("createroomUrl 请求的参数=" + createroomUrl);
            log.info("trafficPoliceCreateRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceCreateRoomBO));
            createroomResult = HttpUtil.postGeneralUrl(createroomUrl, "application/json", JSON.toJSONString(trafficPoliceCreateRoomBO), "UTF-8");
            log.info("createroomResult 返回结果=" + createroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法createroom配置在authMethod当中，参数AccessToken传递");
            recordhttp.setUrl(createroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceCreateRoomBO));
            recordhttp.setResponse(createroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
            Assert.assertEquals(createroomResult.contains("id"),true);
            Assert.assertEquals(createroomResult.contains("idStr"),true);
            Assert.assertEquals(createroomResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(createroomResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 方法createroom配置在authMethod当中，参数AccessToken不传递
     * @throws Exception
     */
    @Test
    public void createRoomByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String createroomUrl =null;
        TrafficPoliceCreateRoomBO trafficPoliceCreateRoomBO =null;
        String createroomResult ="";
        try{
            createroomUrl = url+"/BusinessService/trafficpolice/createroom";
            trafficPoliceCreateRoomBO = new TrafficPoliceCreateRoomBO();
            UUID uUID=UUID.randomUUID();
            String filePath=getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceCreateRoomBO.setUuid(uUID);
            trafficPoliceCreateRoomBO.setUid(Integer.valueOf(hs.get("uid")));
//            trafficPoliceCreateRoomBO.setAccessToken(bmAppids.get("100.00002"));
            trafficPoliceCreateRoomBO.setBmAppid("100.00002");
            trafficPoliceCreateRoomBO.setAppid("100.00002");
            trafficPoliceCreateRoomBO.setContact("13093863510");
            trafficPoliceCreateRoomBO.setName("ai小助手测试");
            //uid与UnionId需要存在映射关系，在表trafficpolice_user
            trafficPoliceCreateRoomBO.setUnionId(unionId);
            trafficPoliceCreateRoomBO.setContent("ai小助手测试");
            log.info("createroomUrl 请求的参数=" + createroomUrl);
            log.info("trafficPoliceCreateRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceCreateRoomBO));
            createroomResult = HttpUtil.postGeneralUrl(createroomUrl, "application/json", JSON.toJSONString(trafficPoliceCreateRoomBO), "UTF-8");
            log.info("createroomResult 返回结果=" + createroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法createroom配置在authMethod当中，参数AccessToken不传递");
            recordhttp.setUrl(createroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceCreateRoomBO));
            recordhttp.setResponse(createroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(createroomResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(createroomResult.contains("\"result\":101"),true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {
        UserLoginBO userLoginBO = new UserLoginBO();
        userLoginBO.setUname(unionId);
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("4.00099");
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setUnionid(unionId);
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        hs=userLoginTest(userLoginBO);
    }



    @Override
    public void destroyData() {

    }
}
