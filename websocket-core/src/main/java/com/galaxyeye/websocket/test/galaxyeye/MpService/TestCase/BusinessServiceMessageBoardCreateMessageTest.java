package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 14:43
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/22日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.UploadImageBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.TestCase.UploadImageTest;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.CreateMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.VO.CreateMessageVO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Component
@Slf4j
@Deprecated
public class BusinessServiceMessageBoardCreateMessageTest extends BaseTest {

    @Autowired
    private UploadImageTest uploadImageTest;


    /**
     * 获取message id的信息
     * @throws Exception
     */
    @Deprecated
    public CreateMessageVO getMessageIDTest() {
        String createmessageUrl = null;
        CreateMessageBO createMessageBO = null;
        String CreatemessageResult = "";
        CreateMessageVO createMessageVO=null;
        try {
            createmessageUrl = url + "/BusinessService/messageboard/createmessage";
            createMessageBO = new CreateMessageBO();
            HashMap<String, String> hs = userLoginTest();
            createMessageBO.setAppid("1.00002");
            createMessageBO.setToken(hs.get("token"));
            createMessageBO.setUid(hs.get("uid"));
            //传递该参数，在messagedb.message_board.appid写入值
            createMessageBO.setBmAppid("3.00014");
            createMessageBO.setContact("18888888888");
            createMessageBO.setContent("测试内容");
            List<String> images = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            images.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            createMessageBO.setImages(images);
            createMessageBO.setNickname("hh");
            createMessageBO.setTitle("测试标题");//测试标题
            log.info("createmessageUrl 请求的参数=" + JSON.toJSONString(createmessageUrl));
            log.info("createMessageBO 请求的参数=" + JSON.toJSONString(createMessageBO));
            CreatemessageResult = HttpUtil.postGeneralUrl(createmessageUrl, "application/json", JSON.toJSONString(createMessageBO), "UTF-8");
            log.info("CreatemessageResult 返回结果=" + JSON.parseObject(CreatemessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取message id的信息");
            recordhttp.setUrl(createmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createMessageBO));
            recordhttp.setResponse(CreatemessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(CreatemessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(CreatemessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(CreatemessageResult.contains("id"), true);
            createMessageVO=JSON.parseObject(CreatemessageResult, CreateMessageVO.class);
            return createMessageVO;
        }
    }

    /**
     * 根据uid与token创建留言板
     * @throws Exception
     */
    @Test
    @Deprecated
    public void BusinessServiceMessageBoardCreateMessageTestByUidAndToken() throws Exception {
        String createmessageUrl = null;
        CreateMessageBO createMessageBO = null;
        String CreatemessageResult = "";
        try {
            createmessageUrl = url + "/BusinessService/messageboard/createmessage";
            createMessageBO = new CreateMessageBO();
            HashMap<String, String> hs = userLoginTest();
            createMessageBO.setAppid("1.00002");
            createMessageBO.setToken(hs.get("token"));
            createMessageBO.setUid(hs.get("uid"));
            //传递该参数，在messagedb.message_board.appid写入值
            createMessageBO.setBmAppid("3.00014");
            createMessageBO.setContact("18888888888");
            createMessageBO.setContent("测试内容");
            List<String> images = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            images.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            createMessageBO.setImages(images);
            createMessageBO.setNickname("hh");
            createMessageBO.setSeq(123);
            createMessageBO.setTitle("测试标题");//测试标题
            log.info("createmessageUrl 请求的参数=" + JSON.toJSONString(createmessageUrl));
            log.info("createMessageBO 请求的参数=" + JSON.toJSONString(createMessageBO));
            CreatemessageResult = HttpUtil.postGeneralUrl(createmessageUrl, "application/json", JSON.toJSONString(createMessageBO), "UTF-8");
            log.info("CreatemessageResult 返回结果=" + JSON.parseObject(CreatemessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据uid与token创建留言板");
            recordhttp.setUrl(createmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createMessageBO));
            recordhttp.setResponse(CreatemessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(CreatemessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(CreatemessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(CreatemessageResult.contains("id"), true);
        }
    }

    /**
     * 根据bmappid与accessToken创建留言板
     * @throws Exception
     */
    @Test
    @Deprecated
    public void BusinessServiceMessageBoardCreateMessageTestByBmAppidAndAccessToken() throws Exception {
        String createmessageUrl = null;
        CreateMessageBO createMessageBO = null;
        String CreatemessageResult = "";
        try {
            createmessageUrl = url + "/BusinessService/messageboard/createmessage";
            createMessageBO = new CreateMessageBO();
            createMessageBO.setAppid("1.00002");
            //传递该参数，在messagedb.message_board.appid写入值
            createMessageBO.setBmAppid("3.00014");
            createMessageBO.setAccessToken(bmAppids.get("3.00014"));
            createMessageBO.setContact("18888888888");
            createMessageBO.setContent("测试内容");
            List<String> images = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            images.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            createMessageBO.setImages(images);
            createMessageBO.setNickname("hh");
            createMessageBO.setSeq(123);
            createMessageBO.setTitle("测试标题");//测试标题
            log.info("createmessageUrl 请求的参数=" + JSON.toJSONString(createmessageUrl));
            log.info("createMessageBO 请求的参数=" + JSON.toJSONString(createMessageBO));
            CreatemessageResult = HttpUtil.postGeneralUrl(createmessageUrl, "application/json", JSON.toJSONString(createMessageBO), "UTF-8");
            log.info("CreatemessageResult 返回结果=" + JSON.parseObject(CreatemessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据bmappid与accessToken创建留言板");
            recordhttp.setUrl(createmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createMessageBO));
            recordhttp.setResponse(CreatemessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(CreatemessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(CreatemessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(CreatemessageResult.contains("id"), true);
        }
    }




    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }
}
