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
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.DeleteSubTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewMainTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewSubTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.SubTypeListBO;
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
public class NewSubTypeTest extends BaseTest {

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SubTypeListTest subTypeListTest;

    @Autowired
    private DeleteSubTypeTest deleteSubTypeTest;

    @Autowired
    private NewMainTypeTest newMainTypeTest;

    @Autowired
    private MainTypeRepository mainTypeRepository;

    @Autowired
    private AssSubTypeRepository assSubTypeRepository;



    private HashMap<String, String> hs=null;


    private List<MainType> mainTypeList= null;

    /**
     * 新增文章小类通用接口
     * @throws Exception
     */
    @Test
    public String newSubTypeTestByGernal(NewSubTypeBO newSubTypeBO) throws Exception {
        String newSubTypeUrl = null;
        String newSubTypeResult = "";
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果=" + newSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增文章小类通用接口");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
           return newSubTypeResult;
        }
    }

    /**
     * 新增文章的小类信息,主服务器发送topic信息“SubtypeNotify_topic_test”
     * 从服务器收到文章大类新增通知“NsqSubtypeHandler”及“nsqAddSubtypeCache”及“nsq.HandlerFunc”，刷新文章缓存
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByReceiveTopic() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subTypeListResult ="";
        String subName="yy";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果=" + newSubTypeResult);

            SubTypeListBO subTypeListBO=new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            subTypeListResult =subTypeListTest.subTypeListTestByGernal(subTypeListBO);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增小类信息，发送topic");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
        }
    }

    /**
     * 新增小类名字超长
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByParameterNameValueIsLong() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subName="yy";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newSubTypeBO.setName(imgParamPng);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果=" + newSubTypeResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增小类名字超长");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"name length longer than 12\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 主类code一样，新增小类名字重复
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByAddSameNameAndSameMaintype() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subName="yy";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果1=" + newSubTypeResult);
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果2=" + newSubTypeResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("主类code一样，新增小类名字重复");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"duplicate_subtype\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":407"), true);
        }
    }


    /**
     * 主类code不同，新增小类名字重复
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByAddSameNameAndDiffrentMaintype() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subName="yy";
        initData();
        try {
            List<MainType> mainTypeAllList= mainTypeRepository.selectAll();
            String maintypeTmp=null;
            for (MainType mainType:mainTypeAllList
                 ) {
                if(!mainTypeList.get(0).getMainTypeCode().equals(mainType.getMainTypeCode())){
                    maintypeTmp=mainType.getMainTypeCode();
                    break;
                }
            }
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果1=" + newSubTypeResult);
            newSubTypeBO.setMaintype(maintypeTmp);
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果2=" + newSubTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("主类code不同，新增小类名字重复");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 新增小类名字为空格
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByParameterNameValueIsTab() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subName=" ";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果1=" + newSubTypeResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增小类名字为空格");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"name is empty\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新增小类名字为表情
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByParameterNameValueIsEmotion() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subName="😂✌";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果1=" + newSubTypeResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增小类名字为表情");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 新增小类名字为首尾去空格
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByParameterNameValueIsTabAndCharacter() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        List<AssSubType> assSubTypeList=null;;
        String subName=" y y ";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果1=" + newSubTypeResult);
            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo("y y");
            assSubTypeList= assSubTypeRepository.selectByExample(example);

            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增小类名字为首尾去空格");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);
            Assert.assertTrue(assSubTypeList.size()>0);
        }
    }

    /**
     * 新增小类,但是主类的code不存在
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByParameterMaintypeValueIsNotExist() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subName="yy";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode().substring(0,mainTypeList.get(0).getMainTypeCode().length()-1));
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果1=" + newSubTypeResult);

            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增小类,但是主类的code不存在");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"maintype_notexist\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":403"), true);
        }
    }


    /**
     * 必填参数Name校验
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByNotExistParameterName() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subName="yy";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
//            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果1=" + newSubTypeResult);

            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Name校验");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"name is empty\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 必填参数Maintype校验
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByNotExistParameterMaintype() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subName="yy";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
//            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果1=" + newSubTypeResult);

            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Maintype校验");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"maintype is empty\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 新增小类后，然后删除小类，最后把删除的小类原样加回来(删除后添加回同名的小类)
     * @throws Exception
     */
    @Test
    public void newSubTypeTestByDeleteSubTypeAndAddDeleteSubType() throws Exception {
        String newSubTypeUrl = null;
        NewSubTypeBO newSubTypeBO = null;
        String newSubTypeResult = "";
        String subName="yy";
        initData();
        try {
            newSubTypeUrl = url + "/ArticleService/newsubtype";
            newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            log.info("newSubTypeUrl 请求的参数=" + newSubTypeUrl);
            log.info("newSubTypeBO 请求的参数=" + JSON.toJSONString(newSubTypeBO));
            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果1=" + newSubTypeResult);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);
            DeleteSubTypeBO deleteSubTypeBO=new DeleteSubTypeBO();
            deleteSubTypeBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            deleteSubTypeBO.setToken(hs.get("token"));
            deleteSubTypeBO.setUid(hs.get("uid"));
            deleteSubTypeBO.setAppid("1.00002");
            deleteSubTypeTest.deleteSubTypeTestByGernal(deleteSubTypeBO);

            newSubTypeResult = HttpUtil.postGeneralUrl(newSubTypeUrl, "application/json", JSON.toJSONString(newSubTypeBO), "UTF-8");
            log.info("newSubTypeResult 返回结果2=" + newSubTypeResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增小类后，然后删除小类，最后把删除的小类原样加回来");
            recordhttp.setUrl(newSubTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newSubTypeBO));
            recordhttp.setResponse(newSubTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);
        }
    }



    public List<MainType> getMainTypeList() {
        return mainTypeList;
    }
    public HashMap<String, String> getHs() {
        return hs;
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
