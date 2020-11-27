package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddeUserBO;
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

import java.util.Date;


@Slf4j
@Component
public class UpdEuserTest extends BaseTest {

    @Autowired
    private AddeUserTest addeUserTest;

    /**
     * 手动修改执行某个企业账号信息，不加入测试用例范围内
     * @throws Exception
     */
    @Test
    public void updEuserTestByManul() throws Exception {
        UpdateUserBO updateUserBO = new UpdateUserBO();
        updateUserBO.setAcctype("om_admin");
        updateUserBO.setEuname("omadmin4");
        updateUserBO.setPasswd("omadmin4");
        updateUserBO.setEuid(1113353020688568320L);
        updEuserTestByGernal(updateUserBO);
    }


    /**
     * 修改企业账号与密码通用方法
     * @throws Exception
     */
    public String updEuserTestByGernal(UpdateUserBO updateUserBO) throws Exception {
        String updeuserUrl = null;
        String updeuserResult = "";
        try {
            updeuserUrl = url + "/AccService/updeuser";
            //用户名与Euid要一致，否则会出现参数错误
            updateUserBO.setEuid(updateUserBO.getEuid());
            updateUserBO.setEuname(updateUserBO.getEuname());
            updateUserBO.setPasswd(updateUserBO.getEuname());
            updateUserBO.setEmail("test3@qq.com");
            updateUserBO.setMobile("13093863588");
            updateUserBO.setContactNumber("");
            updateUserBO.setEnterpriseName("");
            updateUserBO.setAddr("testacc13");
            updateUserBO.setAcctype(updateUserBO.getAcctype());
            //所属客户id
            updateUserBO.setFeuid(1115813755523960832L);
            updateUserBO.setAppid("1.00002");
            log.info("updeuserUrl 请求的参数=" + updeuserUrl);
            log.info("updateUserBO 请求的参数=" + JSON.toJSONString(updateUserBO));
            updeuserResult = HttpUtil.postGeneralUrl(updeuserUrl, "application/json", JSON.toJSONString(updateUserBO), "UTF-8");
            log.info("updeuserResult 返回结果=" + updeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改企业账号与密码通用方法");
            recordhttp.setUrl(updeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(updateUserBO));
            recordhttp.setResponse(updeuserResult);
            initLog(recordhttp,new Object(){});
            return updeuserResult;
        }
    }


    /**
     * 修改存在的企业账号与密码
     * @throws Exception
     */
    @Test
    public void updEuserTestByExistRecord() throws Exception {
        String updeuserUrl = null;
        String updeuserResult = "";
        UpdateUserBO updateUserBO =null;
        try{
            updeuserUrl = url + "/AccService/updeuser";
            updateUserBO = new UpdateUserBO();
            //用户名与Euid要一致，否则会出现参数错误
            updateUserBO.setEuid(1103539011445592064L);
            updateUserBO.setEuname("galaxyeye1");
            updateUserBO.setPasswd("galaxyeye1");
            updateUserBO.setEmail("test3@qq.com");
            updateUserBO.setMobile("13093863588");
            updateUserBO.setContactNumber("");
            updateUserBO.setEnterpriseName("");
            updateUserBO.setAddr("testacc13");
            updateUserBO.setAcctype("admin");
            //所属客户id
            updateUserBO.setFeuid(1115813755523960832L);
            updateUserBO.setAppid("1.00003");
            log.info("updeuserUrl 请求的参数=" + updeuserUrl);
            log.info("updateUserBO 请求的参数=" + JSON.toJSONString(updateUserBO));
            updeuserResult = HttpUtil.postGeneralUrl(updeuserUrl, "application/json", JSON.toJSONString(updateUserBO), "UTF-8");
            log.info("updeuserResult 返回结果=" + updeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改存在的企业账号与密码");
            recordhttp.setUrl(updeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(updateUserBO));
            recordhttp.setResponse(updeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updeuserResult.contains("euid"),true);
            Assert.assertEquals(updeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(updeuserResult.contains("\"msg\":\"\""),true);
        }
    }

    /**
     * 修改不存在的企业账号与密码
     * @throws Exception
     */
    @Test
    public void updEuserTestByNotExistRecord() throws Exception {
        String updeuserUrl = null;
        String updeuserResult = "";
        UpdateUserBO updateUserBO =null;
        try{
            updeuserUrl = url + "/AccService/updeuser";
            updateUserBO = new UpdateUserBO();
            //用户名与Euid要一致，否则会出现参数错误
            updateUserBO.setEuid(1903539011445592064L);
            updateUserBO.setEuname("galaxyeye1");
            updateUserBO.setPasswd("galaxyeye1");
            updateUserBO.setEmail("test3@qq.com");
            updateUserBO.setMobile("13093863588");
            updateUserBO.setContactNumber("");
            updateUserBO.setEnterpriseName("");
            updateUserBO.setAddr("testacc13");
            updateUserBO.setAcctype("admin");
            //所属客户id
            updateUserBO.setFeuid(1115813755523960832L);
            updateUserBO.setAppid("1.00003");
            log.info("updeuserUrl 请求的参数=" + updeuserUrl);
            log.info("updateUserBO 请求的参数=" + JSON.toJSONString(updateUserBO));
            updeuserResult = HttpUtil.postGeneralUrl(updeuserUrl, "application/json", JSON.toJSONString(updateUserBO), "UTF-8");
            log.info("updeuserResult 返回结果=" + updeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改不存在的企业账号与密码");
            recordhttp.setUrl(updeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(updateUserBO));
            recordhttp.setResponse(updeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updeuserResult.contains("\"msg\":\"euser_not_found\""),true);
            Assert.assertEquals(updeuserResult.contains("\"result\":122"),true);
        }
    }

    /**
     * 之前已绑定过企业账号，然后修改企业账号的角色为未启用，则修改失败
     */
    @Test
    public void updEuserTestByModifyDisableRole() throws Exception {
        String updeuserUrl = null;
        String updeuserResult = "";
        UpdateUserBO updateUserBO =null;
        try{
            addeUserTest.modifySysRoleEnable();
            AddeUserBO addeUserBO = new AddeUserBO();
            //设置企业名称
            addeUserBO.setEuname("test_yy7");
            //设置企业密码
            addeUserBO.setPasswd("test_yy7");
            addeUserBO.setEmail("test_yy7@qq.com");
            addeUserBO.setMobile("13093863588");
            addeUserBO.setContactNumber("13093863588");
            addeUserBO.setEnterpriseName("test_yy7");
            addeUserBO.setAddr("test_yy7");
            addeUserBO.setAcctype("admin");
            addeUserBO.setFeuid(1115813755523960832L);
            addeUserBO.setAppid("1.00002");
            addeUserBO.setSeq("test_yy7");
            String addeuserResult=addeUserTest.addeuserTestByGernal(addeUserBO);
            Assert.assertEquals(addeuserResult.contains("euid"),true);
            Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
            Assert.assertEquals(addeuserResult.contains("\"msg\":\"\""),true);

            Long euid=JsonPath.read(addeuserResult,"$.euid");

            updeuserUrl = url + "/AccService/updeuser";
            updateUserBO = new UpdateUserBO();
            //用户名与Euid要一致，否则会出现参数错误
            updateUserBO.setEuid(euid);
            updateUserBO.setEuname(addeUserBO.getEuname());
            updateUserBO.setPasswd(addeUserBO.getPasswd());
            updateUserBO.setEmail(addeUserBO.getEmail());
            updateUserBO.setMobile(addeUserBO.getMobile());
            updateUserBO.setContactNumber("");
            updateUserBO.setEnterpriseName("");
            updateUserBO.setAddr(addeUserBO.getAddr());
            updateUserBO.setAcctype("test");
            //所属客户id
            updateUserBO.setFeuid(addeUserBO.getFeuid());
            updateUserBO.setAppid("1.00003");
            log.info("updeuserUrl 请求的参数=" + updeuserUrl);
            log.info("updateUserBO 请求的参数=" + JSON.toJSONString(updateUserBO));
            addeUserTest.modifySysRoleDisable();
            updateUserBO.setAcctype("test");
            updeuserResult = HttpUtil.postGeneralUrl(updeuserUrl, "application/json", JSON.toJSONString(updateUserBO), "UTF-8");
            log.info("updeuserResult 返回结果=" + updeuserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            addeUserTest.modifySysRoleEnable();
            destroyData();
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("之前已绑定过企业账号，然后修改企业账号的角色为未启用，则修改失败");
            recordhttp.setUrl(updeuserUrl);
            recordhttp.setRequest(JSON.toJSONString(updateUserBO));
            recordhttp.setResponse(updeuserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updeuserResult.contains("\"msg\":\"acctype_not_exist\""),true);
            Assert.assertEquals(updeuserResult.contains("\"result\":136"),true);
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
        addeUserTest.destroyData();
    }
}
