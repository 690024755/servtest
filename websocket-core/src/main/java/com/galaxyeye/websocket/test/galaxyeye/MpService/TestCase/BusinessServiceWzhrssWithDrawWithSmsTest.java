package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 16:30
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/26日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssBankListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssSendsmsCodeBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssWithDrawWithSmsBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceWzhrssBankListDTO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Component
@Slf4j
public class BusinessServiceWzhrssWithDrawWithSmsTest extends BaseTest {
    @Autowired
    private BusinessServiceWzhrssSendsmsCodeTest businessServiceWzhrssSendsmsCodeTest;

    @Autowired
    private BusinessServiceWzhrssBankListTest businessServiceWzhrssBankListTest;

    private BusinessServiceWzhrssBankListDTO businessServiceWzhrssBankListDTO=null;


    /**
     *正常情况下，可提现账户转出，发送短信
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssWithDrawWithSmsTestByNormal() throws Exception {
        String businessServiceWzhrssWithDrawWithSmsUrl =null;
        BusinessServiceWzhrssWithDrawWithSmsBO businessServiceWzhrssWithDrawWithSmsBO =null;
        String businessServiceWzhrssWithDrawWithSmsResult ="";
        HashMap<String, String> hs= userLoginTest();
        //获取提现用户的短信验证码
        BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO=new BusinessServiceWzhrssSendsmsCodeBO();
        businessServiceWzhrssSendsmsCodeBO.setAppid("1.00002");
        businessServiceWzhrssSendsmsCodeBO.setCardno("32500099110004695300");
        businessServiceWzhrssSendsmsCodeBO.setToken(hs.get("token"));
        businessServiceWzhrssSendsmsCodeBO.setUid(hs.get("uid"));
        businessServiceWzhrssSendsmsCodeBO.setSmstype("02");
        businessServiceWzhrssSendsmsCodeBO.setPhone("13819723663");
        //短信流水号
        String no=businessServiceWzhrssSendsmsCodeTest.businessServiceWzhrssSendsmsCodeTestByGernal(businessServiceWzhrssSendsmsCodeBO);

        try{
            businessServiceWzhrssWithDrawWithSmsUrl = url+"/BusinessService/wzhrss/withdrawwithsms";
            businessServiceWzhrssWithDrawWithSmsBO = new BusinessServiceWzhrssWithDrawWithSmsBO();
            businessServiceWzhrssWithDrawWithSmsBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawWithSmsBO.setBmAppid("1.00002");
            //目前只做车改账户的转出余额功能
            businessServiceWzhrssWithDrawWithSmsBO.setAcckind("06");
            //接口设置提现金额为1元，则参数直接设置为1
            businessServiceWzhrssWithDrawWithSmsBO.setAmount(1d);
            businessServiceWzhrssWithDrawWithSmsBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawWithSmsBO.setBankcardno("6212261207009106618");
            //工商银行编码
            List<BusinessServiceWzhrssBankListDTO.Bank> list =businessServiceWzhrssBankListDTO.getList();
            for (BusinessServiceWzhrssBankListDTO.Bank bank:list
            ) {
                if(bank.getBank().contains("工商")){
                    businessServiceWzhrssWithDrawWithSmsBO.setBankno(bank.getNo());
                }
            }
            //短信验证码获取，需要文本文件读入  TODO
            String phonePath=getFilePath("getInfo.txt");
            File file=new File(phonePath);
            FileReader fileReader=new FileReader(file);
            char[] ch=new char[1024];
            while (fileReader.read()!=-1){
                fileReader.read(ch,0,ch.length);
            }
            String phone=new String(ch);
            businessServiceWzhrssWithDrawWithSmsBO.setSmscode("短信验证码");
            businessServiceWzhrssWithDrawWithSmsBO.setSmsno(no);
            businessServiceWzhrssWithDrawWithSmsBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawWithSmsBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithDrawWithSmsUrl 请求的参数=" + businessServiceWzhrssWithDrawWithSmsUrl);
            log.info("businessServiceWzhrssWithDrawWithSmsBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithDrawWithSmsBO));
            businessServiceWzhrssWithDrawWithSmsResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithDrawWithSmsUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithDrawWithSmsBO), "UTF-8");
            log.info("businessServiceWzhrssWithDrawWithSmsResult 返回结果=" +businessServiceWzhrssWithDrawWithSmsResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("正常情况下，可提现账户转出，发送短信");
            recordhttp.setUrl(businessServiceWzhrssWithDrawWithSmsUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithDrawWithSmsBO));
            recordhttp.setResponse(businessServiceWzhrssWithDrawWithSmsResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithDrawWithSmsResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawWithSmsResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawWithSmsResult.contains("\"result\":1"),true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {
        try{
            HashMap<String, String> hs= userLoginTest();
            BusinessServiceWzhrssBankListBO businessServiceWzhrssBankListBO=new BusinessServiceWzhrssBankListBO();
            businessServiceWzhrssBankListBO.setAppid("1.00002");
            businessServiceWzhrssBankListBO.setToken(hs.get("token"));
            businessServiceWzhrssBankListBO.setUid(hs.get("uid"));
            businessServiceWzhrssBankListDTO=businessServiceWzhrssBankListTest.businessServiceWzhrssBankListTestByGernal(businessServiceWzhrssBankListBO);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BusinessServiceWzhrssBankListDTO getBusinessServiceWzhrssBankListDTO() {
        return businessServiceWzhrssBankListDTO;
    }

    @Override
    public void destroyData() {

    }
}
