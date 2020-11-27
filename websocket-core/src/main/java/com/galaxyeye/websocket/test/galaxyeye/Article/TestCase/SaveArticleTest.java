package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:53
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.ArticleRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Article;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.SaveArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.UploadImageBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.SaveArticleDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Component
public class SaveArticleTest extends BaseTest {

    @Autowired
    private GetArticleTest getArticleTest;

    @Autowired
    private DeleteArticleTest deleteArticleTest;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UploadImageTest uploadImageTest;

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Test
    public void test1() throws Exception {
        applicationServiceManaged.deleteArticleApplicationService();
    }

    @Test
    public void test2() throws Exception {
        applicationServiceManaged.restartArticlePid();
    }

    @Test
    public void test3() throws Exception {
        applicationServiceManaged.loadBalanceRestartArticlePid();
    }

    /**
     * 通用性测试，保存更新一篇我文章
     * @throws Exception
     */
    public SaveArticleDTO SaveArticleTestByGernal() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        HashMap<String, String> hs= userLoginTest();
        String title = "title"+uUID.toString();
        initData();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
//        saveArticleBO.setId("0");
            saveArticleBO.setId("613914239605673984");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setToken(hs.get("token"));
            saveArticleBO.setUid(hs.get("uid"));
            saveArticleBO.setAppid("1.00002");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用性测试，保存更新一篇我文章");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(savearticleResult.contains("\"strId\":\"613914239605673984\""), true);
            Assert.assertEquals(savearticleResult.contains("\"id\":613914239605673984"), true);
            SaveArticleDTO saveArticleDTO=JSON.parseObject(savearticleResult, SaveArticleDTO.class);
            getArticleTest.getarticleByGernal(saveArticleDTO.getStrId(),title);
            return saveArticleDTO;
        }
    }

    /**
     * 通用性测试，更新一篇文章
     * @param id
     * @param title
     * @param enable 文章是否上架
     * @return
     * @throws Exception
     */
    public SaveArticleDTO SaveArticleTestByParameter(String id,String title,Boolean enable) throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();

            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            saveArticleBO.setAssLabs(assLabs);

            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            saveArticleBO.setAssSubType(assSubType);


            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId(id);
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            //tes小类，对应表article.sub_type
            saveArticleBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(enable);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setToken(hs.get("token"));
            saveArticleBO.setUid(hs.get("uid"));
