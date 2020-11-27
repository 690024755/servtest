package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TKeyindexRepository;
import com.galaxyeye.websocket.application.repository.TUserRepository;
import com.galaxyeye.websocket.application.repository.TUserkeysRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TKeyindex;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUserkeys;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TKeyindexExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserkeysExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddUserBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UpdPassBO;
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
public class UpdPassTest extends BaseTest {

    @Autowired
    private TUserRepository tUserRepository;

    @Autowired
    private TKeyindexRepository tKeyindexRepository;

    @Autowired
    private TUserkeysRepository tUserkeysRepository;

    @Autowired
    private AddUserTest addUserTest;

    /**
     * 修改用户密码通用方法
     * @throws Exception
     */
    @Test
    public void updpassTestByGernal(UpdPassBO updPassBO) throws Exception {
        String updpassUrl = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改已存在用户的密码");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"result\":1"), true);
            Assert.assertEquals(updpassResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(updpassResult.contains("uid"), true);
            Assert.assertEquals(updpassResult.contains("uname"), true);
        }
    }


    /**
     * 修改已存在用户的密码
     * @throws Exception
     */
    @Test
    public void updpassTestByExisUserPasswd() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
            updPassBO.setPasswd(Uname+Uname);
            updPassBO.setOldpass(Uname);
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改已存在用户的密码");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"result\":1"), true);
            Assert.assertEquals(updpassResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(updpassResult.contains("uid"), true);
            Assert.assertEquals(updpassResult.contains("\"uname\":\"test123456\""), true);
        }
    }


    /**
     * 旧密码输入错误，进行修改密码操作
     * @throws Exception
     */
    @Test
    public void updpassTestByWrongOldPasswd() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
            updPassBO.setPasswd(Uname+Uname);
            updPassBO.setOldpass(Uname+"wrong");
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("旧密码输入错误，进行修改密码操作");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"password_error\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":105"), true);
        }
    }

    /**
     * 旧密码输入正确，进行修改密码操作
     * @throws Exception
     */
    @Test
    public void updpassTestByRightOldPasswd() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
            updPassBO.setPasswd(Uname+Uname);
            updPassBO.setOldpass(Uname);
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("旧密码输入正确，进行修改密码操作");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":1"), true);
            Assert.assertEquals(updpassResult.contains("uid"), true);
            Assert.assertEquals(updpassResult.contains("\"uname\":\"test123456\""), true);
        }
    }


    /**
     * 修改不已存在用户的密码
     * @throws Exception
     */
    @Test
    public void updpassTestByNotExisUserPasswd() throws Exception {
        String Uname="test123456";
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        TUserExample tUserExample=new TUserExample();
        TUserExample.Criteria tUserCr=tUserExample.createCriteria();
        tUserCr.andUnameEqualTo(Uname);
        List<TUser> tUserList=tUserRepository.selectByExample(tUserExample);
        List<TKeyindex> tKeyindexList=new ArrayList<>();
        List<TUserkeys> tUserkeyList=new ArrayList<>();
        TKeyindexExample tKeyindexExample=null;
        TUserkeysExample tUserkeysExample=null;
        if(tUserList.size()>0){
            tKeyindexExample=new TKeyindexExample();
            TKeyindexExample.Criteria tKeyindexCr=tKeyindexExample.createCriteria();
            tKeyindexCr.andUidEqualTo(tUserList.get(0).getUid());
            tKeyindexList=tKeyindexRepository.selectByExample(tKeyindexExample);
            tUserkeysExample=new TUserkeysExample();
            TUserkeysExample.Criteria tUserkeysCr=tUserkeysExample.createCriteria();
            tUserkeysCr.andUidEqualTo(tUserList.get(0).getUid());
            tUserkeyList=tUserkeysRepository.selectByExample(tUserkeysExample);
        }
        if(tUserList.size()>0 || tKeyindexList.size()>0 || tUserkeyList.size()>0){
            tUserRepository.deleteByExample(tUserExample);
            tUserkeysRepository.deleteByExample(tUserkeysExample);
            tKeyindexRepository.deleteByExample(tKeyindexExample);
        }
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
            updPassBO.setPasswd(Uname+Uname);
            updPassBO.setOldpass(Uname);
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改不已存在用户的密码");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":104"), true);
        }
    }

    /**
     * 参数uname必填校验
     * @throws Exception
     */
    @Test
    public void updpassTestByNotExistParameterUname() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
//            updPassBO.setUname(Uname);
            updPassBO.setPasswd(Uname+Uname);
            updPassBO.setOldpass(Uname);
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数uname必填校验");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":104"), true);
        }
    }

    /**
     * 参数OldPassWd必填校验
     * @throws Exception
     */
    @Test
    public void updpassTestByNotExistParameterOldPassWd() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
            updPassBO.setPasswd(Uname+Uname);
//            updPassBO.setOldpass(Uname);
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数OldPassWd必填校验");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数NewPassWd必填校验
     * @throws Exception
     */
    @Test
    public void updpassTestByNotExistParameterNewPassWd() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
//            updPassBO.setPasswd(Uname+Uname);
            updPassBO.setOldpass(Uname);
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数OldPassWd必填校验");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数appid必填校验
     * @throws Exception
     */
    @Test
    public void updpassTestByNotExistParameterAppid() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
            updPassBO.setPasswd(Uname+Uname);
            updPassBO.setOldpass(Uname);
//            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数OldPassWd必填校验");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":106"), true);
        }
    }

    /**
     * 新密码与旧密码一样，可以修改成功
     * @throws Exception
     */
    @Test
    public void updpassTestByNewPasswdEqualToOldPassWd() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
            updPassBO.setPasswd(Uname);
            updPassBO.setOldpass(Uname);
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新密码与旧密码一样，可以修改成功");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新密码字符超过20个字符
     * @throws Exception
     */
    @Test
    public void updpassTestByNewPasswdIsLong() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
            updPassBO.setPasswd("021MUOZp0I2pak13FkYp0ckWZp0MUOZn");
            updPassBO.setOldpass(Uname);
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新密码字符超过20个字符");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新密码字符小于6个字符
     * @throws Exception
     */
    @Test
    public void updpassTestByNewPasswdIsShort() throws Exception {
        String Uname="test123456";
        addUserTest.adduserTestByGeneral(Uname,Uname);
        String updpassUrl = null;
        UpdPassBO updPassBO = null;
        String updpassResult = "";
        try {
            updpassUrl = url + "/AccService/updpass";
            updPassBO = new UpdPassBO();
            updPassBO.setUname(Uname);
            updPassBO.setPasswd("021");
            updPassBO.setOldpass(Uname);
            updPassBO.setAppid("1.00002");
            log.info("updpassUrl 请求的参数=" + updpassUrl);
            log.info("updPassBO 请求的参数=" + JSON.toJSONString(updPassBO));
            updpassResult = HttpUtil.postGeneralUrl(updpassUrl, "application/json", JSON.toJSONString(updPassBO), "UTF-8");
            log.info("updpassResult 返回结果=" + JSON.parseObject(updpassResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新密码字符小于6个字符");
            recordhttp.setUrl(updpassUrl);
            recordhttp.setRequest(JSON.toJSONString(updPassBO));
            recordhttp.setResponse(updpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(updpassResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(updpassResult.contains("\"result\":101"), true);
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

    }

}
