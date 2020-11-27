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
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.LabListDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.LabServiceCon;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.Json.JsonPathUtils;
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
public class EditLabTest extends LabServiceCon {

    @Autowired
    private NewLabTest newLabTest;

    @Autowired
    private AssLabRepository assLabRepository;

    @Autowired
    private LabListTest labListTest;

    @Autowired
    private DeleteLabTest deleteLabTest;

    /**
     * 编辑文章lab通用接口
     * @throws Exception
     */
    public String editLabTestByGernal(EditLabBO editLabBO) throws Exception {
        String editLabUrl = null;
        String editLabResult = "";
        try {
            editLabUrl = url + "/ArticleService/editlab";
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("编辑文章lab通用接口");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            return editLabResult;
        }
    }


    /**
     * 编辑文章的lab信息,主服务器发送topic信息“LabNotify_topic_test”
     * 从服务器收到文章lab编辑通知“NsqLabHandler”及“nsqAddLabCache”及“nsq.HandlerFunc”，刷新文章缓存
     * @throws Exception
     */
    @Test
    public void editLabTestByReceiveTopic() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
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

            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
            editLabBO.setCode(assLabList.get(0).getAssLabCode());
            editLabBO.setName(labName);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);

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
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("编辑lab信息，发送topic");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 修改lab的code值不存在
     * @throws Exception
     */
    @Test
    public void editLabTestByParameterCodeValueIsNotExist() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
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

            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
            editLabBO.setCode(assLabList.get(0).getAssLabCode().substring(0,assLabList.get(0).getAssLabCode().length()-1));
            editLabBO.setName(labName);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改lab的code值不存在");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"lab_notexist\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":405"), true);
        }
    }

    /**
     * 修改lab的code值存在,则是对该条信息进行编辑不进行code修改
     * @throws Exception
     */
    @Test
    public void editLabTestByParameterCodeValueIsExist() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
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

            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
            editLabBO.setCode(assLabList.get(0).getAssLabCode());
            editLabBO.setName(labName);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改lab的code值存在,则是对该条信息进行编辑不进行code修改");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参数Name=表情
     * @throws Exception
     */
    @Test
    public void editLabTestByParameterNameValueIsEmotion() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
        String labName="😂✌";
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

            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
            editLabBO.setCode(assLabList.get(0).getAssLabCode());
            editLabBO.setName(labName);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Name=表情");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参数Name的值为超长
     * @throws Exception
     */
    @Test
    public void editLabTestByParameterNameValueIsLong() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labNameBefore="yy";
        String labNameAfter=imgParamPng;
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labNameBefore);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labNameBefore);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
            editLabBO.setCode(assLabList.get(0).getAssLabCode());
            editLabBO.setName(labNameAfter);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Name的值为超长");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"name length longer than 12\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数Name的值需要首尾去空格
     * @throws Exception
     */
    @Test
    public void editLabTestByParameterNameValueIsTabAndCharacter() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labNameAfter=" y y ";
        String labNameBefore="y y";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labNameBefore);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labNameBefore);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
            editLabBO.setCode(assLabList.get(0).getAssLabCode());
            editLabBO.setName(labNameAfter);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);

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
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
            LabListDTO labListDTO=JsonPathUtils.parse(labListResult,"$", LabListDTO.class);
            Assert.assertTrue(labNameBefore.equals(labListDTO.getList().get(0).getAss_lab_name()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Name的值为超长");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 必填参数name校验
     * @throws Exception
     */
    @Test
    public void editLabTestByNotExistParameterName() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
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

            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
            editLabBO.setCode(assLabList.get(0).getAssLabCode());
//            editLabBO.setName(labName);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数name校验");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"name is empty\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数code校验
     * @throws Exception
     */
    @Test
    public void editLabTestByNotExistParameterCode() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
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

            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
//            editLabBO.setCode(assLabList.get(0).getAssLabCode());
            editLabBO.setName(labName);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数code校验");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"code is empty\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 标签删除后，再次编辑已逻辑删除的标签
     * @throws Exception
     */
    @Test
    public void editLabTestByDeleteLabAndEditLabRepeat() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
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


            DeleteLabBO deleteLabBO=new DeleteLabBO();
            deleteLabBO.setToken(hs.get("token"));
            deleteLabBO.setUid(hs.get("uid"));
            deleteLabBO.setAppid("1.00002");
            deleteLabBO.setCode(assLabList.get(0).getAssLabCode());
            deleteLabTest.deleteLabTestByGernal(deleteLabBO);


            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
            editLabBO.setCode(assLabList.get(0).getAssLabCode());
            editLabBO.setName(labName);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("标签删除后，再次编辑已逻辑删除的标签");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"lab_notexist\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":405"), true);
        }
    }

    /**
     * 新增文章标签后，将其他已存在标签改为该标签名称,提示名称重复
     * @throws Exception
     */
    @Test
    public void editLabTestByModifyOtherLabToExistLabName() throws Exception {
        String editLabUrl = null;
        EditLabBO editLabBO = null;
        String editLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labName1="yy";
        String labName2="😂✌";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName1);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult1 =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult1.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult1.contains("\"result\":1"), true);

            newLabBO.setName(labName2);
            String newLabResult2 =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult2.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult2.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName2);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);


            editLabUrl = url + "/ArticleService/editlab";
            editLabBO = new EditLabBO();
            editLabBO.setCode(assLabList.get(0).getAssLabCode());
            editLabBO.setName(labName1);
            editLabBO.setToken(hs.get("token"));
            editLabBO.setUid(hs.get("uid"));
            editLabBO.setAppid("1.00002");
            log.info("editLabUrl 请求的参数=" + editLabUrl);
            log.info("editLabBO 请求的参数=" + JSON.toJSONString(editLabBO));
            editLabResult = HttpUtil.postGeneralUrl(editLabUrl, "application/json", JSON.toJSONString(editLabBO), "UTF-8");
            log.info("editLabResult 返回结果=" + editLabResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增文章标签后，将其他已存在标签改为该标签名称,提示名称重复");
            recordhttp.setUrl(editLabUrl);
            recordhttp.setRequest(JSON.toJSONString(editLabBO));
            recordhttp.setResponse(editLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(editLabResult.contains("\"msg\":\"duplicate_lab\""), true);
            Assert.assertEquals(editLabResult.contains("\"result\":408"), true);
        }
    }


}
