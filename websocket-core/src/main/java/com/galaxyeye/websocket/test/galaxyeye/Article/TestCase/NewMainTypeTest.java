package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.ArticleCatAssLabRepository;
import com.galaxyeye.websocket.application.repository.ArticleCatAssSubTypeRepository;
import com.galaxyeye.websocket.application.repository.ArticleRepository;
import com.galaxyeye.websocket.application.repository.MainTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.MainTypeListBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewMainTypeBO;
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
public class NewMainTypeTest extends BaseTest {

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private MainTypeRepository mainTypeRepository;

    @Autowired
    private MainTypeListTest mainTypeListTest;


    /**
     * 新增文章的大类通用方法
     * @throws Exception
     */
    public String newMainTypeTestByGernal(NewMainTypeBO newMainTypeBO) throws Exception {
        String newMainTypeUrl = null;
        String newMainTypeResult = "";
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增文章的大类通用方法");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            return newMainTypeResult;
        }
    }


    /**
     * 新增文章的大类信息,主服务器发送topic信息“MaintypeNotify_topic_test”
     * 从服务器收到文章大类新增通知“NsqMaintypeHandler”及“nsqUpdateMaintypeCache”及“HandlerFunc”，刷新文章缓存
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByReceiveTopic() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        String mainTypeListResult ="";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list= mainTypeRepository.selectByExample(example);

            MainTypeListBO mainTypeListBO=new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(1);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setMaintain(1);
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);
            mainTypeListResult = mainTypeListTest.mainTypeListTestByGernal(url,mainTypeListBO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增大类信息，发送topic");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);

            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 新增大类的名字出现重复
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByAddSameName() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果1=" + newMainTypeResult);
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果2=" + newMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增大类的名字出现重复");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"duplicate_maintype\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":406"), true);
        }
    }

    /**
     * 新增大类的名字超长
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByParameterNameValueIsLong() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            String filePathPng=getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            newMainTypeBO.setName(imgParamPng);
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增大类的名字超长");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"name length longer than 12\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新增大类的名字是表情
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByParameterNameValueIsEmotion() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("😂✌");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增大类的名字是表情");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 新增大类的名字是表情
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByParameterNameValueIsTab() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName(" ");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增大类的名字是表情");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"name is empty\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新增大类的名字是表情
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByParameterNameValueIsTabAndCharacter() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName(" y y ");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增大类的名字是表情");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参数Maintain=1
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByParameterMaintainValueIsOne() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Maintain=1");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 参数Maintain=2
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByParameterMaintainValueIsTwo() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(2);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Maintain=2");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参数Maintain=10
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByParameterMaintainValueIsTen() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(10);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Maintain=2");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"maintain has a wrong value\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数Maintain校验
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByNotExistParameterMaintain() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
//            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Maintain校验");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"maintain has a wrong value\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数Name校验
     * @throws Exception
     */
    @Test
    public void newMainTypeTestByNotExistParameterName() throws Exception {
        mainTypeListTest.destroyData();
        String newMainTypeUrl = null;
        NewMainTypeBO newMainTypeBO = null;
        String newMainTypeResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            newMainTypeUrl = url + "/ArticleService/newmaintype";
            newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
//            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            log.info("newMainTypeUrl 请求的参数=" + newMainTypeUrl);
            log.info("newMainTypeBO 请求的参数=" + JSON.toJSONString(newMainTypeBO));
            newMainTypeResult = HttpUtil.postGeneralUrl(newMainTypeUrl, "application/json", JSON.toJSONString(newMainTypeBO), "UTF-8");
            log.info("newMainTypeResult 返回结果=" + newMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Name校验");
            recordhttp.setUrl(newMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(newMainTypeBO));
            recordhttp.setResponse(newMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"name is empty\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":101"), true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {
        //yy,y y为测试数据
        MainTypeExample example=new MainTypeExample();
        example.createCriteria().andMainTypeNameIn(new ArrayList(){
            {
                add("yy");
                add("y y");
                add("😂✌");
                add("y");
            }
        });
        mainTypeRepository.deleteByExample(example);
    }
}
