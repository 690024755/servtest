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
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.DelAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.DelUserBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.GetAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import io.github.sskorol.core.DataSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;


@Component
@Slf4j
public class GetAppidTest extends BaseTest {
    private final static AddAppidBO addAppidBO =new AddAppidBO();;

    @Autowired
    private AddAppidTest addAppidTest;

    @Autowired
    private DelAppidTest delAppidTest;

    @Autowired
    private AppidRepository appidRepository;



    public String getAppidTestByGernal(String host, String port,String bmAppid) throws Exception {
        StringBuilder getappidUrl=new StringBuilder("http://").append(host).append(":").append(port).append("/AccService/getappid");
        GetAppidBO getAppidBO = new GetAppidBO();
        getAppidBO.setAppid("1.00002");//通过哪个接口带有方法getappid进行请求
        getAppidBO.setBmAppid(bmAppid);
        log.info("getappidUrl 请求的参数=" + getappidUrl.toString());
        log.info("getAppidBO 请求的参数=" + JSON.toJSONString(getAppidBO));
        String getappidResult = HttpUtil.postGeneralUrl(getappidUrl.toString(), "application/json", JSON.toJSONString(getAppidBO), "UTF-8");
        log.info("getappidResult 返回结果=" + getappidResult);
        return getappidResult;
    }