//            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用性测试，更新一篇文章");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            SaveArticleDTO saveArticleDTO=JSON.parseObject(savearticleResult, SaveArticleDTO.class);
            Assert.assertEquals(savearticleResult.contains("\"strId\":\""+saveArticleDTO.getId()+"\""), true);
            Assert.assertEquals(savearticleResult.contains("\"id\":"+saveArticleDTO.getId()), true);
            getArticleTest.getarticleByGernal(saveArticleDTO.getStrId(),title);
            return saveArticleDTO;
        }
    }

    /**
     * 通用性测试，更新一篇文章
     *
     * title重复，文章id已存在，则保存文章均会失败
     * maintype与ass_lab需要对应,否则保存文章也会失败
     * maintype与ass_sub_type需要对应,否则保存文章也会失败
     * @param saveArticleBO
     * @return
     * @throws Exception
     */
    public SaveArticleDTO SaveArticleTestByParameter(SaveArticleBO saveArticleBO) throws Exception {
        String savearticleUrl = null;
        String savearticleResult = "";
        try{
            savearticleUrl = url+"/ArticleService/savearticle";
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            saveArticleBO.setAssLabs(saveArticleBO.getAssLabs());

            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            saveArticleBO.setAssSubType(saveArticleBO.getAssSubType());


            //main_type_code=01000005，对应的名称是母婴，在表main_type
//            saveArticleBO.setMainType("母婴");
            saveArticleBO.setMainType(saveArticleBO.getMainType());
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId(saveArticleBO.getId());
            saveArticleBO.setTitle(saveArticleBO.getTitle());
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            //tes小类，对应表article.sub_type
            saveArticleBO.setSubType(saveArticleBO.getSubType());
            //tes标签，对应表article.labs
            saveArticleBO.setLabs(saveArticleBO.getLabs());
            saveArticleBO.setQuality(saveArticleBO.getQuality());
            saveArticleBO.setRefUrl(saveArticleBO.getRefUrl());
            saveArticleBO.setSrcSite(saveArticleBO.getSrcSite());
            saveArticleBO.setEditor(saveArticleBO.getEditor());
            saveArticleBO.setInfo(saveArticleBO.getInfo());
            saveArticleBO.setComment(saveArticleBO.getComment());
            saveArticleBO.setEnable(saveArticleBO.getEnable());//文章的启用与不启用
            saveArticleBO.setThumbnail(saveArticleBO.getThumbnail());
            saveArticleBO.setBody(saveArticleBO.getBody());
            saveArticleBO.setToken(saveArticleBO.getToken());
            saveArticleBO.setUid(saveArticleBO.getUid());
//            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid(saveArticleBO.getAppid());
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用性测试，更新一篇文章");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            SaveArticleDTO saveArticleDTO=JSON.parseObject(savearticleResult, SaveArticleDTO.class);
            Assert.assertEquals(savearticleResult.contains("\"strId\":\""+saveArticleDTO.getId()+"\""), true);
            Assert.assertEquals(savearticleResult.contains("\"id\":"+saveArticleDTO.getId()), true);
            getArticleTest.getarticleByGernal(saveArticleDTO.getStrId(),saveArticleBO.getTitle());
            return saveArticleDTO;
        }
    }

    /**
     * 新增一篇文章完成后，查询下该篇文章
     * @throws Exception
     */
    @Test
    public void saveArticleTestByNewArticle() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章完成后，查询下该篇文章");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("id"), true);
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(savearticleResult.contains("strId"), true);
            SaveArticleDTO saveArticleDTO=JSON.parseObject(savearticleResult, SaveArticleDTO.class);
            getArticleTest.getarticleByGernal(saveArticleDTO.getStrId(),title);
        }
    }

    /**
     * 编辑完成一篇文章后，查询下该篇文章
     * @throws Exception
     */
    @Test
    public void saveArticleTestByUpdateArticle() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        initData();
        try{
            HashMap<String, String> hs=userLoginTest();
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
//        saveArticleBO.setId("0");
            saveArticleBO.setId("613914239605673984");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
//            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setUid(hs.get("uid"));
            saveArticleBO.setToken(hs.get("token"));
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("编辑完成一篇文章后，查询下该篇文章");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(savearticleResult.contains("\"strId\":\"613914239605673984\""), true);
            Assert.assertEquals(savearticleResult.contains("\"id\":613914239605673984"), true);
            SaveArticleDTO saveArticleDTO=JSON.parseObject(savearticleResult, SaveArticleDTO.class);
            getArticleTest.getarticleByGernal(saveArticleDTO.getStrId(),title);
        }
    }

    /**
     * 新增一篇文章,其中id=既非文章id也不是0，如填写为1，提示返回错误信息
     * @throws Exception
     */
    @Test
    public void saveArticleTestByParameterIdValueIsOne() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("1");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章,其中id=既非文章id也不是0，如填写为1，提示返回错误信息");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"result\":111"), true);
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"data_error\""), true);
        }
    }

    /**
     * 新增一篇文章,请求参数title为重复，提示db_error
     * @throws Exception
     */
    @Test
    public void saveArticleTestByRepeatTitle() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = uUID.toString();
        SaveArticleTestByParameter("613914239605673984",title,true);
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章,请求参数title为重复，提示db_error");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":112"), true);
        }
    }

    /**
     * 新增一篇文章,请求参数MainType的值不存在，提示db_error
     * @throws Exception
     */
    @Test
    public void saveArticleTestByParameterMainTypeValueIsNotExist() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴9999999999999");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章,请求参数MainType的值不存在，提示db_error");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":112"), true);
        }
    }

    /**
     * 新增一篇文章,请求参数MainType不传，提示db_error
     * @throws Exception
     */
    @Test
    public void saveArticleTestByNotExistParameterMainType() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
//            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章,请求参数MainType不传，提示db_error");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":112"), true);
        }
    }

    /**
     * 新增一篇文章,请求参数Title不传，提示parameter_error
     * @throws Exception
     */
    @Test
    public void saveArticleTestByNotExistParameterTitle() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
