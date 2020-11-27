package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 15:33
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/15日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.DelUserBindBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.DTO.DelUserBindDTO;
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
public class DelUserBindTest extends BaseTest {

    @Autowired
    private UserBindTest userBindTest;

    /**
     * 一次性删除多个绑定信息如、unionid、deviceid、openid、cacc、nickname、email、mobile
     * @throws Exception
     */
    @Test
    public void deluserbindTestByMore() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            DelUserBindBO.KeysBean bean2=new DelUserBindBO.KeysBean();
            bean2.setDeviceid("Deviceid_yy");
            DelUserBindBO.KeysBean bean3=new DelUserBindBO.KeysBean();
            bean3.setEmail("yy@163.com");
            DelUserBindBO.KeysBean bean4=new DelUserBindBO.KeysBean();
            bean4.setMobile("13093863511");
            DelUserBindBO.KeysBean bean5=new DelUserBindBO.KeysBean();
            bean5.setNickname("yy");
            DelUserBindBO.KeysBean bean6=new DelUserBindBO.KeysBean();
            bean6.setOpenid("Openid_yy");
            DelUserBindBO.KeysBean bean7=new DelUserBindBO.KeysBean();
            bean7.setUnionid("Unionid_yy");
            keys.add(bean1);
            keys.add(bean2);
            keys.add(bean3);
            keys.add(bean4);
            keys.add(bean5);
            keys.add(bean6);
            keys.add(bean7);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一次性删除多个绑定信息如、unionid、deviceid、openid、cacc、nickname、email、mobile");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"cacc\":\"Cacc_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("keys"), true);
            Assert.assertEquals(deluserbindResult.contains("\"deviceid\":\"Deviceid_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"email\":\"yy@163.com\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"mobile\":\"13093863511\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"nickname\":\"yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"openid\":\"Openid_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"unionid\":\"Unionid_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
        }
    }

    /**
     * 删除一个绑定信息如、unionid、deviceid、openid、cacc、nickname、email、mobile任意一个
     * @throws Exception
     */
    @Test
    public void deluserbindTestByOne() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            keys.add(bean1);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除一个绑定信息如、unionid、deviceid、openid、cacc、nickname、email、mobile任意一个");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"cacc\":\"Cacc_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("keys"), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
        }
    }

    /**
     * 循环删除绑定信息，每次删除一个如、unionid、deviceid、openid、cacc、nickname、email、mobile任意一个
     * @throws Exception
     */
    @Test
    public void deluserbindTestByCircal() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
        DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
        bean1.setCacc("Cacc_yy");
        DelUserBindBO.KeysBean bean2=new DelUserBindBO.KeysBean();
        bean2.setDeviceid("Deviceid_yy");
        DelUserBindBO.KeysBean bean3=new DelUserBindBO.KeysBean();
        bean3.setEmail("yy@163.com");
        DelUserBindBO.KeysBean bean4=new DelUserBindBO.KeysBean();
        bean4.setMobile("13093863511");
        DelUserBindBO.KeysBean bean5=new DelUserBindBO.KeysBean();
        bean5.setNickname("yy");
        DelUserBindBO.KeysBean bean6=new DelUserBindBO.KeysBean();
        bean6.setOpenid("Openid_yy");
        DelUserBindBO.KeysBean bean7=new DelUserBindBO.KeysBean();
        bean7.setUnionid("Unionid_yy");
        keys.add(bean1);
        keys.add(bean2);
        keys.add(bean3);
        keys.add(bean4);
        keys.add(bean5);
        keys.add(bean6);
        keys.add(bean7);
        for (DelUserBindBO.KeysBean keysBean :keys) {
            try {
                List<DelUserBindBO.KeysBean> tmp = new ArrayList<>();
                tmp.add(keysBean);
                deluserbindUrl = url + "/AccService/deluserbind";
                delUserBindBO = new DelUserBindBO();
                delUserBindBO.setAppid("1.00002");
                delUserBindBO.setUname("cacc_8rhbukbq");
                delUserBindBO.setKeys(tmp);
                log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
                log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
                deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
                log.info("deluserbindResult 返回结果=" + deluserbindResult);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("删除一个绑定信息如、unionid、deviceid、openid、cacc、nickname、email、mobile任意一个");
                recordhttp.setUrl(deluserbindUrl);
                recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
                recordhttp.setResponse(deluserbindResult);
                initLog(recordhttp, new Object() {
                });
                DelUserBindDTO.KeysBean keysBeanResult=JSON.parseObject(deluserbindResult, DelUserBindDTO.class).getKeys().get(0);
                Assert.assertNotNull(keysBeanResult);
                Assert.assertEquals(deluserbindResult.contains(keysBeanResult.get(keysBeanResult)), true);
                Assert.assertEquals(deluserbindResult.contains("keys"), true);
                Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
                Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
            }
        }
    }

    /**
     * 删除用户绑定信息时候，参数keys为空
     * @throws Exception
     */
    @Test
    public void deluserbindTestByEmptyKeys() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            delUserBindBO.setKeys(new ArrayList<>());
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除用户绑定信息时候，参数keys为空");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"keys\":[]"), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
        }
    }

    /**
     * 删除不存在用户的绑定信息
     * @throws Exception
     */
    @Test
    public void deluserbindTestByNotExistUser() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("cacc_8rhbukbq===tes");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            keys.add(bean1);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除不存在用户的绑定信息");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":104"), true);
        }
    }

    /**
     * 删除不存在keys信息
     * @throws Exception
     */
    @Test
    public void deluserbindTestByNotExistKeysValue() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setTest("Test_yy");
            keys.add(bean1);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除不存在keys信息");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"msg\":\"key_not_found\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":109"), true);
        }
    }

    /**
     * 必填参数keys校验
     * @throws Exception
     */
    @Test
    public void deluserbindTestByNotExistParameterKeys() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            keys.add(bean1);
