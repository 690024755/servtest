package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 13:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/18日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.DelUserBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.DTO.AddUserDTO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.List;


@Slf4j
@Component
public class DelUserTest extends BaseTest {
    @Autowired
    private AddUserTest addUserTest;

    @Autowired
    private UserBindTest userBindTest;

    @Autowired
    private TUserRepository tUserRepository;

    /**
     * 通用删除用户的信息
     * @throws Exception
     */
    public void deluserTestByGeneral(Long uid) throws Exception {
        String deluserUrl = null;
        DelUserBO delUserBO = null;
        String deluserResult = "";
            try {
                deluserUrl = url + "/AccService/deluser";
                delUserBO = new DelUserBO();
                delUserBO.setAppid("1.00002");
                delUserBO.setUid(uid);
                delUserBO.setSeq("abc");
                log.info("deluserUrl 请求的参数=" + deluserUrl);
                log.info("delUserBO 请求的参数=" + JSON.toJSONString(delUserBO));
                deluserResult = HttpUtil.postGeneralUrl(deluserUrl, "application/json", JSON.toJSONString(delUserBO), "UTF-8");
                log.info("deluserResult 返回结果=" + deluserResult);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("通用删除用户的信息");
                recordhttp.setUrl(deluserUrl);
                recordhttp.setRequest(JSON.toJSONString(delUserBO));
                recordhttp.setResponse(deluserResult);
                initLog(recordhttp, new Object() {
                });
                Assert.assertEquals(deluserResult.contains("\"msg\":\"ok\""), true);
                Assert.assertEquals(deluserResult.contains("\"result\":1"), true);
                Assert.assertEquals(deluserResult.contains("\"uid\":"+String.valueOf(uid)), true);
            }
    }

    /**
     * 删除存在用户的信息
     * @throws Exception
     */
    @Test
    public void deluserTestByExistUser() throws Exception {
        String Uname="test123456";
        AddUserDTO addUserDTO=JSON.parseObject(addUserTest.adduserTestByGeneral(Uname,Uname), AddUserDTO.class);
        TUserExample tUserExample = new TUserExample();
        TUserExample.Criteria tUserCr = tUserExample.createCriteria();
        tUserCr.andUidEqualTo(addUserDTO.getUid());
        List<TUser> tUserList = tUserRepository.selectByExample(tUserExample);
        userBindTest.userbindTestByGeneral(tUserList.get(0).getUname());
        String deluserUrl = null;
        DelUserBO delUserBO = null;
        String deluserResult = "";
        if(addUserDTO!=null && addUserDTO.getUid()!=null){
            try {
                deluserUrl = url + "/AccService/deluser";
                delUserBO = new DelUserBO();
                delUserBO.setAppid("1.00002");
                delUserBO.setUid(addUserDTO.getUid());
                delUserBO.setSeq("abc");
                log.info("deluserUrl 请求的参数=" + deluserUrl);
                log.info("delUserBO 请求的参数=" + JSON.toJSONString(delUserBO));
                deluserResult = HttpUtil.postGeneralUrl(deluserUrl, "application/json", JSON.toJSONString(delUserBO), "UTF-8");
                log.info("deluserResult 返回结果=" + deluserResult);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("删除存在用户的信息");
                recordhttp.setUrl(deluserUrl);
                recordhttp.setRequest(JSON.toJSONString(delUserBO));
                recordhttp.setResponse(deluserResult);
                initLog(recordhttp, new Object() {
                });
                Assert.assertEquals(deluserResult.contains("\"msg\":\"ok\""), true);
                Assert.assertEquals(deluserResult.contains("\"result\":1"), true);
                Assert.assertEquals(deluserResult.contains("\"uid\":"+String.valueOf(addUserDTO.getUid())), true);
            }
        }
    }

