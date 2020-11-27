package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.EuserRepository;
import com.galaxyeye.websocket.application.repository.SysRoleRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.SysRole;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.EuserExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SysRoleExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddeUserBO;
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
import java.util.List;


@Slf4j
@Component
public class AddeUserTest extends BaseTest {

    @Autowired
    private EuserRepository euserRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    public String addeuserTestByGernal(AddeUserBO addeUserBO) throws Exception {
        String addeuserUrl =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加企业账号，euser增加一条不存在类型的企业账号记录");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            return addeuserResult;
        }
    }


    /**
     * 增加企业账号，euser增加一条不存在类型的企业账号记录
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeValuesIsNotExist() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy0");
            //设置企业密码
            addeUserBO.setPasswd("test_yy0");
            addeUserBO.setEmail("test_yy0@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy0");
            addeUserBO.setAddr("test_yy0");
            addeUserBO.setAcctype("test_yy0");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy0");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加企业账号，euser增加一条不存在类型的企业账号记录");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"result\":136"),true);
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"accType not exist\""),true);
        }
    }

    /**
     * 增加企业账号，euser增加一条记录A，账号类型是admin
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeValuesIsdmin() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy1");
            //设置企业密码
            addeUserBO.setPasswd("test_yy1");
            addeUserBO.setEmail("test_yy1@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy1");
            addeUserBO.setAddr("test_yy1");
            addeUserBO.setAcctype("admin");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy1");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加企业账号，euser增加一条记录A，账号类型是admin");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
        }
    }

    /**
     * 增加企业账号，euser增加一条记录A，账号类型是e-admin
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeValuesIsEadmin() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy2");
            //设置企业密码
            addeUserBO.setPasswd("test_yy2");
            addeUserBO.setEmail("test_yy2@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy2");
            addeUserBO.setAddr("test_yy2");
            addeUserBO.setAcctype("e-admin");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy2");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加企业账号，euser增加一条记录A，账号类型是e-admin");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
        }
    }

    /**
     * 增加企业账号，euser增加一条记录A，账号类型是e-sub
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeValuesIsEsub() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy3");
            //设置企业密码
            addeUserBO.setPasswd("test_yy3");
            addeUserBO.setEmail("test_yy3@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy3");
            addeUserBO.setAddr("test_yy3");
            addeUserBO.setAcctype("e-sub");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy3");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加企业账号，euser增加一条记录A，账号类型是e-sub");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
        }
    }

    /**
     * 增加企业账号，euser增加一条记录A，账号类型是e-channel
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeValuesIsEchannel() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy4");
            //设置企业密码
            addeUserBO.setPasswd("test_yy4");
            addeUserBO.setEmail("test_yy4@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy4");
            addeUserBO.setAddr("test_yy4");
            addeUserBO.setAcctype("e-channel");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy4");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加企业账号，euser增加一条记录A，账号类型是e-channel");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
        }
    }

    /**
     * 增加企业账号，euser增加一条记录A，账号类型是om_admin
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeValuesIsOmAdmin() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy5");
            //设置企业密码
            addeUserBO.setPasswd("test_yy5");
            addeUserBO.setEmail("test_yy5@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy5");
            addeUserBO.setAddr("test_yy5");
            addeUserBO.setAcctype("om_admin");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy5");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加企业账号，euser增加一条记录A，账号类型是om_admin");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
        }
    }

    /**
     * 增加企业账号，euser增加一条记录A，账号类型是om_sub_community
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeValuesIsOmSubCommunity() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy6");
            //设置企业密码
            addeUserBO.setPasswd("test_yy6");
            addeUserBO.setEmail("test_yy6@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy6");
            addeUserBO.setAddr("test_yy6");
            addeUserBO.setAcctype("om_sub_community");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy6");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加企业账号，euser增加一条记录A，账号类型是om_sub_community");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
        }
    }

    /**
     * 增加企业账号，euser增加一条记录A，账号类型是om_sub_enterprise
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeValuesIsOmSubEnterprise() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加企业账号，euser增加一条记录A，账号类型是om_sub_enterprise");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
        }
    }

    /**
     * 已存在一条记录，重复增加
     * @throws Exception
     */
    @Test
    public void addeuserTestByRepeatAddeUser() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            destroyData();
            for (int i = 0; i < 2; i++) {
                addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            }
            log.info("addeuserResult 返回结果=" + addeuserResult);
            destroyData();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已存在一条记录，重复增加");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"euname exist\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":121"),true);
        }
    }

    /**
     * 必填参数Euname校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterEuname() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
//            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Euname校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 必填参数passwd校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterPasswd() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
//            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数passwd校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 必填参数passwd值设置为空字符串
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterPasswdValueIsEmpty() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd(" ");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数passwd值设置为空字符串");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 必填参数passwd值超长
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterPasswdValueIsLong() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数passwd值超长");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 必填参数Euname值超长
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterEunameValueIsLong() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Euname值超长");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 参数Email值非邮箱格式
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterEmailValueIsInvalidate() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Email值非邮箱格式");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 非必填参数Email校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterEmail() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
//            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
            destroyData();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数Email校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 非必填参数mobile校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterMobile() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
//            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
            destroyData();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数mobile校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 非必填参数mobile值是非手机号码如130938635110，12位手机号码
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterMobileValueIsLong() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("130938635880");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数mobile值是非手机号码如130938635110，12位手机号码");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 非必填参数mobile值非1开头，如9开头
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterMobileValueIsInvalidate() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("93093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数mobile值非1开头，如9开头");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 非必填参数ContactNumber校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterContactNumber() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
//            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
            destroyData();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数ContactNumber校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 非必填参数enterpriseName校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterEnterpriseName() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
//            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
            destroyData();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数enterpriseName校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 非必填参数addr校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterAddr() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
//            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
            destroyData();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数addr校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 必填参数acctype校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterAcctype() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
//            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数acctype校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"acctype is empty\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 非必填参数feuid校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterFeuid() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
//            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
            destroyData();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数feuid校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void addeuserTestByNotExistParameterAppid() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("om_sub_enterprise");
            addeUserBO.setFeuid(1115813755523960832L);
//            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":106"),true);
        }
    }

    /**
     * 企业账号注册时候，该角色未启用，则注册失败
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeIsNotEnable() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        destroyData();
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("test");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            modifySysRoleDisable();
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifySysRoleEnable();
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("企业账号注册时候，该角色未启用，则注册失败");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"accType not exist\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":136"),true);
        }
    }

    /**
     * 企业账号注册时候，该角色已启用，则注册成功
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeIsEnable() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        destroyData();
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("test");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            modifySysRoleEnable();
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("企业账号注册时候，该角色未启用，则注册失败");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"\""),true);
        }
    }


    /**
     * 企业账号注册时候，绑定的角色不存在
     * @throws Exception
     */
    @Test
    public void addeuserTestByParameterAcctypeValueIsNotExist() throws Exception {
        String addeuserUrl =null;
        AddeUserBO addeUserBO =null;
        String addeuserResult ="";
        destroyData();
        try{
            addeuserUrl = url+"/AccService/addeuser";
            addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("test_yy");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            modifySysRoleEnable();
            log.info("addeuserUrl 请求的参数=" + addeuserUrl);
            log.info("addeUserBO 请求的参数=" + JSON.toJSONString(addeUserBO));
            addeuserResult = HttpUtil.postGeneralUrl(addeuserUrl, "application/json", JSON.toJSONString(addeUserBO), "UTF-8");
            log.info("addeuserResult 返回结果=" + addeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("企业账号注册时候，绑定的角色不存在");
            recordhttp.setUrl(addeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(addeUserBO));
            recordhttp.setResponse(addeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"accType not exist\""),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":136"),true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {
        EuserExample example=new EuserExample();
        EuserExample.Criteria cr=example.createCriteria();
        List<String> values=new ArrayList<>();
        values.add("test_yy0");//对应不存在的账号类型
        values.add("test_yy1");//admin
        values.add("test_yy2");//e-admin
        values.add("test_yy3");//e-sub
        values.add("test_yy4");//e-channel
        values.add("test_yy5");//om_admin
        values.add("test_yy6");//om_sub_community
        values.add("test_yy7");//om_sub_enterprise
        cr.andEunameIn(values);
        euserRepository.deleteByExample(example);
    }

    /**
     * 修改角色未启用状态
     */
    public void modifySysRoleDisable(){
        SysRole record=new SysRole();
        record.setStatus(false);
        SysRoleExample example=new SysRoleExample();
        example.createCriteria().andRoleTypeEqualTo("test");
        sysRoleRepository.updateByExampleSelective(record,example);
    }

    /**
     * 修改角色启用状态
     */
    public void modifySysRoleEnable(){
        SysRole record=new SysRole();
        record.setStatus(true);
        SysRoleExample example=new SysRoleExample();
        example.createCriteria().andRoleTypeEqualTo("test");
        sysRoleRepository.updateByExampleSelective(record,example);
    }
}
