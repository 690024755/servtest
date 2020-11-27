package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/24日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.WxDataMessageSendBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;


@Slf4j
@Component
public class BusinessServiceWxDataMessageSendTest extends BaseTest {
    //yangyi的uid=239192
    String expertKeyValue="ou0a55W6lWjrNL0dEGEqqHMq9x2E";


    String commonKeyValue="ou0a55WOjXRYGDyfNWKN5b5q_-WM";
    HashMap<String, String> expertHs=null;
    HashMap<String, String> commonHs=null;
    //需要给哪个小程序发送微信订阅通知
    private final String notifyAppid="4.00099";

    /**
     *需要使用uid=1075821，小程序是智慧交通小帮手，点击首页顶部的获取订阅
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByTokenAndUid() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setUid(expertHs.get("uid"));
            wxDataMessageSendBO.setToken(expertHs.get("token"));
            //给哪个小程序发送通知消息
            wxDataMessageSendBO.setNotifyAppid(notifyAppid);
            //自己给自己发送微信订阅消息
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token发送订阅消息");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 使用bmappid与accesstoken发送订阅消息
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByAccessTokenAndBmAppid() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            //给哪个小程序发送通知消息
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            //自己给自己发送微信订阅消息
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用bmappid与accesstoken发送订阅消息");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 发送订阅消息的小程序不存在
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByParameterNotifyAppidValueIsOne() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            //给哪个小程序发送通知消息
            wxDataMessageSendBO.setNotifyAppid("1");
            //自己给自己发送微信订阅消息
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("发送订阅消息的小程序不存在");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"openid_not_found\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":129"),true);
        }
    }

    /**
     * 必填参数NotifyAppid校验
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByNotExistParameterNotifyAppid() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            //给哪个小程序发送通知消息
//            wxDataMessageSendBO.setNotifyAppid("4.00099");
            //自己给自己发送微信订阅消息
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数NotifyAppid校验");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"notifyAppid is empty\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 必填参数Appid校验
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByNotExistParameterAppid() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
//            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            //给哪个小程序发送通知消息
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            //自己给自己发送微信订阅消息
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Appid校验");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":106"),true);
        }
    }

    /**
     * 必填参数DstUid校验
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByNotExistParameterDstUid() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            //给哪个小程序发送通知消息
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            //自己给自己发送微信订阅消息
//            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数DstUid校验");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"dstUid is empty\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 参数DstUid值在数据库不存在
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByParameterDstUidValueIsNotExist() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            //给哪个小程序发送通知消息
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            //自己给自己发送微信订阅消息
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid").concat("1")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数DstUid值在数据库不存在");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"internal_error\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":100"),true);
        }
    }


    /**
     * 必填参数TemplateMsg校验
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByNotExistParameterTemplateMsg() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            //给哪个小程序发送通知消息
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            //自己给自己发送微信订阅消息
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
//            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数TemplateMsg校验");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"templateMsg has wrong value\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 非必填参数page校验，参数page不填写，收到订阅消息后，点击订阅消息无法跳转
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByNotExistParameterPage() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            //给哪个小程序发送通知消息
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            //自己给自己发送微信订阅消息
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
//            templateMsg.put("page","pages/respDetail/respDetail?id=1217716391918964736&replyNo=1");
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数page校验，参数page不填写，收到订阅消息后，点击订阅消息无法跳转");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 参数page填写正确后，收到订阅消息后，点击订阅消息可以跳转到小程序对应的页面
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByParameterPageValueIsRight() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            templateMsg.put("page","pages/respDetail/respDetail?id=1217716391918964736&replyNo=1");
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数page填写正确后，收到订阅消息后，点击订阅消息可以跳转到小程序对应的页面");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 参数page填写错误跳转页面，收到订阅消息后，点击订阅消息可以跳转到小程序对应的页面，但是会提示该页面不存在
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByParameterPageValueIsWrong() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            templateMsg.put("page","yangyi?id=1");
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数page填写错误跳转页面，收到订阅消息后，点击订阅消息可以跳转到小程序对应的页面，但是会提示该页面不存在");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 非必填参数MiniState校验，默认跳转到正式版本小程序
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByNotExistParameterMiniState() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
//            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            templateMsg.put("page","pages/respDetail/respDetail?id=1217716391918964736&replyNo=1");
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数MiniState校验，默认跳转到正式版本小程序");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 订阅消息发送到开发版本
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByExistParameterMiniStateValueIsDeveloper() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("developer");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            templateMsg.put("page","pages/respDetail/respDetail?id=1217716391918964736&replyNo=1");
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("订阅消息发送到开发版本");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 订阅消息发送到体验版本
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByExistParameterMiniStateValueIsTrial() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("trial");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            templateMsg.put("page","pages/respDetail/respDetail?id=1217716391918964736&replyNo=1");
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("订阅消息发送到体验版本");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 订阅消息发送到正式版本
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByExistParameterMiniStateValueIsFormal() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAccessToken(bmAppids.get("1.00002"));
            wxDataMessageSendBO.setNotifyAppid("4.00099");
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            Map<String,Object> templateMsg=new HashMap<>();
            Map<String,Object> Msg=new HashMap<>();
            Msg.put("留言内容","我是留言内容");
            Msg.put("回复内容","我是回复内容");
            Msg.put("文章标题","我是文章标题");
            templateMsg.put("title","留言审核通知");
            templateMsg.put("msg",Msg);
            templateMsg.put("page","pages/respDetail/respDetail?id=1217716391918964736&replyNo=1");
            wxDataMessageSendBO.setTemplateMsg(templateMsg);
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("订阅消息发送到正式版本");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 使用模板1即”留言审核通知“推送微信订阅消息
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByTemplateMsgOne() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setUid(expertHs.get("uid"));
            wxDataMessageSendBO.setToken(expertHs.get("token"));
            wxDataMessageSendBO.setNotifyAppid(notifyAppid);
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            wxDataMessageSendBO.setTemplateMsg(getTemplateMsgByType(1));
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token发送订阅消息");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /**
     * 使用模板2即“签到提醒”推送微信订阅消息
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByTemplateMsgTwo() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setUid(expertHs.get("uid"));
            wxDataMessageSendBO.setToken(expertHs.get("token"));
            wxDataMessageSendBO.setNotifyAppid(notifyAppid);
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            wxDataMessageSendBO.setTemplateMsg(getTemplateMsgByType(2));
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token发送订阅消息");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 使用模板3即“温州交警模板推送”推送微信订阅消息
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByTemplateMsgThree() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setUid(expertHs.get("uid"));
            wxDataMessageSendBO.setToken(expertHs.get("token"));
            wxDataMessageSendBO.setNotifyAppid(notifyAppid);
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            wxDataMessageSendBO.setTemplateMsg(getTemplateMsgByType(3));
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token发送订阅消息");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 使用模板4即“客户咨询通知”推送微信订阅消息
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByTemplateMsgFour() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setUid(expertHs.get("uid"));
            wxDataMessageSendBO.setToken(expertHs.get("token"));
            wxDataMessageSendBO.setNotifyAppid(notifyAppid);
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            wxDataMessageSendBO.setTemplateMsg(getTemplateMsgByType(4));
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token发送订阅消息");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 使用模板5即“咨询回复通知”推送微信订阅消息
     * @throws Exception
     */
    @Test
    public void wxDataMessageSendTestByTemplateMsgFive() throws Exception {
        String messagesendUrl =null;
        WxDataMessageSendBO wxDataMessageSendBO =null;
        String messagesendResult ="";
        try{
            messagesendUrl = url+"/BusinessService/wxdata/messagesend";
            wxDataMessageSendBO = new WxDataMessageSendBO();
            wxDataMessageSendBO.setBmAppid("1.00002");
            wxDataMessageSendBO.setAppid("1.00002");
            wxDataMessageSendBO.setUid(expertHs.get("uid"));
            wxDataMessageSendBO.setToken(expertHs.get("token"));
            wxDataMessageSendBO.setNotifyAppid(notifyAppid);
            wxDataMessageSendBO.setDstUid(Integer.valueOf(expertHs.get("uid")));
            wxDataMessageSendBO.setMiniState("formal");
            wxDataMessageSendBO.setTemplateMsg(getTemplateMsgByType(5));
            log.info("messagesendUrl 请求的参数=" + messagesendUrl);
            log.info("wxDataMessageSendBO 请求的参数=" + JSON.toJSONString(wxDataMessageSendBO));
            messagesendResult = HttpUtil.postGeneralUrl(messagesendUrl, "application/json", JSON.toJSONString(wxDataMessageSendBO), "UTF-8");
            log.info("messagesendResult 返回结果=" + messagesendResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token发送订阅消息");
            recordhttp.setUrl(messagesendUrl);
            recordhttp.setRequest(JSON.toJSONString(wxDataMessageSendBO));
            recordhttp.setResponse(messagesendResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(messagesendResult.contains("\"result\":1"),true);
            Assert.assertEquals(messagesendResult.contains("\"msg\":\"ok\""),true);
        }
    }





    private Map<String,Object> getTemplateMsgByType(Integer type){
        Map<String,Object> templateMsg=templateMsg=new HashMap<>();
        Map<String,Object> Msg=Msg=new HashMap<>();
        switch (type){
            case 1:
                Msg.put("留言内容","我是留言内容");
                Msg.put("回复内容","我是回复内容");
                Msg.put("文章标题","我是文章标题");
                templateMsg.put("title","留言审核通知");
                templateMsg.put("msg",Msg);
                templateMsg.put("page","pages/respDetail/respDetail?id=1217716391918964736&replyNo=1");
                break;
            case 2:
                Msg.put("活动名称","我是活动名称");
                Msg.put("签到方式","我是签到方式");
                templateMsg.put("title","签到提醒");
                templateMsg.put("msg",Msg);
                break;
            case 3:
                Msg.put("回复内容","我是温州交警回复内容");
                Msg.put("投诉建议内容","ai小助手测试");
                Msg.put("状态","已回复");
                templateMsg.put("title","投诉建议回复通知");
                templateMsg.put("msg",Msg);
                templateMsg.put("page","pages/index/index?id=1293445859182120960&replyNo=1");
                break;
            case 4:
                Msg.put("咨询工号","my is answer job number");
                Msg.put("咨询内容","我是客户咨询内容");
                templateMsg.put("title","客户咨询通知");
                templateMsg.put("msg",Msg);
                templateMsg.put("page","pages/index/index?id=1293445859182120960&replyNo=1");
                break;
            case 5:
                Msg.put("回复工号","my is reply job number");
                Msg.put("咨询标题","我是咨询标题");
                Msg.put("回复内容","我是回复内容");
                templateMsg.put("title","咨询回复通知");
                templateMsg.put("msg",Msg);
                templateMsg.put("page","pages/index/index?id=1293445859182120960&replyNo=1");
                break;
        }

        return templateMsg;
    }



    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {
        UserLoginBO expertUserLoginBO = new UserLoginBO();
        expertUserLoginBO.setUname(expertKeyValue);
        expertUserLoginBO.setPasswd("");
        expertUserLoginBO.setAppid("4.00099");
        expertUserLoginBO.setKeytp("openid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setOpenid(expertKeyValue);
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        expertUserLoginBO.setKeys(list);
        expertUserLoginBO.setThirdlogin(false);
        expertHs=userLoginTest(expertUserLoginBO);
        UserLoginBO commonUserLoginBO = new UserLoginBO();
        commonUserLoginBO.setUname(commonKeyValue);
        commonUserLoginBO.setPasswd("");
        commonUserLoginBO.setAppid("4.00099");
        commonUserLoginBO.setKeytp("openid");
        UserLoginBO.Keys commonKeys = new UserLoginBO.Keys();
        commonKeys.setInfo("");
        commonKeys.setOpenid(commonKeyValue);
        List<UserLoginBO.Keys> commonList = new ArrayList<>();
        commonList.add(commonKeys);
        commonUserLoginBO.setKeys(commonList);
        commonUserLoginBO.setThirdlogin(false);
         commonHs=userLoginTest(commonUserLoginBO);
    }

    @Override
    public void destroyData() {

    }
}
