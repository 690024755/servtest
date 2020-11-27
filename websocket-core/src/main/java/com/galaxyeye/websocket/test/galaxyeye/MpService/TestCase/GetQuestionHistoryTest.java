package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 10:57
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/26日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.HealthQuestionHistoryRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HealthQuestionHistory;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HealthQuestionHistoryExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.GetQuestionHistoryBO;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Component
public class GetQuestionHistoryTest extends BaseTest {

    @Autowired
    private HealthQuestionHistoryRepository healthQuestionHistoryRepository;

    @Autowired
    InsertQuestionHistoryTest insertQuestionHistoryTest;

    /**
     * 过期的token获取用户答题历史记录，方法配置在openMethod当中，不验证token过期
     * 过期的token获取用户答题历史记录，方法配置在authMethod当中，验证token过期
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByExpireToken() throws Exception {
        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            HashMap<String,String> userLogin=getuserExpireToken();
            getQuestionHistoryBO.setToken(userLogin.get("token"));
            getQuestionHistoryBO.setUid(userLogin.get("uid"));
            getQuestionHistoryBO.setAppid("100.00002");
            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("过期的token获取用户答题历史记录，方法配置在openMethod当中，不验证token过期;过期的token获取用户答题历史记录，方法配置在authMethod当中，验证token过期");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"token_error\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":115"),true);
        }
    }

    /**
     * 使用uid与token获取历史答题记录
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByUidAndToken() throws Exception {

        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            HashMap<String,String> userLogin=userLoginTest();
            getQuestionHistoryBO.setToken(userLogin.get("token"));
            getQuestionHistoryBO.setUid(userLogin.get("uid"));
            getQuestionHistoryBO.setAppid("1.00002");
            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token获取历史答题记录");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("history"),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":1"),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("history"),true);
        }
    }


    /**
     * 参数uid不传
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByParameterUidIsNotExist() throws Exception {
        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            HashMap<String,String> userLogin=getuserExpireToken();
            getQuestionHistoryBO.setToken(userLogin.get("token"));
//            getQuestionHistoryBO.setUid(userLogin.get("uid"));
            getQuestionHistoryBO.setAppid("1.00002");
            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数uid不传");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"uid is missing\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByNotExistParameterAppid() throws Exception {
        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            HashMap<String,String> userLogin=getuserExpireToken();
            getQuestionHistoryBO.setToken(userLogin.get("token"));
            getQuestionHistoryBO.setUid(userLogin.get("uid"));
//            getQuestionHistoryBO.setAppid("1.00002");
            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":106"),true);
        }
    }

    /**
     * 参数uid的值是错误值,方法getquestionhistory配置在openMethod当中，不校验uid与token或者不校验accessToken与bmappid
     * 参数uid的值是错误值,方法getquestionhistory配置在authMethod当中，校验uid与token或者校验accessToken与bmappid
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByParameterUidValueIsError() throws Exception {
        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            HashMap<String,String> userLogin=getuserExpireToken();
            getQuestionHistoryBO.setToken(userLogin.get("token"));
            getQuestionHistoryBO.setUid("99999999");
            getQuestionHistoryBO.setAppid("100.00002");
            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数uid的值是错误值,方法getquestionhistory配置在openMethod当中，不校验uid与token或者不校验accessToken与bmappid;参数uid的值是错误值,方法getquestionhistory配置在authMethod当中，校验uid与token或者校验accessToken与bmappid");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"token_error\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":115"),true);
        }
    }

    /**
     * 使用BmAppid与AccessToken获取历史答题记录，但是请求参数uid传递了
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByBmAppidAndAccessTokenAndUid() throws Exception {

        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            getQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            getQuestionHistoryBO.setBmAppid("1.00002");
            getQuestionHistoryBO.setUid("237671");
            getQuestionHistoryBO.setAppid("1.00002");
            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken获取历史答题记录，但是请求参数uid传递了");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("histor"),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 使用BmAppid与AccessToken获取历史答题记录,但是请求参数uid不传
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByBmAppidAndAccessTokenAndParameterUidNotExist() throws Exception {
        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            getQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            getQuestionHistoryBO.setBmAppid("1.00002");
//            getQuestionHistoryBO.setUid("237671");
            getQuestionHistoryBO.setAppid("1.00002");
            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken获取历史答题记录,但是请求参数uid不传");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"uid is missing\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 参数accessToken与bmappid组合传递，必填参数bmappid校验，方法getquestionhistory配置在openMethod当中，不校验uid与token或者不校验bmappid与accessToken
     * 参数accessToken与bmappid组合传递，必填参数bmappid校验，方法getquestionhistory配置在openMethod当中，校验uid与token或者校验bmappid与accessToken
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByParameterBmAppidNotExist() throws Exception {
        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            getQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
//            getQuestionHistoryBO.setBmAppid("1.00002");
            getQuestionHistoryBO.setUid("237671");
            getQuestionHistoryBO.setAppid("100.00002");
            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数accessToken与bmappid组合传递，必填参数bmappid校验");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 必填参数date校验
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByParameterDateNotExist() throws Exception {
        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            getQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            getQuestionHistoryBO.setBmAppid("1.00002");
            getQuestionHistoryBO.setUid("237671");
            getQuestionHistoryBO.setAppid("1.00002");
//            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数date校验");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"date is missing\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":101"),true);
        }
    }

    /**
     * date的值格式类型检查
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByParameterDateformat() throws Exception {
        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            getQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            getQuestionHistoryBO.setBmAppid("1.00002");
            getQuestionHistoryBO.setUid("237671");
            getQuestionHistoryBO.setAppid("1.00002");
            getQuestionHistoryBO.setDate("2020-05-11 11:36:31");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("date的值格式类型检查");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"date format is wrong\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 未查询到记录，显示为空
     * @throws Exception
     */
    @Test
    public void getQuestionHistoryTestByTableIsEmpty() throws Exception {
        healthQuestionHistoryRepository.deleteAll();
        String getquestionhistoryUrl =null;
        GetQuestionHistoryBO getQuestionHistoryBO =null;
        String getquestionhistoryResult ="";
        try{
            getquestionhistoryUrl =url+ "/BusinessService/health/getquestionhistory";
            getQuestionHistoryBO = new GetQuestionHistoryBO();
            HashMap<String,String> userLogin=userLoginTest();
            getQuestionHistoryBO.setToken(userLogin.get("token"));
            getQuestionHistoryBO.setUid(userLogin.get("uid"));
            getQuestionHistoryBO.setAppid("1.00002");
            getQuestionHistoryBO.setDate("2020-05-11");
            log.info("getquestionhistoryUrl 请求的参数=" + getquestionhistoryUrl);
            log.info("getQuestionHistoryBO 请求的参数=" + JSON.toJSONString(getQuestionHistoryBO));
            getquestionhistoryResult = HttpUtil.postGeneralUrl(getquestionhistoryUrl, "application/json", JSON.toJSONString(getQuestionHistoryBO), "UTF-8");
            log.info("getquestionhistoryResult 返回结果=" + getquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取健康答题的历史记录");
            recordhttp.setUrl(getquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(getQuestionHistoryBO));
            recordhttp.setResponse(getquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getquestionhistoryResult.contains("\"history\":[]"),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getquestionhistoryResult.contains("\"result\":1"),true);
        }
    }





    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {

        HealthQuestionHistoryExample selectByExample=new HealthQuestionHistoryExample();
        selectByExample.createCriteria().andUidEqualTo(237671L);
        List<HealthQuestionHistory> list=healthQuestionHistoryRepository.selectByExample(selectByExample);
        if(list.size()<=1 || list.isEmpty()){
            healthQuestionHistoryRepository.deleteAll();
            insertQuestionHistoryTest.insertQuestionHistoryTest();
        }
    }

    @Override
    public void destroyData() {
        healthQuestionHistoryRepository.deleteAll();
    }
}
