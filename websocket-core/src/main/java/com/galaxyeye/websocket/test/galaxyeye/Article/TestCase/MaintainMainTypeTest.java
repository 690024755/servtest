package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.MainTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.MainTypeListBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.MaintainMainTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewMainTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.MainTypeListDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.Json.JsonPathUtils;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.TypeRef;
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
public class MaintainMainTypeTest extends BaseTest {

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private MainTypeRepository mainTypeRepository;

    @Autowired
    private NewMainTypeTest newMainTypeTest;

    @Autowired
    private MainTypeListTest mainTypeListTest;


    /**
     * 编辑大类信息通用接口
     * @throws Exception
     */
    public String maintainMainTypeTestByGernal(MaintainMainTypeBO maintainMainTypeBO) throws Exception {
        String maintainMainTypeUrl = null;
        String maintainMainTypeResult = "";
        destroyData();
        try {
            maintainMainTypeUrl = url + "/ArticleService/maintainmaintype";
            log.info("maintainMainTypeUrl 请求的参数=" + maintainMainTypeUrl);
            log.info("maintainMainTypeBO 请求的参数=" + JSON.toJSONString(maintainMainTypeBO));
            maintainMainTypeResult = HttpUtil.postGeneralUrl(maintainMainTypeUrl, "application/json", JSON.toJSONString(maintainMainTypeBO), "UTF-8");
            log.info("maintainMainTypeResult 返回结果=" + maintainMainTypeResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("编辑大类信息通用接口");
            recordhttp.setUrl(maintainMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(maintainMainTypeBO));
            recordhttp.setResponse(maintainMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            return maintainMainTypeResult;
        }
    }

    /**
     * 主服务器发送topic,从服务器可以收到大类维护变更通知
     * @throws Exception
     */
    @Test
    public void maintainMainTypeTestByReceiveTopic() throws Exception {
        String maintainMainTypeUrl = null;
        MaintainMainTypeBO maintainMainTypeBO = null;
        String maintainMainTypeResult = "";
        HashMap<String, String> hs= userLoginTest();
        Integer MaintainBefore=1;
        Integer MaintainAfter=2;
        destroyData();
        try {
            NewMainTypeBO newMainTypeBO=new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(MaintainBefore);
            newMainTypeBO.setAppid("1.00002");
            String newMainTypeResult=newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            maintainMainTypeUrl = url + "/ArticleService/maintainmaintype";
            maintainMainTypeBO = new MaintainMainTypeBO();
            maintainMainTypeBO.setCode(list.get(0).getMainTypeCode());
            maintainMainTypeBO.setMaintain(MaintainAfter);
            maintainMainTypeBO.setUid(hs.get("uid"));
            maintainMainTypeBO.setToken(hs.get("token"));
            maintainMainTypeBO.setAppid("1.00002");
            log.info("maintainMainTypeUrl 请求的参数=" + maintainMainTypeUrl);
            log.info("maintainMainTypeBO 请求的参数=" + JSON.toJSONString(maintainMainTypeBO));
            maintainMainTypeResult = HttpUtil.postGeneralUrl(maintainMainTypeUrl, "application/json", JSON.toJSONString(maintainMainTypeBO), "UTF-8");
            log.info("maintainMainTypeResult 返回结果=" + maintainMainTypeResult);

            MainTypeListBO mainTypeListBO=new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setMaintain(MaintainAfter);
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            String mainTypeListResult = mainTypeListTest.mainTypeListTestByGernal(mainTypeListBO);
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("主服务器发送topic,从服务器可以收到大类维护变更通知");
            recordhttp.setUrl(maintainMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(maintainMainTypeBO));
            recordhttp.setResponse(maintainMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(maintainMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(maintainMainTypeResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 修改的大类code不存在
     * @throws Exception
     */
    @Test
    public void maintainMainTypeTestByModifyNotExistCode() throws Exception {
        String maintainMainTypeUrl = null;
        MaintainMainTypeBO maintainMainTypeBO = null;
        String maintainMainTypeResult = "";
        HashMap<String, String> hs= userLoginTest();
        Integer MaintainBefore=1;
        Integer MaintainAfter=2;
        destroyData();
        try {
            NewMainTypeBO newMainTypeBO=new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(MaintainBefore);
            newMainTypeBO.setAppid("1.00002");
            String newMainTypeResult=newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            maintainMainTypeUrl = url + "/ArticleService/maintainmaintype";
            maintainMainTypeBO = new MaintainMainTypeBO();
            maintainMainTypeBO.setCode(list.get(0).getMainTypeCode().substring(0,list.get(0).getMainTypeCode().length()-1));
            maintainMainTypeBO.setMaintain(MaintainAfter);
            maintainMainTypeBO.setUid(hs.get("uid"));
            maintainMainTypeBO.setToken(hs.get("token"));
            maintainMainTypeBO.setAppid("1.00002");
            log.info("maintainMainTypeUrl 请求的参数=" + maintainMainTypeUrl);
            log.info("maintainMainTypeBO 请求的参数=" + JSON.toJSONString(maintainMainTypeBO));
            maintainMainTypeResult = HttpUtil.postGeneralUrl(maintainMainTypeUrl, "application/json", JSON.toJSONString(maintainMainTypeBO), "UTF-8");
            log.info("maintainMainTypeResult 返回结果=" + maintainMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改的大类code不存在");
            recordhttp.setUrl(maintainMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(maintainMainTypeBO));
            recordhttp.setResponse(maintainMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(maintainMainTypeResult.contains("\"msg\":\"maintype_notexist\""), true);
            Assert.assertEquals(maintainMainTypeResult.contains("\"result\":403"), true);
        }
    }


    /**
     * 参数Maintain=1
     * @throws Exception
     */
    @Test
    public void maintainMainTypeTestByParameterMaintainValueIsOne() throws Exception {
        String maintainMainTypeUrl = null;
        MaintainMainTypeBO maintainMainTypeBO = null;
        String maintainMainTypeResult = "";
        HashMap<String, String> hs= userLoginTest();
        Integer MaintainBefore=2;
        Integer MaintainAfter=1;
        destroyData();
        try {
            NewMainTypeBO newMainTypeBO=new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(MaintainBefore);
            newMainTypeBO.setAppid("1.00002");
            String newMainTypeResult=newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            maintainMainTypeUrl = url + "/ArticleService/maintainmaintype";
            maintainMainTypeBO = new MaintainMainTypeBO();
            maintainMainTypeBO.setCode(list.get(0).getMainTypeCode());
            maintainMainTypeBO.setMaintain(MaintainAfter);
            maintainMainTypeBO.setUid(hs.get("uid"));
            maintainMainTypeBO.setToken(hs.get("token"));
            maintainMainTypeBO.setAppid("1.00002");
            log.info("maintainMainTypeUrl 请求的参数=" + maintainMainTypeUrl);
            log.info("maintainMainTypeBO 请求的参数=" + JSON.toJSONString(maintainMainTypeBO));
            maintainMainTypeResult = HttpUtil.postGeneralUrl(maintainMainTypeUrl, "application/json", JSON.toJSONString(maintainMainTypeBO), "UTF-8");
            log.info("maintainMainTypeResult 返回结果=" + maintainMainTypeResult);

            MainTypeListBO mainTypeListBO=new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setMaintain(MaintainAfter);
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            String mainTypeListResult = mainTypeListTest.mainTypeListTestByGernal(mainTypeListBO);
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
            MainTypeListDTO mainTypeListDTO=JsonPathUtils.parse(mainTypeListResult, "$", new TypeRef<MainTypeListDTO>(){});
            List<MainTypeListDTO.MainTypeDTO> listBean=mainTypeListDTO.getList();
            Assert.assertTrue(Integer.valueOf(listBean.get(0).getIs_maintained()).equals(MaintainAfter));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Maintain=1");
            recordhttp.setUrl(maintainMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(maintainMainTypeBO));
            recordhttp.setResponse(maintainMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(maintainMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(maintainMainTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参数Maintain=2
     * @throws Exception
     */
    @Test
    public void maintainMainTypeTestByParameterMaintainValueIsTwo() throws Exception {
        String maintainMainTypeUrl = null;
        MaintainMainTypeBO maintainMainTypeBO = null;
        String maintainMainTypeResult = "";
        HashMap<String, String> hs= userLoginTest();
        Integer MaintainBefore=1;
        Integer MaintainAfter=2;
        destroyData();
        try {
            NewMainTypeBO newMainTypeBO=new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(MaintainBefore);
            newMainTypeBO.setAppid("1.00002");
            String newMainTypeResult=newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            maintainMainTypeUrl = url + "/ArticleService/maintainmaintype";
            maintainMainTypeBO = new MaintainMainTypeBO();
            maintainMainTypeBO.setCode(list.get(0).getMainTypeCode());
            maintainMainTypeBO.setMaintain(MaintainAfter);
            maintainMainTypeBO.setUid(hs.get("uid"));
            maintainMainTypeBO.setToken(hs.get("token"));
            maintainMainTypeBO.setAppid("1.00002");
            log.info("maintainMainTypeUrl 请求的参数=" + maintainMainTypeUrl);
            log.info("maintainMainTypeBO 请求的参数=" + JSON.toJSONString(maintainMainTypeBO));
            maintainMainTypeResult = HttpUtil.postGeneralUrl(maintainMainTypeUrl, "application/json", JSON.toJSONString(maintainMainTypeBO), "UTF-8");
            log.info("maintainMainTypeResult 返回结果=" + maintainMainTypeResult);

            MainTypeListBO mainTypeListBO=new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setMaintain(MaintainAfter);
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            String mainTypeListResult = mainTypeListTest.mainTypeListTestByGernal(mainTypeListBO);
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
            MainTypeListDTO mainTypeListDTO=JsonPathUtils.parse(mainTypeListResult, "$", new TypeRef<MainTypeListDTO>(){});
            List<MainTypeListDTO.MainTypeDTO> listBean=mainTypeListDTO.getList();
            Assert.assertTrue(Integer.valueOf(listBean.get(0).getIs_maintained()).equals(MaintainAfter));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Maintain=2");
            recordhttp.setUrl(maintainMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(maintainMainTypeBO));
            recordhttp.setResponse(maintainMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(maintainMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(maintainMainTypeResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参数Maintain=10
     * @throws Exception
     */
    @Test
    public void maintainMainTypeTestByParameterMaintainValueIsTen() throws Exception {
        String maintainMainTypeUrl = null;
        MaintainMainTypeBO maintainMainTypeBO = null;
        String maintainMainTypeResult = "";
        HashMap<String, String> hs= userLoginTest();
        Integer MaintainBefore=1;
        Integer MaintainAfter=10;
        destroyData();
        try {
            NewMainTypeBO newMainTypeBO=new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(MaintainBefore);
            newMainTypeBO.setAppid("1.00002");
            String newMainTypeResult=newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            maintainMainTypeUrl = url + "/ArticleService/maintainmaintype";
            maintainMainTypeBO = new MaintainMainTypeBO();
            maintainMainTypeBO.setCode(list.get(0).getMainTypeCode());
            maintainMainTypeBO.setMaintain(MaintainAfter);
            maintainMainTypeBO.setUid(hs.get("uid"));
            maintainMainTypeBO.setToken(hs.get("token"));
            maintainMainTypeBO.setAppid("1.00002");
            log.info("maintainMainTypeUrl 请求的参数=" + maintainMainTypeUrl);
            log.info("maintainMainTypeBO 请求的参数=" + JSON.toJSONString(maintainMainTypeBO));
            maintainMainTypeResult = HttpUtil.postGeneralUrl(maintainMainTypeUrl, "application/json", JSON.toJSONString(maintainMainTypeBO), "UTF-8");
            log.info("maintainMainTypeResult 返回结果=" + maintainMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Maintain=10");
            recordhttp.setUrl(maintainMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(maintainMainTypeBO));
            recordhttp.setResponse(maintainMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(maintainMainTypeResult.contains("\"msg\":\"maintain has a wrong value\""), true);
            Assert.assertEquals(maintainMainTypeResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数Code校验
     * @throws Exception
     */
    @Test
    public void maintainMainTypeTestByNotExistParameterCode() throws Exception {
        String maintainMainTypeUrl = null;
        MaintainMainTypeBO maintainMainTypeBO = null;
        String maintainMainTypeResult = "";
        HashMap<String, String> hs= userLoginTest();
        Integer MaintainBefore=1;
        Integer MaintainAfter=2;
        destroyData();
        try {
            NewMainTypeBO newMainTypeBO=new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(MaintainBefore);
            newMainTypeBO.setAppid("1.00002");
            String newMainTypeResult=newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            maintainMainTypeUrl = url + "/ArticleService/maintainmaintype";
            maintainMainTypeBO = new MaintainMainTypeBO();
//            maintainMainTypeBO.setCode(list.get(0).getMainTypeCode());
            maintainMainTypeBO.setMaintain(MaintainAfter);
            maintainMainTypeBO.setUid(hs.get("uid"));
            maintainMainTypeBO.setToken(hs.get("token"));
            maintainMainTypeBO.setAppid("1.00002");
            log.info("maintainMainTypeUrl 请求的参数=" + maintainMainTypeUrl);
            log.info("maintainMainTypeBO 请求的参数=" + JSON.toJSONString(maintainMainTypeBO));
            maintainMainTypeResult = HttpUtil.postGeneralUrl(maintainMainTypeUrl, "application/json", JSON.toJSONString(maintainMainTypeBO), "UTF-8");
            log.info("maintainMainTypeResult 返回结果=" + maintainMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Code校验");
            recordhttp.setUrl(maintainMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(maintainMainTypeBO));
            recordhttp.setResponse(maintainMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(maintainMainTypeResult.contains("\"msg\":\"code is empty\""), true);
            Assert.assertEquals(maintainMainTypeResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数Maintain校验
     * @throws Exception
     */
    @Test
    public void maintainMainTypeTestByNotExistParameterMaintain() throws Exception {
        String maintainMainTypeUrl = null;
        MaintainMainTypeBO maintainMainTypeBO = null;
        String maintainMainTypeResult = "";
        HashMap<String, String> hs= userLoginTest();
        Integer MaintainBefore=1;
        Integer MaintainAfter=2;
        destroyData();
        try {
            NewMainTypeBO newMainTypeBO=new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(MaintainBefore);
            newMainTypeBO.setAppid("1.00002");
            String newMainTypeResult=newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            maintainMainTypeUrl = url + "/ArticleService/maintainmaintype";
            maintainMainTypeBO = new MaintainMainTypeBO();
            maintainMainTypeBO.setCode(list.get(0).getMainTypeCode());
//            maintainMainTypeBO.setMaintain(MaintainAfter);
            maintainMainTypeBO.setUid(hs.get("uid"));
            maintainMainTypeBO.setToken(hs.get("token"));
            maintainMainTypeBO.setAppid("1.00002");
            log.info("maintainMainTypeUrl 请求的参数=" + maintainMainTypeUrl);
            log.info("maintainMainTypeBO 请求的参数=" + JSON.toJSONString(maintainMainTypeBO));
            maintainMainTypeResult = HttpUtil.postGeneralUrl(maintainMainTypeUrl, "application/json", JSON.toJSONString(maintainMainTypeBO), "UTF-8");
            log.info("maintainMainTypeResult 返回结果=" + maintainMainTypeResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Maintain校验");
            recordhttp.setUrl(maintainMainTypeUrl);
            recordhttp.setRequest(JSON.toJSONString(maintainMainTypeBO));
            recordhttp.setResponse(maintainMainTypeResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(maintainMainTypeResult.contains("\"msg\":\"maintain has a wrong value\""), true);
            Assert.assertEquals(maintainMainTypeResult.contains("\"result\":101"), true);
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
        mainTypeListTest.destroyData();
    }
}
