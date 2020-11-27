package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddeUserBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Component
public class AddAppidTest extends BaseTest {
    public static List<String> openMethods=new ArrayList<>();

    static {
        openMethods.add("createappidtoken");
        openMethods.add("queryusercountbydate");
        openMethods.add("getdiscountcoupon");
        openMethods.add("matcharticle");
        openMethods.add("getarticle");
        openMethods.add("getusercollect");
        openMethods.add("fetcharticle");
        openMethods.add("articlestatistics");
        openMethods.add("getappid");
        openMethods.add("geterobotpkg");
        openMethods.add("adderobotpkg");
        openMethods.add("geterobot");
        openMethods.add("userlogin");
        openMethods.add("userlogout");
        openMethods.add("login");
        openMethods.add("logout");
        openMethods.add("uploadimage");
        openMethods.add("savearticle");
        openMethods.add("articlelistquery");
        openMethods.add("articlelistquerybysession");
        openMethods.add("getarticleaddinfo");
        openMethods.add("auditarticle");
        openMethods.add("deletearticle");
        openMethods.add("setimages");
        openMethods.add("createmessage");
        openMethods.add("querytotalusercount");
        openMethods.add("queryloginusercountbydate");
        openMethods.add("registerqaexpert");
        openMethods.add("createqaexpertmessage");
        openMethods.add("replyqaexpertmessage");
        openMethods.add("getqaexpertmessagelist");
        openMethods.add("fetchqaexpertmessage");
        openMethods.add("msgtomq");
        openMethods.add("uploadimageform");
        openMethods.add("euserlogin");
        openMethods.add("checktoken");
        openMethods.add("addeuser");
        openMethods.add("updeuser");
        openMethods.add("addappid");
        openMethods.add("updappid");
        openMethods.add("delappid");
        openMethods.add("getuser");
        openMethods.add("resetpass");
        openMethods.add("authtoken");
        openMethods.add("refreshtoken");
        openMethods.add("createaccesstoken");
        openMethods.add("refreshaccesstoken");
        openMethods.add("checkaccesstoken");
        openMethods.add("getappidaccess");
        openMethods.add("getroomlist");
        openMethods.add("sendimageurl");
        openMethods.add("deleteimage");
        openMethods.add("sendmessage");
        openMethods.add("checkunionid");
        openMethods.add("createroom");
        openMethods.add("readroom");
        openMethods.add("getlist");
        openMethods.add("replymessage");
        openMethods.add("finishroom");
        openMethods.add("adduser");
        openMethods.add("updpass");
        openMethods.add("userbind");
        openMethods.add("deluserbind");
        openMethods.add("deluser");
    }

    @Autowired
    private AppidRepository appidRepository;

    @Autowired
    private GetAppidTest getAppidTest;

    @Autowired
    private LoginTest loginTest;

