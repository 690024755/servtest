package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:50
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssWithDrawAccountBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssWithDrawWithPinBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceWzhrssBankListDTO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceWzhrssWithDrawAccountDTO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Deprecated
@Component
@Slf4j
public class BusinessServiceWzhrssWithDrawWithPinTest extends BaseTest implements IResource {
    @Autowired
    private BusinessServiceWzhrssStartTransactionCodeTest businessServiceWzhrssStartTransactionCodeTest;

    @Autowired
    private BusinessServiceWzhrssWithDrawWithSmsTest businessServiceWzhrssWithDrawWithSmsTest;

    @Autowired
    private BusinessServiceWzhrssWithDrawAccountTest businessServiceWzhrssWithDrawAccountTest;

    /**
     *正常情况下，市民卡账户启用交易密码
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssWithDrawWithPinTestByParameterBankcardnoValueIsRight() throws Exception {
        String businessServiceWzhrssWithDrawWithPinUrl =null;
        BusinessServiceWzhrssWithDrawWithPinBO businessServiceWzhrssWithDrawWithPinBO =null;
        String businessServiceWzhrssWithDrawWithPinResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            //查询可提现账户的银行卡号与银行编码
            BusinessServiceWzhrssWithDrawAccountBO businessServiceWzhrssWithDrawAccountBO=new BusinessServiceWzhrssWithDrawAccountBO();
            businessServiceWzhrssWithDrawAccountBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setBmAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawAccountBO.setAcckind("01");
            businessServiceWzhrssWithDrawAccountBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawAccountBO.setUid(hs.get("uid"));
            BusinessServiceWzhrssWithDrawAccountDTO businessServiceWzhrssWithDrawAccountDTO=businessServiceWzhrssWithDrawAccountTest.businessServiceWzhrssWithDrawAccountTestByGernal(businessServiceWzhrssWithDrawAccountBO);

            businessServiceWzhrssWithDrawWithPinUrl = url+"/BusinessService/wzhrss/withdrawwithpin";
            businessServiceWzhrssWithDrawWithPinBO = new BusinessServiceWzhrssWithDrawWithPinBO();
            businessServiceWzhrssWithDrawWithPinBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawWithPinBO.setBmAppid("1.00002");
            businessServiceWzhrssWithDrawWithPinBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawWithPinBO.setPin(businessServiceWzhrssStartTransactionCodeTest.getCipherWithSM2("123456"));
            businessServiceWzhrssWithDrawWithPinBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawWithPinBO.setAcckind("06");
            businessServiceWzhrssWithDrawWithPinBO.setAmount(0.01d);
            businessServiceWzhrssWithDrawWithPinBO.setUid(hs.get("uid"));
            businessServiceWzhrssWithDrawWithPinBO.setBankcardno(businessServiceWzhrssWithDrawAccountDTO.getBankcardno());
//            businessServiceWzhrssWithDrawWithPinBO.setBankcardno("6212261207009106618");
            businessServiceWzhrssWithDrawWithPinBO.setBankno(businessServiceWzhrssWithDrawAccountDTO.getBankno());
            log.info("businessServiceWzhrssWithDrawWithPinUrl 请求的参数=" + businessServiceWzhrssWithDrawWithPinUrl);
            log.info("businessServiceWzhrssWithDrawWithPinBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithDrawWithPinBO));
            businessServiceWzhrssWithDrawWithPinResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithDrawWithPinUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithDrawWithPinBO), "UTF-8");
            log.info("businessServiceWzhrssWithDrawWithPinResult 返回结果=" +businessServiceWzhrssWithDrawWithPinResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("正常情况下，市民卡账户启用交易密码");
            recordhttp.setUrl(businessServiceWzhrssWithDrawWithPinUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithDrawWithPinBO));
            recordhttp.setResponse(businessServiceWzhrssWithDrawWithPinResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithDrawWithPinResult.contains("msg"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawWithPinResult.contains("\"result\":520"),true);
        }
    }

    /**
     *参数银行卡号与市民卡绑定的银行卡不一致
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssWithDrawWithPinTestByParameterBankcardnoValueIsWrong() throws Exception {
        String businessServiceWzhrssWithDrawWithPinUrl =null;
        BusinessServiceWzhrssWithDrawWithPinBO businessServiceWzhrssWithDrawWithPinBO =null;
        String businessServiceWzhrssWithDrawWithPinResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            //查询可提现账户的银行卡号与银行编码
            BusinessServiceWzhrssWithDrawAccountBO businessServiceWzhrssWithDrawAccountBO=new BusinessServiceWzhrssWithDrawAccountBO();
            businessServiceWzhrssWithDrawAccountBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setBmAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawAccountBO.setAcckind("01");
            businessServiceWzhrssWithDrawAccountBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawAccountBO.setUid(hs.get("uid"));
            BusinessServiceWzhrssWithDrawAccountDTO businessServiceWzhrssWithDrawAccountDTO=businessServiceWzhrssWithDrawAccountTest.businessServiceWzhrssWithDrawAccountTestByGernal(businessServiceWzhrssWithDrawAccountBO);

            businessServiceWzhrssWithDrawWithPinUrl = url+"/BusinessService/wzhrss/withdrawwithpin";
            businessServiceWzhrssWithDrawWithPinBO = new BusinessServiceWzhrssWithDrawWithPinBO();
            businessServiceWzhrssWithDrawWithPinBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawWithPinBO.setBmAppid("1.00002");
            businessServiceWzhrssWithDrawWithPinBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawWithPinBO.setPin(businessServiceWzhrssStartTransactionCodeTest.getCipherWithSM2("123456"));
            businessServiceWzhrssWithDrawWithPinBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawWithPinBO.setAcckind("06");
            businessServiceWzhrssWithDrawWithPinBO.setAmount(0.01d);
            businessServiceWzhrssWithDrawWithPinBO.setUid(hs.get("uid"));
            businessServiceWzhrssWithDrawWithPinBO.setBankcardno("6212261207009106618");
            businessServiceWzhrssWithDrawWithPinBO.setBankno(businessServiceWzhrssWithDrawAccountDTO.getBankno());
            log.info("businessServiceWzhrssWithDrawWithPinUrl 请求的参数=" + businessServiceWzhrssWithDrawWithPinUrl);
            log.info("businessServiceWzhrssWithDrawWithPinBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithDrawWithPinBO));
            businessServiceWzhrssWithDrawWithPinResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithDrawWithPinUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithDrawWithPinBO), "UTF-8");
            log.info("businessServiceWzhrssWithDrawWithPinResult 返回结果=" +businessServiceWzhrssWithDrawWithPinResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数银行卡号与市民卡绑定的银行卡不一致");
            recordhttp.setUrl(businessServiceWzhrssWithDrawWithPinUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithDrawWithPinBO));
            recordhttp.setResponse(businessServiceWzhrssWithDrawWithPinResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithDrawWithPinResult.contains("\"result\":129"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawWithPinResult.contains("\"msg\":\"bankno_or_bankcardno_not_matchts\""),true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {
        businessServiceWzhrssWithDrawWithSmsTest.initData();
    }

    @Override
    public void destroyData() {

    }
}
