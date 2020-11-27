package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.*;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddUserBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


@Slf4j
@Component
public class AddUserTest extends BaseTest {

    @Autowired
    private TUserRepository tUserRepository;

    @Autowired
    private TKeyindexRepository tKeyindexRepository;

    @Autowired
    private TUserkeysRepository tUserkeysRepository;

    @Autowired
    private SysLogRepository sysLogRepository;

    @Autowired
    private TAllUsersRepository tAllUsersRepository;

    @Autowired
    private LoginTest loginTest;

    /**
     * 一般通用性注册用户
     * @throws Exception
     */
    public String adduserTestByGeneral(String Uname,String Passwd) throws Exception {
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            Map<String,String> statistics=new HashMap<>();
            statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
            statistics.put("维度一","渠道编号");
            statistics.put("维度二","28");
            addUserBO.setStatistics(statistics);
            addUserBO.setUname(Uname);
            addUserBO.setPasswd(Passwd);
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean=new AddUserBO.KeysBean();
            keys.add(keysBean);
//            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一般通用性注册用户");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"result\":1"), true);
            Assert.assertEquals(adduserResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(adduserResult.contains("uid"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\""+Uname+"\""), true);
            Assert.assertEquals(adduserResult.contains("channelNo"), true);

            //判断新增用户后，流水是否存在
            SysLogExample example=new SysLogExample();
            SysLogExample.Criteria cr=example.createCriteria();
            Integer uid=JsonPath.read(adduserResult, "$.uid");
            cr.andUidEqualTo(new Long(uid));
            List<SysLog> sysLogList=sysLogRepository.selectByExample(example);
            Assert.assertTrue(sysLogList.size()==1);

            return adduserResult;
        }
    }

    /**
     * 增加用户账号，t_user表增加一条记录，其中参数keys不传且参数Statistics不传递,结果返回channelNo的值为空
     * @throws Exception
     */
    @Test
    public void adduserTestByAddNotExistUserAndNotExistParameterKeysAndNotExistParameterStatistics() throws Exception {
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
        String Uname="test123456";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            Map<String,String> statistics=new HashMap<>();
            statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
            statistics.put("维度一","渠道编号");
            statistics.put("维度二","28");
//            addUserBO.setStatistics(statistics);
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("test123456");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean=new AddUserBO.KeysBean();
            keys.add(keysBean);
//            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加用户账号，t_user表增加一条记录，其中参数keys不传且参数Statistics不传递,结果返回channelNo的值为空");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"result\":1"), true);
            Assert.assertEquals(adduserResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(adduserResult.contains("uid"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"test123456\""), true);
            Assert.assertEquals(adduserResult.contains("channelNo"), true);
            String channelNo=JsonPath.read(adduserResult, "$.channelNo");
            Assert.assertTrue(channelNo.trim().length()==0);
            //判断新增用户后，流水是否存在
            SysLogExample example=new SysLogExample();
            SysLogExample.Criteria cr=example.createCriteria();
            Integer uid=JsonPath.read(adduserResult, "$.uid");
            cr.andUidEqualTo(uid.longValue());
            cr.andOperateTimeGreaterThan(DateTool.reduceMilliseconds(new Date(),60000L));
            List<SysLog> sysLogList=sysLogRepository.selectByExample(example);
            Assert.assertTrue(sysLogList.size()==1);

            TUserExample tUserExampleChannelNo=new TUserExample();
            TUserExample.Criteria tUserExampleChannelNoCr=tUserExampleChannelNo.createCriteria();
            tUserExampleChannelNoCr.andUidEqualTo(uid.longValue());
            List<TUser> listChannelNo=tUserRepository.selectByExample(tUserExampleChannelNo);
            Assert.assertTrue(listChannelNo.get(0).getChannelNo().isEmpty());

        }
    }