//            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数keys校验");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"keys\":null"), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
        }
    }

    /**
     * 必填参数Uname校验
     * @throws Exception
     */
    @Test
    public void deluserbindTestByNotExistParameterUname() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
//            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            keys.add(bean1);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Uname校验");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":101"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":\"\""), true);
        }
    }

    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void deluserbindTestByNotExistParameterAppid() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
//            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            keys.add(bean1);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":106"), true);
        }
    }

    /**
     * 参数uname的值使用uid进行传递
     * @throws Exception
     */
    @Test
    public void deluserbindTestByUnameValueIsUid() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("!225825");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            keys.add(bean1);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数uname的值使用uid进行传递");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("keys"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"cacc\":\"Cacc_yy\""), true);
        }
    }

    /**
     * 用户未绑定信息，然后删除
     * @throws Exception
     */
    @Test
    public void deluserbindTestByUnameNotBindAny() throws Exception {
        userBindTest.destroyData();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("!225825");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            keys.add(bean1);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户未绑定信息，然后删除");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("keys"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"cacc\":\"Cacc_yy\""), true);
        }
    }

    /**
     * 用户绑定信息，删除的key值不正确
     * @throws Exception
     */
    @Test
    public void deluserbindTestByKeysValueNotRight() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00002");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy1");
            keys.add(bean1);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一次性删除多个绑定信息如、unionid、deviceid、openid、cacc、nickname、email、mobile");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"cacc\":\"Cacc_yy1\""), true);
            Assert.assertEquals(deluserbindResult.contains("keys"), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
        }
    }


    /**
     * 原先旧appid换成新appid，绑定7个，7个绑定信息全部删除
     * @throws Exception
     */
    @Test
    public void deluserbindTestByMoreAndModifyAppidValueAndParameterKeysValueIsNotEmpty1() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00003");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            DelUserBindBO.KeysBean bean2=new DelUserBindBO.KeysBean();
            bean2.setDeviceid("Deviceid_yy");
            DelUserBindBO.KeysBean bean3=new DelUserBindBO.KeysBean();
            bean3.setEmail("yy@163.com");
            DelUserBindBO.KeysBean bean4=new DelUserBindBO.KeysBean();
            bean4.setMobile("13093863511");
            DelUserBindBO.KeysBean bean5=new DelUserBindBO.KeysBean();
            bean5.setNickname("yy");
            DelUserBindBO.KeysBean bean6=new DelUserBindBO.KeysBean();
            bean6.setOpenid("Openid_yy");
            DelUserBindBO.KeysBean bean7=new DelUserBindBO.KeysBean();
            bean7.setUnionid("Unionid_yy");
            keys.add(bean1);
            keys.add(bean2);
            keys.add(bean3);
            keys.add(bean4);
            keys.add(bean5);
            keys.add(bean6);
            keys.add(bean7);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("原先旧appid换成新appid，绑定7个，7个绑定信息全部删除");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"cacc\":\"Cacc_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("keys"), true);
            Assert.assertEquals(deluserbindResult.contains("\"deviceid\":\"Deviceid_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"email\":\"yy@163.com\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"mobile\":\"13093863511\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"nickname\":\"yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"openid\":\"Openid_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"unionid\":\"Unionid_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
        }
    }


    /**
     * 原先旧appid换成新appid，绑定7个，删除6个保留一个
     * @throws Exception
     */
    @Test
    public void deluserbindTestByMoreAndModifyAppidValueAndParameterKeysValueIsNotEmpty2() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00003");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            DelUserBindBO.KeysBean bean2=new DelUserBindBO.KeysBean();
            bean2.setDeviceid("Deviceid_yy");
            DelUserBindBO.KeysBean bean3=new DelUserBindBO.KeysBean();
            bean3.setEmail("yy@163.com");
            DelUserBindBO.KeysBean bean4=new DelUserBindBO.KeysBean();
            bean4.setMobile("13093863511");
            DelUserBindBO.KeysBean bean5=new DelUserBindBO.KeysBean();
            bean5.setNickname("yy");
            DelUserBindBO.KeysBean bean6=new DelUserBindBO.KeysBean();
            bean6.setOpenid("Openid_yy");
            DelUserBindBO.KeysBean bean7=new DelUserBindBO.KeysBean();