    /**
     * 1、在表appid新增一条appid的记录
     * @throws Exception
     */
    public String addAppidTestByGernal(AddAppidBO addAppidBO) {
        String addappidUrl =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";

            addAppidBO.setBmAppid(addAppidBO.getBmAppid());//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype(addAppidBO.getApptype());
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(addAppidBO.getErid());
            addAppidBO.setEuid(addAppidBO.getEuid());
            addAppidBO.setIdentity(addAppidBO.getIdentity());
            addAppidBO.setGroupTag(addAppidBO.getGroupTag());
            addAppidBO.setDesc(addAppidBO.getDesc());
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return addappidResult;
        }
    }


    /**
     * 新增一条不存在的记录
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistRecord(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        String getappidResult="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
            getappidResult=getAppidTest.getAppidTestByGernal("172.16.0.25","18080",bmAppid);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一条不存在的记录");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);

            Assert.assertEquals(getappidResult.contains("appkey"),true);
            Assert.assertEquals(getappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(getappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(getappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(getappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(getappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(getappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(getappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(getappidResult.contains("info"),true);
            Assert.assertEquals(getappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(getappidResult.contains("openMethods"),true);
            Assert.assertEquals(getappidResult.contains("authMethods"),true);
            Assert.assertEquals(getappidResult.contains("allowTopics"),true);
            Assert.assertEquals(getappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(getappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(getappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 新增一条不存在的记录,但是Euid在表euser查询不到
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterEuidValue(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        String getappidResult="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(110353901144559206L);
            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一条不存在的记录,但是Euid在表euser查询不到");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("\"msg\":\"erid mismatch with euid\""),true);
            Assert.assertEquals(addappidResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 新增一条已存在的记录
     * @throws Exception
     */
    @Test
    public void addAppidTestByExistRecord(){
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一条已存在的记录");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }


    /**
     * 必填参数euid校验
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterEuid(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
//            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数euid校验");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("\"msg\":\"euid cannot be empty！！！\""),true);
            Assert.assertEquals(addappidResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 联合必填参数euid与erid校验
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterEuidAndNotExistParameterErid(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
//            addAppidBO.setErid(1103548278986772480L);
//            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("联合必填参数euid与erid校验");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("\"msg\":\"euid cannot be empty！！！\""),true);
            Assert.assertEquals(addappidResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 必填参数erid校验
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterErid(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
//            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数erid校验");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 必填参数desc校验
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterDesc(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
//            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数desc校验");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("\"msg\":\"desc is empty\""),true);
            Assert.assertEquals(addappidResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 非必填参数callbackUrl校验
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterCallbackUrl(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
//            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数desc校验");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 非必填参数info校验
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterInfo(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
//            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数info校验");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":null"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 非必填参数identity校验
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterIdentity(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
//            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数identity校验");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }


    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterAppid(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
//            addAppidBO.setIdentity(bmAppid);
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
//            addAppidBO.setAppid("1.00002");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(addappidResult.contains("\"result\":106"),true);
        }
    }


    /**
     * 相同euid、erid、identity，生成的appid唯一
     * @throws Exception
     */
    @Test
    public void addAppidTestByEuidAndEridAndIdentity(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            String bmAppid="110.00004";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity("110.00003");
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            addAppidBO.setIdentity("110.00003");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            AddAppidBO addAppidBOTarget = new AddAppidBO();
            BeanUtils.copyProperties(addAppidBO,addAppidBOTarget);
            addAppidBOTarget.setBmAppid("110.00003");
            addAppidTestByGernal(addAppidBOTarget);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("相同euid、erid、identity，生成的appid唯一");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }


    /**
     * 超级管理员super=1,参数Euid传递且参数Erid不传
     * @throws Exception
     */
    @Test
    public void addAppidTestByExistParameterEuidAndNotExistParameterEridAndSuperValueIsOne(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            addAppidBO.set___super(1);
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
//            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity("110.00003");
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            addAppidBO.setIdentity("110.00003");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("超级管理员super=1,参数Euid传递且参数Erid不传");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 超级管理员super=1,参数Euid传递且参数Erid传
     * @throws Exception
     */
    @Test
    public void addAppidTestByExistParameterEuidAndExistParameterEridAndSuperValueIsOne(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            addAppidBO.set___super(1);
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity("110.00003");
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            addAppidBO.setIdentity("110.00003");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("超级管理员super=1,参数Euid传递且参数Erid不传");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }


    /**
     * 超级管理员super=1,参数Euid不传递且参数Erid不传
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterEuidAndNotExistParameterEridAndSuperValueIsOne(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            addAppidBO.set___super(1);
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
//            addAppidBO.setErid(1103548278986772480L);
//            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity("110.00003");
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            addAppidBO.setIdentity("110.00003");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("超级管理员super=1,参数Euid不传递且参数Erid不传");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 超级管理员super=1,参数Euid不传递且参数Erid传
     * @throws Exception
     */
    @Test
    public void addAppidTestByNotExistParameterEuidAndExistParameterEridAndSuperValueIsOne(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            addAppidBO.set___super(1);
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
//            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity("110.00003");
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            addAppidBO.setIdentity("110.00003");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("超级管理员super=1,参数Euid不传递且参数Erid传");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 超级管理员super=1,增加参数guestUpperLimit、normalUpperLimit、guestPeriodValid
     * @throws Exception
     */
    @Test
    public void addAppidTestByExistParameterEuidAndExistParameterEridAndSuperValueIsOneAndAddParameterPrivilegeLevelAndAddParameterGuestPeriodValidAndAddParameterNormalUpperLimit(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            addAppidBO.set___super(1);
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity("110.00003");
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            addAppidBO.setIdentity("110.00003");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            infoBean.setGuestPeriodValid(1);
            infoBean.setGuestUpperLimit(5);
            infoBean.setNormalUpperLimit(5);
            addAppidBO.setInfo(infoBean);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("超级管理员super=1,增加参数guestUpperLimit、normalUpperLimit、guestPeriodValid");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
            Assert.assertEquals(addappidResult.contains("guestUpperLimit"),true);
            Assert.assertEquals(addappidResult.contains("normalUpperLimit"),true);
            Assert.assertEquals(addappidResult.contains("guestPeriodValid"),true);
            Assert.assertEquals(addappidResult.contains("protVer"),true);
        }
    }

    /**
     * 超级管理员super=1,增加参数guestUpperLimit、normalUpperLimit、guestPeriodValid、ProtVer
     * @throws Exception
     */
    @Test
    public void addAppidTestByExistParameterEuidAndExistParameterEridAndSuperValueIsOneAndAddParameterPrivilegeLevelAndAddParameterGuestPeriodValidAndAddParameterNormalUpperLimitAndAddParameterProtVer(){
        destroyData();
        String addappidUrl =null;
        AddAppidBO addAppidBO =null;
        String addappidResult ="";
        try{
            addappidUrl = url+"/AccService/addappid";
            addAppidBO = new AddAppidBO();
            addAppidBO.set___super(1);
            String bmAppid="110.00003";
            addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
            addAppidBO.setApptype("7");
            //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
            addAppidBO.setErid(1103548278986772480L);
            addAppidBO.setEuid(1103539011445592064L);
            addAppidBO.setIdentity("110.00003");
            addAppidBO.setGroupTag("test");
            addAppidBO.setDesc("自动化测试使用");
            addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
            //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
            addAppidBO.setAppid("1.00002");
            addAppidBO.setIdentity("110.00003");
            AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            infoBean.setAllowIps(allowIps);
            List<String> openMethods=new ArrayList<>();
            openMethods.add("createroom");
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            infoBean.setGuestPeriodValid(1);
            infoBean.setGuestUpperLimit(5);
            infoBean.setNormalUpperLimit(5);
            addAppidBO.setInfo(infoBean);
            addAppidBO.setProtVer(10);
            log.info("addappidUrl 请求的参数=" + addappidUrl);
            log.info("addAppidBO 请求的参数=" + JSON.toJSONString(addAppidBO));
            addappidResult = HttpUtil.postGeneralUrl(addappidUrl, "application/json", JSON.toJSONString(addAppidBO), "UTF-8");
            log.info("addappidResult 返回结果=" + addappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("超级管理员super=1,增加参数guestUpperLimit、normalUpperLimit、guestPeriodValid、ProtVer");
            recordhttp.setUrl(addappidUrl);
            recordhttp.setRequest(JSON.toJSONString(addAppidBO));
            recordhttp.setResponse(addappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addappidResult.contains("appkey"),true);
            Assert.assertEquals(addappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(addappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(addappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(addappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(addappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(addappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(addappidResult.contains("info"),true);
            Assert.assertEquals(addappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(addappidResult.contains("openMethods"),true);
            Assert.assertEquals(addappidResult.contains("authMethods"),true);
            Assert.assertEquals(addappidResult.contains("allowTopics"),true);
            Assert.assertEquals(addappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(addappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(addappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(addappidResult.contains("\"status\":0"),true);
            Assert.assertEquals(addappidResult.contains("guestUpperLimit"),true);
            Assert.assertEquals(addappidResult.contains("normalUpperLimit"),true);
            Assert.assertEquals(addappidResult.contains("guestPeriodValid"),true);
            Assert.assertEquals(addappidResult.contains("\"protVer\":10"),true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {
        destroyData();
        Appid record=new Appid();
        record.setId(1252440402124869632L);
        record.setAppid("110.00003");
        record.setAppkey("1PuLJtLybXFWllo2");
        record.setApptype("7");
        record.setDesc("自动化测试使用");
        record.setGroupTag("test");
        record.setIdentity("110.00003");
        record.setCallbackUrl("http://test.galaxyeye.xyz/");
        record.setErid(1103548278986772480L);
        record.setEuid(1103539011445592064L);
        record.setPid(0L);
        record.setInfo("{\"allowIps\":[\"*.*.*.*\"],\"openMethods\":[\"createappidtoken\",\"queryusercountbydate\",\"getdiscountcoupon\",\"matcharticle\",\"getarticle\",\"getusercollect\",\"fetcharticle\",\"articlestatistics\",\"getappid\",\"geterobotpkg\",\"adderobotpkg\",\"geterobot\",\"userlogin\",\"userlogout\", \"login\", \"logout\",\"uploadimage\",\"savearticle\",\"articlelistquery\",\"articlelistquerybysession\",\"getarticleaddinfo\",\"auditarticle\",\"deletearticle\",\"setimages\",\"createmessage\",\"querytotalusercount\",\"queryloginusercountbydate\",\"registerqaexpert\",\"createqaexpertmessage\",\"replyqaexpertmessage\",\"getqaexpertmessagelist\",\"fetchqaexpertmessage\",\"msgtomq\",\"uploadimageform\",\"euserlogin\",\"checktoken\",\"addeuser\",\"updeuser\",\"addappid\",\"updappid\",\"delappid\",\"getuser\",\"resetpass\",\"authtoken\",\"refreshtoken\",\"createaccesstoken\",\"refreshaccesstoken\",\"checkaccesstoken\"],\"AuthMethods\":[\"createappidtoken\",\"queryusercountbydate\",\"getdiscountcoupon\",\"matcharticle\",\"getarticle\",\"getusercollect\",\"fetcharticle\",\"articlestatistics\",\"getappid\",\"geterobotpkg\",\"adderobotpkg\",\"geterobot\",\"userlogin\",\"userlogout\", \"login\", \"logout\",\"uploadimage\",\"savearticle\",\"articlelistquery\",\"articlelistquerybysession\",\"getarticleaddinfo\",\"auditarticle\",\"deletearticle\",\"setimages\",\"createmessage\",\"querytotalusercount\",\"queryloginusercountbydate\",\"registerqaexpert\",\"createqaexpertmessage\",\"replyqaexpertmessage\",\"getqaexpertmessagelist\",\"fetchqaexpertmessage\",\"msgtomq\",\"uploadimageform\",\"euserlogin\",\"checktoken\",\"addeuser\",\"updeuser\",\"addappid\",\"updappid\",\"delappid\",\"getuser\",\"resetpass\",\"authtoken\",\"refreshtoken\",\"createaccesstoken\",\"refreshaccesstoken\",\"checkaccesstoken\"],\"privilegeLevel\":1}");
        record.setFromAppid("1.00002");
        record.setStatus(0);
        record.setCreatedAt(DateTool.parseDateStr("2020-04-21 11:33:56",DateTool.DATE_FMT_5));
        record.setUpdatedAt(DateTool.parseDateStr("2020-04-21 11:33:56",DateTool.DATE_FMT_5));
        record.setDeletedAt(null);
        record.setVersion(1);
        appidRepository.insert(record);
    }

    @Override
    public void destroyData() {
        AppidExample example=new AppidExample();
        AppidExample.Criteria cr=example.createCriteria();
        cr.andAppidEqualTo("110.00003");
        appidRepository.deleteByExample(example);
    }
}
