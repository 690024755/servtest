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
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AccServiceCheckSignBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.encr.MD5Utils;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


@Slf4j
@Component
public class AccServiceCheckSignTest extends BaseTest {
    @Autowired
    private AppidRepository appidRepository;

    /**
     * 1、在表appid新增一条appid的记录
     *
     * @throws Exception
     */
    public String accServiceCheckSignTestByGernal(AccServiceCheckSignBO accServiceCheckSignBO) {
        String accServiceCheckSignUrl = null;
        String accServiceCheckSignResult = "";
        try {
            accServiceCheckSignUrl = url + "/AccService/checksign";
            log.info("accServiceCheckSignUrl 请求的参数=" + accServiceCheckSignUrl);
            log.info("accServiceCheckSignBO 请求的参数=" + JSON.toJSONString(accServiceCheckSignBO));
            accServiceCheckSignResult = HttpUtil.postGeneralUrl(accServiceCheckSignUrl, "application/json", JSON.toJSONString(accServiceCheckSignBO), "UTF-8");
            log.info("accServiceCheckSignResult 返回结果=" + accServiceCheckSignResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return accServiceCheckSignResult;
        }
    }


    /**
     * 签名值是小写
     * @throws Exception
     */
    @Test
    public void accServiceCheckSignTestByParameterSignValueIsLowerCase() {
        String accServiceCheckSignUrl = null;
        AccServiceCheckSignBO accServiceCheckSignBO = null;
        String accServiceCheckSignResult = "";
        try {
            accServiceCheckSignUrl = url + "/AccService/checksign";
            accServiceCheckSignBO = new AccServiceCheckSignBO();
            accServiceCheckSignBO.setAppid("1.00002");
            accServiceCheckSignBO.setBmAppid("1.00002");
            accServiceCheckSignBO.setAccessToken(bmAppids.get("1.00002"));
            accServiceCheckSignBO.setRandomSeed(String.valueOf(System.currentTimeMillis()));
            accServiceCheckSignBO.setSignAppid("1.00002");
            accServiceCheckSignBO.setSign(generateSign(accServiceCheckSignBO).toLowerCase(Locale.getDefault()));
            log.info("accServiceCheckSignUrl 请求的参数=" + accServiceCheckSignUrl);
            log.info("accServiceCheckSignBO 请求的参数=" + JSON.toJSONString(accServiceCheckSignBO));
            accServiceCheckSignResult = HttpUtil.postGeneralUrl(accServiceCheckSignUrl, "application/json", JSON.toJSONString(accServiceCheckSignBO), "UTF-8");
            log.info("accServiceCheckSignResult 返回结果=" + accServiceCheckSignResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("签名值是小写");
            recordhttp.setUrl(accServiceCheckSignUrl);
            recordhttp.setRequest(JSON.toJSONString(accServiceCheckSignBO));
            recordhttp.setResponse(accServiceCheckSignResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(accServiceCheckSignResult.contains("\"isSign\":true"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("msg"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 签名值是大写,返回错误，暂不支持
     *
     * @throws Exception
     */
    @Test
    public void accServiceCheckSignTestByParameterSignValueIsUpperCase() {
        String accServiceCheckSignUrl = null;
        AccServiceCheckSignBO accServiceCheckSignBO = null;
        String accServiceCheckSignResult = "";
        try {
            accServiceCheckSignUrl = url + "/AccService/checksign";
            accServiceCheckSignBO = new AccServiceCheckSignBO();
            accServiceCheckSignBO.setAppid("1.00002");
            accServiceCheckSignBO.setBmAppid("1.00002");
            accServiceCheckSignBO.setAccessToken(bmAppids.get("1.00002"));
            accServiceCheckSignBO.setRandomSeed(String.valueOf(System.currentTimeMillis()));
            accServiceCheckSignBO.setSignAppid("1.00002");
            accServiceCheckSignBO.setSign(generateSign(accServiceCheckSignBO).toUpperCase(Locale.getDefault()));
            log.info("accServiceCheckSignUrl 请求的参数=" + accServiceCheckSignUrl);
            log.info("accServiceCheckSignBO 请求的参数=" + JSON.toJSONString(accServiceCheckSignBO));
            accServiceCheckSignResult = HttpUtil.postGeneralUrl(accServiceCheckSignUrl, "application/json", JSON.toJSONString(accServiceCheckSignBO), "UTF-8");
            log.info("accServiceCheckSignResult 返回结果=" + accServiceCheckSignResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("签名值是大写");
            recordhttp.setUrl(accServiceCheckSignUrl);
            recordhttp.setRequest(JSON.toJSONString(accServiceCheckSignBO));
            recordhttp.setResponse(accServiceCheckSignResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(accServiceCheckSignResult.contains("\"isSign\":false"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("msg"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 使用uid与token进行签名与验证
     * @throws Exception
     */
    @Test
    public void accServiceCheckSignTestByParameterIsUidAndParameterIsToken() {
        String accServiceCheckSignUrl = null;
        AccServiceCheckSignBO accServiceCheckSignBO = null;
        String accServiceCheckSignResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            accServiceCheckSignUrl = url + "/AccService/checksign";
            accServiceCheckSignBO = new AccServiceCheckSignBO();
            accServiceCheckSignBO.setAppid("1.00002");
            accServiceCheckSignBO.setUid(hs.get("uid"));
            accServiceCheckSignBO.setToken(hs.get("token"));
            accServiceCheckSignBO.setRandomSeed(String.valueOf(System.currentTimeMillis()));
            accServiceCheckSignBO.setSignAppid("1.00002");
            accServiceCheckSignBO.setSign(generateSign(accServiceCheckSignBO).toLowerCase(Locale.getDefault()));
            log.info("accServiceCheckSignUrl 请求的参数=" + accServiceCheckSignUrl);
            log.info("accServiceCheckSignBO 请求的参数=" + JSON.toJSONString(accServiceCheckSignBO));
            accServiceCheckSignResult = HttpUtil.postGeneralUrl(accServiceCheckSignUrl, "application/json", JSON.toJSONString(accServiceCheckSignBO), "UTF-8");
            log.info("accServiceCheckSignResult 返回结果=" + accServiceCheckSignResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token进行签名与验证");
            recordhttp.setUrl(accServiceCheckSignUrl);
            recordhttp.setRequest(JSON.toJSONString(accServiceCheckSignBO));
            recordhttp.setResponse(accServiceCheckSignResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(accServiceCheckSignResult.contains("\"isSign\":true"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("msg"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参与签名的参数appkey错误
     * @throws Exception
     */
    @Test
    public void accServiceCheckSignTestByNotExistParameterSignAppid() {
        String accServiceCheckSignUrl = null;
        AccServiceCheckSignBO accServiceCheckSignBO = null;
        String accServiceCheckSignResult = "";
        try {
            accServiceCheckSignUrl = url + "/AccService/checksign";
            accServiceCheckSignBO = new AccServiceCheckSignBO();
            accServiceCheckSignBO.setAppid("1.00002");
            accServiceCheckSignBO.setBmAppid("1.00002");
            accServiceCheckSignBO.setAccessToken(bmAppids.get("1.00002"));
            accServiceCheckSignBO.setRandomSeed(String.valueOf(System.currentTimeMillis()));
            accServiceCheckSignBO.setSignAppid("1.00002");
            accServiceCheckSignBO.setSign("test");
            log.info("accServiceCheckSignUrl 请求的参数=" + accServiceCheckSignUrl);
            log.info("accServiceCheckSignBO 请求的参数=" + JSON.toJSONString(accServiceCheckSignBO));
            accServiceCheckSignResult = HttpUtil.postGeneralUrl(accServiceCheckSignUrl, "application/json", JSON.toJSONString(accServiceCheckSignBO), "UTF-8");
            log.info("accServiceCheckSignResult 返回结果=" + accServiceCheckSignResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参与签名的参数appkey错误");
            recordhttp.setUrl(accServiceCheckSignUrl);
            recordhttp.setRequest(JSON.toJSONString(accServiceCheckSignBO));
            recordhttp.setResponse(accServiceCheckSignResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(accServiceCheckSignResult.contains("\"isSign\":false"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("msg"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("\"result\":1"), true);
        }
    }

    /**
     * RandomSeed的值是个空字符串
     * @throws Exception
     */
    @Test
    public void accServiceCheckSignTestByParameterRandomSeedValueIsEmpty() {
        String accServiceCheckSignUrl = null;
        AccServiceCheckSignBO accServiceCheckSignBO = null;
        String accServiceCheckSignResult = "";
        try {
            accServiceCheckSignUrl = url + "/AccService/checksign";
            accServiceCheckSignBO = new AccServiceCheckSignBO();
            accServiceCheckSignBO.setAppid("1.00002");
            accServiceCheckSignBO.setBmAppid("1.00002");
            accServiceCheckSignBO.setAccessToken(bmAppids.get("1.00002"));
            accServiceCheckSignBO.setRandomSeed("");
            accServiceCheckSignBO.setSignAppid("1.00002");
            accServiceCheckSignBO.setSign(generateSign(accServiceCheckSignBO).toLowerCase(Locale.getDefault()));
            log.info("accServiceCheckSignUrl 请求的参数=" + accServiceCheckSignUrl);
            log.info("accServiceCheckSignBO 请求的参数=" + JSON.toJSONString(accServiceCheckSignBO));
            accServiceCheckSignResult = HttpUtil.postGeneralUrl(accServiceCheckSignUrl, "application/json", JSON.toJSONString(accServiceCheckSignBO), "UTF-8");
            log.info("accServiceCheckSignResult 返回结果=" + accServiceCheckSignResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("RandomSeed的值是个空字符串");
            recordhttp.setUrl(accServiceCheckSignUrl);
            recordhttp.setRequest(JSON.toJSONString(accServiceCheckSignBO));
            recordhttp.setResponse(accServiceCheckSignResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(accServiceCheckSignResult.contains("\"isSign\":true"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("msg"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("\"result\":1"), true);
        }
    }

    /**
     * RandomSeed的值是个空字符串
     * @throws Exception
     */
    @Test
    public void accServiceCheckSignTestByParameterRandomSeedValueIsTab() {
        String accServiceCheckSignUrl = null;
        AccServiceCheckSignBO accServiceCheckSignBO = null;
        String accServiceCheckSignResult = "";
        try {
            accServiceCheckSignUrl = url + "/AccService/checksign";
            accServiceCheckSignBO = new AccServiceCheckSignBO();
            accServiceCheckSignBO.setAppid("1.00002");
            accServiceCheckSignBO.setBmAppid("1.00002");
            accServiceCheckSignBO.setAccessToken(bmAppids.get("1.00002"));
            accServiceCheckSignBO.setRandomSeed("   ");
            accServiceCheckSignBO.setSignAppid("1.00002");
            accServiceCheckSignBO.setSign(generateSign(accServiceCheckSignBO).toLowerCase(Locale.getDefault()));
            log.info("accServiceCheckSignUrl 请求的参数=" + accServiceCheckSignUrl);
            log.info("accServiceCheckSignBO 请求的参数=" + JSON.toJSONString(accServiceCheckSignBO));
            accServiceCheckSignResult = HttpUtil.postGeneralUrl(accServiceCheckSignUrl, "application/json", JSON.toJSONString(accServiceCheckSignBO), "UTF-8");
            log.info("accServiceCheckSignResult 返回结果=" + accServiceCheckSignResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("RandomSeed的值是个空字符串");
            recordhttp.setUrl(accServiceCheckSignUrl);
            recordhttp.setRequest(JSON.toJSONString(accServiceCheckSignBO));
            recordhttp.setResponse(accServiceCheckSignResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(accServiceCheckSignResult.contains("\"isSign\":true"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("msg"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("\"result\":1"), true);
        }
    }

    /**
     * RandomSeed的值超长
     * @throws Exception
     */
    @Test
    public void accServiceCheckSignTestByParameterRandomSeedValueIsLong() {
        String accServiceCheckSignUrl = null;
        AccServiceCheckSignBO accServiceCheckSignBO = null;
        String accServiceCheckSignResult = "";
        String filePathPng =getFilePath("2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        try {
            accServiceCheckSignUrl = url + "/AccService/checksign";
            accServiceCheckSignBO = new AccServiceCheckSignBO();
            accServiceCheckSignBO.setAppid("1.00002");
            accServiceCheckSignBO.setBmAppid("1.00002");
            accServiceCheckSignBO.setAccessToken(bmAppids.get("1.00002"));
            accServiceCheckSignBO.setRandomSeed(imgParamPng);
            accServiceCheckSignBO.setSignAppid("1.00002");
            accServiceCheckSignBO.setSign(generateSign(accServiceCheckSignBO).toLowerCase(Locale.getDefault()));
            log.info("accServiceCheckSignUrl 请求的参数=" + accServiceCheckSignUrl);
            log.info("accServiceCheckSignBO.getRandomSeed().length 请求的参数=" + accServiceCheckSignBO.getRandomSeed().length());
            log.info("accServiceCheckSignBO 请求的参数=" + JSON.toJSONString(accServiceCheckSignBO));
            accServiceCheckSignResult = HttpUtil.postGeneralUrl(accServiceCheckSignUrl, "application/json", JSON.toJSONString(accServiceCheckSignBO), "UTF-8");
            log.info("accServiceCheckSignResult 返回结果=" + accServiceCheckSignResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("RandomSeed的值超长");
            recordhttp.setUrl(accServiceCheckSignUrl);
            recordhttp.setRequest(JSON.toJSONString(accServiceCheckSignBO));
            recordhttp.setResponse(accServiceCheckSignResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(accServiceCheckSignResult.contains("\"isSign\":true"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("msg"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 必填参数Sign校验
     * @throws Exception
     */
    @Test
    public void accServiceCheckSignTestByNotExistParameterSign() {
        String accServiceCheckSignUrl = null;
        AccServiceCheckSignBO accServiceCheckSignBO = null;
        String accServiceCheckSignResult = "";
        try {
            accServiceCheckSignUrl = url + "/AccService/checksign";
            accServiceCheckSignBO = new AccServiceCheckSignBO();
            accServiceCheckSignBO.setAppid("1.00002");
            accServiceCheckSignBO.setBmAppid("1.00002");
            accServiceCheckSignBO.setAccessToken(bmAppids.get("1.00002"));
            accServiceCheckSignBO.setRandomSeed("test");
            accServiceCheckSignBO.setSignAppid("1.00002");
//            accServiceCheckSignBO.setSign(generateSign(accServiceCheckSignBO).toLowerCase(Locale.getDefault()));
            log.info("accServiceCheckSignUrl 请求的参数=" + accServiceCheckSignUrl);
            log.info("accServiceCheckSignBO 请求的参数=" + JSON.toJSONString(accServiceCheckSignBO));
            accServiceCheckSignResult = HttpUtil.postGeneralUrl(accServiceCheckSignUrl, "application/json", JSON.toJSONString(accServiceCheckSignBO), "UTF-8");
            log.info("accServiceCheckSignResult 返回结果=" + accServiceCheckSignResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Sign校验");
            recordhttp.setUrl(accServiceCheckSignUrl);
            recordhttp.setRequest(JSON.toJSONString(accServiceCheckSignBO));
            recordhttp.setResponse(accServiceCheckSignResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(accServiceCheckSignResult.contains("\"isSign\":false"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("msg"), true);
            Assert.assertEquals(accServiceCheckSignResult.contains("\"result\":1"), true);
        }
    }


    private String generateSign(AccServiceCheckSignBO accServiceCheckSignBO) {
        AppidExample example = new AppidExample();
        example.createCriteria().andAppidEqualTo(accServiceCheckSignBO.getSignAppid());
        return MD5Utils.getMD5(accServiceCheckSignBO.getRandomSeed() + appidRepository.selectByExample(example).get(0).getAppkey());
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
