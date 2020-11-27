package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.XmlCase
 * @Date Create on 16:58
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/26日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.HealthQuestionHistoryRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.InsertQuestionHistoryBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
public class InsertQuestionHistoryTest extends BaseTest {
    @Autowired
    private HealthQuestionHistoryRepository healthQuestionHistoryRepository;

    /**
     * 用户答题历史保存
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTest() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            insertQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            insertQuestionHistoryBO.setBmAppid("1.00002");
            insertQuestionHistoryBO.setUid("237671");
            insertQuestionHistoryBO.setAppid("1.00002");
            insertQuestionHistoryBO.setQid(1L);
            insertQuestionHistoryBO.setAnswer(1);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户答题历史保存");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 根据AccessToken和BmAppid保存用户答题历史记录,参数uid传递
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByAccessTokenAndBmAppidAndExistParameterUid() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            insertQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            insertQuestionHistoryBO.setBmAppid("1.00002");
            insertQuestionHistoryBO.setUid("237671");
            insertQuestionHistoryBO.setAppid("1.00002");
            insertQuestionHistoryBO.setQid(1L);
            insertQuestionHistoryBO.setAnswer(1);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据AccessToken和BmAppid保存用户答题历史记录,参数uid传递");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 根据AccessToken和BmAppid保存用户答题历史记录,参数uid不传递
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByAccessTokenAndBmAppidAndNotExistParameterUid() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            insertQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            insertQuestionHistoryBO.setBmAppid("1.00002");
//            insertQuestionHistoryBO.setUid("237671");
            insertQuestionHistoryBO.setAppid("1.00002");
            insertQuestionHistoryBO.setQid(1L);
            insertQuestionHistoryBO.setAnswer(1);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据AccessToken和BmAppid保存用户答题历史记录,参数uid不传递");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"uid is missing\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 根据Token和uid保存用户答题历史记录
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByUidAndToken() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            HashMap<String, String> hs= userLoginTest();
            insertQuestionHistoryBO.setUid(hs.get("uid"));
            insertQuestionHistoryBO.setToken(hs.get("token"));
            insertQuestionHistoryBO.setAppid("1.00002");
            insertQuestionHistoryBO.setQid(1L);
            insertQuestionHistoryBO.setAnswer(1);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据Token和uid保存用户答题历史记录");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 用户过期的token,保存用户的答题历史记录，方法配置在authMethod当中，验证token过期
     * 用户过期的token,保存用户的答题历史记录，方法配置在openMethod当中，不验证token过期
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByExpireToken() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            HashMap<String, String> hs=getuserExpireToken();
            insertQuestionHistoryBO.setUid(hs.get("uid"));
            insertQuestionHistoryBO.setToken(hs.get("token"));
//            insertQuestionHistoryBO.setAppid("1.00002");
            insertQuestionHistoryBO.setAppid("100.00002");
            insertQuestionHistoryBO.setQid(1L);
            insertQuestionHistoryBO.setAnswer(1);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户过期的token,保存用户的答题历史记录，方法配置在authMethod当中，验证token过期，用户过期的token,保存用户的答题历史记录，方法配置在openMethod当中，不验证token过期");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"token_error\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":115"),true);
        }
    }

    /**
     * 非必填参数answer校验.不传默认是0
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByNotExistParameterAnswer() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            insertQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            insertQuestionHistoryBO.setBmAppid("1.00002");
            insertQuestionHistoryBO.setUid("237671");
            insertQuestionHistoryBO.setAppid("1.00002");
            insertQuestionHistoryBO.setQid(21474836471L);
//            insertQuestionHistoryBO.setAnswer(1);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数answer校验.不传默认是0");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 参数answer的值设置为1
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByParameterAnswerValueIsOne() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            insertQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            insertQuestionHistoryBO.setBmAppid("1.00002");
            insertQuestionHistoryBO.setUid("237671");
            insertQuestionHistoryBO.setAppid("1.00002");
            insertQuestionHistoryBO.setQid(21474836471L);
            insertQuestionHistoryBO.setAnswer(1);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数answer校验.不传默认是0");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 参数answer的值设置为2,目前服务端未做校验
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByParameterAnswerValueIsTwo() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            insertQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            insertQuestionHistoryBO.setBmAppid("1.00002");
            insertQuestionHistoryBO.setUid("237671");
            insertQuestionHistoryBO.setAppid("1.00002");
            insertQuestionHistoryBO.setQid(21474836471L);
            insertQuestionHistoryBO.setAnswer(2);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数answer的值设置为2,目前服务端未做校验");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 必填参数qid校验
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByNotExistParameterQid() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            insertQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            insertQuestionHistoryBO.setBmAppid("1.00002");
            insertQuestionHistoryBO.setUid("237671");
            insertQuestionHistoryBO.setAppid("1.00002");
//            insertQuestionHistoryBO.setQid(1L);
            insertQuestionHistoryBO.setAnswer(999999999);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数qid校验");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"qid is missing\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 参数accessToken与bmAppid组合传递，必填参数bmAppid校验，方法配置在openMethod当中，不检验AccessToken与bmappid或者不检验token与uid
     * 参数accessToken与bmAppid组合传递，必填参数bmAppid校验，方法配置在authMethod当中，检验AccessToken与bmappid或者检验token与uid
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByParameterBmAppidNotExist() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            insertQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
//            insertQuestionHistoryBO.setBmAppid("1.00002");
            insertQuestionHistoryBO.setUid("237671");
            insertQuestionHistoryBO.setAppid("100.00002");
//            insertQuestionHistoryBO.setQid(1L);
            insertQuestionHistoryBO.setAnswer(999999999);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数accessToken与bmAppid组合传递，必填参数bmAppid校验，方法配置在openMethod当中，不检验AccessToken与bmappid或者不检验token与uid;参数accessToken与bmAppid组合传递，必填参数bmAppid校验，方法配置在authMethod当中，检验AccessToken与bmappid或者检验token与uid");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void insertQuestionHistoryTestByParameterAppidNotExist() {
        String insertquestionhistoryUrl =null;
        InsertQuestionHistoryBO insertQuestionHistoryBO =null;
        String insertquestionhistoryResult ="";
        try{
            insertquestionhistoryUrl =url+ "/BusinessService/health/insertquestionhistory";
            insertQuestionHistoryBO = new InsertQuestionHistoryBO();
            insertQuestionHistoryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            insertQuestionHistoryBO.setBmAppid("1.00002");
            insertQuestionHistoryBO.setUid("237671");
//            insertQuestionHistoryBO.setAppid("1.00002");
//            insertQuestionHistoryBO.setQid(1L);
            insertQuestionHistoryBO.setAnswer(999999999);
            log.info("insertquestionhistoryUrl 请求的参数=" + insertquestionhistoryUrl);
            log.info("insertQuestionHistoryBO 请求的参数=" + JSON.toJSONString(insertQuestionHistoryBO));
            insertquestionhistoryResult = HttpUtil.postGeneralUrl(insertquestionhistoryUrl, "application/json", JSON.toJSONString(insertQuestionHistoryBO), "UTF-8");
            log.info("insertquestionhistoryResult 返回结果=" + insertquestionhistoryResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(insertquestionhistoryUrl);
            recordhttp.setRequest(JSON.toJSONString(insertQuestionHistoryBO));
            recordhttp.setResponse(insertquestionhistoryResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertquestionhistoryResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(insertquestionhistoryResult.contains("\"result\":106"),true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {
        healthQuestionHistoryRepository.deleteAll();
    }

}
