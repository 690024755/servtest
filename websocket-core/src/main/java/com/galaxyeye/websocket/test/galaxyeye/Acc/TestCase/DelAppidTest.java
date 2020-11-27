package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.DelAppidBO;
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

@Slf4j
@Component
public class DelAppidTest extends BaseTest {
    private final static AddAppidBO addAppidBO  = new AddAppidBO();;

    @Autowired
    private AddAppidTest addAppidTest;

    @Autowired
    private GetAppidTest getAppidTest;

    @Autowired
    private AppidRepository appidRepository;

    @Test
    public void delappidTestByGernal(DelAppidBO delAppidBO) {
        String delappidUrl =null;
        String delappidResult ="";
        try{
            delappidUrl = url+"/AccService/delappid";
            delAppidBO.setAppid(delAppidBO.getAppid());
            delAppidBO.setBmAppid(delAppidBO.getBmAppid());
            log.info("delappidUrl 请求的参数=" + delappidUrl);
            log.info("delAppidBO 请求的参数=" + JSON.toJSONString(delAppidBO));
            delappidResult = HttpUtil.postGeneralUrl(delappidUrl, "application/json", JSON.toJSONString(delAppidBO), "UTF-8");
            log.info("delappidResult 返回结果=" + delappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用性删除appid记录");
            recordhttp.setUrl(delappidUrl);
            recordhttp.setRequest(JSON.toJSONString(delAppidBO));
            recordhttp.setResponse(delappidResult);
            initLog(recordhttp,new Object(){});
        }
    }

    /**
     * 删除一条存在appid记录
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void delappidTestByDeleteExistAppid(final DelAppidBO delAppidBO) throws Exception {
        String delappidUrl =null;
        String delappidResult ="";
        String getappidResult ="";
        try{
            delappidUrl = url+"/AccService/delappid";

            log.info("delappidUrl 请求的参数=" + delappidUrl);
            log.info("delAppidBO 请求的参数=" + JSON.toJSONString(delAppidBO));
            delappidResult = HttpUtil.postGeneralUrl(delappidUrl, "application/json", JSON.toJSONString(delAppidBO), "UTF-8");
            log.info("delappidResult 返回结果=" + delappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            AppidExample appidExampleDelete=new AppidExample();
            appidExampleDelete.createCriteria().andAppidEqualTo(addAppidBO.getBmAppid());
            appidRepository.deleteByExample(appidExampleDelete);
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除一条存在appid记录");
            recordhttp.setUrl(delappidUrl);
            recordhttp.setRequest(JSON.toJSONString(delAppidBO));
            recordhttp.setResponse(delappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(delappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(delappidResult.contains("\"result\":1"),true);
            //删除后检查数据是否存在
            getappidResult=getAppidTest.getAppidTestByGernal("172.16.0.25","18080",addAppidBO.getBmAppid());
            Assert.assertEquals(getappidResult.contains("\"msg\":\"appid_not_found\""),true);
            Assert.assertEquals(getappidResult.contains("\"result\":128"),true);
        }
    }

    /**
     * 重复删除一条已存在的appid记录
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void delappidTestByDeleteExistAppidAndRepeatDelete(final DelAppidBO delAppidBO) throws Exception {
        String delappidUrl =null;
        String delappidResult ="";
        try{
            delappidUrl = url+"/AccService/delappid";
            log.info("delappidUrl 请求的参数=" + delappidUrl);
            log.info("delAppidBO 请求的参数=" + JSON.toJSONString(delAppidBO));
            delappidResult = HttpUtil.postGeneralUrl(delappidUrl, "application/json", JSON.toJSONString(delAppidBO), "UTF-8");
            delappidResult = HttpUtil.postGeneralUrl(delappidUrl, "application/json", JSON.toJSONString(delAppidBO), "UTF-8");
            log.info("delappidResult 返回结果=" + delappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            AppidExample appidExampleDelete=new AppidExample();
            appidExampleDelete.createCriteria().andAppidEqualTo(addAppidBO.getBmAppid());
            appidRepository.deleteByExample(appidExampleDelete);
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("重复删除一条已存在的appid记录");
            recordhttp.setUrl(delappidUrl);
            recordhttp.setRequest(JSON.toJSONString(delAppidBO));
            recordhttp.setResponse(delappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(delappidResult.contains("\"msg\":\"appid_not_found\""),true);
            Assert.assertEquals(delappidResult.contains("\"result\":128"),true);
        }
    }

    /**
     * 删除一条不存在appid记录
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void delappidTestByDeleteNotExistAppid(final DelAppidBO delAppidBO) throws Exception {
        String delappidUrl =null;
        String delappidResult ="";
        try{
            delappidUrl = url+"/AccService/delappid";
            delAppidBO.setBmAppid("999999");
            log.info("delappidUrl 请求的参数=" + delappidUrl);
            log.info("delAppidBO 请求的参数=" + JSON.toJSONString(delAppidBO));
            delappidResult = HttpUtil.postGeneralUrl(delappidUrl, "application/json", JSON.toJSONString(delAppidBO), "UTF-8");
            log.info("delappidResult 返回结果=" + delappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除一条不存在appid记录");
            recordhttp.setUrl(delappidUrl);
            recordhttp.setRequest(JSON.toJSONString(delAppidBO));
            recordhttp.setResponse(delappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(delappidResult.contains("\"msg\":\"appid_not_found\""),true);
            Assert.assertEquals(delappidResult.contains("\"result\":128"),true);
        }
    }

    /**
     * 必填参数Appid校验
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void delappidTestByParameterAppidNotExist(final DelAppidBO delAppidBO) throws Exception {
        String delappidUrl =null;
        String delappidResult ="";
        try{
            delappidUrl = url+"/AccService/delappid";
            delAppidBO.setAppid(null);
            log.info("delappidUrl 请求的参数=" + delappidUrl);
            log.info("delAppidBO 请求的参数=" + JSON.toJSONString(delAppidBO));
            delappidResult = HttpUtil.postGeneralUrl(delappidUrl, "application/json", JSON.toJSONString(delAppidBO), "UTF-8");
            log.info("delappidResult 返回结果=" + delappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Appid校验");
            recordhttp.setUrl(delappidUrl);
            recordhttp.setRequest(JSON.toJSONString(delAppidBO));
            recordhttp.setResponse(delappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(delappidResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(delappidResult.contains("\"result\":106"),true);
        }
    }

    /**
     * 必填参数BmAppid校验
     * @throws Exception
     */
    @Test(dataProvider = "DataSupplierByStream")
    public void delappidTestByParameterBmAppidNotExist(final DelAppidBO delAppidBO) throws Exception {
        String delappidUrl =null;
        String delappidResult ="";
        try{
            delappidUrl = url+"/AccService/delappid";
            delAppidBO.setBmAppid(null);
            log.info("delappidUrl 请求的参数=" + delappidUrl);
            log.info("delAppidBO 请求的参数=" + JSON.toJSONString(delAppidBO));
            delappidResult = HttpUtil.postGeneralUrl(delappidUrl, "application/json", JSON.toJSONString(delAppidBO), "UTF-8");
            log.info("delappidResult 返回结果=" + delappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数BmAppid校验");
            recordhttp.setUrl(delappidUrl);
            recordhttp.setRequest(JSON.toJSONString(delAppidBO));
            recordhttp.setResponse(delappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(delappidResult.contains("\"msg\":\"bmAppid can not be null\""),true);
            Assert.assertEquals(delappidResult.contains("\"result\":101"),true);
        }
    }


    @DataSupplier
    public Stream<DelAppidBO> DataSupplierByStream() {
        DelAppidBO delAppidBO=new DelAppidBO();
        delAppidBO.setAppid(addAppidBO.getAppid());
        delAppidBO.setBmAppid(addAppidBO.getBmAppid());
        delAppidBO.setSeq("seq");
        return Stream.of(delAppidBO);
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
        addAppidTest.addAppidTestByGernal(addAppidBO);
    }

    @Override
    public void destroyData() {
        DelAppidBO delAppidBO=new DelAppidBO();
        delAppidBO.setAppid(addAppidBO.getAppid());
        delAppidBO.setBmAppid(addAppidBO.getBmAppid());
        delappidTestByGernal(delAppidBO);
    }
}
