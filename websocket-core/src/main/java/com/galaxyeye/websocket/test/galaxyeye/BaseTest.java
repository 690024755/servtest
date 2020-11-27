package com.galaxyeye.websocket.test.galaxyeye; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye
 * @Date Create on 16:31
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/26日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.HttpLogRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.EUserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.DTO.ELoginDTO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase.ELoginTest;
import com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase.LoginTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.CustomReport;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ContextConfiguration(locations = {"/META-INF/spring/websocket-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public abstract class BaseTest extends AbstractTestNGSpringContextTests implements IResource {

    public Map<String, String> urls = new HashMap<String, String>() {{
        put(ModuleNameEnum.getName(0), "http://172.16.0.25:18080");
        put(ModuleNameEnum.getName(1), "http://172.16.0.25:18081");
        put(ModuleNameEnum.getName(2), "http://172.16.0.25:19092");
        put(ModuleNameEnum.getName(3), "http://172.16.0.25:8088");
        put(ModuleNameEnum.getName(4), "http://172.16.0.25:18082");
        put(ModuleNameEnum.getName(5), "http://172.16.0.25");//存在问题
        put(ModuleNameEnum.getName(6), "http://172.16.0.25:8099");
        put(ModuleNameEnum.getName(7), "ws:172.16.0.25:2345");
        put(ModuleNameEnum.getName(8), "https://wxchat.galaxyeye-api.com");
        put(ModuleNameEnum.getName(9), "http://172.16.0.25:9091");//存在问题
        put(ModuleNameEnum.getName(10), "http://172.16.0.25/");
        put(ModuleNameEnum.getName(11), "http://172.16.0.25:48080");
        put(ModuleNameEnum.getName(12), "ws://172.16.0.25:1443");
        put(ModuleNameEnum.getName(13), "http://172.16.0.25:9093");
        put(ModuleNameEnum.getName(14), "http://172.16.0.25:8099");
        put(ModuleNameEnum.getName(15), "ws://172.16.0.25:48081");
    }};

    public Map<String, String> bmAppids = new HashMap<String, String>() {{
        put("100.00001", "6ec287b5ef320ddd268520ffae176be2159adabf75e6f8dd5ef4e01ad4af496d96884e169383e0affa8a091c91f73a93");
        put("100.00002", "11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
        put("100.00003", "d33a5a411e9fcb60b27bccee8bd06901708942bad729a481fe12c6bc437eec4d5890b9bac38f958c0b5ee9d6719708ba");
        put("1.00002", "4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
        put("1.00003", "9e08dcd7ce2c590cb8962832b6dbd5e929fd169d361d91bcd6612bb39d3b178b");
        put("3.00007", "72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9");
        put("3.00014", "ae6013a81c61813b3d31a0d54ca8948f9dac3753b433689f7023d41dfe284dc0");
        put("3.00016", "021094da1b80fbfdfbd2adc9bca933f848cd262c1bc8ff12c8b9af5f8bde4078");
        put("4.00090", "d4c430ebc03debd8a00e15c1fef36110368c314c03cc9a7aa586af7f9e55da87");
        put("4.00047", "611bddcb3209bdd81ca135a0087150030c4e446a8ac675c8cac13b4e54da2c92");
    }};


    @Autowired
    public HttpLogRepository httpLogRepository;

    public ConcurrentHashMap<String, Long> httpidMap = new ConcurrentHashMap<>();

    public final String url = initUrl();

    public abstract String initUrl();


    public abstract void initData();


    private Class<?> InitClass() {
        return this.getClass();
    }


    public abstract void destroyData();


    @BeforeClass
    public void excuteInitData() {
        initData();
        InitClass();
    }


    @AfterClass
    public void excutedestroyData() {
        destroyData();
    }


    @Override
    public String getFilePath(String filePath, Class<?> clz) {
        InputStream pictureInputStream = null;
        FileOutputStream fileOutputStream = null;
        ClassLoader baseClassLoader = null;
        try {
            StringBuilder basePathFile = new StringBuilder();
            basePathFile.append(clz.getPackage().getName()).append(".DataCase.");
            basePathFile.toString().replace(".", "/");
            baseClassLoader = clz.getClassLoader();
            pictureInputStream = baseClassLoader.getResourceAsStream(basePathFile.toString().replace(".", "/") + filePath);
            fileOutputStream = new FileOutputStream(baseClassLoader.getResource("").getPath() + filePath);
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = pictureInputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //不关闭流，会提示另一个程序正在使用此文件，进程无法访问
                pictureInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return baseClassLoader.getResource("").getPath() + filePath;
        }
    }

    @Override
    public String getFilePath(String filePath) {
        Class<?> clz = InitClass();
        ClassLoader baseClassLoader = null;
        InputStream pictureInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            StringBuilder basePathFile = new StringBuilder();
            basePathFile.append(clz.getPackage().getName()).append(".DataCase.");
            basePathFile.toString().replace(".", "/");
            baseClassLoader = clz.getClassLoader();
            pictureInputStream = baseClassLoader.getResourceAsStream(basePathFile.toString().replace(".", "/") + filePath);
            fileOutputStream = new FileOutputStream(baseClassLoader.getResource("").getPath() + filePath);
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = pictureInputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pictureInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return baseClassLoader.getResource("").getPath() + filePath;
        }
    }


    public void inserLog(Object obj, Long httpId) {
        String ClassAndMethodName = this.getClass().getName() + "." + obj.getClass().getEnclosingMethod().getName();
        httpidMap.put(ClassAndMethodName, httpId);
        CustomReport.setHttpidMap(httpidMap);
    }


    public void inserLog(Object obj, Long httpId, String key) {
        String ClassAndMethodName = this.getClass().getName() + "." + obj.getClass().getEnclosingMethod().getName();
        httpidMap.put(ClassAndMethodName, httpId);
        CustomReport.setHttpidMap(httpidMap);
        CustomReport.key = key;
    }


    public void initLog(HttpLog recordhttp, Object obj) {
        inserLog(obj, httpLogRepository.insert(recordhttp));
    }


    public void initLog(HttpLog recordhttp, Object obj, String key) {
        inserLog(obj, httpLogRepository.insert(recordhttp), key);
    }


    @Autowired
    private LoginTest loginTest;


    @Autowired
    private ELoginTest eLoginTest;



    public String Login(UserLoginBO userLoginBO) {
        return loginTest.LoginByGernal(userLoginBO);
    }


    public HashMap<String, String> userLoginTest() {
        UserLoginBO userLoginBO = new UserLoginBO();
        userLoginBO.setUname("wx_fbqejw0x");
        userLoginBO.setPasswd("123456");
        userLoginBO.setAppid("1.00002");
        userLoginBO.setKeytp("");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
//        keys.setInfo("bindNo1forOpenid");
//        keys.setNickname("NBind_openid_0812_01");
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq("abc");
        String userLoginResult = loginTest.LoginByGernal(userLoginBO);
        String token = JSON.parseObject(userLoginResult).getString("token");
        String uid = JSON.parseObject(userLoginResult).getString("uid");
        String sessionCode = JSON.parseObject(userLoginResult).getString("sessionCode");
        HashMap<String, String> hs = new HashMap<>();
        hs.put("token", token);
        hs.put("uid", uid);
        hs.put("sessionCode", sessionCode);
        hs.put("appid", userLoginBO.getAppid());
        return hs;
    }


    public HashMap<String, String> userLoginTest(UserLoginBO userLoginBO) {
        String userLoginResult = loginTest.LoginByGernal(userLoginBO);
        String token = JSON.parseObject(userLoginResult).getString("token");
        String uid = JSON.parseObject(userLoginResult).getString("uid");
        String sessionCode = JSON.parseObject(userLoginResult).getString("sessionCode");
        HashMap<String, String> hs = new HashMap<>();
        hs.put("token", token);
        hs.put("uid", uid);
        hs.put("sessionCode", sessionCode);
        hs.put("appid", userLoginBO.getAppid());
        return hs;
    }



    public HashMap<String, String> getuserExpireToken() throws Exception {
        String token = "Ij2kwlDffiMImBJ7rdi3wH9RJwHz6wCXHxmILI5KjsBdxT6wKILyThyOk3gQKAe5aiYw81OTcg8l5y3Cs4csGAi-N_Z2V3GZiUeAFsu8Xgr8ucE2tune_Jb0F3GSSpnA";
        String uid = "237671";
        HashMap<String, String> hs = new HashMap<>();
        hs.put("token", token);
        hs.put("uid", uid);
        return hs;
    }



    public String ELogin(EUserLoginBO eUserLoginBO) throws Exception {
        String eUserLoginResult = eLoginTest.eLogin(eUserLoginBO);
        return eUserLoginResult;
    }



    public HashMap<String, String> EUserLoginTest() throws Exception {
        HashMap<String, String> hs = new HashMap<>();
        EUserLoginBO eUserLoginBO = new EUserLoginBO();
        eUserLoginBO.setEuid(1186531925544669184L);
        eUserLoginBO.setEuname("yangyi");
        eUserLoginBO.setPasswd("yangyi");
        eUserLoginBO.setAppid("1.00002");
        String eUserLoginResult = eLoginTest.eLogin(eUserLoginBO);
        ELoginDTO eLoginDTO = JSON.parseObject(eUserLoginResult, ELoginDTO.class);
        hs.put("token", eLoginDTO.getToken());
        hs.put("euid", String.valueOf(eLoginDTO.getEuid()));
        log.info("eUserLoginResult 返回结果=" + eUserLoginResult);
        return hs;
    }



    public HashMap<String, String> EUserLoginTest(EUserLoginBO eUserLoginBO) throws Exception {
        HashMap<String, String> hs = new HashMap<>();
        String eUserLoginResult = eLoginTest.eLogin(eUserLoginBO);
        ELoginDTO eLoginDTO = JSON.parseObject(eUserLoginResult, ELoginDTO.class);
        hs.put("token", eLoginDTO.getToken());
        hs.put("euid", String.valueOf(eLoginDTO.getEuid()));
        log.info("eUserLoginResult 返回结果=" + eUserLoginResult);
        return hs;
    }



}
