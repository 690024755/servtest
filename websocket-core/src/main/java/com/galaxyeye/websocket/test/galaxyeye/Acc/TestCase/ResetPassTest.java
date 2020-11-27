package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

/**
 * 针对表t_user表的用户重置密码接口
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
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.ResetPassBO;
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

@Component
@Slf4j
public class ResetPassTest extends BaseTest {

    @Autowired
    private TUserRepository tUserRepository;

    @Autowired
    private TKeyindexRepository tKeyindexRepository;

    @Autowired
    private TUserkeysRepository tUserkeysRepository;

    @Autowired
    private AddUserTest addUserTest;

    /**
     * 一般通用性重置用户密码
     * @param resetPassBO
     * @return
     * @throws Exception
     */
    public String resetPass(ResetPassBO resetPassBO) throws Exception {
        String resetpassUrl = null;
        String resetpassResult = "";
        //用户重置密码，针对普通用户重置密码，即针对表t_user表的用户重置密码接口
        try{
            resetpassUrl = url + "/AccService/resetpass";
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一般通用性重置用户密码");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"result\":1"), true);
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(resetpassResult.contains("\"uname\":"+"\""+resetPassBO.getUname()+"\""), true);
            Assert.assertEquals(resetpassResult.contains("uid"), true);
            return resetpassResult;
        }
    }


    /**
     * 新注册用户重置密码
     * @throws Exception
     */
    @Test
    public void resetPassTest() throws Exception {
        String Uname = "test123456";
        addUserTest.adduserTestByGeneral(Uname, Uname);
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetpassResult = "";
        try {
            resetpassUrl = url + "/AccService/resetpass";
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname(Uname);
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
            log.info("resetpassResult 返回结果=" + resetpassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新注册用户重置密码");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"result\":1"), true);
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(resetpassResult.contains("\"uname\":\"test123456\""), true);
            Assert.assertEquals(resetpassResult.contains("uid"), true);
        }
    }

    /**
     * 不存在的用户，重置密码
     * @throws Exception
     */
    @Test
    public void resetPassTestByNotExistUser() throws Exception {
        String Uname = "test123456";
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetpassResult = "";
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
            resetpassUrl = url + "/AccService/resetpass";
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname(Uname);
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
            log.info("resetpassResult 返回结果=" + resetpassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("不存在的用户，重置密码");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(resetpassResult.contains("\"result\":104"), true);
        }
    }

    /**
     * 重置密码接口，重置的新密码与旧密码一样，支持该种方式重置
     * @throws Exception
     */
    @Test
    public void resetPassTestByNewPasswdEqualToOldPasswd() throws Exception {
        String Uname = "test123456";
        addUserTest.adduserTestByGeneral(Uname, Uname);
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetpassResult = "";
        try {
            resetpassUrl = url + "/AccService/resetpass";
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd(Uname);
            resetPassBO.setUname(Uname);
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
            log.info("resetpassResult 返回结果=" + resetpassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("重置密码接口，重置的新密码与旧密码一样，支持该种方式重置");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(resetpassResult.contains("\"result\":1"), true);
            Assert.assertEquals(resetpassResult.contains("uid"), true);
            Assert.assertEquals(resetpassResult.contains("\"uname\":\"test123456\""), true);
        }
    }

    /**
     * 新密码小于规定的6-20个字符
     * @throws Exception
     */
    @Test
    public void resetPassTestByNewPasswdIsShort() throws Exception {
        String Uname = "test123456";
        addUserTest.adduserTestByGeneral(Uname, Uname);
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetpassResult = "";
        try {
            resetpassUrl = url + "/AccService/resetpass";
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("021");
            resetPassBO.setUname(Uname);
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
            log.info("resetpassResult 返回结果=" + resetpassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新密码小于规定的6-20个字符");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(resetpassResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新密码大于规定的6-20个字符
     * @throws Exception
     */
    @Test
    public void resetPassTestByNewPasswdIsLong() throws Exception {
        String Uname = "test123456";
        addUserTest.adduserTestByGeneral(Uname, Uname);
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetpassResult = "";
        try {
            resetpassUrl = url + "/AccService/resetpass";
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("021MUOZp0I2pak13FkYp0ckWZp0MUOZn");
            resetPassBO.setUname(Uname);
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
            log.info("resetpassResult 返回结果=" + resetpassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新密码大于规定的6-20个字符");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(resetpassResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 重置的密码为空
     * @throws Exception
     */
    @Test
    public void resetPassTestByNewPasswdIsEmpty() throws Exception {
        String Uname = "test123456";
        addUserTest.adduserTestByGeneral(Uname, Uname);
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetpassResult = "";
        try {
            resetpassUrl = url + "/AccService/resetpass";
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("");
            resetPassBO.setUname(Uname);
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
            log.info("resetpassResult 返回结果=" + resetpassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新密码大于规定的6-20个字符");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(resetpassResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数uname必填校验
     * @throws Exception
     */
    @Test
    public void resetPassTestByParameterUnameIsNotExist() throws Exception {
        String Uname = "test123456";
        addUserTest.adduserTestByGeneral(Uname, Uname);
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetpassResult = "";
        try {
            resetpassUrl = url + "/AccService/resetpass";
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd(Uname);
//            resetPassBO.setUname(Uname);
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
            log.info("resetpassResult 返回结果=" + resetpassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数uname必填校验");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(resetpassResult.contains("\"result\":104"), true);
        }
    }

    /**
     * 参数appid必填校验
     * @throws Exception
     */
    @Test
    public void resetPassTestByParameterAppidIsNotExist() throws Exception {
        String Uname = "test123456";
        addUserTest.adduserTestByGeneral(Uname, Uname);
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetpassResult = "";
        try {
            resetpassUrl = url + "/AccService/resetpass";
            resetPassBO = new ResetPassBO();
//            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd(Uname);
            resetPassBO.setUname(Uname);
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
            log.info("resetpassResult 返回结果=" + resetpassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数appid必填校验");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(resetpassResult.contains("\"result\":106"), true);
        }
    }

    /**
     * 参数passwd必填校验
     * @throws Exception
     */
    @Test
    public void resetPassTestByParameterPasswdIsNotExist() throws Exception {
        String Uname = "test123456";
        addUserTest.adduserTestByGeneral(Uname, Uname);
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetpassResult = "";
        try {
            resetpassUrl = url + "/AccService/resetpass";
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
//            resetPassBO.setPasswd(Uname);
            resetPassBO.setUname(Uname);
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetpassResult = HttpUtil.postGeneralUrl(resetpassUrl, "application/json", JSON.toJSONString(resetPassBO), "UTF-8");
            log.info("resetpassResult 返回结果=" + resetpassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数passwd必填校验");
            recordhttp.setUrl(resetpassUrl);
            recordhttp.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp.setResponse(resetpassResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(resetpassResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(resetpassResult.contains("\"result\":101"), true);
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