//            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章,请求参数Title不传，提示parameter_error");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新增一篇文章,参数enable=false，文章为不启用
     * @throws Exception
     */
    @Test
    public void saveArticleTestByParameterEnableValueIsFalse() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(false);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章,参数enable=false，文章为不启用");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("id"), true);
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(savearticleResult.contains("strId"), true);
        }
    }

    /**
     * 新增一篇文章,请求参数articledb.assLabs的数组值的main_type_code或者articledb.ass_sub_type的数组值的main_type_code与请求参数的MainType不一致，接口返回db_error错误信息
     * @throws Exception
     */
    @Test
    public void saveArticleTestByParameterAssLabsNotMatchMainTypeOrAssSubTypeNotMatchMainType() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("生活");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(false);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章,请求参数articledb.assLabs的数组值的main_type_code或者articledb.ass_sub_type的数组值的main_type_code与请求参数的MainType不一致，接口返回db_error错误信息");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":112"), true);
        }
    }

    /**
     * 方法savearticle配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void saveArticleTestByOpenMethodAndUid() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        HashMap<String, String> hs=userLoginTest();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setUid(hs.get("uid"));
//            saveArticleBO.setToken(hs.get("token"));
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法savearticle配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("id"), true);
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(savearticleResult.contains("strId"), true);
            SaveArticleDTO saveArticleDTO=JSON.parseObject(savearticleResult, SaveArticleDTO.class);
            getArticleTest.getarticleByGernal(saveArticleDTO.getStrId(),title);
        }
    }

    /**
     * 方法savearticle配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void saveArticleTestByOpenMethodAndBmAppid() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        HashMap<String, String> hs=userLoginTest();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
//            saveArticleBO.setAccessToken(bmAppids.get("1.00002"));
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("1.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法savearticle配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("id"), true);
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(savearticleResult.contains("strId"), true);
            SaveArticleDTO saveArticleDTO=JSON.parseObject(savearticleResult, SaveArticleDTO.class);
            getArticleTest.getarticleByGernal(saveArticleDTO.getStrId(),title);
        }
    }

    /**
     * 方法savearticle配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void saveArticleTestByAuthMethodAndUid() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        HashMap<String, String> hs=userLoginTest();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setToken(hs.get("token"));
            saveArticleBO.setUid(hs.get("uid"));
            saveArticleBO.setAppid("100.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法savearticle配置在authMethod当中，校验Token");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("id"), true);
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(savearticleResult.contains("strId"), true);
            SaveArticleDTO saveArticleDTO=JSON.parseObject(savearticleResult, SaveArticleDTO.class);
            getArticleTest.getarticleByGernal(saveArticleDTO.getStrId(),title);
        }
    }

    /**
     * 方法savearticle配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void saveArticleTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        HashMap<String, String> hs=userLoginTest();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
//            saveArticleBO.setToken(hs.get("token"));
            saveArticleBO.setUid(hs.get("uid"));
            saveArticleBO.setAppid("100.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法savearticle配置在authMethod当中，不校验Token");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 方法savearticle配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void saveArticleTestByAuthMethodAndBmAppid() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        HashMap<String, String> hs=userLoginTest();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setAccessToken(bmAppids.get("1.00002"));
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("100.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法savearticle配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("id"), true);
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(savearticleResult.contains("strId"), true);
            SaveArticleDTO saveArticleDTO=JSON.parseObject(savearticleResult, SaveArticleDTO.class);
            getArticleTest.getarticleByGernal(saveArticleDTO.getStrId(),title);
        }
    }

    /**
     * 方法savearticle配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void saveArticleTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String savearticleUrl = null;
        SaveArticleBO saveArticleBO = null;
        String savearticleResult = "";
        UUID uUID=UUID.randomUUID();
        String title = "title"+uUID.toString();
        HashMap<String, String> hs=userLoginTest();
        try{
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            savearticleUrl = url+"/ArticleService/savearticle";
            saveArticleBO = new SaveArticleBO();
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");

            saveArticleBO.setAssLabs(assLabs);
            saveArticleBO.setAssSubType(assSubType);
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            saveArticleBO.setSubType("02003033|02003034");
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
//            saveArticleBO.setAccessToken(bmAppids.get("1.00002"));
            saveArticleBO.setBmAppid("1.00002");
            saveArticleBO.setAppid("100.00002");
            saveArticleBO.setSeq("abc");
            log.info("savearticleUrl 请求的参数=" + savearticleUrl);
            log.info("saveArticleBO 请求的参数=" + JSON.toJSONString(saveArticleBO));
            savearticleResult = HttpUtil.postGeneralUrl(savearticleUrl, "application/json", JSON.toJSONString(saveArticleBO), "UTF-8");
            log.info("savearticleResult 返回结果=" + savearticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法savearticle配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(savearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(saveArticleBO));
            recordhttp.setResponse(savearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(savearticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(savearticleResult.contains("\"result\":101"), true);

        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {
        try{
            //用于文章的编辑
            ArticleExample example=new ArticleExample();
            ArticleExample.Criteria cr=example.createCriteria();
            List<Long> ids=new ArrayList<>();
            ids.add(613914239605673984L);
            ids.add(608898375646973952L);
            cr.andIdIn(ids);
            if(articleRepository.selectByExample(example).size()!=2){
                articleRepository.deleteByExample(example);
                Article record=new Article();
                record.setArticleType(null);
                record.setAssLabs("assLabName4434");
                record.setComment("新增");
                record.setCreatedAt(DateTool.toDate("2019-08-22 11:10:42",DateTool.DATE_FMT_5));
                record.setDeletedAt(null);
                record.setEditor("李梦颖1");
                record.setEnable(0);
                record.setHtmltext("article/200529/613914239605673984/613914239605673984.body");
                record.setId(613914239605673984L);
                record.setInfo("无");
                record.setIntro("10月怀胎，体质、生活习惯的不同，妈妈们的身体状况也让不尽相同。但造物主是公平的，在产后会给每个妈妈都提供一段心理、生理修复的最佳时期，让你重回少女时代。");
                record.setLabs("03004435|03004434");
                record.setMainType("母婴");
                record.setQuality("优");
                record.setRefUrl("https://baike.pcbaby.com.cn/qzbd/1136412.html");
                record.setSrcSite("PCbaby百科");
                record.setSubType("02003033|02003034");
                record.setThumbnail("http://47.96.10.106/img/article/200529/1266192839914557440.jpg");
                record.setTitle("产后恢复");
                record.setUpdatedAt(DateTool.toDate("2020-05-29 10:22:40",DateTool.DATE_FMT_5));
                articleRepository.insertSelective(record);
                Article record2=new Article();
                record2.setArticleType(null);
                record2.setAssLabs("assLabName4434");
                record2.setComment("新增");
                record2.setCreatedAt(DateTool.toDate("2019-08-22 11:10:42",DateTool.DATE_FMT_5));
                record2.setDeletedAt(null);
                record2.setEditor("李梦颖1");
                record2.setEnable(0);
                record2.setHtmltext("article/200529/613914239605673984/613914239605673984.body");
                record2.setId(608898375646973952L);
                record2.setInfo("无");
                record2.setIntro("10月怀胎，体质、生活习惯的不同，妈妈们的身体状况也让不尽相同。但造物主是公平的，在产后会给每个妈妈都提供一段心理、生理修复的最佳时期，让你重回少女时代。");
                record2.setLabs("03004435|03004434");
                record2.setMainType("母婴");
                record2.setQuality("优");
                record2.setRefUrl("https://baike.pcbaby.com.cn/qzbd/1136412.html");
                record2.setSrcSite("PCbaby百科");
                record2.setSubType("02003033|02003034");
                record2.setThumbnail("http://47.96.10.106/img/article/200529/1266192839914557440.jpg");
                record2.setTitle("产后恢复1");
                record2.setUpdatedAt(DateTool.toDate("2020-05-29 10:22:40",DateTool.DATE_FMT_5));
                articleRepository.insertSelective(record2);
            }
            //重启服务，缓存生效
            applicationServiceManaged.restartArticlePid();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    @Override
    public void destroyData() {
        try{
            //测试使用的文章id=613914239605673984与608898375646973952
            deleteArticleTest.deleteArticleTestByGernal("613914239605673984");
            deleteArticleTest.deleteArticleTestByGernal("608898375646973952");
            //重启服务，缓存生效
            applicationServiceManaged.restartArticlePid();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
