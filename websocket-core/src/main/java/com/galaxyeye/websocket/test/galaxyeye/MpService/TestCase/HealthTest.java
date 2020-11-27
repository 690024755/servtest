package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 10:51
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/20日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.*;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.*;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Component
@Slf4j
public class HealthTest extends BaseTest {

    @Autowired
    private GetGeneralTest getGeneralTest;




    @Test
    public void completehealthplanTest() throws Exception {
        String completehealthplanUrl =null;
        CompleteHealthPlanBO completeHealthPlanBO =null;
        String completehealthplanResult ="";
        try{
             completehealthplanUrl = url+"/BusinessService/health/completehealthplan";
             completeHealthPlanBO = new CompleteHealthPlanBO();
            HashMap<String,String> userLogin=userLoginTest();
            completeHealthPlanBO.setToken(userLogin.get("token"));
            completeHealthPlanBO.setUid(userLogin.get("uid"));
            completeHealthPlanBO.setBmAppid("4.00090");
            completeHealthPlanBO.setAppid("4.00090");
            CompleteHealthPlanDTO completeHealthPlanDTO=JSONObject.parseObject(getGeneralTest.getgeneralTest("healthPlanDetail"), CompleteHealthPlanDTO.class);
            HealthPlanDetailParentDTO healthPlanDetailParentDTO=completeHealthPlanDTO.getConfig();
            HealthPlanDetailSubDTO healthPlanDetailSubDTO=healthPlanDetailParentDTO.getConfig();
            List<HealthPlanDetailHealthPlanDTO> list=healthPlanDetailSubDTO.getHealthPlan();
            log.info("List<HealthPlanDetailHealthPlanDTO>="+JSON.toJSONString(list));
            HealthPlanDetailHealthPlanDTO healthPlanDetailHealthPlanDTO=list.get(0);
            completeHealthPlanBO.setId(healthPlanDetailHealthPlanDTO.getId());
            completeHealthPlanBO.setBonus(healthPlanDetailHealthPlanDTO.getBonus());
            log.info("completehealthplanUrl 请求的参数=" + completehealthplanUrl);
            log.info("completeHealthPlanBO 请求的参数=" + JSON.toJSONString(completeHealthPlanBO));
             completehealthplanResult = HttpUtil.postGeneralUrl(completehealthplanUrl, "application/json", JSON.toJSONString(completeHealthPlanBO), "UTF-8");
            log.info("completehealthplanResult 返回结果=" + JSON.parseObject(completehealthplanResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("打卡健康计划");
            recordhttp.setUrl(completehealthplanUrl);
            recordhttp.setRequest(JSON.toJSONString(completeHealthPlanBO));
            recordhttp.setResponse(completehealthplanResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(completehealthplanResult.contains("\"result\":1"),true);
        }

    }


    @Test
    public void listhealthplanTest() throws Exception {
        String listhealthplanUrl =null;
        ListHealthPlanBO listHealthPlanBO =null;
        String listhealthplanResult ="";
        try{
             listhealthplanUrl =url+ "/BusinessService/health/listhealthplan";
             listHealthPlanBO = new ListHealthPlanBO();
            HashMap<String,String> userLogin=userLoginTest();
            listHealthPlanBO.setToken(userLogin.get("token"));
            listHealthPlanBO.setUid(userLogin.get("uid"));
            listHealthPlanBO.setBmAppid("4.00090");
            listHealthPlanBO.setAppid("4.00090");
            log.info("listhealthplanUrl 请求的参数=" + listhealthplanUrl);
            log.info("listHealthPlanBO 请求的参数=" + JSON.toJSONString(listHealthPlanBO));
             listhealthplanResult = HttpUtil.postGeneralUrl(listhealthplanUrl, "application/json", JSON.toJSONString(listHealthPlanBO), "UTF-8");
            log.info("listhealthplanResult 返回结果=" + JSON.parseObject(listhealthplanResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取健康计划列表");
            recordhttp.setUrl(listhealthplanUrl);
            recordhttp.setRequest(JSON.toJSONString(listHealthPlanBO));
            recordhttp.setResponse(listhealthplanResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(listhealthplanResult.contains("\"result\":1"),true);
            Assert.assertEquals(listhealthplanResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(listhealthplanResult.contains("plan"),true);
            Assert.assertEquals(listhealthplanResult.contains("over"),true);
            Assert.assertEquals(listhealthplanResult.contains("in"),true);
        }

    }


    /**
     * 获取某个用户的健康计划打卡天数。一次只能查询一条健康计划打卡情况
     * @throws Exception
     */
    @Test
    public void gethealthplandayTest() throws Exception {
        String gethealthplandayUrl =null;
        GetHealthPlanDayBO getHealthPlanDayBO =null;
        String gethealthplandayResult ="";
        try{
             gethealthplandayUrl = url+"/BusinessService/health/gethealthplanday";
             getHealthPlanDayBO = new GetHealthPlanDayBO();
            HashMap<String,String> userLogin=userLoginTest();
            getHealthPlanDayBO.setToken(userLogin.get("token"));
            getHealthPlanDayBO.setUid(userLogin.get("uid"));
            getHealthPlanDayBO.setBmAppid("4.00090");
            getHealthPlanDayBO.setAppid("4.00090");
            getHealthPlanDayBO.setId("4");
            log.info("gethealthplandayUrl 请求的参数=" + gethealthplandayUrl);
            log.info("completeHealthPlanBO 请求的参数=" + JSON.toJSONString(getHealthPlanDayBO));
             gethealthplandayResult = HttpUtil.postGeneralUrl(gethealthplandayUrl, "application/json", JSON.toJSONString(getHealthPlanDayBO), "UTF-8");
            log.info("gethealthplandayResult 返回结果=" + JSON.parseObject(gethealthplandayResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取某个用户的健康计划打卡天数。一次只能查询一条健康计划打卡情况");
            recordhttp.setUrl(gethealthplandayUrl);
            recordhttp.setRequest(JSON.toJSONString(getHealthPlanDayBO));
            recordhttp.setResponse(gethealthplandayResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(gethealthplandayResult.contains("\"result\":1"),true);
            Assert.assertEquals(gethealthplandayResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(gethealthplandayResult.contains("signAt"),true);
            Assert.assertEquals(gethealthplandayResult.contains("isOver"),true);
            Assert.assertEquals(gethealthplandayResult.contains("planDay"),true);
        }

    }


    /**
     * 根据疾病内容获取疾病详情
     * @throws Exception
     */
    @Test
    public void getdiseasewikiTest() throws Exception {
        String getdiseasewikiUrl =null;
        GetDiseaseWikiBO getDiseaseWikiBO =null;
        String getdiseasewikiResult ="";
        try{
             getdiseasewikiUrl =url+ "/BusinessService/health/getdiseasewiki";
             getDiseaseWikiBO = new GetDiseaseWikiBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDiseaseWikiBO.setToken(userLogin.get("token"));
            getDiseaseWikiBO.setUid(userLogin.get("uid"));
            getDiseaseWikiBO.setAppid("4.00090");
            getDiseaseWikiBO.setName("艾滋病");
            getDiseaseWikiBO.setSeq("abc");
            log.info("getdiseasewikiUrl 请求的参数=" + getdiseasewikiUrl);
            log.info("getDiseaseWikiBO 请求的参数=" + JSON.toJSONString(getDiseaseWikiBO));
             getdiseasewikiResult = HttpUtil.postGeneralUrl(getdiseasewikiUrl, "application/json", JSON.toJSONString(getDiseaseWikiBO), "UTF-8");
            log.info("getdiseasewikiResult 返回结果=" + JSON.parseObject(getdiseasewikiResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据疾病内容获取疾病详情");
            recordhttp.setUrl(getdiseasewikiUrl);
            recordhttp.setRequest(JSON.toJSONString(getDiseaseWikiBO));
            recordhttp.setResponse(getdiseasewikiResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdiseasewikiResult.contains("\"result\":1"),true);
            Assert.assertEquals(getdiseasewikiResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getdiseasewikiResult.contains("wiki"),true);
            Assert.assertEquals(getdiseasewikiResult.contains("summary"),true);
        }

    }


    /**
     * 健康计划删除
     * @throws Exception
     */
    @Test
    public void deletehealthplanTest() throws Exception {
        String deletehealthplanUrl =null;
        DeleteHealthPlanBO deleteHealthPlanBO =null;
        String deletehealthplanResult ="";
        try{
             deletehealthplanUrl = url+"/BusinessService/health/deletehealthplan";
             deleteHealthPlanBO = new DeleteHealthPlanBO();
            HashMap<String,String> userLogin=userLoginTest();
            deleteHealthPlanBO.setToken(userLogin.get("token"));
            deleteHealthPlanBO.setUid(userLogin.get("uid"));
            deleteHealthPlanBO.setAppid("4.00090");
            deleteHealthPlanBO.setBmAppid("4.00090");
            deleteHealthPlanBO.setId("6");
            log.info("deletehealthplanUrl 请求的参数=" + deletehealthplanUrl);
            log.info("deleteHealthPlanBO 请求的参数=" + JSON.toJSONString(deleteHealthPlanBO));
             deletehealthplanResult = HttpUtil.postGeneralUrl(deletehealthplanUrl, "application/json", JSON.toJSONString(deleteHealthPlanBO), "UTF-8");
            log.info("deletehealthplanResult 返回结果=" + JSON.parseObject(deletehealthplanResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("健康计划删除,删除不存在的健康计划id");
            recordhttp.setUrl(deletehealthplanUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteHealthPlanBO));
            recordhttp.setResponse(deletehealthplanResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(deletehealthplanResult.contains("health_plan_notexist"),true);
        }
    }


    /**
     * 健康计划里阅读文章加分
     *
     * @throws Exception
     */
    @Test
    public void readarticleTest() throws Exception {
        String readarticleUrl =null;
        ReadArticleBO readArticleBO =null;
        String readarticleResult ="";
        try{
             readarticleUrl = url+"/BusinessService/health/readarticle";
             readArticleBO = new ReadArticleBO();
            HashMap<String,String> userLogin=userLoginTest();
            readArticleBO.setToken(userLogin.get("token"));
            readArticleBO.setUid(userLogin.get("uid"));
            readArticleBO.setAppid("4.00090");
            readArticleBO.setBmAppid("4.00090");
            readArticleBO.setId("6");
            log.info("readarticleUrl 请求的参数=" + readarticleUrl);
            log.info("readArticleBO 请求的参数=" + JSON.toJSONString(readArticleBO));
             readarticleResult = HttpUtil.postGeneralUrl(readarticleUrl, "application/json", JSON.toJSONString(readArticleBO), "UTF-8");
            log.info("readarticleResult 返回结果=" + JSON.parseObject(readarticleResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("健康计划里阅读文章加分");
            recordhttp.setUrl(readarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(readArticleBO));
            recordhttp.setResponse(readarticleResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(readarticleResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(readarticleResult.contains("\"result\":1"),true);
            Assert.assertEquals(readarticleResult.contains("point"),true);
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

    }

}