    /**
     * 增加用户账号，t_user表增加一条记录，其中参数keys不传且参数Statistics传递,ChannelNo长度正常范围内
     * @throws Exception
     */
    @Test
    public void adduserTestByAddNotExistUserAndNotExistParameterKeysAndExistParameterStatisticsAndChannelNoIsShort() throws Exception {
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
        String Uname="test123456";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            Map<String,String> statistics=new HashMap<>();
            statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
            statistics.put("维度一","渠道编号");
            statistics.put("维度二","28");
            addUserBO.setStatistics(statistics);
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("test123456");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean=new AddUserBO.KeysBean();
            keys.add(keysBean);
//            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加用户账号，t_user表增加一条记录，其中参数keys不传且参数Statistics传递,ChannelNo长度正常范围内");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"result\":1"), true);
            Assert.assertEquals(adduserResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(adduserResult.contains("uid"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"test123456\""), true);
            Assert.assertEquals(adduserResult.contains("channelNo"), true);
            String channelNo=JsonPath.read(adduserResult, "$.channelNo");
            Assert.assertTrue(channelNo.trim().length()>0);
            //判断新增用户后，流水是否存在
            SysLogExample example=new SysLogExample();
            SysLogExample.Criteria cr=example.createCriteria();
            Integer uid=JsonPath.read(adduserResult, "$.uid");
            cr.andUidEqualTo(uid.longValue());
            cr.andOperateTimeGreaterThan(DateTool.reduceMilliseconds(new Date(),60000L));
            List<SysLog> sysLogList=sysLogRepository.selectByExample(example);
            Assert.assertTrue(sysLogList.size()==1);

            TUserExample tUserExampleChannelNo=new TUserExample();
            TUserExample.Criteria tUserExampleChannelNoCr=tUserExampleChannelNo.createCriteria();
            tUserExampleChannelNoCr.andUidEqualTo(uid.longValue());
            List<TUser> listChannelNo=tUserRepository.selectByExample(tUserExampleChannelNo);
            Assert.assertTrue(!listChannelNo.get(0).getChannelNo().isEmpty());

            TAllUsersExample tAllUsersExample=new TAllUsersExample();
            TAllUsersExample.Criteria tAllUsersExampleCr=tAllUsersExample.createCriteria();
            tAllUsersExampleCr.andUidEqualTo(uid.longValue());
            List<TAllUsers> list=tAllUsersRepository.selectByExample(tAllUsersExample);
            Assert.assertTrue(list.size()==1);
        }
    }

