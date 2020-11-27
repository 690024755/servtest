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
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.GetEuserBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UpdAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UpdateUserBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
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
public class GetEuserTest extends BaseTest {

    @Autowired
    private EuserRepository euserRepository;

    @Autowired
    private AddeUserTest addeUserTest;

    @Autowired
    private UpdEuserTest updEuserTest;

    @Autowired
    private SysRoleRepository sysRoleRepository;


    public String getEuserTestByGernal(GetEuserBO getEuserBO) throws Exception {
        String getEuserUrl =null;
        String getEuserResult ="";
        try{
            getEuserUrl = url+"/AccService/geteuser";
            log.info("getEuserUrl 请求的参数=" + getEuserUrl);
            log.info("getEuserBO 请求的参数=" + JSON.toJSONString(getEuserBO));
            getEuserResult = HttpUtil.postGeneralUrl(getEuserUrl, "application/json", JSON.toJSONString(getEuserBO), "UTF-8");
            log.info("getEuserResult 返回结果=" + getEuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("查询企业账号信息通用方法");
            recordhttp.setUrl(getEuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getEuserBO));
            recordhttp.setResponse(getEuserResult);
            initLog(recordhttp,new Object(){});
            return getEuserResult;
        }
    }

    /**
     * 查询未启用角色的企业账号信息
     */
    @Test
    public void getEuserTestBySysRoleIsNotEnable() {
        String getEuserUrl =null;
        GetEuserBO getEuserBO =null;
        String getEuserResult ="";
        destroyData();
        try{
            AddeUserBO addeUserBO=new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy0");
            //设置企业密码
            addeUserBO.setPasswd("test_yy0");
            addeUserBO.setEmail("test_yy0@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy0");
            addeUserBO.setAddr("test_yy0");
            addeUserBO.setAcctype("test");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy0");
            addeUserTest.modifySysRoleEnable();
            String addeuserResult=addeUserTest.addeuserTestByGernal(addeUserBO);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Long euid= JsonPath.read(addeuserResult,"$.euid");
            addeUserTest.modifySysRoleDisable();
            getEuserUrl =url+ "/AccService/geteuser";
            getEuserBO = new GetEuserBO();
            getEuserBO.setAppid("1.00002");
            getEuserBO.setEuid(euid);
            log.info("getEuserUrl 请求的参数=" + getEuserUrl);
            log.info("getEuserBO 请求的参数=" + JSON.toJSONString(getEuserBO));
            getEuserResult = HttpUtil.postGeneralUrl(getEuserUrl, "application/json", JSON.toJSONString(getEuserBO), "UTF-8");
            log.info("getEuserResult 返回结果=" + getEuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            addeUserTest.modifySysRoleEnable();
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("tttt");
            recordhttp.setUrl(getEuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getEuserBO));
            recordhttp.setResponse(getEuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getEuserResult.contains("\"acctype\":\"test\""),true);
            Assert.assertEquals(getEuserResult.contains("addr"),true);
            Assert.assertEquals(getEuserResult.contains("contactNumber"),true);
            Assert.assertEquals(getEuserResult.contains("email"),true);
            Assert.assertEquals(getEuserResult.contains("enterpriseName"),true);
            Assert.assertEquals(getEuserResult.contains("erobots"),true);
            Assert.assertEquals(getEuserResult.contains("euid"),true);
            Assert.assertEquals(getEuserResult.contains("euname"),true);
            Assert.assertEquals(getEuserResult.contains("fromAppid"),true);
            Assert.assertEquals(getEuserResult.contains("mobile"),true);
            Assert.assertEquals(getEuserResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getEuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(getEuserResult.contains("status"),true);
        }
    }

    /**
     * 查询已启用角色的企业账号信息
     */
    @Test
    public void getEuserTestBySysRoleIsEnable() {
        String getEuserUrl =null;
        GetEuserBO getEuserBO =null;
        String getEuserResult ="";
        destroyData();
        try{
            AddeUserBO addeUserBO=new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy0");
            //设置企业密码
            addeUserBO.setPasswd("test_yy0");
            addeUserBO.setEmail("test_yy0@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy0");
            addeUserBO.setAddr("test_yy0");
            addeUserBO.setAcctype("test");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy0");
            addeUserTest.modifySysRoleEnable();
            String addeuserResult=addeUserTest.addeuserTestByGernal(addeUserBO);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Long euid= JsonPath.read(addeuserResult,"$.euid");
            getEuserUrl =url+ "/AccService/geteuser";
            getEuserBO = new GetEuserBO();
            getEuserBO.setAppid("1.00002");
            getEuserBO.setEuid(euid);
            log.info("getEuserUrl 请求的参数=" + getEuserUrl);
            log.info("getEuserBO 请求的参数=" + JSON.toJSONString(getEuserBO));
            getEuserResult = HttpUtil.postGeneralUrl(getEuserUrl, "application/json", JSON.toJSONString(getEuserBO), "UTF-8");
            log.info("getEuserResult 返回结果=" + getEuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            addeUserTest.modifySysRoleEnable();
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("tttt");
            recordhttp.setUrl(getEuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getEuserBO));
            recordhttp.setResponse(getEuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getEuserResult.contains("\"acctype\":\"test\""),true);
            Assert.assertEquals(getEuserResult.contains("addr"),true);
            Assert.assertEquals(getEuserResult.contains("contactNumber"),true);
            Assert.assertEquals(getEuserResult.contains("email"),true);
            Assert.assertEquals(getEuserResult.contains("enterpriseName"),true);
            Assert.assertEquals(getEuserResult.contains("erobots"),true);
            Assert.assertEquals(getEuserResult.contains("euid"),true);
            Assert.assertEquals(getEuserResult.contains("euname"),true);
            Assert.assertEquals(getEuserResult.contains("fromAppid"),true);
            Assert.assertEquals(getEuserResult.contains("mobile"),true);
            Assert.assertEquals(getEuserResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getEuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(getEuserResult.contains("status"),true);
        }
    }


    /**
     * 非必填参数校验euid
     */
    @Test
    public void getEuserTestByNotExistEuid() {
        String getEuserUrl =null;
        GetEuserBO getEuserBO =null;
        String getEuserResult ="";
        destroyData();
        try{
            AddeUserBO addeUserBO=new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy0");
            //设置企业密码
            addeUserBO.setPasswd("test_yy0");
            addeUserBO.setEmail("test_yy0@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy0");
            addeUserBO.setAddr("test_yy0");
            addeUserBO.setAcctype("test");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy0");
            addeUserTest.modifySysRoleEnable();
            String addeuserResult=addeUserTest.addeuserTestByGernal(addeUserBO);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Long euid= JsonPath.read(addeuserResult,"$.euid");
            getEuserUrl =url+ "/AccService/geteuser";
            getEuserBO = new GetEuserBO();
            getEuserBO.setAppid("1.00002");
//            getEuserBO.setEuid(euid);
            log.info("getEuserUrl 请求的参数=" + getEuserUrl);
            log.info("getEuserBO 请求的参数=" + JSON.toJSONString(getEuserBO));
            getEuserResult = HttpUtil.postGeneralUrl(getEuserUrl, "application/json", JSON.toJSONString(getEuserBO), "UTF-8");
            log.info("getEuserResult 返回结果=" + getEuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            addeUserTest.modifySysRoleEnable();
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数校验euid");
            recordhttp.setUrl(getEuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getEuserBO));
            recordhttp.setResponse(getEuserResult);
            initLog(recordhttp,new Object(){});
            List<Object> list=JsonPath.read(getEuserResult,"$.eusers");
            Assert.assertTrue(list.size()>1);
            Assert.assertEquals(getEuserResult.contains("eusers"),true);
            Assert.assertEquals(getEuserResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getEuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(getEuserResult.contains("euid"),true);
            Assert.assertEquals(getEuserResult.contains("euname"),true);
            Assert.assertEquals(getEuserResult.contains("email"),true);
            Assert.assertEquals(getEuserResult.contains("mobile"),true);
            Assert.assertEquals(getEuserResult.contains("contactNumber"),true);
            Assert.assertEquals(getEuserResult.contains("enterpriseName"),true);
            Assert.assertEquals(getEuserResult.contains("addr"),true);
            Assert.assertEquals(getEuserResult.contains("acctype"),true);
            Assert.assertEquals(getEuserResult.contains("feuid"),true);
            Assert.assertEquals(getEuserResult.contains("fromAppid"),true);
            Assert.assertEquals(getEuserResult.contains("status"),true);
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
