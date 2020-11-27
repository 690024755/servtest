package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssLabExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.*;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.LabServiceCon;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Component
public class DeleteLabTest extends LabServiceCon {

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private NewLabTest newLabTest;

    @Autowired
    private AssLabRepository assLabRepository;

    @Autowired
    private LabListTest labListTest;


    /**
     * 删除文章lab通用接口
     * @throws Exception
     */
    public String deleteLabTestByGernal(DeleteLabBO deleteLabBO) throws Exception {
        String deleteLabUrl = null;
        String deleteLabResult = "";
        try {
            deleteLabUrl = url + "/ArticleService/deletelab";
            log.info("deleteLabUrl 请求的参数=" + deleteLabUrl);
            log.info("deleteLabBO 请求的参数=" + JSON.toJSONString(deleteLabBO));
            deleteLabResult = HttpUtil.postGeneralUrl(deleteLabUrl, "application/json", JSON.toJSONString(deleteLabBO), "UTF-8");
            log.info("deleteLabResult 返回结果=" + deleteLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除文章lab通用接口");
            recordhttp.setUrl(deleteLabUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteLabBO));
            recordhttp.setResponse(deleteLabResult);
            initLog(recordhttp, new Object() {
            });
            return deleteLabResult;
        }
    }


    /**
     * 编辑文章的lab信息,主服务器发送topic信息“LabNotify_topic_test”
     * 从服务器收到文章lab编辑通知“NsqLabHandler”及“nsqAddLabCache”及“nsq.HandlerFunc”，刷新文章缓存
     * @throws Exception
     */
    @Test
    public void deleteLabTestByReceiveTopic() throws Exception {
        String deleteLabUrl = null;
        DeleteLabBO deleteLabBO = null;
        String deleteLabResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            deleteLabUrl = url + "/ArticleService/deletelab";
            deleteLabBO = new DeleteLabBO();
            deleteLabBO.setToken(hs.get("token"));
            deleteLabBO.setUid(hs.get("uid"));
            deleteLabBO.setAppid("1.00002");
            deleteLabBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("deleteLabUrl 请求的参数=" + deleteLabUrl);
            log.info("deleteLabBO 请求的参数=" + JSON.toJSONString(deleteLabBO));
            deleteLabResult = HttpUtil.postGeneralUrl(deleteLabUrl, "application/json", JSON.toJSONString(deleteLabBO), "UTF-8");
            log.info("deleteLabResult 返回结果=" + deleteLabResult);

            LabListBO labListBO=new LabListBO();
            labListBO.setAppid("1.00002");
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCount(10);
            labListBO.setPage(10);
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String labListResult=labListTest.labListTestByGernal(labListBO);
            Assert.assertEquals(labListResult.contains("\"count\":0"), true);
            Assert.assertEquals(labListResult.contains("\"list\":[]"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("\"page\":0"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除lab信息，发送topic");
            recordhttp.setUrl(deleteLabUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteLabBO));
            recordhttp.setResponse(deleteLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deleteLabResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 删除标签后，在添加相同的标签
     * @throws Exception
     */
    @Test
    public void deleteLabTestByReceiveTopicAndAddRepeatLab() throws Exception {
        String deleteLabUrl = null;
        DeleteLabBO deleteLabBO = null;
        String deleteLabResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            deleteLabUrl = url + "/ArticleService/deletelab";
            deleteLabBO = new DeleteLabBO();
            deleteLabBO.setToken(hs.get("token"));
            deleteLabBO.setUid(hs.get("uid"));
            deleteLabBO.setAppid("1.00002");
            deleteLabBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("deleteLabUrl 请求的参数=" + deleteLabUrl);
            log.info("deleteLabBO 请求的参数=" + JSON.toJSONString(deleteLabBO));
            deleteLabResult = HttpUtil.postGeneralUrl(deleteLabUrl, "application/json", JSON.toJSONString(deleteLabBO), "UTF-8");
            log.info("deleteLabResult 返回结果=" + deleteLabResult);

            LabListBO labListBO=new LabListBO();
            labListBO.setAppid("1.00002");
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCount(10);
            labListBO.setPage(10);
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String labListResult=labListTest.labListTestByGernal(labListBO);
            Assert.assertEquals(labListResult.contains("\"count\":0"), true);
            Assert.assertEquals(labListResult.contains("\"list\":[]"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("\"page\":0"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);

            String newLabResult_repeat =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult_repeat.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult_repeat.contains("\"result\":1"), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除标签后，在添加相同的标签");
            recordhttp.setUrl(deleteLabUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteLabBO));
            recordhttp.setResponse(deleteLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deleteLabResult.contains("\"result\":1"), true);
        }
    }


    /**
     *参数code的值不存在
     * @throws Exception
     */
    @Test
    public void deleteLabTestByParameterCodeValueIsNotExist() throws Exception {
        String deleteLabUrl = null;
        DeleteLabBO deleteLabBO = null;
        String deleteLabResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            deleteLabUrl = url + "/ArticleService/deletelab";
            deleteLabBO = new DeleteLabBO();
            deleteLabBO.setToken(hs.get("token"));
            deleteLabBO.setUid(hs.get("uid"));
            deleteLabBO.setAppid("1.00002");
            deleteLabBO.setCode(assLabList.get(0).getAssLabCode().substring(0,assLabList.get(0).getAssLabCode().length()-1));
            log.info("deleteLabUrl 请求的参数=" + deleteLabUrl);
            log.info("deleteLabBO 请求的参数=" + JSON.toJSONString(deleteLabBO));
            deleteLabResult = HttpUtil.postGeneralUrl(deleteLabUrl, "application/json", JSON.toJSONString(deleteLabBO), "UTF-8");
            log.info("deleteLabResult 返回结果=" + deleteLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数code的值不存在");
            recordhttp.setUrl(deleteLabUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteLabBO));
            recordhttp.setResponse(deleteLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteLabResult.contains("\"msg\":\"lab_notexist\""), true);
            Assert.assertEquals(deleteLabResult.contains("\"result\":405"), true);
        }
    }

    /**
     *参数code的值存在
     * @throws Exception
     */
    @Test
    public void deleteLabTestByParameterCodeValueIsExist() throws Exception {
        String deleteLabUrl = null;
        DeleteLabBO deleteLabBO = null;
        String deleteLabResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            deleteLabUrl = url + "/ArticleService/deletelab";
            deleteLabBO = new DeleteLabBO();
            deleteLabBO.setToken(hs.get("token"));
            deleteLabBO.setUid(hs.get("uid"));
            deleteLabBO.setAppid("1.00002");
            deleteLabBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("deleteLabUrl 请求的参数=" + deleteLabUrl);
            log.info("deleteLabBO 请求的参数=" + JSON.toJSONString(deleteLabBO));
            deleteLabResult = HttpUtil.postGeneralUrl(deleteLabUrl, "application/json", JSON.toJSONString(deleteLabBO), "UTF-8");
            log.info("deleteLabResult 返回结果=" + deleteLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数code的值不存在");
            recordhttp.setUrl(deleteLabUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteLabBO));
            recordhttp.setResponse(deleteLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deleteLabResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 必填参数code校验
     * @throws Exception
     */
    @Test
    public void deleteLabTestByNotExistParameterCode() throws Exception {
        String deleteLabUrl = null;
        DeleteLabBO deleteLabBO = null;
        String deleteLabResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            deleteLabUrl = url + "/ArticleService/deletelab";
            deleteLabBO = new DeleteLabBO();
            deleteLabBO.setToken(hs.get("token"));
            deleteLabBO.setUid(hs.get("uid"));
            deleteLabBO.setAppid("1.00002");
//            deleteLabBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("deleteLabUrl 请求的参数=" + deleteLabUrl);
            log.info("deleteLabBO 请求的参数=" + JSON.toJSONString(deleteLabBO));
            deleteLabResult = HttpUtil.postGeneralUrl(deleteLabUrl, "application/json", JSON.toJSONString(deleteLabBO), "UTF-8");
            log.info("deleteLabResult 返回结果=" + deleteLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数code校验");
            recordhttp.setUrl(deleteLabUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteLabBO));
            recordhttp.setResponse(deleteLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteLabResult.contains("\"msg\":\"code is empty\""), true);
            Assert.assertEquals(deleteLabResult.contains("\"result\":101"), true);
        }
    }

}
