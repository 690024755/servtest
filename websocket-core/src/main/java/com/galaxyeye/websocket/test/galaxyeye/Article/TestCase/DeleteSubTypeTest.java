package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssSubType;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssSubTypeExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.*;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
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
public class DeleteSubTypeTest extends BaseTest {

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private NewSubTypeTest newSubTypeTest;

    @Autowired
    private NewMainTypeTest newMainTypeTest;

    @Autowired
    private AssSubTypeRepository assSubTypeRepository;

    @Autowired
    private MainTypeRepository mainTypeRepository;

    @Autowired
    private SubTypeListTest subTypeListTest;

    private HashMap<String, String> hs=null;
    private List<MainType> mainTypeList= null;
    /**
     * 删除文章小类通用接口
     * @throws Exception
     */
    public String deleteSubTypeTestByGernal(DeleteSubTypeBO deleteSubTypeBO) throws Exception {
        String deleteSubTypeUrl = null;
        String deleteSubTypeResult = "";
        try {
            deleteSubTypeUrl = url + "/ArticleService/deletesubtype";
            log.info("deleteSubTypeUrl 请求的参数=" + deleteSubTypeUrl);
            log.info("deleteSubTypeBO 请求的参数=" + JSON.toJSONString(deleteSubTypeBO));
            deleteSubTypeResult = HttpUtil.postGeneralUrl(deleteSubTypeUrl, "application/json", JSON.toJSONString(deleteSubTypeBO), "UTF-8");
            log.info("deleteSubTypeResult 返回结果=" + deleteSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除文章小类通用接口");
            recordhttp.setUrl(deleteSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteSubTypeBO));
            recordhttp.setResponse(deleteSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            return deleteSubTypeResult;
        }
    }

    /**
     * 编辑文章的小类信息,主服务器发送topic信息“send: topic=SubtypeNotify_topic_test”
     * 从服务器收到文章小类编辑通知“NsqSubtypeHandler”及“nsqUpdSubtypeCache”及“HandlerFunc”，刷新文章修改后缓存
     * @throws Exception
     */
    @Test
    public void deleteSubTypeTestByReceiveTopic() throws Exception {
        String deleteSubTypeUrl = null;
        DeleteSubTypeBO deleteSubTypeBO = null;
        String deleteSubTypeResult = "";
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO=new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            deleteSubTypeUrl = url + "/ArticleService/deletesubtype";
            deleteSubTypeBO = new DeleteSubTypeBO();
            deleteSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            deleteSubTypeBO.setToken(hs.get("token"));
            deleteSubTypeBO.setUid(hs.get("uid"));
            deleteSubTypeBO.setAppid("1.00002");
            log.info("deleteSubTypeUrl 请求的参数=" + deleteSubTypeUrl);
            log.info("deleteSubTypeBO 请求的参数=" + JSON.toJSONString(deleteSubTypeBO));
            deleteSubTypeResult = HttpUtil.postGeneralUrl(deleteSubTypeUrl, "application/json", JSON.toJSONString(deleteSubTypeBO), "UTF-8");
            log.info("deleteSubTypeResult 返回结果=" + deleteSubTypeResult);

            SubTypeListBO subTypeListBO=new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            String subTypeListResult=subTypeListTest.subTypeListTestByGernal(subTypeListBO);
            Assert.assertEquals(subTypeListResult.contains("\"count\":0"), true);
            Assert.assertEquals(subTypeListResult.contains("\"list\":[]"), true);
            Assert.assertEquals(subTypeListResult.contains("\"page\":0"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除小类信息，发送topic");
            recordhttp.setUrl(deleteSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteSubTypeBO));
            recordhttp.setResponse(deleteSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deleteSubTypeResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 小类删除后，在新增同一个小类
     * @throws Exception
     */
    @Test
    public void deleteSubTypeTestByReceiveTopicAndAddRepeatSubType() throws Exception {
        String deleteSubTypeUrl = null;
        DeleteSubTypeBO deleteSubTypeBO = null;
        String deleteSubTypeResult = "";
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO=new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            deleteSubTypeUrl = url + "/ArticleService/deletesubtype";
            deleteSubTypeBO = new DeleteSubTypeBO();
            deleteSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            deleteSubTypeBO.setToken(hs.get("token"));
            deleteSubTypeBO.setUid(hs.get("uid"));
            deleteSubTypeBO.setAppid("1.00002");
            log.info("deleteSubTypeUrl 请求的参数=" + deleteSubTypeUrl);
            log.info("deleteSubTypeBO 请求的参数=" + JSON.toJSONString(deleteSubTypeBO));
            deleteSubTypeResult = HttpUtil.postGeneralUrl(deleteSubTypeUrl, "application/json", JSON.toJSONString(deleteSubTypeBO), "UTF-8");
            log.info("deleteSubTypeResult 返回结果=" + deleteSubTypeResult);

            SubTypeListBO subTypeListBO=new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            String subTypeListResult=subTypeListTest.subTypeListTestByGernal(subTypeListBO);
            Assert.assertEquals(subTypeListResult.contains("\"count\":0"), true);
            Assert.assertEquals(subTypeListResult.contains("\"list\":[]"), true);
            Assert.assertEquals(subTypeListResult.contains("\"page\":0"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);

            String newSubTypeResult_repeat=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult_repeat.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult_repeat.contains("\"result\":1"), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除小类信息，发送topic");
            recordhttp.setUrl(deleteSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteSubTypeBO));
            recordhttp.setResponse(deleteSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deleteSubTypeResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 删除已存在的code
     * @throws Exception
     */
    @Test
    public void deleteSubTypeTestByParameterCodeValueIsExist() throws Exception {
        String deleteSubTypeUrl = null;
        DeleteSubTypeBO deleteSubTypeBO = null;
        String deleteSubTypeResult = "";
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO=new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            deleteSubTypeUrl = url + "/ArticleService/deletesubtype";
            deleteSubTypeBO = new DeleteSubTypeBO();
            deleteSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            deleteSubTypeBO.setToken(hs.get("token"));
            deleteSubTypeBO.setUid(hs.get("uid"));
            deleteSubTypeBO.setAppid("1.00002");
            log.info("deleteSubTypeUrl 请求的参数=" + deleteSubTypeUrl);
            log.info("deleteSubTypeBO 请求的参数=" + JSON.toJSONString(deleteSubTypeBO));
            deleteSubTypeResult = HttpUtil.postGeneralUrl(deleteSubTypeUrl, "application/json", JSON.toJSONString(deleteSubTypeBO), "UTF-8");
            log.info("deleteSubTypeResult 返回结果=" + deleteSubTypeResult);

            SubTypeListBO subTypeListBO=new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            String subTypeListResult=subTypeListTest.subTypeListTestByGernal(subTypeListBO);
            Assert.assertEquals(subTypeListResult.contains("\"count\":0"), true);
            Assert.assertEquals(subTypeListResult.contains("\"list\":[]"), true);
            Assert.assertEquals(subTypeListResult.contains("\"page\":0"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除已存在的code");
            recordhttp.setUrl(deleteSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteSubTypeBO));
            recordhttp.setResponse(deleteSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deleteSubTypeResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 删除不存在的code
     * @throws Exception
     */
    @Test
    public void deleteSubTypeTestByParameterCodeValueIsNotExist() throws Exception {
        String deleteSubTypeUrl = null;
        DeleteSubTypeBO deleteSubTypeBO = null;
        String deleteSubTypeResult = "";
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO=new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            deleteSubTypeUrl = url + "/ArticleService/deletesubtype";
            deleteSubTypeBO = new DeleteSubTypeBO();
            deleteSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode().substring(0,assSubTypeList.get(0).getAssSubTypeCode().length()-1));
            deleteSubTypeBO.setToken(hs.get("token"));
            deleteSubTypeBO.setUid(hs.get("uid"));
            deleteSubTypeBO.setAppid("1.00002");
            log.info("deleteSubTypeUrl 请求的参数=" + deleteSubTypeUrl);
            log.info("deleteSubTypeBO 请求的参数=" + JSON.toJSONString(deleteSubTypeBO));
            deleteSubTypeResult = HttpUtil.postGeneralUrl(deleteSubTypeUrl, "application/json", JSON.toJSONString(deleteSubTypeBO), "UTF-8");
            log.info("deleteSubTypeResult 返回结果=" + deleteSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除不存在的code");
            recordhttp.setUrl(deleteSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteSubTypeBO));
            recordhttp.setResponse(deleteSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteSubTypeResult.contains("\"msg\":\"subtype_notexist\""), true);
            Assert.assertEquals(deleteSubTypeResult.contains("\"result\":404"), true);
        }
    }

    /**
     * 必填参数code校验
     * @throws Exception
     */
    @Test
    public void deleteSubTypeTestByNotExistParameterCode() throws Exception {
        String deleteSubTypeUrl = null;
        DeleteSubTypeBO deleteSubTypeBO = null;
        String deleteSubTypeResult = "";
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO=new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            deleteSubTypeUrl = url + "/ArticleService/deletesubtype";
            deleteSubTypeBO = new DeleteSubTypeBO();
//            deleteSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            deleteSubTypeBO.setToken(hs.get("token"));
            deleteSubTypeBO.setUid(hs.get("uid"));
            deleteSubTypeBO.setAppid("1.00002");
            log.info("deleteSubTypeUrl 请求的参数=" + deleteSubTypeUrl);
            log.info("deleteSubTypeBO 请求的参数=" + JSON.toJSONString(deleteSubTypeBO));
            deleteSubTypeResult = HttpUtil.postGeneralUrl(deleteSubTypeUrl, "application/json", JSON.toJSONString(deleteSubTypeBO), "UTF-8");
            log.info("deleteSubTypeResult 返回结果=" + deleteSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除不存在的code");
            recordhttp.setUrl(deleteSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteSubTypeBO));
            recordhttp.setResponse(deleteSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deleteSubTypeResult.contains("\"msg\":\"code is empty\""), true);
            Assert.assertEquals(deleteSubTypeResult.contains("\"result\":101"), true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {
        destroyData();
        try{
            hs= userLoginTest();
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            String newMainTypeResult=newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);
            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            mainTypeList= mainTypeRepository.selectByExample(example);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroyData() {
        AssSubTypeExample example=new AssSubTypeExample();
        example.createCriteria().andAssSubTypeNameIn(new ArrayList(){{
            add("yy");
            add("y y");
            add("😂✌");
            add("y");
        }});
        assSubTypeRepository.deleteByExample(example);
        newMainTypeTest.destroyData();
    }
}
