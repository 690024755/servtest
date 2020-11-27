package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 15:24
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/15日galaxyeye All Rights Reserved.
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
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserBindBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.Data;
import lombok.ToString;
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
public class UserBindTest extends BaseTest {
    @Autowired
    private TUserRepository tUserRepository;

    @Autowired
    private TKeyindexRepository tKeyindexRepository;

    @Autowired
    private TUserkeysRepository tUserkeysRepository;

    @Autowired
    private AddUserTest addUserTest;

    /**
     * 存在用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个
     * @throws Exception
     */
    public void userbindTestByGeneral(String uname) throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname(uname);
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("存在用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("uid"), true);
        }
    }

    /**
     * 通用绑定方法
     * @throws Exception
     */
    public void userbindTestByGeneral(UserBindBO userbindBO) throws Exception {
        destroyData();
        String userbindUrl = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用绑定方法");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("value"), true);
            Assert.assertEquals(userbindResult.contains("appid"), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("uid"), true);
        }
    }


    /**
     * 存在用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个
     * @throws Exception
     */
    @Test
    public void userbindTestByOne1() throws Exception {
        destroyData();
        String Uname="cacc_8rhbukbq";
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname(Uname);
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("存在用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Long uid = 225825L;
            TKeyindexExample tKeyindexExample = new TKeyindexExample();
            TKeyindexExample.Criteria tKeyindexCr = tKeyindexExample.createCriteria();
            tKeyindexCr.andUidEqualTo(uid);
            TUserkeysExample tUserkeysExample = new TUserkeysExample();
            TUserkeysExample.Criteria tUserkeysCr = tUserkeysExample.createCriteria();
            tUserkeysCr.andUidEqualTo(uid);
            List<TKeyindex> TKeyindexList=tKeyindexRepository.selectByExample(tKeyindexExample);
            log.info("TKeyindexList="+JSON.toJSONString(TKeyindexList)+" ; 条数="+TKeyindexList.size());
//            Assert.assertTrue(tKeyindexRepository.selectByExample(tKeyindexExample).size()>0);
//            Assert.assertTrue(tUserkeysRepository.selectByExample(tUserkeysExample).size()>0);
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * 用户=test123456先绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个；
     * 用户=cacc_8rhbukbq最后绑定如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个;
     * 且两个用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile值相同
     * @throws Exception
     */
    @Test
    public void userbindTestByOne2() throws Exception {
        String UnameTmp = "test123456";
        addUserTest.adduserTestByGeneral(UnameTmp, UnameTmp);
        userbindTestByGeneral(UnameTmp);
        destroyData();
        String Uname="cacc_8rhbukbq";
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname(Uname);
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户=test123456先绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个；用户=cacc_8rhbukbq最后绑定如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个;且两个用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile值相同");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Long uid = 225825L;
            TKeyindexExample tKeyindexExample = new TKeyindexExample();
            TKeyindexExample.Criteria tKeyindexCr = tKeyindexExample.createCriteria();
            tKeyindexCr.andUidEqualTo(uid);
            TUserkeysExample tUserkeysExample = new TUserkeysExample();
            TUserkeysExample.Criteria tUserkeysCr = tUserkeysExample.createCriteria();
            tUserkeysCr.andUidEqualTo(uid);
            List<TKeyindex> TKeyindexList=tKeyindexRepository.selectByExample(tKeyindexExample);
            List<TUserkeys> TUserkeysList=tUserkeysRepository.selectByExample(tUserkeysExample);
            log.info("userbindTestByOne2 TKeyindexList="+JSON.toJSONString(TKeyindexList)+" ; 条数="+TKeyindexList.size());
            log.info("userbindTestByOne2 TUserkeysList="+JSON.toJSONString(TUserkeysList)+" ; 条数="+TUserkeysList.size());
            Assert.assertTrue(TKeyindexList.size()==0);
            Assert.assertTrue(TUserkeysList.size()>0);
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * 用户=test123456先绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个；
     * 用户=cacc_8rhbukbq最后绑定如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个;
     * 且两个用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile值不相同
     * @throws Exception
     */
    @Test
    public void userbindTestByOne3() throws Exception {
        String UnameTmp = "test123456";
        addUserTest.adduserTestByGeneral(UnameTmp, UnameTmp);
        userbindTestByGeneral(UnameTmp);
        destroyData();
        String Uname="cacc_8rhbukbq";
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname(Uname);
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy_diff");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy_diff");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy_diff@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511_diff");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy_diff");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy_diff");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy_diff");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户=test123456先绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个；用户=cacc_8rhbukbq最后绑定如openid、unionid、cacc、nickname、deviceid、email、mobile,每个值一次绑定一个;且两个用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile值不相同");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Long uid = 225825L;
            TKeyindexExample tKeyindexExample = new TKeyindexExample();
            TKeyindexExample.Criteria tKeyindexCr = tKeyindexExample.createCriteria();
            tKeyindexCr.andUidEqualTo(uid);
            TUserkeysExample tUserkeysExample = new TUserkeysExample();
            TUserkeysExample.Criteria tUserkeysCr = tUserkeysExample.createCriteria();
            tUserkeysCr.andUidEqualTo(uid);
            List<TKeyindex> TKeyindexList=tKeyindexRepository.selectByExample(tKeyindexExample);
            List<TUserkeys> TUserkeysList=tUserkeysRepository.selectByExample(tUserkeysExample);
            log.info("userbindTestByOne2 TKeyindexList="+JSON.toJSONString(TKeyindexList)+" ; 条数="+TKeyindexList.size());
            log.info("userbindTestByOne2 TUserkeysList="+JSON.toJSONString(TUserkeysList)+" ; 条数="+TUserkeysList.size());
            Assert.assertTrue(TKeyindexList.size()>0);
            Assert.assertTrue(TUserkeysList.size()>0);
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy_diff\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy_diff\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy_diff@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511_diff\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy_diff\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy_diff\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy_diff\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * 存在用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile,一次绑定多个openid与unionid
     * @throws Exception
     */
    @Test
    public void userbindTestByTwo() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname("cacc_8rhbukbq");
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setOpenid("Openid_yy1");
            UserBindBO.KeysBean KeysBean8 = new UserBindBO.KeysBean();
            KeysBean8.setUnionid("Unionid_yy");
            UserBindBO.KeysBean KeysBean9 = new UserBindBO.KeysBean();
            KeysBean9.setUnionid("Unionid_yy1");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            keys.add(KeysBean8);
            keys.add(KeysBean9);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("存在用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile,一次绑定多个openid与unionid");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":112"), true);
        }
    }

    /**
     * 重复绑定openid的不同值，结果是追加写入
     * @throws Exception
     */
    @Test
    public void userbindTestByRepeatBindOpenid() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            for (int i = 0; i < 2; i++) {
                userbindUrl = url + "/AccService/userbind";
                userbindBO = new UserBindBO();
                userbindBO.setAppid("1.00002");
                userbindBO.setUname("cacc_8rhbukbq");
                List<UserBindBO.KeysBean> keys=new ArrayList<>();
                UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
                KeysBean1.setCacc("Cacc_yy");
                UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
                KeysBean2.setDeviceid("Deviceid_yy");
                UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
                KeysBean3.setEmail("yy@163.com");
                UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
                KeysBean4.setMobile("13093863511");
                UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
                KeysBean5.setNickname("yy");
                UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
                KeysBean6.setOpenid("Openid_yy");
                UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
                KeysBean7.setOpenid("Openid_yy1");
                UserBindBO.KeysBean KeysBean8 = new UserBindBO.KeysBean();
                KeysBean8.setUnionid("Unionid_yy");
                if(i==1){
                    keys.add(KeysBean7);
                }else {
                    keys.add(KeysBean6);
                }
                keys.add(KeysBean1);
                keys.add(KeysBean2);
                keys.add(KeysBean3);
                keys.add(KeysBean4);
                keys.add(KeysBean5);
                keys.add(KeysBean8);
                userbindBO.setKeys(keys);
                log.info("userbindUrl 请求的参数=" + userbindUrl);
                log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
                userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
                log.info("userbindResult 返回结果=" + userbindResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("重复绑定openid的不同值");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy1\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * 重复绑定Unionid的不同值，结果是覆盖之前
     * @throws Exception
     */
    @Test
    public void userbindTestByRepeatBindUnionid() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            for (int i = 0; i < 2; i++) {
                userbindUrl = url + "/AccService/userbind";
                userbindBO = new UserBindBO();
                userbindBO.setAppid("1.00002");
                userbindBO.setUname("cacc_8rhbukbq");
                List<UserBindBO.KeysBean> keys=new ArrayList<>();
                UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
                KeysBean1.setCacc("Cacc_yy");
                UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
                KeysBean2.setDeviceid("Deviceid_yy");
                UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
                KeysBean3.setEmail("yy@163.com");
                UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
                KeysBean4.setMobile("13093863511");
                UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
                KeysBean5.setNickname("yy");
                UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
                KeysBean6.setOpenid("Openid_yy");
                UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
                KeysBean7.setOpenid("Openid_yy1");
                UserBindBO.KeysBean KeysBean8 = new UserBindBO.KeysBean();
                KeysBean8.setUnionid("Unionid_yy");
                if(i==1){
                    keys.add(KeysBean7);
                }else {
                    keys.add(KeysBean6);
                }
                keys.add(KeysBean1);
                keys.add(KeysBean2);
                keys.add(KeysBean3);
                keys.add(KeysBean4);
                keys.add(KeysBean5);
                keys.add(KeysBean8);
                userbindBO.setKeys(keys);
                log.info("userbindUrl 请求的参数=" + userbindUrl);
                log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
                userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
                log.info("userbindResult 返回结果=" + userbindResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("重复绑定Unionid的不同值，结果是覆盖之前");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * 不存在用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile
     * @throws Exception
     */
    @Test
    public void userbindTestByNotExistUname() throws Exception {
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname("———…………%%￥￥#@@");
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("不存在用户绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":104"), true);
        }
    }

    /**
     * 从未绑定过的用户进行绑定，绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile
     * @throws Exception
     */
    @Test
    public void userbindTestByExistUnameAndNeverBind() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname("cacc_8rhbukbq");
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("从未绑定过的用户进行绑定，绑定类型如openid、unionid、cacc、nickname、deviceid、email、mobile");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
        }
    }


    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void userbindTestByNotExistParameterAppid() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
//            userbindBO.setAppid("1.00002");
            userbindBO.setUname("cacc_8rhbukbq");
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":106"), true);
        }
    }

    /**
     * 必填参数Uname校验
     * @throws Exception
     */
    @Test
    public void userbindTestByNotExistParameterUname() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