//            bean7.setUnionid("Unionid_yy");
            keys.add(bean1);
            keys.add(bean2);
            keys.add(bean3);
            keys.add(bean4);
            keys.add(bean5);
            keys.add(bean6);
            keys.add(bean7);
            delUserBindBO.setKeys(keys);
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("原先旧appid换成新appid，绑定7个，删除6个保留一个");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"cacc\":\"Cacc_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("keys"), true);
            Assert.assertEquals(deluserbindResult.contains("\"deviceid\":\"Deviceid_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"email\":\"yy@163.com\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"mobile\":\"13093863511\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"nickname\":\"yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"openid\":\"Openid_yy\""), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
        }
    }



    /**
     * 原先旧appid换成新appid，但是需要删除的keys为空，则不会删除绑定信息
     * @throws Exception
     */
    @Test
    public void deluserbindTestByMoreAndModifyAppidValueAndParameterKeysValueIsEmpty() throws Exception {
        userBindTest.userbindTestByOne1();
        String deluserbindUrl = null;
        DelUserBindBO delUserBindBO = null;
        String deluserbindResult = "";
        try {
            deluserbindUrl = url + "/AccService/deluserbind";
            delUserBindBO = new DelUserBindBO();
            delUserBindBO.setAppid("1.00003");
            delUserBindBO.setUname("cacc_8rhbukbq");
            List<DelUserBindBO.KeysBean> keys = new ArrayList<>();
            DelUserBindBO.KeysBean bean1=new DelUserBindBO.KeysBean();
            bean1.setCacc("Cacc_yy");
            DelUserBindBO.KeysBean bean2=new DelUserBindBO.KeysBean();
            bean2.setDeviceid("Deviceid_yy");
            DelUserBindBO.KeysBean bean3=new DelUserBindBO.KeysBean();
            bean3.setEmail("yy@163.com");
            DelUserBindBO.KeysBean bean4=new DelUserBindBO.KeysBean();
            bean4.setMobile("13093863511");
            DelUserBindBO.KeysBean bean5=new DelUserBindBO.KeysBean();
            bean5.setNickname("yy");
            DelUserBindBO.KeysBean bean6=new DelUserBindBO.KeysBean();
            bean6.setOpenid("Openid_yy");
            DelUserBindBO.KeysBean bean7=new DelUserBindBO.KeysBean();
            bean7.setUnionid("Unionid_yy");
            keys.add(bean1);
            keys.add(bean2);
            keys.add(bean3);
            keys.add(bean4);
            keys.add(bean5);
            keys.add(bean6);
            keys.add(bean7);
            delUserBindBO.setKeys(new ArrayList<>());
            log.info("deluserbindUrl 请求的参数=" + deluserbindUrl);
            log.info("delUserBindBO 请求的参数=" + JSON.toJSONString(delUserBindBO));
            deluserbindResult = HttpUtil.postGeneralUrl(deluserbindUrl, "application/json", JSON.toJSONString(delUserBindBO), "UTF-8");
            log.info("deluserbindResult 返回结果=" + deluserbindResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("原先旧appid换成新appid，但是需要删除的keys为空，则不会删除绑定信息");
            recordhttp.setUrl(deluserbindUrl);
            recordhttp.setRequest(JSON.toJSONString(delUserBindBO));
            recordhttp.setResponse(deluserbindResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deluserbindResult.contains("\"keys\":[]"), true);
            Assert.assertEquals(deluserbindResult.contains("\"result\":1"), true);
            Assert.assertEquals(deluserbindResult.contains("\"uname\":225825"), true);
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
