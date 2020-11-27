package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 16:27
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/22日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.SysAccFlow;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.*;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.KfkServiceBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.LogOutBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserBindBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IResource;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.encr.MD5Utils;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;


@Component
@Slf4j
public class KfkserviceTest extends BaseTest implements IResource {

    private final static String topic="acc_userloginout_flow";


    @Autowired
    private ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private TUserRepository tUserRepository;

    @Autowired
    private TKeyindexRepository tKeyindexRepository;

    @Autowired
    private TUserkeysRepository tUserkeysRepository;

    @Autowired
    private LogOutTest logOutTest;

    @Autowired
    private LoginTest loginTest;

    @Autowired
    private AppidRepository appidRepository;

    @Autowired
    private UserBindTest userBindTest;

    @Autowired
    private SysAccFlowRepository sysAccFlowRepository;



    /**
     * 使用普通的用户名与密码登录，记录流水
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByUnameAndPasswdLogin() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("cacc_8rhbukbq");

        userLoginBO.setPasswd("123456");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("test");
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用普通的用户名与密码登录");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);


        Map<String,String> hs=null;
        String userloginResult=sendLogin(userLoginBO);

        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);

        String caseDescribe="使用普通的用户名与密码登录，记录流水";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(2));
        }
    }

    /**
     * 使用token登录，记录流水
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByTokenLogin() throws Exception {
        ;
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
//        String token="rIjPEyuKPs7YUIavh-pE3eqA8C7IBFJg1uX7nDd4GfAJbAwbtc7zCkXH0IATVxDJ6aNXxVryLfJ9cnweWd8PaytGInj-LicpSkmisMN_Pcni6vU2QfhwIMccc5p6VSGa";
        userLoginBO.setUname(getToken());
//        userLoginBO.setUname(token);
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("token");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("test");
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用token登录");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);

        Map<String,String> hs=null;
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);

        String caseDescribe="使用token登录，记录流水";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(3));
        }
    }

    /**
     * 使用Cacc登录，记录流水
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByKeytpValueIsCaccLogin() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Cacc_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("cacc");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("test");
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用cacc进行登录");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);


        Map<String,String> hs=null;
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);
        String caseDescribe="使用Cacc登录，记录流水";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(1));
        }
    }

    /**
     * 使用Deviceid登录，记录流水
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByKeytpValueIsDeviceidLogin() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Deviceid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("deviceid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("test");
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用Deviceid进行登录");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);


        Map<String,String> hs=null;
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);
        String caseDescribe="使用Deviceid登录，记录流水";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(0));
        }
    }

    /**
     * 使用Openid登录，记录流水
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByKeytpValueIsOpenidLogin() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Openid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("openid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("test");
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用Openid进行登录");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);


        Map<String,String> hs=null;
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);
        String caseDescribe="使用Openid登录，记录流水";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(4));
        }
    }


    /**
     * 使用Unionid登录，记录流水,参数Statistics传递，则上报统计系统
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByKeytpValueIsUnionidLoginAndParameterStatisticsValueIsExist() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Unionid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("test");
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);

        Map<String,String> hs=null;
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);
        String caseDescribe="使用Unionid登录，记录流水,参数Statistics传递，则上报统计系统";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(5));
        }
    }


    /**
     * 使用Unionid登录，记录流水,参数Statistics传递，channelNo太长，流水记录失败
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByKeytpValueIsUnionidLoginAndParameterStatisticsValueIsLong() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Unionid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("test");
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();

        String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
        statistics.put("channelNo",imgParam);
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);

        Map<String,String> hs=null;
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在,channelNo太长，流水记录失败
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()<=0);
        String caseDescribe="使用Unionid登录，记录流水,参数Statistics传递，channelNo太长，流水记录失败";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            channelNo太长，流水记录失败,不进行校验
        }
    }


    /**
     * 使用Unionid登录，记录流水,参数Statistics不传，则也上报统计系统
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByKeytpValueIsUnionidLoginAndParameterStatisticsValueIsNotExist() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Unionid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("test");
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
//        userLoginBO.setStatistics(statistics);

        Map<String,String> hs=null;
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);
        String caseDescribe="使用Unionid登录，记录流水,参数Statistics不传，则也上报统计系统";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(5));
        }
    }

    /**
     * 使用openid进行登录,appid=1.00002---->appid=1.00003,记录流水
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByKeytpValueIsUnionidLoginAndChangeAppid() throws Exception {
        UserBindBO userbindBO = new UserBindBO();
        userbindBO.setAppid("1.00002");
        userbindBO.setUname("cacc_8rhbukbq");
        List<UserBindBO.KeysBean> serbindKeys=new ArrayList<>();
        UserBindBO.KeysBean KeysBean6 = new UserBindBO.KeysBean();
        KeysBean6.setOpenid("Openid_yy");
        serbindKeys.add(KeysBean6);
        userbindBO.setKeys(serbindKeys);
        userBindTest.userbindTestByGeneral(userbindBO);
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Openid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00003");//
        userLoginBO.setKeytp("openid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用openid进行登录,appid=1.00002---->appid=1.00003");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);

        Map<String,String> hs=null;
        //设置appid=1.00002与appid=1.00003属于同一个企业下同一个分组
        changeEuidAndGroupTagByAppid();
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);
        String caseDescribe="使用openid进行登录,appid=1.00002---->appid=1.00003,记录流水";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            restoreEuidAndGroupTagByAppid();
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(4));
        }
    }


    /**
     * 新增用户注册，增加流水记录,登录携带参数statistics且不为空
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByAddNewUserAndParameterStatisticsNotEmpty() throws Exception {
        String logoutResult=null;
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Unionid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00003");//
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);
        Map<String,String> hs=null;

        //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
        restoreEuidAndGroupTagByAppid();
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);
        String caseDescribe="新增用户注册，增加流水记录,登录携带参数statistics且不为空";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            logoutResult=sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //新增用户数据删除
            Integer uidStr=(Integer)JSON.parseObject(logoutResult).get("uid");
            Long uid=Long.valueOf(uidStr);
            TUserExample tUserExample=new TUserExample();
            TUserExample.Criteria tUserExamplecr=tUserExample.createCriteria();
            tUserExamplecr.andUidEqualTo(uid);
            List<TUser> listTUser=tUserRepository.selectByExample(tUserExample);
            //新增用户登录时候携带参数statistics，则Channelno不为空
            Assert.assertNotNull(listTUser.get(0).getChannelNo());
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(5));

            tUserRepository.deleteByExample(tUserExample);

            TKeyindexExample tKeyindexExample=new TKeyindexExample();
            TKeyindexExample.Criteria tKeyindexExamplecr=tKeyindexExample.createCriteria();
            tKeyindexExamplecr.andUidEqualTo(uid);
            tKeyindexRepository.deleteByExample(tKeyindexExample);

            TUserkeysExample tUserkeysExample=new TUserkeysExample();
            TUserkeysExample.Criteria tUserkeysExamplecr=tUserkeysExample.createCriteria();
            tUserkeysExamplecr.andUidEqualTo(uid);
            tUserkeysRepository.deleteByExample(tUserkeysExample);
        }
    }


    /**
     * 新增用户注册，增加流水记录,登录携带参数statistics且不为空
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByAddNewUserAndParameterStatisticsNotEmptyAndChannelNoIsLong() throws Exception {
        String logoutResult=null;
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Unionid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00003");//
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
        statistics.put("channelNo",imgParam);
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);
        Map<String,String> hs=null;

        //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
        restoreEuidAndGroupTagByAppid();
        String userloginResult=Login(userLoginBO);
        //新增失败断言
        Assert.assertEquals(userloginResult.contains("uname"),true);
        Assert.assertEquals(userloginResult.contains("\"result\":100"),true);
        if(userloginResult.contains("newUser") && userloginResult.contains("\"msg\":\"\"")){
            //判断登录流水是否存在
            SysAccFlowExample example=new SysAccFlowExample();
            SysAccFlowExample.Criteria cr=example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size()==1);
            String caseDescribe="新增用户注册，增加流水记录,登录携带参数statistics且不为空";
            try{
                //用户在线时间
                for (int i = 0; i < 2; i++) {
                    hs=getMsg(userloginResult);
                    sendHeartBeats(hs,caseDescribe);
                    Thread.sleep(10000);
                }
                //用户退出登录
                LogOutBO logOutBO = new LogOutBO();
                logOutBO.setAppid(hs.get("appid"));
                logOutBO.setUid(Long.valueOf(hs.get("uid")));
                logOutBO.setSessionCode(hs.get("sessionCode"));
                logOutBO.setSeq(hs.get("sessionCode"));
                logoutResult=sendLogout(logOutBO);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //新增用户数据删除
                Integer uidStr=(Integer)JSON.parseObject(logoutResult).get("uid");
                Long uid=Long.valueOf(uidStr);
                TUserExample tUserExample=new TUserExample();
                TUserExample.Criteria tUserExamplecr=tUserExample.createCriteria();
                tUserExamplecr.andUidEqualTo(uid);
                List<TUser> listTUser=tUserRepository.selectByExample(tUserExample);
                //新增用户登录时候携带参数statistics，则Channelno不为空
                Assert.assertNotNull(listTUser.get(0).getChannelNo());
                SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
                SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
                sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
                List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
                Assert.assertTrue(sysAccFlowListQuery.size()==1);
                Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(5));

                tUserRepository.deleteByExample(tUserExample);

                TKeyindexExample tKeyindexExample=new TKeyindexExample();
                TKeyindexExample.Criteria tKeyindexExamplecr=tKeyindexExample.createCriteria();
                tKeyindexExamplecr.andUidEqualTo(uid);
                tKeyindexRepository.deleteByExample(tKeyindexExample);

                TUserkeysExample tUserkeysExample=new TUserkeysExample();
                TUserkeysExample.Criteria tUserkeysExamplecr=tUserkeysExample.createCriteria();
                tUserkeysExamplecr.andUidEqualTo(uid);
                tUserkeysRepository.deleteByExample(tUserkeysExample);
            }
        }
    }


    /**
     * 新增用户注册，增加流水记录,登录携带参数statistics且值为空
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByAddNewUserAndParameterStatisticsValueIsEmpty() throws Exception {
        String logoutResult=null;
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Unionid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00003");//
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
//        statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
//        statistics.put("维度一","渠道编号");
//        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);
        Map<String,String> hs=null;

        //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
        restoreEuidAndGroupTagByAppid();
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);
        String caseDescribe="新增用户注册，登录携带参数statistics且值为空";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            logoutResult=sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //新增用户数据删除
            Integer uidStr=(Integer)JSON.parseObject(logoutResult).get("uid");
            Long uid=Long.valueOf(uidStr);
            TUserExample tUserExample=new TUserExample();
            TUserExample.Criteria tUserExamplecr=tUserExample.createCriteria();
            tUserExamplecr.andUidEqualTo(uid);
            List<TUser> listTUser=tUserRepository.selectByExample(tUserExample);
            //新增用户登录时候携带参数statistics，则Channelno为空字符串
            Assert.assertTrue(listTUser.get(0).getChannelNo().equals(""));
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(5));

            tUserRepository.deleteByExample(tUserExample);

            TKeyindexExample tKeyindexExample=new TKeyindexExample();
            TKeyindexExample.Criteria tKeyindexExamplecr=tKeyindexExample.createCriteria();
            tKeyindexExamplecr.andUidEqualTo(uid);
            tKeyindexRepository.deleteByExample(tKeyindexExample);

            TUserkeysExample tUserkeysExample=new TUserkeysExample();
            TUserkeysExample.Criteria tUserkeysExamplecr=tUserkeysExample.createCriteria();
            tUserkeysExamplecr.andUidEqualTo(uid);
            tUserkeysRepository.deleteByExample(tUserkeysExample);
        }
    }


    /**
     * 新增用户注册，增加流水记录,登录不携带参数statistics
     * @throws Exception
     */
    @Test
    public void KfkserviceTestByAddNewUserAndParameterStatisticsNotExist() throws Exception {
        String logoutResult=null;
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Unionid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00003");//
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
//        userLoginBO.setStatistics(statistics);
        Map<String,String> hs=null;

        //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
        restoreEuidAndGroupTagByAppid();
        String userloginResult=sendLogin(userLoginBO);
        //判断登录流水是否存在
        SysAccFlowExample example=new SysAccFlowExample();
        SysAccFlowExample.Criteria cr=example.createCriteria();
        cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
        List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(example);
        Assert.assertTrue(sysAccFlowList.size()==1);
        String caseDescribe="新增用户注册，登录不携带参数statistics";
        try{
            //用户在线时间
            for (int i = 0; i < 2; i++) {
                hs=getMsg(userloginResult);
                sendHeartBeats(hs,caseDescribe);
                Thread.sleep(10000);
            }
            //用户退出登录
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(hs.get("appid"));
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSessionCode(hs.get("sessionCode"));
            logOutBO.setSeq(hs.get("sessionCode"));
            logoutResult=sendLogout(logOutBO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //新增用户数据删除
            Integer uidStr=(Integer)JSON.parseObject(logoutResult).get("uid");
            Long uid=Long.valueOf(uidStr);
            TUserExample tUserExample=new TUserExample();
            TUserExample.Criteria tUserExamplecr=tUserExample.createCriteria();
            tUserExamplecr.andUidEqualTo(uid);
            List<TUser> listTUser=tUserRepository.selectByExample(tUserExample);
            //新增用户登录时候携带参数statistics，则Channelno为空字符串
            Assert.assertTrue(listTUser.get(0).getChannelNo().equals(""));
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            SysAccFlowExample.Criteria sysAccFlowExampleCr=sysAccFlowExample.createCriteria();
            sysAccFlowExampleCr.andSessionCodeEqualTo(hs.get("sessionCode"));
            List<SysAccFlow> sysAccFlowListQuery=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowListQuery.size()==1);
            Assert.assertTrue(sysAccFlowListQuery.get(0).getLoginType().equals(5));

            tUserRepository.deleteByExample(tUserExample);

            TKeyindexExample tKeyindexExample=new TKeyindexExample();
            TKeyindexExample.Criteria tKeyindexExamplecr=tKeyindexExample.createCriteria();
            tKeyindexExamplecr.andUidEqualTo(uid);
            tKeyindexRepository.deleteByExample(tKeyindexExample);

            TUserkeysExample tUserkeysExample=new TUserkeysExample();
            TUserkeysExample.Criteria tUserkeysExamplecr=tUserkeysExample.createCriteria();
            tUserkeysExamplecr.andUidEqualTo(uid);
            tUserkeysRepository.deleteByExample(tUserkeysExample);
        }
    }