    /**
     * 增加用户账号，t_user表增加一条记录，其中参数keys不传且参数Statistics传递，ChannelNo长度超长
     * @throws Exception
     */
    @Test
    public void adduserTestByAddNotExistUserAndNotExistParameterKeysAndExistParameterStatisticsAndChannelNoIsLong() throws Exception {
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
        String Uname="test123456";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            Map<String,String> statistics=new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo",imgParam);
            statistics.put("维度一","渠道编号");
            statistics.put("维度二","28");
            addUserBO.setStatistics(statistics);
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("test123456");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean=new AddUserBO.KeysBean();
            keys.add(keysBean);
//            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加用户账号，t_user表增加一条记录，其中参数keys不传且参数Statistics传递，ChannelNo长度超长");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"result\":100"), true);
            Assert.assertEquals(!adduserResult.contains("uid"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"test123456\""), true);
        }
    }


    /**
     * 增加用户账号，t_user表增加一条记录，其中参数keys传递,其中keys为一个数组
     * keys为一个数组，则t_userkeys表增加一条记录
     * @throws Exception
     */
    @Test
    public void adduserTestByAddNotExistUserAndExistParameterKeys1() throws Exception {
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
        String Uname="test123456";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            Map<String,String> statistics=new HashMap<>();
            statistics.put("channelNo","微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
            statistics.put("维度一","渠道编号");
            statistics.put("维度二","28");
            addUserBO.setStatistics(statistics);
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("test123456");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加用户账号，user表增加一条记录，其中参数keys传递,其中keys为一个数组");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"result\":1"), true);
            Assert.assertEquals(adduserResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(adduserResult.contains("uid"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"test123456\""), true);
        }
    }

    /**
     * 增加用户账号，t_user表增加一条记录，其中参数keys传递,其中keys为2个数组
     * keys为2个数组，则t_userkeys表增加2条记录
     * 其中keysBean1属性deviceid在表t_keyinfo.unique=1就需要建立反向索引;其中keysBean2属性Unionid在表t_keyinfo.unique=1就需要建立反向索引,因此t_keyindex表增加2条记录
     * @throws Exception
     */
    @Test
    public void adduserTestByAddNotExistUserAndExistParameterKeys2() throws Exception {
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
        String Uname="test123456";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("test123456");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            AddUserBO.KeysBean keysBean2=new AddUserBO.KeysBean();
            keysBean2.setUnionid("Unionid13093863511");
            keysBean2.setInfo("测试使用");
            keys.add(keysBean2);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加用户账号，t_user表增加一条记录，其中参数keys传递,其中keys为2个数组");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"result\":1"), true);
            Assert.assertEquals(adduserResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(adduserResult.contains("uid"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"test123456\""), true);
        }
    }


    /**
     * 重复增加已经存在的用户
     * @throws Exception
     */
    @Test
    public void adduserTestByAddExistUser() throws Exception {
        adduserTestByAddNotExistUserAndNotExistParameterKeysAndNotExistParameterStatistics();
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
        String Uname="test123456";
        TUserExample tUserExample=new TUserExample();
        TUserExample.Criteria tUserCr=tUserExample.createCriteria();
        tUserCr.andUnameEqualTo(Uname);
        List<TUser> tUserList=tUserRepository.selectByExample(tUserExample);
        List<TKeyindex> tKeyindexList=new ArrayList<>();
        List<TUserkeys> tUserkeyList=new ArrayList<>();
        TKeyindexExample tKeyindexExample=null;
        TUserkeysExample tUserkeysExample=null;
        if(tUserList.size()>0){
            try {
                adduserUrl = url + "/AccService/adduser";
                addUserBO = new AddUserBO();
                addUserBO.setUname(Uname);
                addUserBO.setPasswd("test123456");
                addUserBO.setAuto(false);
                addUserBO.setPrefix("guest");
                List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
                AddUserBO.KeysBean keysBean=new AddUserBO.KeysBean();
                keys.add(keysBean);
//            addUserBO.setKeys(keys);
                addUserBO.setAppid("1.00002");
                log.info("adduserUrl 请求的参数=" + adduserUrl);
                log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
                adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
                log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("重复增加已经存在的用户");
                recordhttp.setUrl(adduserUrl);
                recordhttp.setRequest(JSON.toJSONString(addUserBO));
                recordhttp.setResponse(adduserResult);
                initLog(recordhttp, new Object() {});
                Assert.assertEquals(adduserResult.contains("\"msg\":\"user_exist\""), true);
                Assert.assertEquals(adduserResult.contains("\"result\":103"), true);
                Assert.assertEquals(adduserResult.contains("uid"), true);
                Assert.assertEquals(adduserResult.contains("\"uname\":\"test123456\""), true);
            }
        }
    }

    /**
     * uname不允许填入手机号校验
     * @throws Exception
     */
    @Test
    public void adduserTestByParameterUnameValueIsPhone() throws Exception {
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
        String Uname="13093863511";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("13093863511");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("uname不允许填入手机号校验");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(adduserResult.contains("\"result\":101"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"13093863511\""), true);
        }
    }

    /**
     * uname不允许填入邮箱校验
     * @throws Exception
     */
    @Test
    public void adduserTestByParameterUnameValueIsEmail() throws Exception {
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
        String Uname="13093863511@qq.com";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("13093863511");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("uname不允许填入邮箱校验");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(adduserResult.contains("\"result\":101"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"13093863511@qq.com\""), true);
        }
    }

    /**
     * uname填入表情
     * @throws Exception
     */
    @Test
    public void adduserTestByParameterUnameValueIsEmoji() throws Exception {
        String Uname="😂😂😂😂✌✌✌✌";
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("13093863511");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("uname填入表情");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(adduserResult.contains("\"result\":1"), true);
            Assert.assertEquals(adduserResult.contains("uid"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"😂😂😂😂✌✌✌✌\""), true);
        }
    }

    /**
     * uname超过20个字符
     * @throws Exception
     */
    @Test
    public void adduserTestByParameterUnameValueIsLarge() throws Exception {
        String Uname="021MUOZp0I2pak13FkYp0ckWZp0MUOZn";
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("13093863511");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("uname超过20个字符");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"result\":101"), true);
            Assert.assertEquals(adduserResult.contains("\"msg\":\"parameter_error\""), true);
        }
    }

    /**
     * uname是中文
     * @throws Exception
     */
    @Test
    public void adduserTestByParameterUnameValueIsChinese() throws Exception {
        String Uname="测试使用测试使用";
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("13093863511");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("uname是中文");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(adduserResult.contains("\"result\":1"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"测试使用测试使用\""), true);
            Assert.assertEquals(adduserResult.contains("uid"), true);
        }
    }

    /**
     * 参数NormalUpperLimit设置为5，增加用户提示到达用户上限
     * @throws Exception
     */
    @Test
    public void adduserTestByParameterNormalUpperLimitAndReacheUpperLimit() throws Exception {
        String Uname="测试使用测试使用";
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("13093863511");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            loginTest.modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(addUserBO.getAppid(), null, null, 5);
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loginTest.modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(addUserBO.getAppid(), null, null, 0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数NormalUpperLimit设置为5，增加用户提示到达用户上限");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"msg\":\"user reaches upper limit\""), true);
            Assert.assertEquals(adduserResult.contains("\"result\":139"), true);
        }
    }


    /**
     * uname是特殊字符串
     * @throws Exception
     */
    @Test
    public void adduserTestByParameterUnameValueIsSpecialStr() throws Exception {
        String Uname="~`!#$%^*()+=}[|;'?";
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("13093863511");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("uname是特殊字符串");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(adduserResult.contains("\"result\":1"), true);
            Assert.assertEquals(adduserResult.contains("\"uname\":\"~`!#$%^*()+=}[|;'?\""), true);
            Assert.assertEquals(adduserResult.contains("uid"), true);
        }
    }

    /**
     * 自动创建账号,前缀是guest开头
     * @throws Exception
     */
    @Test
    public void adduserTestByAutoCreateUser() throws Exception {
        String Uname="~`!#$%^*()+=}[|;'?";
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
//            addUserBO.setUname(Uname);
//            addUserBO.setPasswd("13093863511");
            addUserBO.setAuto(true);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("自动创建账号,前缀是guest开头");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(adduserResult.contains("\"result\":1"), true);
            Assert.assertEquals(adduserResult.contains("uname"), true);
            Assert.assertEquals(adduserResult.contains("uid"), true);
        }
    }

    /**
     * 自动创建账号,前缀非guest开头
     * @throws Exception
     */
    @Test
    public void adduserTestByAutoCreateUser1() throws Exception {
        String Uname="~`!#$%^*()+=}[|;'?";
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
//            addUserBO.setUname(Uname);
//            addUserBO.setPasswd("13093863511");
            addUserBO.setAuto(true);
            addUserBO.setPrefix("yy");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("自动创建账号");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(adduserResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数passwd的值超过20个字符，不在规定的6~20个字符内
     * @throws Exception
     */
    @Test
    public void adduserTestByParameterPassWdValueIsLarge() throws Exception {
        String Uname="test123456";
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("021MUOZp0I2pak13FkYp0ckWZp0MUOZn");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数passwd的值超过20个字符，不在规定的6~20个字符内");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"result\":101"), true);
            Assert.assertEquals(adduserResult.contains("\"msg\":\"parameter_error\""), true);
        }
    }

    /**
     * 参数passwd的值太短为3个字符,不在规定的6~20个字符内
     * @throws Exception
     */
    @Test
    public void adduserTestByParameterPassWdValueIsShort() throws Exception {
        String Uname="test123456";
        String adduserUrl = null;
        AddUserBO addUserBO = null;
        String adduserResult = "";
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
            adduserUrl = url + "/AccService/adduser";
            addUserBO = new AddUserBO();
            addUserBO.setUname(Uname);
            addUserBO.setPasswd("021");
            addUserBO.setAuto(false);
            addUserBO.setPrefix("guest");
            List<AddUserBO.KeysBean> keys=new ArrayList<AddUserBO.KeysBean>();
            AddUserBO.KeysBean keysBean1=new AddUserBO.KeysBean();
            keysBean1.setDeviceid("Deviceid13093863511");
            keysBean1.setInfo("测试使用");
            keys.add(keysBean1);
            addUserBO.setKeys(keys);
            addUserBO.setAppid("1.00002");
            log.info("adduserUrl 请求的参数=" + adduserUrl);
            log.info("addUserBO 请求的参数=" + JSON.toJSONString(addUserBO));
            adduserResult = HttpUtil.postGeneralUrl(adduserUrl, "application/json", JSON.toJSONString(addUserBO), "UTF-8");
            log.info("adduserResult 返回结果=" + JSON.parseObject(adduserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数passwd的值太短为3个字符,不在规定的6~20个字符内");
            recordhttp.setUrl(adduserUrl);
            recordhttp.setRequest(JSON.toJSONString(addUserBO));
            recordhttp.setResponse(adduserResult);
            initLog(recordhttp, new Object() {});
            Assert.assertEquals(adduserResult.contains("\"result\":101"), true);
            Assert.assertEquals(adduserResult.contains("\"msg\":\"parameter_error\""), true);
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
