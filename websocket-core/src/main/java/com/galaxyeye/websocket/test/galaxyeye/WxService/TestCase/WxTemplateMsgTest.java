package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 10:41
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.SendFormidBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.WxTemplateMsgBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.util.HashMap;

@Slf4j
@Component
public class WxTemplateMsgTest extends BaseTest {


    @Autowired
    private JedisTemplate jedisTemplate;

    @Test
    public void initTest() throws Exception {
        String accessToken="27_Rd73RsMs4Hw6BU-jcniNGtBhEfCcEi1P8PzOme_qpl9J568MP5qoOW_PPQ8hz1U-MGf2CyDOhPBVyj_65bhH9k_cdoN-AbSjP367H_DMjLhjlU754oWHF25-J1JZVn6OKg-IW6arC19Fv3maVNYjAFAZRC";
        jedisTemplate.set("WxAccessToken:wx47f7443e87543790",accessToken);
        jedisTemplate.del("WxAccessToken:wx47f7443e87543790");
        log.info("WxAccessToken:wx47f7443e87543790 查询结果=" + jedisTemplate.get("WxAccessToken:wx47f7443e87543790"));
    }


    @Test
    public void requestWxTemplateMsgTest() throws Exception {
        String Url = url+"/wxs/v1/wxTemplateMsg";
        WxTemplateMsgBO wxTemplateMsgBO = new WxTemplateMsgBO();
        wxTemplateMsgBO.setDstUid(225882);
        wxTemplateMsgBO.setSrcUid(100002);//目前未做验证，随便填写
        wxTemplateMsgBO.setNotifyAppid("4.00047");
        wxTemplateMsgBO.setAccessToken("9e08dcd7ce2c590cb8962832b6dbd5e925a62166de093b5c27675c83ff9d6098");
        wxTemplateMsgBO.setAppid("1.00003");
        wxTemplateMsgBO.setSeq("abc");
        HashMap<String,String> msgMap=new HashMap<>();
        HashMap<String,Object> templateMsg=new HashMap<>();
        msgMap.put("投诉建议内容","温州交警测试");
        msgMap.put("状态","已回复");
        msgMap.put("回复内容","回复小助手");
        templateMsg.put("msg",msgMap);
        templateMsg.put("page","index/index");
        templateMsg.put("title","投诉建议回复通知");
        wxTemplateMsgBO.setTemplateMsg(templateMsg);
        log.info("wxTemplateMsgmessage 请求的参数=" + JSON.toJSONString(wxTemplateMsgBO));
        String wxTemplateMsgmessage = HttpUtil.postGeneralUrl(Url, "application/json", JSON.toJSONString(wxTemplateMsgBO), "UTF-8");
        log.info("wxTemplateMsgmessage 返回结果=" + JSON.parseObject(wxTemplateMsgmessage));
    }

    /**
     * 回复者
     * @throws Exception
     */
    @Test
    public void replyWxTemplateMsgTest() throws Exception {
        String Url = url+"/wxs/v1/wxTemplateMsg";
        WxTemplateMsgBO wxTemplateMsgBO = new WxTemplateMsgBO();
        wxTemplateMsgBO.setDstUid(225882);
        wxTemplateMsgBO.setSrcUid(100002);//目前未做验证，随便填写
        wxTemplateMsgBO.setNotifyAppid("4.00071");
        wxTemplateMsgBO.setAccessToken("9e08dcd7ce2c590cb8962832b6dbd5e984e79c1bca0888dd47c49f27e2cba863");
//        wxTemplateMsgBO.setAppid("3.00011");
        wxTemplateMsgBO.setAppid("1.00003");
        wxTemplateMsgBO.setSeq("abc");
        HashMap<String,String> msgMap=new HashMap<>();
        HashMap<String,Object> templateMsg=new HashMap<>();
        msgMap.put("咨询标题","咨询标题1");
        msgMap.put("回复内容","测试回复内容1");
        msgMap.put("回复者","已回复1");
        templateMsg.put("msg",msgMap);
        templateMsg.put("page","index/index");
        templateMsg.put("title","事项办理结果通知");
        wxTemplateMsgBO.setTemplateMsg(templateMsg);
        log.info("wxTemplateMsgmessage 请求的参数=" + JSON.toJSONString(wxTemplateMsgBO));
        String wxTemplateMsgmessage = HttpUtil.postGeneralUrl(Url, "application/json", JSON.toJSONString(wxTemplateMsgBO), "UTF-8");
        log.info("wxTemplateMsgmessage 返回结果=" + JSON.parseObject(wxTemplateMsgmessage));
    }