    /**
     * 通用删除用户的信息
     * @throws Exception
     */
    @Test
    public void deluserTestByNotExistUser() throws Exception {
        Long uid=9999999999L;
        String deluserUrl = null;
        DelUserBO delUserBO = null;
        String deluserResult = "";
        try {
            deluserUrl = url + "/AccService/deluser";
            delUserBO = new DelUserBO();
            delUserBO.setAppid("1.00002");
            delUserBO.setUid(uid);
            delUserBO.setSeq("abc");
            log.info("deluserUrl 请求的参数=" + deluserUrl);
            log.info("delUserBO 请求的参数=" + JSON.toJSONString(delUserBO));
            deluserResult = HttpUtil.postGeneralUrl(deluserUrl, "application/json", JSON.toJSONString(delUserBO), "UTF-8");
            log.info("deluserResult 返回结果=" + deluserResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用删除用户的信息");
            recordhttp.setUrl(deluserUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBO));
            recordhttp.setResponse(deluserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(deluserResult.contains("\"result\":104"), true);
        }
    }

    /**
     * 必填参数uid校验
     * @throws Exception
     */
    @Test
    public void deluserTestByNotExistParameterUid() throws Exception {
        String Uname="test123456";
        AddUserDTO addUserDTO=JSON.parseObject(addUserTest.adduserTestByGeneral(Uname,Uname), AddUserDTO.class);
        TUserExample tUserExample = new TUserExample();
        TUserExample.Criteria tUserCr = tUserExample.createCriteria();
        tUserCr.andUidEqualTo(addUserDTO.getUid());
        List<TUser> tUserList = tUserRepository.selectByExample(tUserExample);
        userBindTest.userbindTestByGeneral(tUserList.get(0).getUname());
        String deluserUrl = null;
        DelUserBO delUserBO = null;
        String deluserResult = "";
        if(addUserDTO!=null && addUserDTO.getUid()!=null){
            try {
                deluserUrl = url + "/AccService/deluser";
                delUserBO = new DelUserBO();
                delUserBO.setAppid("1.00002");
//                delUserBO.setUid(addUserDTO.getUid());
                delUserBO.setSeq("abc");
                log.info("deluserUrl 请求的参数=" + deluserUrl);
                log.info("delUserBO 请求的参数=" + JSON.toJSONString(delUserBO));
                deluserResult = HttpUtil.postGeneralUrl(deluserUrl, "application/json", JSON.toJSONString(delUserBO), "UTF-8");
                log.info("deluserResult 返回结果=" + deluserResult);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("必填参数uid校验");
                recordhttp.setUrl(deluserUrl);
                recordhttp.setRequest(JSON.toJSONString(delUserBO));
                recordhttp.setResponse(deluserResult);
                initLog(recordhttp, new Object() {
                });
                Assert.assertEquals(deluserResult.contains("\"msg\":\"user_not_found\""), true);
                Assert.assertEquals(deluserResult.contains("\"result\":104"), true);
            }
        }
    }

    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void deluserTestByNotExistAppid() throws Exception {
        String Uname="test123456";
        AddUserDTO addUserDTO=JSON.parseObject(addUserTest.adduserTestByGeneral(Uname,Uname), AddUserDTO.class);
        TUserExample tUserExample = new TUserExample();
        TUserExample.Criteria tUserCr = tUserExample.createCriteria();
        tUserCr.andUidEqualTo(addUserDTO.getUid());
        List<TUser> tUserList = tUserRepository.selectByExample(tUserExample);
        userBindTest.userbindTestByGeneral(tUserList.get(0).getUname());
        String deluserUrl = null;
        DelUserBO delUserBO = null;
        String deluserResult = "";
        if(addUserDTO!=null && addUserDTO.getUid()!=null){
            try {
                deluserUrl = url + "/AccService/deluser";
                delUserBO = new DelUserBO();
//                delUserBO.setAppid("1.00002");
                delUserBO.setUid(addUserDTO.getUid());
                delUserBO.setSeq("abc");
                log.info("deluserUrl 请求的参数=" + deluserUrl);
                log.info("delUserBO 请求的参数=" + JSON.toJSONString(delUserBO));
                deluserResult = HttpUtil.postGeneralUrl(deluserUrl, "application/json", JSON.toJSONString(delUserBO), "UTF-8");
                log.info("deluserResult 返回结果=" + deluserResult);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("必填参数appid校验");
                recordhttp.setUrl(deluserUrl);
                recordhttp.setRequest(JSON.toJSONString(delUserBO));
                recordhttp.setResponse(deluserResult);
                initLog(recordhttp, new Object() {
                });
                Assert.assertEquals(deluserResult.contains("\"msg\":\"access_deny\""), true);
                Assert.assertEquals(deluserResult.contains("\"result\":106"), true);
            }
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