//            userbindBO.setUname("cacc_8rhbukbq");
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Uname校验");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":101"), true);
            Assert.assertEquals(userbindResult.contains("\"uname\":\"\""), true);
        }
    }

    /**
     * 必填参数keys校验
     * @throws Exception
     */
    @Test
    public void userbindTestByNotExistParameterKeys() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname("cacc_8rhbukbq");
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
//            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数keys校验");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("\"keys\":{}"), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * 参数keys的值为空
     * @throws Exception
     */
    @Test
    public void userbindTestByEmptyKeys() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname("cacc_8rhbukbq");
            userbindBO.setKeys(new ArrayList<>());
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数keys校验");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("\"keys\":{}"), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
        }
    }


    /**
     * 一个账号绑定多个信息,然后修改appid值，此时表t_userkeys的appid值会被修改
     * 需要手动检查下数据库表t_keyindex、t_userkeys的appid均变更
     * SELECT * from userdb.t_keyindex where uid=225825;
     * SELECT * from userdb.t_userkeys where uid=225825;
     *
     * @throws Exception
     */
    @Test
    public void userbindTestByBindMultiParameterAndModifyAppidValue1() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname("cacc_8rhbukbq");
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            //首次绑定的appid=1.00002
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
            userbindBO.setAppid("1.00003");
            //第二次绑定的appid=1.00003
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一个账号绑定多个信息,然后修改appid值，此时表t_userkeys的appid值会被修改");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
            UserBindBO.KeysBean keysBean=JSON.parseObject(userbindResult,UserBindBO.class).getKeys().get(0);
            String CaccAppid=JSON.parseObject(keysBean.getCacc(),JsonRootBean.class).getValue().get(0).getAppid();
            String DeviceidAppid=JSON.parseObject(keysBean.getDeviceid(),JsonRootBean.class).getValue().get(0).getAppid();
            String EmailAppid=JSON.parseObject(keysBean.getEmail(),JsonRootBean.class).getValue().get(0).getAppid();
            String MobileAppid=JSON.parseObject(keysBean.getMobile(),JsonRootBean.class).getValue().get(0).getAppid();
            String NicknameAppid=JSON.parseObject(keysBean.getNickname(),JsonRootBean.class).getValue().get(0).getAppid();
            String OpenidAppid=JSON.parseObject(keysBean.getOpenid(),JsonRootBean.class).getValue().get(0).getAppid();
            String UnionidAppid=JSON.parseObject(keysBean.getUnionid(),JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(CaccAppid.equals("1.00003"));
            Assert.assertTrue(DeviceidAppid.equals("1.00003"));
            Assert.assertTrue(EmailAppid.equals("1.00003"));
            Assert.assertTrue(MobileAppid.equals("1.00003"));
            Assert.assertTrue(NicknameAppid.equals("1.00003"));
            Assert.assertTrue(OpenidAppid.equals("1.00003"));
            Assert.assertTrue(UnionidAppid.equals("1.00003"));
        }
    }

    /**
     * 一个账号绑定多个信息,然后修改appid值，此时表t_userkeys的appid值会被修改
     * @throws Exception
     */
    @Test
    public void userbindTestByBindMultiParameterAndModifyAppidValue2() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname("cacc_8rhbukbq");
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
            //一共7个key,修改其中6个key绑定的appid，另外key的appid不修改
            userbindBO.setAppid("1.00003");
            keys.remove(0);
            userbindBO.setKeys(keys);
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一个账号绑定多个信息,然后修改appid值，此时表t_userkeys的appid值会被修改");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
            UserBindBO.KeysBean keysBean=JSON.parseObject(userbindResult,UserBindBO.class).getKeys().get(0);
            String CaccAppid=JSON.parseObject(keysBean.getCacc(),JsonRootBean.class).getValue().get(0).getAppid();
            String DeviceidAppid=JSON.parseObject(keysBean.getDeviceid(),JsonRootBean.class).getValue().get(0).getAppid();
            String EmailAppid=JSON.parseObject(keysBean.getEmail(),JsonRootBean.class).getValue().get(0).getAppid();
            String MobileAppid=JSON.parseObject(keysBean.getMobile(),JsonRootBean.class).getValue().get(0).getAppid();
            String NicknameAppid=JSON.parseObject(keysBean.getNickname(),JsonRootBean.class).getValue().get(0).getAppid();
            String OpenidAppid=JSON.parseObject(keysBean.getOpenid(),JsonRootBean.class).getValue().get(0).getAppid();
            String UnionidAppid=JSON.parseObject(keysBean.getUnionid(),JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(CaccAppid.equals("1.00002"));
            Assert.assertTrue(DeviceidAppid.equals("1.00003"));
            Assert.assertTrue(EmailAppid.equals("1.00003"));
            Assert.assertTrue(MobileAppid.equals("1.00003"));
            Assert.assertTrue(NicknameAppid.equals("1.00003"));
            Assert.assertTrue(OpenidAppid.equals("1.00003"));
            Assert.assertTrue(UnionidAppid.equals("1.00003"));
        }
    }

    /**
     * 一个账号绑定多个信息,然后修改appid值，但是keys为空，此时表t_userkeys的appid值不会被修改
     * @throws Exception
     */
    @Test
    public void userbindTestByBindMultiParameterAndModifyAppidValue3() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname("cacc_8rhbukbq");
            List<UserBindBO.KeysBean> keys=new ArrayList<>();
            UserBindBO.KeysBean KeysBean1 = new UserBindBO.KeysBean();
            KeysBean1.setCacc("Cacc_yy");
            UserBindBO.KeysBean KeysBean2 = new UserBindBO.KeysBean();
            KeysBean2.setDeviceid("Deviceid_yy");
            UserBindBO.KeysBean KeysBean3 = new UserBindBO.KeysBean();
            KeysBean3.setEmail("yy@163.com");
            UserBindBO.KeysBean KeysBean4 = new UserBindBO.KeysBean();
            KeysBean4.setMobile("13093863511");
            UserBindBO.KeysBean KeysBean5 = new UserBindBO.KeysBean();
            KeysBean5.setNickname("yy");
            UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
            KeysBean6.setOpenid("Openid_yy");
            UserBindBO.KeysBean KeysBean7 = new UserBindBO.KeysBean();
            KeysBean7.setUnionid("Unionid_yy");
            keys.add(KeysBean1);
            keys.add(KeysBean2);
            keys.add(KeysBean3);
            keys.add(KeysBean4);
            keys.add(KeysBean5);
            keys.add(KeysBean6);
            keys.add(KeysBean7);
            userbindBO.setKeys(keys);
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
            //一共7个key,修改其中6个key绑定的appid，另外key的appid不修改
            userbindBO.setAppid("1.00003");
            userbindBO.setKeys(new ArrayList<>());
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一个账号绑定多个信息,然后修改appid值，但是keys为空，此时表t_userkeys的appid值不会被修改");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("keys"), true);
            Assert.assertEquals(userbindResult.contains("cacc"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(userbindResult.contains("deviceid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("email"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(userbindResult.contains("mobile"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(userbindResult.contains("nickname"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(userbindResult.contains("openid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("unionid"), true);
            Assert.assertEquals(userbindResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
            UserBindBO.KeysBean keysBean=JSON.parseObject(userbindResult,UserBindBO.class).getKeys().get(0);
            String CaccAppid=JSON.parseObject(keysBean.getCacc(),JsonRootBean.class).getValue().get(0).getAppid();
            String DeviceidAppid=JSON.parseObject(keysBean.getDeviceid(),JsonRootBean.class).getValue().get(0).getAppid();
            String EmailAppid=JSON.parseObject(keysBean.getEmail(),JsonRootBean.class).getValue().get(0).getAppid();
            String MobileAppid=JSON.parseObject(keysBean.getMobile(),JsonRootBean.class).getValue().get(0).getAppid();
            String NicknameAppid=JSON.parseObject(keysBean.getNickname(),JsonRootBean.class).getValue().get(0).getAppid();
            String OpenidAppid=JSON.parseObject(keysBean.getOpenid(),JsonRootBean.class).getValue().get(0).getAppid();
            String UnionidAppid=JSON.parseObject(keysBean.getUnionid(),JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(CaccAppid.equals("1.00002"));
            Assert.assertTrue(DeviceidAppid.equals("1.00002"));
            Assert.assertTrue(EmailAppid.equals("1.00002"));
            Assert.assertTrue(MobileAppid.equals("1.00002"));
            Assert.assertTrue(NicknameAppid.equals("1.00002"));
            Assert.assertTrue(OpenidAppid.equals("1.00002"));
            Assert.assertTrue(UnionidAppid.equals("1.00002"));
        }
    }


    /**
     * 参数keys的值为空，更新appid
     * @throws Exception
     */
    @Test
    public void userbindTestByEmptyKeysAndModifyAppid() throws Exception {
        destroyData();
        String userbindUrl = null;
        UserBindBO userbindBO = null;
        String userbindResult = "";
        try {
            userbindUrl = url + "/AccService/userbind";
            userbindBO = new UserBindBO();
            userbindBO.setAppid("1.00002");
            userbindBO.setUname("cacc_8rhbukbq");
            userbindBO.setKeys(new ArrayList<>());
            log.info("userbindUrl 请求的参数=" + userbindUrl);
            log.info("userbindBO 请求的参数=" + JSON.toJSONString(userbindBO));
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            userbindBO.setAppid("1.00003");
            userbindResult = HttpUtil.postGeneralUrl(userbindUrl, "application/json", JSON.toJSONString(userbindBO), "UTF-8");
            log.info("userbindResult 返回结果=" + userbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数keys的值为空，更新appid");
            recordhttp.setUrl(userbindUrl);
            recordhttp.setRequest(JSON.toJSONString(userbindBO));
            recordhttp.setResponse(userbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userbindResult.contains("\"keys\":{}"), true);
            Assert.assertEquals(userbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(userbindResult.contains("\"uid\":225825"), true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {
        Long uid = 225825L;
        TUserExample tUserExample = new TUserExample();
        TUserExample.Criteria tUserCr = tUserExample.createCriteria();
        tUserCr.andUidEqualTo(uid);
        List<TUser> tUserList = tUserRepository.selectByExample(tUserExample);
        if (tUserList.size() < 0) {
//            tUserRepository.deleteByExample(tUserExample);
            TUser recordTUser = new TUser();
            recordTUser.setUid(225825L);
            recordTUser.setUname("cacc_8rhbukbq");
            recordTUser.setBlockedtimeout(0L);
            recordTUser.setPassword("a2ql6nsAUN5icIPkrlJxAaJQB/BNXSBmByzvm6QQKuQ=");
            recordTUser.setChannelNo("");
            recordTUser.setActivityno("");
            recordTUser.setCreatetime(1568019693L);
            recordTUser.setModifytime(1589512339L);
            recordTUser.setBlocked(0);
            recordTUser.setStatus(1);
            recordTUser.setAppid("1.00003");
            recordTUser.setUc(67L);
            recordTUser.setIp("172.16.5.6");
            recordTUser.setLastlogintime(1589512339L);
            recordTUser.setLastlogouttime(0L);
            tUserRepository.insert(recordTUser);
        }
    }



    @Override
    public void destroyData() {
        Long uid = 225825L;
        TKeyindexExample tKeyindexExample = new TKeyindexExample();
        TKeyindexExample.Criteria tKeyindexCr = tKeyindexExample.createCriteria();
        tKeyindexCr.andUidEqualTo(uid);
        tKeyindexRepository.deleteByExample(tKeyindexExample);
        TUserkeysExample tUserkeysExample = new TUserkeysExample();
        TUserkeysExample.Criteria tUserkeysCr = tUserkeysExample.createCriteria();
        tUserkeysCr.andUidEqualTo(uid);
        tUserkeysRepository.deleteByExample(tUserkeysExample);
    }



    @Data
    @ToString
    public static class JsonRootBean {
        private List<Value> value;
        @Data
        @ToString
        public static class Value {
            private String value;
            private String appid;
            private long modifyTime;
        }
    }
}