    private String sendLogin(UserLoginBO userLoginBO) throws Exception {
        String userloginResult=loginTest.LoginByGernal(userLoginBO);
        return userloginResult;
    }


    /**
     * 用于token登录使用
     * @return
     * @throws Exception
     */
    private String getToken() throws Exception {
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("cacc_8rhbukbq");

        userLoginBO.setPasswd("123456");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("test");
        keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq("abc");
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
//        userLoginBO.setStatistics(statistics);
        String userloginResult=loginTest.LoginByGernal(userLoginBO);
        return (String)JSON.parseObject(userloginResult).get("token");
    }

    private String sendHeartBeats(Map<String,String> hs,String caseDescribe) throws Exception {
        String kfkserviceUrl =null;
        KfkServiceBO kfkServiceBO =null;
        String kfkserviceResult ="";
        try {
            kfkserviceUrl = url+"/AccService/kfkservice";
            kfkServiceBO = new KfkServiceBO();
            kfkServiceBO.setAppid(hs.get("appid"));
            kfkServiceBO.setMsg(hs.get("msg"));
            kfkServiceBO.setSign(hs.get("sign"));
            kfkServiceBO.setTopic(topic);
            log.info("kfkserviceUrl 请求的参数=" + kfkserviceUrl);
            log.info("kfkServiceBO 请求的参数=" + JSON.toJSONString(kfkServiceBO));
            kfkserviceResult = HttpUtil.postGeneralUrl(kfkserviceUrl, "application/json", JSON.toJSONString(kfkServiceBO), "UTF-8");
            log.info("kfkserviceResult 返回结果=" + JSON.parseObject(kfkserviceResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe(caseDescribe);
            recordhttp.setUrl(kfkserviceUrl);
            recordhttp.setRequest(JSON.toJSONString(kfkServiceBO));
            recordhttp.setResponse(kfkserviceResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(kfkserviceResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(kfkserviceResult.contains("\"result\":1"),true);
            Thread.sleep(10000);
        }
        return kfkserviceResult;
    }




    /**msg的内容格式
     * {\"sessionCode\":\"11716f16-a54c-4961-b03e-212b16e96a71\",\"UID\":235225,\"appid\":\"1.00005\",\"loginType\":\"login\",\"heartbeatsTime\":1593672920,\"seq\":\"10cd85b4-8333-422c-9bsd1-45955zc\",\"partitionKey\":\"11716f16-a54c-4961-b03e-212b16e96a71\"}

     请求字段	类型	最大长度(字符)	是否必填	字段说明
     sessionCode	string		是	批次code，同一组登录登出相同，同时用于kafka推入同一个分区，尽量保证数据分区有序
     uid	Int64		是	用户id
     appid	string		是	调用方应用id
     loginType	String		是	loginType = login
     heartbeatsTime	int64		是	在线当前时间戳
     seq	string		是	kafka每条记录唯一标识,建议用uuid
     partitionKey	String		是	kafka分区key，用户把消息同一分区
     */
    private Map<String,String> getMsg(String userloginResult){
        MsgBean msgBean=null;
        try {
            Assert.assertEquals(userloginResult.contains("keys"),true);
            Assert.assertEquals(userloginResult.contains("value"),true);
            Assert.assertEquals(userloginResult.contains("appid"),true);
            Assert.assertEquals(userloginResult.contains("modifyTime"),true);
            Assert.assertEquals(userloginResult.contains("newUser"),true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"),true);
            Assert.assertEquals(userloginResult.contains("token"),true);
            Assert.assertEquals(userloginResult.contains("uid"),true);
            Assert.assertEquals(userloginResult.contains("uname"),true);
        }catch (Exception e){
            e.printStackTrace();
        }
        String sessionCode=JSON.parseObject(userloginResult).getString("sessionCode");
        String uid=JSON.parseObject(userloginResult).getString("uid");
        UserBindBO.KeysBean keysBean=JSON.parseObject(userloginResult,UserBindBO.class).getKeys().get(0);
        String OpenidAppid=JSON.parseObject(keysBean.getOpenid(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
        UUID uUID=UUID.randomUUID();
        msgBean=new MsgBean();
        msgBean.setAppid(OpenidAppid);
        msgBean.setHeartbeatsTime(System.currentTimeMillis()/1000);
        msgBean.setLoginType("login");
        msgBean.setPartitionKey(sessionCode);
        msgBean.setSessionCode(sessionCode);
        msgBean.setSeq(uUID.toString());
        msgBean.setUid(Integer.valueOf(uid));
        Map<String,String> hs=new HashMap<>();
        hs.put("msg",JSON.toJSONString(msgBean));
        hs.put("uid",uid);
        hs.put("sessionCode",sessionCode);
        hs.put("appid",OpenidAppid);
        AppidExample example=new AppidExample();
        example.createCriteria().andIdentityEqualTo(OpenidAppid);
        List<Appid> appidList=appidRepository.selectByExample(example);
        hs.put("sign",MD5Utils.getMD5(JSON.toJSONString(msgBean)+appidList.get(0).getAppkey(),"utf-8"));
        return hs;
    }


    private String sendLogout(LogOutBO logOutBO)throws Exception{
        return logOutTest.logoutTestByGernal(logOutBO);
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }


    @Override
    public void initData() {
        try{
            userBindTest.userbindTestByGeneral("cacc_8rhbukbq");
        }catch (Exception e){
            e.printStackTrace();
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
    public static class MsgBean {
        private String sessionCode;
        private String appid;
        private String loginType;
        private String seq;
        private String partitionKey;
        private Integer uid;
        private Long heartbeatsTime;
    }

    /**
     * 修改appid=1.00002的GroupTag与Euid与appid=1.00003一样的GroupTag与Euid值
     */
    private void changeEuidAndGroupTagByAppid() throws IOException, InterruptedException {
        Appid record=new Appid();
        record.setEuid(1115813658564235264L);
        record.setGroupTag("g35");
        AppidExample example=new AppidExample();
        AppidExample.Criteria cr=example.createCriteria();
        cr.andIdEqualTo(1105700271196999680L);
        appidRepository.updateByExampleSelective(record,example);
        //修改完appid的配置，需要重新启动下服务使得配置生效
        applicationServiceManaged.restartAccPid();
    }

    /**
     * 恢复appid=1.00002的GroupTag与Euid默认值
     */
    private void restoreEuidAndGroupTagByAppid() throws IOException, InterruptedException {
        Appid record=new Appid();
        record.setEuid(1103539011445592064L);
        record.setGroupTag("1.00002yy81");
        AppidExample example=new AppidExample();
        AppidExample.Criteria cr=example.createCriteria();
        cr.andIdEqualTo(1105700271196999680L);
        appidRepository.updateByExampleSelective(record,example);
        //修改完appid的配置，需要重新启动下服务使得配置生效
        applicationServiceManaged.restartAccPid();

    }


    @Test
    public void test1() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("Unionid_yy");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setOpenid("Openid_yy");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String,String> statistics=new HashMap<>();
        statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
        statistics.put("维度一","渠道编号");
        statistics.put("维度二","28");
        userLoginBO.setStatistics(statistics);
        String userloginResult=sendLogin(userLoginBO);
        String test=JSON.toJSONString(getMsg(userloginResult));

        String msg = JsonPath.read(test, "$.msg");
        String partitionKey =JsonPath.read(msg, "$.partitionKey");


        String yy1="{ \"store\": {\n" +
                "    \"book\": [\n" +
                "      { \"category\": \"reference\",\n" +
                "        \"author\": \"Nigel Rees\",\n" +
                "        \"title\": \"Sayings of the Century\",\n" +
                "        \"price\": 8.95\n" +
                "      },\n" +
                "      { \"category\": \"fiction\",\n" +
                "        \"author\": \"Evelyn Waugh\",\n" +
                "        \"title\": \"Sword of Honour\",\n" +
                "        \"price\": 12.99,\n" +
                "        \"isbn\": \"0-553-21311-3\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"bicycle\": {\n" +
                "      \"color\": \"red\",\n" +
                "      \"price\": 19.95\n" +
                "    }\n" +
                "  }\n" +
                "}";
        List<Object> yy2 = JsonPath.read(yy1, "$.store.book");
    }
}