    @Test
    public void userLoginTest1() throws Exception {
        String Url = url+"/wxs/v1/userLogin";
        UserLoginBO userLoginBO = new UserLoginBO();
        userLoginBO.setBmAppid("4.00075");
        userLoginBO.setKeytp("wxcode");
        userLoginBO.setPasswd("123456");
        userLoginBO.setUname("wx_pm3rrlj7");
        userLoginBO.setUserInfoRes("");
        userLoginBO.setWxAppid("wx47f7443e87543790");
        log.info("UserLoginmessage 请求的参数=" + JSON.toJSONString(userLoginBO));
        String UserLoginmessage = HttpUtil.postGeneralUrl(Url, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
        log.info("UserLoginmessage 返回结果=" + JSON.parseObject(UserLoginmessage));
    }

    @Test
    @Deprecated
    public void sendFormidTest() throws Exception {
        String Url = url+"/wxs/v1/sendFormid";
        SendFormidBO sendFormidBO = new SendFormidBO();
        sendFormidBO.setBmAppid("4.00071");
        sendFormidBO.setFormid("fff");
        sendFormidBO.setOpenid("1");
        sendFormidBO.setSeq("abc");
        sendFormidBO.setUid("511762");
        sendFormidBO.setWxAppid("1");
        log.info("sendFormidmessage 请求的参数=" + JSON.toJSONString(sendFormidBO));
        String SendFormidmessage = HttpUtil.postGeneralUrl(Url, "application/json", JSON.toJSONString(sendFormidBO), "UTF-8");
        log.info("sendFormidmessage 返回结果=" + JSON.parseObject(SendFormidmessage));
    }


    public static void main(String[] args) {
        String t1="88发个dddd1111111111";
        String t2_1024="截至4月7日24时，31个省区市和新疆生产建设兵团现有确诊病例1190例，其中重症病例189例；累计治愈出院病例77279例，累计死亡病例3333例，累计报告确诊病例81802例，现有疑似病例83例。新增无症状感染者137例，其中境外输入102例；当日转为确诊病例11例，全部为境外输入；当日解除医学观察64例；尚在医学观察无症状感染者1095例，其中境外输入358例。4月7日，现有本土确诊病例降至500例以下，尚在医学观察的密切接触者数量连续7天下降。现有确诊病例和无症状感染者中，境外输入所占比例持续增大，关联病例散发出现截至4月7日24时，31个省区市和新疆生产建设兵团现有确诊病例1190例，其中重症病例189例；累计治愈出院病例77279例，累计死亡病例3333例，累计报告确诊病例81802例，现有疑似病例83例。新增无症状感染者137例，其中境外输入102例；当日转为确诊病例11例，全部为境外输入；当日解除医学观察64例；尚在医学观察无症状感染者1095例，其中境外输入358例。4月7日，现有本土确诊病例降至500例以下，尚在医学观察的密切接触者数量连续7天下降。现有确诊病例和无症状感染者中，境外输入所占比例持续增大，关联病例散发出现截至4月7日24时，31个省区市和新疆生产建设兵团现有确诊病例1190例，其中重症病例189例；累计治愈出院病例77279例，累计死亡病例3333例，累计报告确诊病例81802例，现有疑似病例83例。新增无症状感染者137例，其中境外输入102例；当日转为确诊病例11例，全部为境外输入；当日解除医学观察64例；尚在医学观察无症状感染者1095例，其中境外输入358例。4月7日，现有本土确诊病例降至500例以下，尚在医学观察的密切接触者数量连续7天下降。现有确诊病例和无症状感染者中，境外输入所占比例持续增大，关联病例散发出现截至4月7日24时，31个省区市和新疆生产建设兵团现有确诊病例1190例，其中重症病例189例；累计治愈出院病例77279例，累计死亡病例3333例，累计报告确诊病例81802例，现有疑似病例83例。新增无症状感染者137例，其中境外输入102例；当日转为确诊病例11例，全部为境外输入；当日解除医学观察64例；尚在医学观察无症状感染者1095例，其中境外输入358例。4月7日，现有本土确诊病例降至500例以下，尚在医学观察的密切接触者数量连续7天下降。现天下降";
        String t3="88dddd12345678901111";
        String t4="88发个dddd123456789011111./;'\\][-=";
        String t5="dddd1111111111dddd1111111111";
        System.out.println(t1.length());
        System.out.println(t2_1024.length());
        System.out.println(t3.length());
        System.out.println(t4.length());
        System.out.println(t5.length());
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(9));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }
}
