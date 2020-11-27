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
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssSubTypeExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.DeleteLabBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.LabListBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewLabBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewMainTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.LabServiceCon;
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
public class NewLabTest extends LabServiceCon {

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private AssLabRepository assLabRepository;

    @Autowired
    private MainTypeRepository mainTypeRepository;

    @Autowired
    private LabListTest labListTest;

    @Autowired
    private DeleteLabTest deleteLabTest;


    /**
     * 新增lab通用接口
     * @throws Exception
     */
    public String newLabTestByGernal(NewLabBO newLabBO) throws Exception {
        String newLabUrl = null;
        String newLabResult = "";
        try {
            newLabUrl = url + "/ArticleService/newlab";
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果=" + newLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增lab通用接口");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
           return newLabResult;
        }
    }


    /**
     * 新增文章的lab信息,主服务器发送topic信息“LabNotify_topic_test”
     * 从服务器收到文章lab新增通知“NsqLabHandler”及“nsqAddLabCache”及“nsq.HandlerFunc”，刷新文章缓存
     * @throws Exception
     */
    @Test
    public void newLabTestByReceiveTopic() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String labName="yy";
        try {
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果=" + newLabResult);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

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
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增lab信息，发送topic");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 主类code一样，新增小类名字重复
     * @throws Exception
     */
    @Test
    public void newLabTestByAddSameNameAndSameMaintype() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String labName="yy";
        try {
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果1=" + newLabResult);
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果2=" + newLabResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("主类code一样，新增小类名字重复");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"duplicate_lab\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":408"), true);
        }
    }

    /**
     * 主类code不同，新增小类名字重复
     * @throws Exception
     */
    @Test
    public void newLabTestByAddSameNameAndDiffrentMaintype() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String labName="yy";
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
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果1=" + newLabResult);
            newLabBO.setMaintype(maintypeTmp);
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果2=" + newLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("主类code不同，新增小类名字重复");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 新增lab名字超长
     * @throws Exception
     */
    @Test
    public void newLabTestByParameterNameValueIsLong() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labName=imgParamPng;
        try {
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果=" + newLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增lab名字超长");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"name length longer than 12\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新增lab名字为表情
     * @throws Exception
     */
    @Test
    public void newLabTestByParameterNameValueIsEmotion() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labName="😂✌";
        try {
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果=" + newLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增lab名字为表情");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 新增lab名字为空格
     * @throws Exception
     */
    @Test
    public void newLabTestByParameterNameValueIsTab() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labName=" ";
        try {
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果=" + newLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增lab名字为空格");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"name is empty\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新增lab名字为首尾去空格
     * @throws Exception
     */
    @Test
    public void newLabTestByParameterNameValueIsTabAndCharacter() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labName=" y y ";
        try {
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果=" + newLabResult);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo("y y");
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

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
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增lab名字为空格");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 必填参数name校验
     * @throws Exception
     */
    @Test
    public void newLabTestByNotExistParameterName() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labName="yy";
        try {
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
//            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果=" + newLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数name校验");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"name is empty\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 必填参数Maintype校验
     * @throws Exception
     */
    @Test
    public void newLabTestByNotExistParameterMaintype() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labName="yy";
        try {
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
            newLabBO.setName(labName);
//            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果=" + newLabResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Maintype校验");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"maintype is empty\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":101"), true);
        }
    }



    /**
     * 新增标签后，然后删除标签，最后把删除的标签原样加回来(删除后添加回同名的标签)
     * @throws Exception
     */
    @Test
    public void newLabTestByDeleteLabAndAddDeleteLab() throws Exception {
        String newLabUrl = null;
        NewLabBO newLabBO = null;
        String newLabResult = "";
        String filePathPng=getFilePath("Article2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        String labName="yy";
        try {
            newLabUrl = url + "/ArticleService/newlab";
            newLabBO = new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            log.info("newLabUrl 请求的参数=" + newLabUrl);
            log.info("newLabBO 请求的参数=" + JSON.toJSONString(newLabBO));
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果1=" + newLabResult);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);
            DeleteLabBO deleteLabBO = new DeleteLabBO();
            deleteLabBO.setToken(hs.get("token"));
            deleteLabBO.setUid(hs.get("uid"));
            deleteLabBO.setAppid("1.00002");
            deleteLabBO.setCode(assLabList.get(0).getAssLabCode());
            deleteLabTest.deleteLabTestByGernal(deleteLabBO);
            newLabResult = HttpUtil.postGeneralUrl(newLabUrl, "application/json", JSON.toJSONString(newLabBO), "UTF-8");
            log.info("newLabResult 返回结果2=" + newLabResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增标签后，然后删除标签，最后把删除的标签原样加回来");
            recordhttp.setUrl(newLabUrl);
            recordhttp.setRequest(JSON.toJSONString(newLabBO));
            recordhttp.setResponse(newLabResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);
        }
    }


}