    /**
     * 获取已存在的一条appid记录
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void getAppidTestByExistAppidRecord(final GetAppidBO getAppidBO) throws Exception {
        String getappidUrl =null;
        String getappidResult ="";
        try{
            getappidUrl = url+"/AccService/getappid";
            log.info("getappidUrl 请求的参数=" + getappidUrl);
            log.info("getAppidBO 请求的参数=" + JSON.toJSONString(getAppidBO));
            getappidResult = HttpUtil.postGeneralUrl(getappidUrl, "application/json", JSON.toJSONString(getAppidBO), "UTF-8");
            log.info("getappidResult 返回结果=" + getappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取已存在的一条appid记录");
            recordhttp.setUrl(getappidUrl);
            recordhttp.setRequest(JSON.toJSONString(getAppidBO));
            recordhttp.setResponse(getappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getappidResult.contains("appkey"),true);
            Assert.assertEquals(getappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(getappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(getappidResult.contains("\"callbackUrl\":\"http://test.galaxyeye.xyz/\""),true);
            Assert.assertEquals(getappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(getappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(getappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(getappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(getappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(getappidResult.contains("info"),true);
            Assert.assertEquals(getappidResult.contains("openMethods"),true);
            Assert.assertEquals(getappidResult.contains("authMethods"),true);
            Assert.assertEquals(getappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(getappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 获取不存在的一条appid记录
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void getAppidTestByNotExistAppidRecord(final GetAppidBO getAppidBO) throws Exception {
        String getappidUrl =null;
        String getappidResult ="";
        destroyData();
        try{
            getappidUrl = url+"/AccService/getappid";
            log.info("getappidUrl 请求的参数=" + getappidUrl);
            log.info("getAppidBO 请求的参数=" + JSON.toJSONString(getAppidBO));
            getappidResult = HttpUtil.postGeneralUrl(getappidUrl, "application/json", JSON.toJSONString(getAppidBO), "UTF-8");
            log.info("getappidResult 返回结果=" + getappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取不存在的一条appid记录");
            recordhttp.setUrl(getappidUrl);
            recordhttp.setRequest(JSON.toJSONString(getAppidBO));
            recordhttp.setResponse(getappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getappidResult.contains("\"msg\":\"appid_not_found\""),true);
            Assert.assertEquals(getappidResult.contains("\"result\":128"),true);
        }
    }

    /**
     * 必填参数Appid校验
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void getAppidTestByParameterAppidNotExist(final GetAppidBO getAppidBO) throws Exception {
        String getappidUrl =null;
        String getappidResult ="";
        destroyData();
        try{
            getappidUrl = url+"/AccService/getappid";
            getAppidBO.setAppid(null);
            log.info("getappidUrl 请求的参数=" + getappidUrl);
            log.info("getAppidBO 请求的参数=" + JSON.toJSONString(getAppidBO));
            getappidResult = HttpUtil.postGeneralUrl(getappidUrl, "application/json", JSON.toJSONString(getAppidBO), "UTF-8");
            log.info("getappidResult 返回结果=" + getappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Appid校验");
            recordhttp.setUrl(getappidUrl);
            recordhttp.setRequest(JSON.toJSONString(getAppidBO));
            recordhttp.setResponse(getappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getappidResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(getappidResult.contains("\"result\":106"),true);
        }
    }

    /**
     * 参数BmAppid不传，查全部的appid的记录
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void getAppidTestByParameterBmAppidNotExist(final GetAppidBO getAppidBO) throws Exception {
        String getappidUrl =null;
        String getappidResult ="";
        destroyData();
        try{
            getappidUrl = url+"/AccService/getappid";
            getAppidBO.setBmAppid(null);
            log.info("getappidUrl 请求的参数=" + getappidUrl);
            log.info("getAppidBO 请求的参数=" + JSON.toJSONString(getAppidBO));
            getappidResult = HttpUtil.postGeneralUrl(getappidUrl, "application/json", JSON.toJSONString(getAppidBO), "UTF-8");
            log.info("getappidResult 返回结果=" + getappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数BmAppid不传，查全部的appid的记录");
            recordhttp.setUrl(getappidUrl);
            recordhttp.setRequest(JSON.toJSONString(getAppidBO));
            recordhttp.setResponse(getappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getappidResult.contains("appkey"),true);
            Assert.assertEquals(getappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(getappidResult.contains("bmAppid"),true);
            Assert.assertEquals(getappidResult.contains("callbackUrl"),true);
            Assert.assertEquals(getappidResult.contains("desc"),true);
            Assert.assertEquals(getappidResult.contains("erid"),true);
            Assert.assertEquals(getappidResult.contains("euid"),true);
            Assert.assertEquals(getappidResult.contains("groupTag"),true);
            Assert.assertEquals(getappidResult.contains("identity"),true);
            Assert.assertEquals(getappidResult.contains("info"),true);
            Assert.assertEquals(getappidResult.contains("openMethods"),true);
            Assert.assertEquals(getappidResult.contains("authMethods"),true);
            Assert.assertTrue(JSON.parseObject(getappidResult).getJSONArray("appids").size()>1);
            Assert.assertEquals(getappidResult.contains("appids"),true);
            Assert.assertEquals(getappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getappidResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 参数BmAppid不传，查全部的appid的记录,接口返回增加参数ProtVer、guestUpperLimit、normalUpperLimit、guestPeriodValid
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void getAppidTestByAndAddParameterGuestUpperLimitAndAddParameterNormalUpperLimitAndAddParameterGuestPeriodValid(final GetAppidBO getAppidBO) throws Exception {
        String getappidUrl =null;
        String getappidResult ="";
        try{
            getappidUrl = url+"/AccService/getappid";
            getAppidBO.setBmAppid(null);
            log.info("getappidUrl 请求的参数=" + getappidUrl);
            log.info("getAppidBO 请求的参数=" + JSON.toJSONString(getAppidBO));
            getappidResult = HttpUtil.postGeneralUrl(getappidUrl, "application/json", JSON.toJSONString(getAppidBO), "UTF-8");
            log.info("getappidResult 返回结果=" + getappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            destroyData__();
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数BmAppid不传，查全部的appid的记录,接口返回增加参数ProtVer、guestUpperLimit、normalUpperLimit、guestPeriodValid");
            recordhttp.setUrl(getappidUrl);
            recordhttp.setRequest(JSON.toJSONString(getAppidBO));
            recordhttp.setResponse(getappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getappidResult.contains("appkey"),true);
            Assert.assertEquals(getappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(getappidResult.contains("bmAppid"),true);
            Assert.assertEquals(getappidResult.contains("callbackUrl"),true);
            Assert.assertEquals(getappidResult.contains("desc"),true);
            Assert.assertEquals(getappidResult.contains("erid"),true);
            Assert.assertEquals(getappidResult.contains("euid"),true);
            Assert.assertEquals(getappidResult.contains("groupTag"),true);
            Assert.assertEquals(getappidResult.contains("identity"),true);
            Assert.assertEquals(getappidResult.contains("info"),true);
            Assert.assertEquals(getappidResult.contains("openMethods"),true);
            Assert.assertEquals(getappidResult.contains("authMethods"),true);
            Assert.assertTrue(JSON.parseObject(getappidResult).getJSONArray("appids").size()>1);
            Assert.assertEquals(getappidResult.contains("appids"),true);
            Assert.assertEquals(getappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(getappidResult.contains("protVer"),true);
            Assert.assertEquals(getappidResult.contains("guestUpperLimit"),true);
            Assert.assertEquals(getappidResult.contains("normalUpperLimit"),true);
            Assert.assertEquals(getappidResult.contains("guestPeriodValid"),true);
        }
    }


    /**
     * 参数BmAppid传，获取指定的appid的所有信息
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void getAppidTestByAllocateAppid(final GetAppidBO getAppidBO) throws Exception {
        String getappidUrl =null;
        String getappidResult ="";
        initData();
        try{
            getappidUrl = url+"/AccService/getappid";
            log.info("getappidUrl 请求的参数=" + getappidUrl);
            log.info("getAppidBO 请求的参数=" + JSON.toJSONString(getAppidBO));
            getappidResult = HttpUtil.postGeneralUrl(getappidUrl, "application/json", JSON.toJSONString(getAppidBO), "UTF-8");
            log.info("getappidResult 返回结果=" + getappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            destroyData__();
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数BmAppid传，获取指定的appid的所有信息");
            recordhttp.setUrl(getappidUrl);
            recordhttp.setRequest(JSON.toJSONString(getAppidBO));
            recordhttp.setResponse(getappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getappidResult.contains("appkey"),true);
            Assert.assertEquals(getappidResult.contains("\"apptype\":\"7\""),true);
            Assert.assertEquals(getappidResult.contains("bmAppid"),true);
            Assert.assertEquals(getappidResult.contains("callbackUrl"),true);
            Assert.assertEquals(getappidResult.contains("desc"),true);
            Assert.assertEquals(getappidResult.contains("erid"),true);
            Assert.assertEquals(getappidResult.contains("euid"),true);
            Assert.assertEquals(getappidResult.contains("groupTag"),true);
            Assert.assertEquals(getappidResult.contains("identity"),true);
            Assert.assertEquals(getappidResult.contains("info"),true);
            Assert.assertEquals(getappidResult.contains("openMethods"),true);
            Assert.assertEquals(getappidResult.contains("authMethods"),true);
            Assert.assertEquals(getappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(getappidResult.contains("\"protVer\":10"),true);
            Assert.assertEquals(getappidResult.contains("guestUpperLimit"),true);
            Assert.assertEquals(getappidResult.contains("normalUpperLimit"),true);
            Assert.assertEquals(getappidResult.contains("guestPeriodValid"),true);
        }
    }

    @DataSupplier
    public Stream<GetAppidBO> DataSupplierByStream() {
        GetAppidBO getAppidBO=new GetAppidBO();
        getAppidBO.setAppid(addAppidBO.getAppid());
        getAppidBO.setBmAppid(addAppidBO.getBmAppid());
        return Stream.of(getAppidBO);
    }



    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }


    @Override
    public void initData() {
        String bmAppid="110.00003";
        addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
        addAppidBO.setApptype("7");
        //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
        addAppidBO.setErid(1103548278986772480L);
        addAppidBO.setEuid(1103539011445592064L);
        addAppidBO.setIdentity(bmAppid);
        addAppidBO.setGroupTag("test");
        addAppidBO.setDesc("自动化测试使用");
        addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
        //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
        addAppidBO.setAppid("1.00002");
        addAppidBO.setProtVer(10);
        AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
        List<String> allowIps=new ArrayList<>();
        allowIps.add("*.*.*.*");
        infoBean.setAllowIps(allowIps);
        List<String> openMethods=new ArrayList<>();
        openMethods.add("createroom");
        infoBean.setOpenMethods(openMethods);
        infoBean.setPrivilegeLevel(1);
        infoBean.setNormalUpperLimit(5);
        infoBean.setGuestUpperLimit(5);
        infoBean.setGuestPeriodValid(1);
        addAppidBO.setInfo(infoBean);

        destroyData__();
        addAppidTest.addAppidTestByGernal(addAppidBO);

        Appid record=new Appid();
        record.setDeletedAt(null);
        record.setIdentity("110.00003");
        AppidExample example=new AppidExample();
        AppidExample.Criteria cr=example.createCriteria();
        cr.andIdentityEqualTo("110.00003");
        appidRepository.updateSetDeletedAtValueIsNull(record,example);
    }

    @Override
    public void destroyData() {
        DelAppidBO delAppidBO=new DelAppidBO();
        delAppidBO.setAppid(addAppidBO.getAppid());
        delAppidBO.setBmAppid(addAppidBO.getBmAppid());
        delAppidTest.delappidTestByGernal(delAppidBO);
    }

    public void destroyData__() {
        AppidExample appidExample=new AppidExample();
        appidExample.createCriteria().andAppidEqualTo(addAppidBO.getBmAppid());
        appidRepository.deleteByExample(appidExample);
    }



}
