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
import com.galaxyeye.websocket.tool.encr.Base64Util;
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
public class EditSubTypeTest extends BaseTest {

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

    @Autowired
    private DeleteSubTypeTest deleteSubTypeTest;

    private HashMap<String, String> hs=null;
    private List<MainType> mainTypeList= null;


    /**
     * 编辑文章小类通用接口
     * @throws Exception
     */
    public String editSubTypeTestByGernal( EditSubTypeBO editSubTypeBO) throws Exception {
        String editSubTypeUrl = null;
        String editSubTypeResult = "";
        try {
            editSubTypeUrl = url + "/ArticleService/editsubtype";
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("编辑文章小类通用接口");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
           return editSubTypeResult;
        }
    }


    /**
     * 编辑文章的小类信息,主服务器发送topic信息“send: topic=SubtypeNotify_topic_test”
     * 从服务器收到文章小类编辑通知“NsqSubtypeHandler”及“nsqUpdSubtypeCache”及“HandlerFunc”，刷新文章修改后缓存
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByReceiveTopic() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String subName="yy";
        initData();
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

            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            editSubTypeBO.setName(assSubTypeList.get(0).getAssSubTypeName());
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);

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
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("编辑小类信息，发送topic");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 修改不存在小类code值
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByParameterCodeValueIsNotExist() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String subName="yy";
        initData();
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

            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode().substring(0,assSubTypeList.get(0).getAssSubTypeCode().length()-1));
            editSubTypeBO.setName(assSubTypeList.get(0).getAssSubTypeName());
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改不存在小类code值");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"subtype_notexist\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":404"), true);
        }
    }

    /**
     * 修改小类名称为表情
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByParameterNameValueIsEmotion() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String subNameAfter="😂✌";
        String subNameBefore="yy";
        initData();
        try {
            NewSubTypeBO newSubTypeBO=new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subNameBefore);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subNameBefore);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            editSubTypeBO.setName(subNameAfter);
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);

            SubTypeListBO subTypeListBO=new SubTypeListBO();
            subTypeListBO.setName(subNameAfter);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            String subTypeListResult=subTypeListTest.subTypeListTestByGernal(subTypeListBO);
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改小类名称为表情");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 修改小类名称超过12个字符
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByParameterNameValueIsLong() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String filePathGif = getFilePath("Article4.gif");
        String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
        String subNameAfter=imgParamGif;
        String subNameBefore="yy";
        initData();
        try {
            NewSubTypeBO newSubTypeBO=new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subNameBefore);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subNameBefore);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            editSubTypeBO.setName(subNameAfter);
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改小类名称为表情");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"name length longer than 12\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 修改为已存在的小类code则相当于修改已存在小类的名称
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByParameterCodeValueIsAnother() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String filePathGif = getFilePath("Article4.gif");
        String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
        String subName="yy";
        initData();
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

            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            editSubTypeBO.setName(subName.substring(0,subName.length()-1));
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);

            SubTypeListBO subTypeListBO=new SubTypeListBO();
            subTypeListBO.setName(subName.substring(0,subName.length()-1));
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            String subTypeListResult=subTypeListTest.subTypeListTestByGernal(subTypeListBO);
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改为已存在的小类code则相当于修改已存在小类的名称");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 小类名称首尾去空格
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByParameterNameValueIsTabAndCharacter() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String filePathGif = getFilePath("Article4.gif");
        String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
        String subNameAfter=" y y ";
        String subNameBefore="y y";
        initData();
        try {
            NewSubTypeBO newSubTypeBO=new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subNameBefore);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subNameBefore);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            editSubTypeBO.setName(subNameAfter);
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);

            SubTypeListBO subTypeListBO=new SubTypeListBO();
            subTypeListBO.setName(subNameBefore);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            String subTypeListResult=subTypeListTest.subTypeListTestByGernal(subTypeListBO);
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("小类名称首尾去空格");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 必填参数name
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByNotExistParameterName() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String filePathGif = getFilePath("Article4.gif");
        String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
        String subName="yy";
        initData();
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

            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
//            editSubTypeBO.setName(subName);
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数name");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"name is empty\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 必填参数code
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByNotExistParameterCode() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String filePathGif = getFilePath("Article4.gif");
        String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
        String subName="yy";
        initData();
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

            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
//            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            editSubTypeBO.setName(subName);
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数code");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"code is empty\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 删除子类后，再次编辑该子类
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByDeleteSubTypeAndEditSubTypeRepeat() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String filePathGif = getFilePath("Article4.gif");
        String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
        String subName="yy";
        initData();
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

            DeleteSubTypeBO deleteSubTypeBO = new DeleteSubTypeBO();
            deleteSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            deleteSubTypeBO.setToken(hs.get("token"));
            deleteSubTypeBO.setUid(hs.get("uid"));
            deleteSubTypeBO.setAppid("1.00002");
            deleteSubTypeTest.deleteSubTypeTestByGernal(deleteSubTypeBO);


            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            editSubTypeBO.setName(subName);
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除子类后，再次编辑该子类");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"subtype_notexist\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":404"), true);
        }
    }


    /**
     * 新增文章小类后，将其他已存在小类改为该小类名称,提示名称重复
     * @throws Exception
     */
    @Test
    public void editSubTypeTestByModifyOtherLabToExistSubTypeName() throws Exception {
        String editSubTypeUrl = null;
        EditSubTypeBO editSubTypeBO = null;
        String editSubTypeResult = "";
        String filePathGif = getFilePath("Article4.gif");
        String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
        String subName1="yy";
        String subName2="😂✌";
        initData();
        try {
            NewSubTypeBO newSubTypeBO=new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName1);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult1=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult1.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult1.contains("\"result\":1"), true);


            newSubTypeBO.setName(subName2);
            String newSubTypeResult2=newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult2.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult2.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName2);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);


            editSubTypeUrl = url + "/ArticleService/editsubtype";
            editSubTypeBO = new EditSubTypeBO();
            editSubTypeBO.setUid(hs.get("uid"));
            editSubTypeBO.setToken(hs.get("token"));
            editSubTypeBO.setAppid("1.00002");
            editSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            editSubTypeBO.setName(subName1);
            log.info("editSubTypeUrl 请求的参数=" + editSubTypeUrl);
            log.info("editSubTypeBO 请求的参数=" + JSON.toJSONString(editSubTypeBO));
            editSubTypeResult = HttpUtil.postGeneralUrl(editSubTypeUrl, "application/json", JSON.toJSONString(editSubTypeBO), "UTF-8");
            log.info("editSubTypeResult 返回结果=" + editSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增文章小类后，将其他已存在小类改为该小类名称,提示名称重复");
            recordhttp.setUrl(editSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(editSubTypeBO));
            recordhttp.setResponse(editSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editSubTypeResult.contains("\"msg\":\"duplicate_subtype\""), true);
            Assert.assertEquals(editSubTypeResult.contains("\"result\":407"), true);
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
