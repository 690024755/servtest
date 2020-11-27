package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 10:58
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/6日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.ManualConfigRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.ManualConfig;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ManualConfigExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.GetDetailListBO;
import com.galaxyeye.websocket.tool.date.DateTool;
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


@Slf4j
@Component
public class GetDetailListTest extends BaseTest {
    @Autowired
    private ManualConfigRepository manualConfigRepository;


    /**
     * 数据初始化
     */
    @Override
    public void initData() {
        ManualConfigExample exampleQ=new ManualConfigExample();
        ManualConfigExample.Criteria crQ=exampleQ.createCriteria();
        List<Long> ids=new ArrayList<>();
        ids.add(1227465942338900013L);
        ids.add(1227465942338900014L);
        ids.add(1227465942338900007L);
        ids.add(1227465942338900002L);
        ids.add(1227465942338900003L);
        ids.add(1227465942338900001L);
        crQ.andIdIn(ids);
        List<ManualConfig> list= manualConfigRepository.selectByExample(exampleQ);
        if(Integer.valueOf(list.size()).equals(6)){
            log.info("已经存在初始化数据");
        }else {
            //初始化6条数据
            manualConfigRepository.deleteByExample(exampleQ);
            ManualConfig record1=new ManualConfig ();
            record1.setId(1227465942338900013L);
            record1.setAppid("4.00090");
            record1.setComment("健康小程序健康计划列表（测试专用）");
            record1.setConfEnv("dev");
            record1.setConfKey("healthPlanAbs");
            record1.setCreatedAt(DateTool.parseDateStr("2020-03-20 10:29:42",DateTool.TIME_PATTERN));
            record1.setData("[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]],[{},[[{},[[{},[[{},[[{},[],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]");
            record1.setEditor(null);
            record1.setEnable(1);
            record1.setUpdatedAt(DateTool.parseDateStr("2020-04-17 10:43:07",DateTool.TIME_PATTERN));
            record1.setVer("5.0.0");
            record1.setVerify(1);
            ManualConfig record2=new ManualConfig ();
            record2.setId(1227465942338900014L);
            record2.setAppid("4.00090");
            record2.setComment("健康小程序健康计划列表（测试专用）");
            record2.setConfEnv("dev");
            record2.setConfKey("healthPlanDetail");
            record2.setCreatedAt(DateTool.parseDateStr("2020-03-20 10:29:42",DateTool.TIME_PATTERN));
            record2.setData("{\"defaultHealthPlanId\":[\"2\",\"3\",\"4\"],\"healthPlan\":[{\"bonus\":\"7dayHealthPlanSign\",\"complete\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_yuanqi/wancheng.png\",\"cover\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_yuanqi/jianjie.png\",\"dayImage\":[1,1,1,1,1,1,1],\"details\":[[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_yuanqi/1.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_yuanqi/2.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_yuanqi/3.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_yuanqi/4.png\"]],\"duration\":7,\"id\":\"2\",\"name\":\"7天元气恢复计划\",\"strip\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_yuanqi/liebiao.png\",\"thumbnail\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_yuanqi/shouye.png\"},{\"bonus\":\"7dayHealthPlanSign\",\"complete\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/wancheng.png\",\"cover\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/jianjie.png\",\"dayImage\":[1,2,3,4,5,6,7],\"details\":[[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D1-1.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D1-2.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D1-3.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D1-4.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D1-5.png\"],[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D2-1.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D2-2.png\"],[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D3-1.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D3-2.png\"],[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D4-1.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D4-2.png\"],[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D5-1.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D5-2.png\"],[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D6-1.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D6-2.png\"],[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D7-1.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/D7-2.png\"]],\"duration\":7,\"id\":\"3\",\"name\":\"7天补水保湿计划\",\"strip\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/liebiao.png\",\"thumbnail\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_bushui/shouye.png\"},{\"bonus\":\"14dayHealthPlanSign\",\"complete\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/14_wanan/wancheng.jpg\",\"cover\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/14_wanan/jianjie.jpg\",\"dayImage\":[1,1,1,1,1,1,1,2,2,2,2,2,2,2],\"details\":[[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/14_wanan/S1-1.jpg\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/14_wanan/S1-2.jpg\"],[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/14_wanan/S2-1.jpg\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/14_wanan/S2-2.jpg\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/14_wanan/S2-3.jpg\"]],\"duration\":14,\"id\":\"4\",\"name\":\"14天晚安好眠计划\",\"strip\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/14_wanan/liebiao.png\",\"thumbnail\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/14_wanan/shouye.png\"},{\"bonus\":\"21dayHealthPlanSign\",\"complete\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/21_jingzhui/wancheng.png\",\"cover\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/21_jingzhui/jianjie.png\",\"dayImage\":[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],\"details\":[[\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/21_jingzhui/1.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/21_jingzhui/2.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/21_jingzhui/3.png\",\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/21_jingzhui/4.png\"]],\"duration\":21,\"id\":\"7\",\"name\":\"21天颈椎保护计划\",\"strip\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/21_jingzhui/liebiao.png\",\"thumbnail\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/21_jingzhui/shouye.png\"},{\"bonus\":\"1dayHealthPlanSign\",\"complete\":\"https://7niu-article.galaxyeye-tech.com/img/article/200326/1243008211976458240.png\",\"complete\":\"https://7niu-article.galaxyeye-tech.com/jiankang/jiankangjihua/7_yuanqi/wancheng.png\",\"thumbnail\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\",\"strip\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\",\"cover\":\"https://7niu-article.galaxyeye-tech.com/article/190522/580598994795761664/610311041892487168.jpg\",\"dayImage\":[1],\"details\":[[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"]],\"duration\":1,\"id\":\"8\",\"name\":\"1天计划测试专用\"}]}");
            record2.setEditor(null);
            record2.setEnable(1);
            record2.setUpdatedAt(DateTool.parseDateStr("2020-03-26 16:59:45",DateTool.TIME_PATTERN));
            record2.setVer("5.0.0");
            record2.setVerify(0);
            ManualConfig record3=new ManualConfig ();
            record3.setId(1227465942338900007L);
            record3.setAppid("4.00090");
            record3.setComment("相关文章推荐条件");
            record3.setConfEnv("dev");
            record3.setConfKey("articleRecommendation");
            record3.setCreatedAt(DateTool.parseDateStr("2020-03-05 14:37:58",DateTool.TIME_PATTERN));
            record3.setData("{\"condArr\":[{\"mainType\":[\"疾病\"],\"subType\":[\"新型冠状病毒肺炎\"],\"andLab\":[],\"orLab\":[\"全国\",\"健康科普\"],\"minQuality\":3,\"maxCount\":5,\"minCount\":5,\"addMainType\":[],\"addSubType\":[]}]}");
            record3.setEditor(null);
            record3.setEnable(1);
            record3.setUpdatedAt(DateTool.parseDateStr("2020-03-25 15:12:58",DateTool.TIME_PATTERN));
            record3.setVer("5.0.0");
            record3.setVerify(0);
            ManualConfig record4=new ManualConfig ();
            record4.setId(1227465942338900003L);
            record4.setAppid("4.00090");
            record4.setComment("健康小程序健康计划列表（待修改）");
            record4.setConfEnv("dev");
            record4.setConfKey("healthPlanDetail");
            record4.setCreatedAt(DateTool.parseDateStr("2020-03-02 11:51:42",DateTool.TIME_PATTERN));
            record4.setData("{\"healthPlan\":[{\"id\":\"1\",\"name\":\"7天压力释放计划\",\"duration\":7,\"cover\":\"https://7niu-article.galaxyeye-tech.com/article/190522/580598994795761664/610311041892487168.jpg\",\"details\":[[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309798804361216/613607293698707456.png\",\"https://7niu-article.galaxyeye-tech.com/article/190514/577740788608274432/579872518098128896.jpg\"],[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309803191603200/613605459332435968.jpg\",\"https://7niu-article.galaxyeye-tech.com/article/190514/577740780001562624/578097282579173376.jpg\"]],\"dayImage\":[1,2,1,2,1,2,1],\"bonus\":\"7dayHealthPlanSign\"},{\"id\":\"2\",\"name\":\"7天元气恢复计划\",\"duration\":7,\"cover\":\"https://7niu-article.galaxyeye-tech.com/article/190522/580599005499625472/thumbnail_610312410510659584.jpg\",\"details\":[[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309803191603200/613605459055611904.jpg\"]],\"dayImage\":[1,1,1,1,1,1,1],\"bonus\":\"7dayHealthPlanSign\"},{\"id\":\"3\",\"name\":\"7天补水保湿计划\",\"duration\":7,\"cover\":\"https://7niu-article.galaxyeye-tech.com/article/190522/580598994795761664/610311041892487168.jpg\",\"details\":[[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"],[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"],[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915824144384.jpg\"]],\"dayImage\":[1,2,3,1,2,3,1],\"bonus\":\"7dayHealthPlanSign\"},{\"id\":\"4\",\"name\":\"14天晚安好眠计划\",\"duration\":14,\"cover\":\"https://7niu-article.galaxyeye-tech.com/article/190522/580599005499625472/thumbnail_610312410510659584.jpg\",\"details\":[[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\",\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"],[\"https://7niu-article.galaxyeye-tech.com/article/190514/577740780001562624/578097282705002496.jpg\",\"https://7niu-article.galaxyeye-tech.com/article/190514/577740780001562624/578097282822443008.jpg\"]],\"dayImage\":[1,2,1,2,1,2,1,1,2,1,2,1,2,1],\"bonus\":\"14dayHealthPlanSign\"},{\"id\":\"5\",\"name\":\"14天发量拯救计划\",\"duration\":14,\"cover\":\"https://7niu-article.galaxyeye-tech.com/article/190522/580598994795761664/610311041892487168.jpg\",\"details\":[[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"],[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"]],\"dayImage\":[1,2,1,2,1,2,1,1,2,1,2,1,2,1],\"bonus\":\"14dayHealthPlanSign\"},{\"id\":\"6\",\"name\":\"21天脾胃养护计划\",\"duration\":21,\"cover\":\"https://7niu-article.galaxyeye-tech.com/article/190522/580599005499625472/thumbnail_610312410510659584.jpg\",\"details\":[[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\",\"https://7niu-article.galaxyeye-tech.com/article/190514/577740780001562624/578097282944077824.jpg\"],[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"],[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915824144384.jpg\",\"https://7niu-article.galaxyeye-tech.com/article/190514/577740780001562624/578097283053129728.jpg\",\"https://7niu-article.galaxyeye-tech.com/article/190514/577740780001562624/578097283183153152.jpg\"]],\"dayImage\":[1,2,3,1,2,3,1,1,2,3,1,2,3,1,1,2,3,1,2,3,1],\"bonus\":\"21dayHealthPlanSign\"},{\"id\":\"7\",\"name\":\"21天颈椎保护计划\",\"duration\":21,\"cover\":\"https://7niu-article.galaxyeye-tech.com/article/190522/580598994795761664/610311041892487168.jpg\",\"details\":[[\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"]],\"dayImage\":[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],\"bonus\":\"21dayHealthPlanSign\"}]}");
            record4.setEditor(null);
            record4.setEnable(1);
            record4.setUpdatedAt(DateTool.parseDateStr("2020-03-04 11:12:05",DateTool.TIME_PATTERN));
            record4.setVer("5.0.0");
            record4.setVerify(0);
            ManualConfig record5=new ManualConfig ();
            record5.setId(1227465942338900002L);
            record5.setAppid("4.00090");
            record5.setComment("健康小程序健康计划列表（待修改）");
            record5.setConfEnv("dev");
            record5.setConfKey("healthPlanAbs");
            record5.setCreatedAt(DateTool.parseDateStr("2020-03-02 11:51:42",DateTool.TIME_PATTERN));
            record5.setData("{\"healthPlan\":[{\"id\":\"1\",\"name\":\"7天压力释放计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309798804361216/613607293698707456.png\"},{\"id\":\"2\",\"name\":\"7天元气恢复计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309803191603200/613605459055611904.jpg\"},{\"id\":\"3\",\"name\":\"7天补水保湿计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"},{\"id\":\"4\",\"name\":\"14天晚安好眠计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"},{\"id\":\"5\",\"name\":\"14天发量拯救计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"},{\"id\":\"6\",\"name\":\"21天脾胃养护计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915824144384.jpg\"},{\"id\":\"7\",\"name\":\"21天颈椎保护计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"}],\"defaultHealthPlanId\":[\"1\",\"2\",\"3\"]}");
            record5.setEditor(null);
            record5.setEnable(1);
            record5.setUpdatedAt(DateTool.parseDateStr("2020-04-18 10:33:16",DateTool.TIME_PATTERN));
            record5.setVer("5.0.0");
            record5.setVerify(0);
            ManualConfig record6=new ManualConfig ();
            record6.setId(1227465942338900001L);
            record6.setAppid("4.00090");
            record6.setComment("健康小程序健康计划列表（待修改）");
            record6.setConfEnv("dev");
            record6.setConfKey("recuperateData");
            record6.setCreatedAt(DateTool.parseDateStr("2020-02-26 14:32:51",DateTool.TIME_PATTERN));
            record6.setData("{\"心情不好\":{\"name\":\"心情不好\",\"type\":\"当下热门\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xinqingbuhao.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xinqingbuhao.png\",\"intro\":\"情绪不好，心里感觉沉重，容易忧心忡忡、愁眉不展，甚至忧虑沮丧、唉声叹气、悲观失望，感到生活乏味、无精打采。 \\n情绪不好不但影响心理健康，还会影响身体，出现食欲不佳、抵抗力下降、疲倦无力等症状。 \\n尽管通常认为坏情绪会影响心情，但来自美国心理学协会的研究显示，那些认为消极情绪也对自己会有帮助、有价值等积极意义的人，他们的坏心情对身体产生的不好影响同样会更少。 \\n所以，即使心情偶尔阴雨也别担心，听听优美舒缓的音乐、用用小方法，心情很快就会亮起来~！\",\"reason\":\"1.季节变化:身体跟大自然一样有一定的规律，会随环境中光照量的变化来调节脑内的激素与神经传导物质。大多数人冬天容易忧郁，是因为光照变少时，会让脑内引起快乐的激素变少。 \\n2.懒惰不爱动:有氧运动能促进脑内分泌多巴胺、血清素与正肾上腺素等，能带来活力跟好心情的荷尔蒙。不爱运动且放假老是窝在沙发上看电视的人就比较容易忧郁。 \\n3.太常看朋友社交圈:刊登在《人格与社会心理学通报期刊》的调查发现，很多人浏览朋友脸书上的消息后，会产生「别人的生活都过得比我好」的错觉，因而导致莫名的沮丧。 \\n4.连续剧或连载小说有陪伴的效果：若每天同一时间收看，常会不知不觉对这些内容产生依赖感。一旦影集结束，生活就会像是突然失去重心一般。 \\n5.美白防晒过度:因为爱美不敢晒太阳，可能让缺乏维他命D的情况更严重；维生素D具有调节脑神经运作的功能，不少研究发现，缺乏维生素D可能会造成焦虑、紧张与心情低落。 \\n6.营养不均衡:身体缺乏叶酸或Omega-3脂肪酸，他们能调节脑内神经运作的功能，研究发现，体内缺乏这些营养素，罹患抑郁症的风险较高。 \\n7.服用某些药物：如口服避孕药、降血压、降胆固醇的药物可能会引起忧郁情绪。\",\"whenDoctor\":\"人嘛，有几天不开心的日子是很正常的。但如果你常常一连数日情绪低落，同时对平常热衷的活动也都打不起兴趣了，可以考虑去找心理医生聊一聊。特别是当你的睡眠作息、食欲发生改变，或抑郁情绪严重，甚至想要借助烟酒使自己放松时，请寻求心理医生的帮助。\",\"advice\":\"1)尽可能多在白天出门。室内照明完全无法达到日照光线的效果，照得再亮也没用。要想心情好，午休、空闲的时候，一定要多出去晒晒太阳光。 \\n2)研究显示，病毒性感冒后，免疫系统会被「捣乱」至一种更易发生情绪抑郁的状态。所以，注意保暖，避免身「心」感冒。 \\n3)适量运动。运动增加内啡肽的分泌，能使情绪由坏变好。当你呼吸变促、心跳加快的时候，就会忘记之前发生的淡淡不快了。 \\n4)搞清楚究竟为什么不开心。或许是一件小事带来的内疚感，可能是被小小地拒绝了，也可能是压力有点大，或者和亲密的人稍微疏远，甚至可能仅仅就是因为——饿了……这些原因都可能导致心情不好。搞清楚不开心的原因，才能通过沟通或调节自己走出低落的情绪。 \\n5)越是不想出门，越是不能宅在家里。无论是去风景好、空气好的地方亲近自然，还是去熙熙攘攘的地方「接地气」，反正是要走出门。\\n6)女性及年轻人相对而言更易出现季节性情绪低落。如果受不了雾霾和寒冷的天气了，就给自己一个奖励，去温暖、日照充足的地方度个假吧！  \\n7)正确看待那些「不良」的情绪。情绪不那么高涨时的你，反而更细腻、更会关注细节，思考也更入微；对遇到问题的思索，也会提升自己。\\n总之，偶尔的心情不好并不太坏，别让它影响你。\\n明天又是新的一天啦！\"},\"睡不着\":{\"name\":\"睡不着\",\"type\":\"当下热门\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/shuibuzhao.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/shuibuzhao.png\",\"intro\":\"再次看看床头的表……已经躺下好久了，怎么还是醒着? 无论多么困倦，却依然难以入睡?或是经常夜半醒来? \\n失眠是很常见的困扰，却也可能会为诸多健康状况敲警钟。 \\n总之，是时候做好“睡个好觉”这件人生大事啦！\",\"reason\":\"压力大、睡眠习惯不好、身体紧张等原因，都可能导致睡不着、梦多、睡不醒、睡不沉等睡眠困扰。\",\"whenDoctor\":\"如果失眠超过两周，且“自救”无效，或者失眠问题是由于生活中的大事件引起，又或者失眠已经引起胸闷、气短、精力严重下降等身体症状，请及时寻求医生和其他专业人士的帮助。\",\"advice\":\"●作息节律、生活工作、睡眠卫生、心理调节……生活中的方方面面都会影响到睡眠，从而对整个人的身心状态产生影响。 \\n●先从睡眠居室环境入手:卧室灯光是否太亮？或是隔音很不好？床垫太硬又或太软？要不就是枕头不舒服？ \\n●注意养成良好的睡眠卫生习惯，如:感到困意再爬上床、睡前一小时调暗灯光等。 \\n●想具体了解自己的问题和解决方案，试试我们的相关测试吧~！\"},\"受寒感冒\":{\"name\":\"受寒感冒\",\"type\":\"当下热门\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/shouhanganmao.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/shouhanganmao.png\",\"intro\":\"普通感冒是外感邪气而来，而很多时候是因为感受寒邪，也就是受寒引起的。但感冒是分阶段的，不同的阶段，身体表现不同，处理方法也要做相应的调整，这点很关键。 \\n感冒初起可能会有这些表现: \\n1.打喷嚏，流像水一样的清鼻涕，感觉稍微有点儿鼻塞；\\n2.怕风怕冷，甚至头痛，手脚较凉； \\n3.此时咳嗽是呛咳，声音清脆，声音很轻，咳嗽的声音就在嗓子那里，如果能咳出痰，痰是清的；\\n4.看看舌头:舌质是淡白的，不红； \\n这是寒邪刚刚入侵体表，及时处理，很快就可以恢复健康，否则寒邪往里走，很快就会发烧、咽喉肿痛等。\",\"reason\":\"西医认为，感冒可由细菌或病毒感染引起；一般情况下，成年人每年可能感冒2~4次。\\n从中医的角度讲，体内正气不足、体表卫气不固密、外感风邪寒邪都可引发感冒。 \\n这些情况下更容易感冒: \\n1.熬夜、疲劳、饥饿、营养不良的状态下； \\n2.由于气候突变、早晚温差增大、吹了冷风、穿衣少冻着了而感受风寒； \\n3.出汗后没有及时换衣服、出汗后吹风； \\n4.在人群多杂且较封闭的环境中久呆，都容易引发感冒。\",\"whenDoctor\":\"突发高热持续不退、呼吸困难、胸闷胸痛、剧烈咳嗽、咽喉异常肿痛、严重肠道反应如腹泻或呕吐，或症状10天都无法缓解时，应前往就医。\",\"advice\":\"1)打喷嚏是身体本能的排寒方式，不建议此时服用一些抑制喷嚏的西药，因为西药的主要作用是阻断神经传递，让身体麻痹，不再打喷嚏，最终只会把外寒留在体内。 \\n2)身体刚受寒的时候，不要用清热解毒的感冒药，咳嗽也不适合用川贝、百合、梨子之类性寒润肺的食疗方法，否则就会把寒气闭在身体里，相当于寒上加寒。\\n3)流鼻涕后，要用软的手帕或纸巾轻轻擦拭，然后抹上一层薄薄的护肤霜，以免皮肤受伤疼痛。\\n4)一旦发现怕冷，要及时增添衣服。出汗后，及时擦汗换衣，避免汗后吹风。\\n5)保证睡眠，适当喝些热水、热汤或热粥，让身体温暖起来，不强求饮食。\\n6)清淡饮食，不要吃辛辣、烘烤煎炸干燥的食物，以防生热上火；同时也不适合吃寒凉的食物，如水果、酸奶之类的，不利于驱除寒气。\\n7)酸味、涩味的食物不利于身体发散，食醋、泡菜、山楂、乌梅、酸枣、酸的柑橘等都不适合吃。\\n8)尽量少去人群密集的地方；去医院等可发生交叉感染的地方时要戴口罩。\"},\"风热感冒\":{\"name\":\"风热感冒\",\"type\":\"当下热门\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/fengreganmao.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/fengreganmao.png\",\"intro\":\"这个阶段的表现就是我们常说的风热感冒，是比较严重的阶段，一派热象:\\n1)身体会出现高烧发热，总想喝凉水解渴。\\n2)咽喉会红肿、疼痛，有些是吞口水时喉咙疼，不愿意吃东西；也有是两腮下疼，可能还会连着鼻子的末端都在疼。\\n3)此时咳嗽非常剧烈，咳嗽的部位很深，感觉是从胸腔里发出来的，痰黄且浓稠，甚至是绿色，甚至会有胸痛。\\n4)鼻涕黄稠，鼻塞严重。\\n5)舌质红，舌头前半部，尤其是舌尖颜色很红，甚至前半部舌体没有舌苔，都是红色的舌质；\\n6)精神状态差，乏力，喜欢睡觉或者烦躁。\",\"reason\":\"现代医学认为：\\n细菌或病毒感染。\\n中医认为:\\n1.如果外寒没有及时驱除，化为内热，并进一步深入，与身体的正气进行激烈的交战，从而表现出明显的热症。\\n2.本身体质偏热，或内有郁热，或吃了辛辣上火的食物之后，由于气候突变、寒暖失调、温热之邪侵犯体表，肺气失和所致。\",\"whenDoctor\":\"这个阶段，不适合在家自行处理，需要在医生的指导下用药。\",\"advice\":\"1)如果体温还没超过38.5°C，且体温上升较慢，可以在家尝试物理降温，比如洗温水澡、温热毛巾湿敷等。\\n2)一定要多喝温开水，如果不愿意喝水，可以用甜的梨子、橙子或西瓜榨成果汁，每次喝几口，少量多次，不应一次大量喝，伤脾胃；也可以喝一些清淡的萝卜白菜汤、米汤。\\n3)饮食不宜吃得过饱，宜吃辛凉清淡的食品，如菊花、茶叶、白菜、白萝卜、豆腐、荸荠之类，清内热。\\n4)忌吃辛热、甜腻的食物，如大葱、姜、辣椒、蒜、大枣、樱桃、榴莲、坚果、蛋糕等，以免加重内热。\\n5)如果体温超过38.5°C，且体温上升很快，应遵医嘱服用退烧药，1h后测体温。服药后观察有无体温骤降、大量出汗、软弱无力等现象。如有以上虚脱表现，一定要及时换掉汗湿的衣服，注意保暖，同时多饮温水。\\n6)这个阶段，一定要坚持用药，直到黄痰彻底消失、咳嗽减轻、不发烧。如果不等外邪清除干净，就停药，会导致病情反复。\"},\"消化不良\":{\"name\":\"消化不良\",\"type\":\"救急\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xiaohuabuliang.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xiaohuabuliang.png\",\"intro\":\"这里的方法尝试帮你改善偶尔的消化不良造成的困扰。没吃舒服，或吃撑了，觉得胃里胀胀的，甚至恶心、反酸，打嗝还会有难闻的味道。长期的消化不良，可以尝试以下方法缓解问题，但是根本还要调养好脾胃。\",\"reason\":\"1.医学上认为消化不良的原因难以精确地指出，对某些人来说，进食某些食物或饮酒会引起消化不良。通常以下情况会影响消化功能，引起消化不良。\\n-吃得太多，吃得太快，吃得太油腻。\\n-压力、生气、紧张、焦虑情绪时进餐。\\n-胃受寒或吃凉的食物。\\n-吃得太晚，吃饱就睡了。\\n2.中医称消化不良为[食滞胃脘]，一种是平日脾胃没问题，但偶尔饮食不节，暴饮暴食，影响了胃之腐熟功能，引起消化不良；一种是食量虽不过多，但因胃之功能素弱，而至饮食停滞难化。\",\"whenDoctor\":\"偶尔的消化不良提醒你注意生活和饮食的方式。但如果长期有消化不良，出现饭后腹胀、反酸、烧心等问题，则应引起重视。体检排除疾病，再找中医师调养脾胃。\",\"advice\":\"1.消化不良后最好的方法就是饿一饿，有饥饿感了，再吃。也可以吃些小米或大米熬到粘稠的米汤，不要再吃难消化的肉类、粗纤维的蔬菜、硬面饼、坚果等。\\n2.吃饱就睡，不但影响消化，对睡眠也有影响。睡前3小时最好不要吃东西。\\n3.吃饱后只适合散步这样温柔的运动。不宜剧烈运动，会影响脾胃的消化能力。饭后瘫，也是造成消化不良的原因之一，脾主运化，主四肢，吃饱不动，脾胃容易积食。\\n4.吃饭的时候不讨论会引起不愉快的事情。压力和坏情绪会影响你的消化能力，中医认为思虑伤脾，怒气伤肝，肝气不舒则脾胃运化受阻。\\n5.平素脾胃就不太好的人，吃饱后受凉和喝冷饮也会引起消化不良。\\n6.专心吃饭，享受美味，不要在吃饭的时候玩手机，专心致志，细嚼慢咽，消化液分泌更充分。\"},\"皮肤干燥\":{\"name\":\"皮肤干燥\",\"type\":\"常见问题\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/pifuganzao.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/pifuganzao.png\",\"intro\":\"秋风一起，镜子里的你，脸上是不是又开始「起皮」、出现皮屑？手臂上轻轻一划，就会留下一条白色划痕？\\n皮肤缺乏水分会导致皮肤发紧、个别部位干燥脱皮、洗澡过后全身发痒等症状，这些皮肤干燥的不适，现在就解决它吧！\",\"reason\":\"1)秋冬季节，或是北方的春季，由于环境气候变化，干燥多风，可能导致皮脂腺和汗腺分泌异常，使皮肤表面变得粗糙，抵抗力减弱。久而久之，形成习惯性干燥。\\n2)过度使用化妆品、清洁用品，致使皮肤表面角质层受损，也会使皮肤分泌油脂能力下降，导致干燥缺水。\\n3)日晒过多，紫外线会穿透表层皮肤，而深层皮肤受损将导致皮肤皱纹、松弛。\\n4)中医认为，皮肤干燥多由于血虚风燥，津液亏虚，失去了这层抵挡风邪的盾牌；而「肺主皮毛」，肺阴虚、肺燥，都会表现呈皮肤干燥问题。\\n5)睡眠不足、疲劳等，也可能导致健康失衡；而饮食过素，也可能由于动物油脂摄入不足而引发皮肤干燥。\\n6)随着年龄增长，皮肤新陈代谢减缓，皮脂分泌减少，皮肤保存水分的能力会下降，也是引起皮肤干燥的原因。\",\"whenDoctor\":\"绝大多数情况下，小方法加以生活方式的改变可以改善皮肤干燥状况。\\n如出现以下情况，请考虑就医:\\n1)无论如何努力，皮肤干燥仍毫无改善；\\n2)皮肤干燥伴有发红、较严重的皲裂；\\n3)皮肤干燥发痒影响睡眠；\\n4)由于抓挠，皮肤出现疼痛、感染；\\n5)皮肤出现较大面积的脱皮。\",\"advice\":\"1)请勿长时间洗热水澡，或蒸桑拿。因为热水澡、桑拿会导致大量出汗，降低皮肤保湿能力。\\n2)请勿过度清洁面部；勿用强碱性、刺激性的化妆品、清洁产品。\\n3)避免强风或燥热环境(如室内空调热风)的刺激。\\n4)饮食过素者，可适当增加饮食中的油脂类摄入，尤其是动物油脂的摄入。\\n饮食起居有度，对全身的健康永远都是重要的。\"},\"感冒咳嗽\":{\"name\":\"感冒咳嗽\",\"type\":\"常见问题\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/ganmaokesou.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/ganmaokesou.png\",\"intro\":\"如果有以下情况，可以试试这里推荐的方法:受凉要感冒，嗓子发痒，开始咳嗽。或者感冒好几天了，只有咳嗽总是反反复复。\\n过敏或其他慢性疾病引起的咳嗽则不建议参考以下内容。\",\"reason\":\"中医认为咳嗽和肺藏的气机不顺畅有关。“肺主气，司呼吸，上连咽喉，开窍于鼻，外合皮毛”，若风寒、风热外邪，或天气干燥等伤了肺气，则可能引发咳嗽。\\n感冒后的咳嗽基本分为以下几种:\\n—寒痰咳嗽:受寒后，咳嗽，痰液清稀\\n—热痰咳嗽:体内有湿热的人常见，咳嗽声音响亮，痰黄\\n—寒热交杂:感冒后期常见，痰黄白交杂\\n—燥咳:干咳无痰\\n西医认为咳嗽通常是上呼吸道病毒感染的症状，也就是鼻、鼻窦及气管的感染。\",\"whenDoctor\":\"如果咳嗽持续了2~3周，或者咳嗽伴随有发热、呼吸急促和血痰症状，应及时就医。\",\"advice\":\"1.受寒痰白的咳嗽，要注意保暖(尤其是咽喉和脖颈位置)，避免吃太多水果和寒凉食物，川贝、罗汉果这类寒凉润燥止咳的食物也不适宜，可能会加重寒咳。\\n2.痰多的咳嗽，要饮食清淡，少吃油腻和甜食。\\n3.感冒后的咳嗽反反复复，伴有痰多色黄，通常和脾胃消化不良、饮食厚腻有关，先清理脾胃食积，饿一饿，保持大便通畅，都会帮助更快改善咳嗽。\\n4.空气污染重的城市，推荐使用空气净化器，可以改善某些类型的咳嗽。\\n5.干燥的季节，房间要保持一定湿度(30%-50%)，减少因干燥空气刺激咽喉引起咳嗽。\\n6.在咽喉干燥疼痛时，要多喝水，可加蜂蜜或新鲜果汁。但要避免咖啡和苏打水。\"},\"痛经\":{\"name\":\"痛经\",\"type\":\"常见问题\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/tongjing.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/tongjing.png\",\"intro\":\"你的大姨妈，每个月是怎么疼你的？每个月那几天，感觉小腹被拧成一团，有一只无形的手在玩捏塑料泡沫的游戏，让你像“虾米”一样蜷缩在床上，全身发冷，甚至腰也疼得直不起来，有时可能还伴有呕吐和腹泻。快来快来，管管小腹里的“孙悟空”！\",\"reason\":\"原发性痛经的「罪魁祸首」是生理期时释放的前列腺素和其他免疫调节因子，它们会引起子宫收缩。如果收缩猛烈，就会压迫到周围的血管，导致痉挛疼痛。\\n中医认为，痛经的原因可根据痛感的不同进行初步判断:\\n1)胀痛多由于气滞不畅；\\n2)坠痛多由于气虚气陷；\\n3)刺痛多鉴于寒凝血瘀；\\n4)腹部冰凉则是血虚体寒。\\n总而言之，痛经的发生一是由于气血运行不畅，或由于经前受寒、饮食太凉，或由于压力大、焦虑郁闷而肝郁气滞，导致血瘀，「不通则痛」；二是由于气血不足，失于濡养，「不荣则痛」，多见腹部隐痛，严重者会由于中气下陷，引发坠痛，而此类人平素容易气虚畏寒，容易手脚冰凉。\",\"whenDoctor\":\"痛经分为原发性与继发性。如果疼痛剧烈、持续时间长，建议就医排除器质性疾病的可能。在此，我们仅提供针对原发性痛经的解决、缓解办法。继发性痛经通常与子宫内膜异位、子宫肌瘤、卵巢囊肿、盆腔炎等疾病有关，可以参考以下方法缓解，而解决疾病还需就医。\",\"advice\":\"除了「喝点热水」以外，你还可以:\\n1)平素体寒的女生一定要注意忌食生冷，注意保暖。经期之前尤是。\\n2)避免抽烟或过量饮酒，它们的刺激可能会加重痛经。体质偏寒的女生，如果平日有喝酒的习惯，可以在经期适度饮用一点黄酒。\\n3)经前及经期减少饮食中的盐分，减少咖啡因的摄入。过多摄入盐分、咖啡因会加重盆腔水肿，导致痛经加剧。\\n4)经期饮食宜清淡，避免辛辣油腻。清淡饮食可以为身体省下消化辛辣、煎炸食物所消耗的能量，让身体的能量集中把经血顺利排出体外。\\n5)避免久坐，适量的运动有助于改善盆腔血液循环，改善痛经。\\n6)调节情绪，舒缓压力，保持好心情，对全身健康都有好处~\"},\"月经延期\":{\"name\":\"月经延期\",\"type\":\"常见问题\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/yuejingyanqi.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/yuejingyanqi.png\",\"intro\":\"等啊等，等啊等……姨妈怎么还不来？尽管来了就会折腾你，但不来却又很担心！除特殊生理状况外，最正常的女性月经周期在28~30天，在此基础上加减5天(21~35天)也属允许范围，周期长短因人而异。如果月经周期错后7天以上，甚至错后3-5个月一行，即可算月经延期，医学上称为“月经后期”。\",\"reason\":\"造成月经后期的原因很多。除去一些影响内分泌的疾病，整体的身体状况、精神压力等心理因素，以及生活方式、生活环境改变等等，都可以引发月经推迟。\\n对于青春期女孩，月经延迟较为常见；随着年龄的增加，性腺轴发育成熟后症状会逐渐缓解。\\n中医认为，月经后期主要由于精血不足引起。常见的原因如下:\\n1)肾气虚:在精血亏少，导致月经延迟，量少、色淡黯、质清稀的同时，还有腰膝酸软等表现。\\n2)气虚:这类原因导致的月经延迟，常会伴有怕冷、易感冒、手脚凉等表现。\\n3)血虚:经期错后、量少色淡。此类型的女生还可能面色苍白或萎黄，心悸失眠，甚至容易头晕。\\n4)血寒:引起月经延迟的血寒可分为虚实两类。虚寒是指素体阳虚，气血虚少的内寒；实寒是指感受寒邪，而寒凝血滞的情况。\\n5)气滞:这类女生多见经前乳房胀痛、烦躁等特点，易因情绪紧张、压力大而导致月经延后。\\n在生活中，原因可具体体现为以下几点:\\n1.过度节食。\\n有研究表明，体内脂肪至少达到体重22%，才能维持正常的月经周期。过度节食，由于机体能量摄入不足，体内大量脂肪和蛋白质被耗用，致使雌激素合成障碍，影响月经来潮，甚至经量稀少或闭经。\\n2.受寒。\\n经期受寒冷刺激，会使盆腔内的血管过分收缩，可引起月经过少、经期错后，色紫黯带血块，小腹冷痛拒按，甚至闭经。因此，日常生活中应注意防寒保暖，饮食不宜过凉，经期更要防寒避湿。\\n3.情绪波动、压力。\\n月经是卵巢分泌激素刺激子宫内膜后形成的，卵巢分泌激素又受脑下垂体和下丘脑释放激素的控制，所以无论是卵巢、脑下垂体，还是下丘脑的功能发生异常，都会影响到月经。所以，精神压力大、工作任务重、精神过度紧张、情绪低落等，这些都可能是导致月经推迟的原因。日常生活中压力大或多或少都会影响到女性的月经，因此需要调节情绪，保持乐观的积极态度。\\n4.缺乏维生素E。\\n维生素E多存于各种烹调油中，饮食过于清淡会造成维生素E的缺乏，导致月经周期不正常，所以此时补充维生素E显得尤为重要。\",\"whenDoctor\":\"一些盆腔、子宫和卵巢疾病可能引发月经后期，因此，定期的盆腔检查十分必要。\\n此外，如果发生以下情况，也建议就医:\\n1)月经突然停止超过90天以上，并且没有怀孕。\\n2)随着月经延期，伴有其他身体不适，如腰酸腿软、头晕目眩、情绪急躁等，可咨询中医给予全身性的调养。\",\"advice\":\"从饮食起居，到情绪工作，生活的方方面面都可能影响月经:\\n1)停止节食减肥。如果节食减肥到月经延迟，请立即停止。否则，雌激素合成受阻，月经迟迟不来，甚至会出现闭经。\\n2)注意保暖。尤其在经期将至的时候，避免饮食生冷。\\n3)选择合适方式缓解压力，保持心情舒畅。情绪不佳时，找到适合自己的方式释放和转移，如做运动、吃美食、和闺蜜逛商店等等~\"},\"小儿拉肚子\":{\"name\":\"小儿拉肚子\",\"type\":\"常见问题\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xiaoerladuzi.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xiaoerladuzi.png\",\"intro\":\"●大便次数明显多于往常。\\n●可以表达的孩子会说肚子疼，不会说话的宝宝可能用哭闹表示。食欲差。\\n●大便性状明显异常(以下一种情形或几种同时出现):\\na.酸臭或腥臭\\nb.完全不成形，呈稀样或蛋花水样\\nc.发黑或发绿，或汤水呈浅黄色\\nd.有像痰一样的黏液\\ne.有泡沫\\nf.有明显奶瓣或明显油腻\\ng.明显含有食物残渣\\n●有时会伴有发热、呕吐等症状。\",\"reason\":\"现代医学:\\n1)病毒感染、细菌感染多见。也可能有真菌或寄生虫感染。\\n2)食物不耐受(乳糖不耐受或喝了太多果汁等)、过敏。\\n3)药物导致，例如抗生素或中成药。\\n4)注射或服用疫苗后，可能出现腹泻。\\n传统医学:\\n1)肠胃感冒或肚子受凉。\\n2)积食或消化不良(过度喂养)\\n3)水土不服(换环境的情况下)。\",\"whenDoctor\":\"出现以下症状，请及时就医:\\n●便色黑或有明显血丝。\\n●灰白色陶土样。\\n●高烧持续24小时，吃了退热药不管用。\\n●严重的呕吐。\\n●出现脱水症状:口唇干燥、无精打采甚至嗜睡昏迷、眼窝囟门凹陷、无泪(尿)少泪(尿)等现象。\\n●伴随其他过敏症状，例如呼吸困难、皮疹或口唇肿胀、放屁等。\\n●根据家里的情况判断，是否可能是食物中毒或吃了不干净的东西。\\n●1岁内发热腹泻后一个月大便性状没恢复正常的，可能是继发乳糖不耐受，可请医生进行专业判断，遵医嘱调理。\",\"advice\":\"[拉肚子期间]\\n1)注意保暖。不要再饮食生冷；如果可以进食，吃少量、容易消化的食物，少量多餐，不要给肠胃负担太大。避免吃高糖和粗纤维食物。\\n2)补水，防止脱水。饮用补液盐，需按照说明书或医嘱操作。\\n3)小宝宝要注意臀部皮肤护理:每次大便后清洗臀部，蘸干水分后自然晾干或用吹风机烘干，涂抹润肤油(推荐使用椰子油)。\\n4)腹泻孩子的用品、餐具要与其他家人的分开；腹泻孩子的餐具注意消毒。注意孩子的口腔卫生。\\n[预防]\\n1)不论孩子还是大人，勤洗手。进食前、大便后、外出归来一定要认真洗手。不使用他人的餐具。\\n2)注意穿衣保暖和饮食不要贪凉，夜里盖好肚子。\\n3)不要乱七八糟的食物混着吃。\\n4)注意孩子的口腔卫生。很小的宝宝喜欢咬东西，要注意定期消毒孩子的玩具和物品。\"},\"小儿鼻塞\":{\"name\":\"小儿鼻塞\",\"type\":\"常见问题\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xiaoerbisai.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xiaoerbisai.png\",\"intro\":\"●孩子鼻子里呼吸声音粗。呼吸一般来说是没有那么大声儿的，有明显的声音时就说明鼻腔不通畅。\\n●孩子总爱张着嘴用嘴呼吸。这说明他鼻子不通气儿，本能地就会改用容易的方式换气。说话有鼻音。\\n●交替性鼻塞:侧卧时位于下侧的鼻腔常阻塞加重。转卧另一侧后，刚才位于上侧没有鼻塞或鼻塞较轻的鼻腔，转到下侧后出现鼻塞或鼻塞加重。而刚才位于下侧的鼻腔鼻塞减轻。\",\"reason\":\"现代医学:\\n小儿鼻塞多由感冒所致，也可见于鼻炎、鼻窦炎或过敏症发作(粘膜水肿，致使鼻道变窄；粘液过稠，堵塞鼻咽及鼻道)。\\n传统医学:\\n鼻者，肺之窍。外感风寒、肺热内火皆可致。一般解表、驱寒、祛风、清肺后可缓解。\\n还有多数情况是因为孩子粘稠鼻涕过多，或被大块的干燥鼻屎堵住，从而出现呼吸不畅或声音加粗。\",\"whenDoctor\":\"如伴有高热寒颤、鼻痒难忍或鼻塞严重，以及反射性头痛、持续咳嗽、头沉耳堵、胃肠道不适等症状，请尽快就医。\",\"advice\":\"[受寒者]\\n注意后脖颈、前胸、后心及腹部保暖。较小的孩子注意穿袜子。玩儿时及时擦汗，不要在有风的地方换衣服。\\n[过敏者]\\n尽量避免或减少接触过敏原，可以有效减少或减轻症状。例如:\\n1)花粉季节尽量减少外出，外出戴口罩，开车的时候不要开窗。\\n2)花粉季节不要在室外晾晒被褥、毛巾、衣物等。\\n3)花粉季节室内和车内使用空气净化器。早晨和花粉比较严重的日子、干燥或有风的日子减少外出。\\n4)家庭定期除螨。\\n5)尽量不新养宠物。如果已经有宠物的家庭，注意不要让它们进入卧室，勤给它们清洗。\"},\"口腔溃疡\":{\"name\":\"口腔溃疡\",\"type\":\"头面五官\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/kouqiangkuiyang.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/kouqiangkuiyang.png\",\"intro\":\"它又出现了！那个在舌头下、脸颊或嘴唇内、牙龈上，或者软腭上出现的口腔溃疡！它边界清楚、中央凹陷、带着灼痛、表面覆盖灰白或黄色假膜，周围红而微肿。而你，则小心翼翼，不敢吃了咸的、热的或任何刺激性的食物，不然就会变本加厉得更疼起来。\",\"reason\":\"1)通常认为“上火”、缺乏维生素B族和锌、铁等微量元素和口腔溃疡有关。中医认为口腔溃疡的“上火”主要由燥、火两邪引起，又有虚火、实火之分，易由饮食不节、情志过激引发。\\n2)饮食不节，过食辛辣肥厚之品也可导致口疮发生。\\n3)女性在月经期发生口腔溃疡，可能与体内黄体酮水平增高而雌激素(孕酮等)的水平降低所致。\\n4)精神紧张、情绪波动、睡眠状况不佳的情况都可能诱发口腔溃疡。\",\"whenDoctor\":\"口腔粘膜的免疫机能缺失，导致局部免疫力低下，使长期潜伏在口腔粘膜中的病毒诱发炎性反应。所以如果频发口腔溃疡，值得重视。\\n同时，根据病史特点，如有频繁复发、难以自愈(一般超过10天)等表现，或溃疡面不红、不肿、不痛，则需引起注意，及时就医\",\"advice\":\"平日饮食中注意维生素B族和C族的补充，最好的办法是:多吃一些水果蔬菜！尤其是对于好发口腔溃疡的人群，更应注意勿过食辛辣厚味，也应避免压力过大，以免诱发。\"},\"口臭\":{\"name\":\"口臭\",\"type\":\"头面五官\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/kouchou.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/kouchou.png\",\"intro\":\"口臭，简直是一种“有口难言”的尴尬，一张嘴就带出一股难闻的气味，和熟人兴致勃勃聊天时人家却表情尴尬，步步退避；谈工作时，别人掩鼻皱眉；连最亲密的人也不愿“零距离”接触。\\n有趣的是，65%的人可以感知自己的口臭，另外35%的人需要别人委婉告知。\\n教你一个最简单的判断方法:向手心哈气或闻闻牙线的残垢，也可用棉签、牙签或小勺在舌苔或牙缝上刮一下，一闻便知。\",\"reason\":\"1.中医认为，口臭是“上火”的表现。通常是胃火大、积食或脾胃湿热，使胃气上逆而引起口臭。胃火大者，多因过食辛辣，常伴有牙龈肿痛或口腔溃疡、便秘口渴；积食者多因暴饮暴食，常伴有厌食、腹胀、吐酸水；脾胃湿热者多伴有口苦、恶心、大便黏马桶等。\\n2.经常愤怒的人很容易有口臭。也是属于中医“上火”的范畴，发怒引动肝火，肝火可传脾胃。而心理压力过大时，天然“漱口水”——唾液明显不足，发干的口腔会变得脏兮兮，于是异味很快产生。\\n3.胃肠道疾病如消化性溃疡、慢性胃炎等；长期便秘的人，会因体内产生的有害物质不能及时排出，而引起口臭。\\n4.口腔疾病。85%的长期口臭其实源自口腔本身的问题。有龋齿、牙龈炎、牙周炎及平时口腔不洁者，口腔内容易滋生细菌而产生口臭。\\n5.服用使唾液分泌减少的药物，如某些镇静药、降血压药、阿托品类药、利尿药以及具有温补作用的中药等，易导致口臭。\\n6.口臭还是人体衰老的征兆，因为人过中年，口腔的自洁作用降低。年龄越大，口臭现象越严重，60岁人群发生口臭现象比年轻人高出5~7倍，过了70岁，95%以上的人存在口臭现象。\",\"whenDoctor\":\"长期的口臭看起来是个小毛病，却可能是某些疾病的信号:如口腔、胃肠道等多种疾病。如果口气有烂苹果味、或鱼腥味、或硫磺味，一定要尽快就医。\",\"advice\":\"1)饮食多样化，多吃新鲜水果蔬菜(莴笋、大白菜、西红柿、梨、苹果、萝卜、胡萝卜、柚子、猕猴桃等)、瘦肉、豆腐、谷类，有利于口腔健康。\\n2)少食多餐，应吃易消化、软烂的食物，细嚼慢咽，不要断顿；吃七八分饱即可，以防积食。生冷、干硬、不易消化的食物要少吃。\\n3)多喝水以保持口腔湿润，不要用咖啡、汽水、酒、碳酸饮料和橘子汁之类的酸性饮料代替，因为这些饮食会导致口腔内细菌快速繁殖。\\n4)少吃大蒜、洋葱等气味较重的食品，吃完以后最好咀嚼一些香芹、香菜、薄荷等，可去除口腔异味。\\n5)少吃蔗糖、精加工食品、过热、辛辣油腻等容易上火的食物。\\n6)养成早晚刷牙、饭后用茶水漱口的习惯。经常用牙刷柄刷舌头表面的舌苔，可除去舌头上的死细胞；用牙线除去牙齿间的食物残渣，每天至少一次；经常使用不含抗菌素的漱口水。\\n7)坚持半年洗一次牙。即使我们每天都很认真地刷牙、漱口，仍免不了牙齿中会存在牙菌斑和牙石，达到一定程度都会引起口臭。\\n8)锻炼不仅能够促进消化，而且可以缓解压力、焦虑等容易导致胃火上升的负面情绪。每天散步半小时，有利于消化系统和牙龈的健康。\\n9)学会减压，压力大时，唾液分泌不足也会引起口臭。当自己感觉愤怒时，马上离开现场，深呼吸，避免大发雷霆；心情压抑郁闷时，要想办法宣泄出来，调整好心态。\"},\"嘴唇干裂\":{\"name\":\"嘴唇干裂\",\"type\":\"头面五官\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/zuichunganlie.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/zuichunganlie.png\",\"intro\":\"嘴唇干干的，还经常起皮，喝多少水也不管用，抹完护唇膏只滋润一小会儿，而且越用润唇膏，起皮的现象越严重。有时候一笑嘴唇就开裂，吃东西嘴巴都不敢张得太开，说话大声点结痂的地方也会裂开。\\n嘴唇干裂不仅容易产生唇纹，不美观，还预示脾胃功能不好。\",\"reason\":\"1.“脾开窍于口，其华在唇”，意思是脾合肉，唇为肌肉组织，口唇的色泽与脾的运化有密切联系。一般来说，脾气旺盛，气血充足，唇色红润饱满；脾气虚，则嘴唇颜色淡白不红、嘴唇瘦薄、干裂。\\n2.平素嗜食辛辣，或情志不遂，气郁化火导致胃阴耗伤，内生虚热，也会引起嘴唇干裂，还可能伴有食欲不佳、口燥咽干、大便干结、干呕等症状。\\n3.嘴唇部位是粘膜，没有汗腺和唾液腺，干燥多风的季节容易发干，春秋冬季都常见。\\n4.好发于爱舔唇、咬唇、用手撕嘴上死皮的年轻人。因为口水蒸发时会带走嘴唇内部更多的水分，同水分蒸发后唾液里的淀粉酶就粘在唇上，引起深部结缔组织的收缩，唇粘膜发皱，因而干燥得更厉害。\\n5.有可能是身体缺少维生素B2。一般嘴角开始干裂，口角有白色分泌物，是缺少维生素的表现。\\n6.口红中的石蜡、色素都会带走水分；护唇膏中的某些化学成分(如丙基乙二醇、水杨酸盐)也可以引起唇干裂；一般牙膏中含有的十二烷基硫酸钠，会引起唇干裂，所以要尽量避免让嘴唇接触到。\",\"whenDoctor\":\"如果通过正常的护理，口唇干仍不能短期缓解，尤其症状超过三个月的，应到医院做全面检查确定是否是其他疾病所引起的口唇干。\",\"advice\":\"1.常吃一些健脾胃、补益气血的食物，如山药、大枣、党参、鲈鱼、猪肚、乌鸡、黑豆、小米等。\\n2.戴口罩。外出戴个口罩能挡住风吹，保持嘴唇的湿度。改掉舔嘴唇、咬嘴唇、撕嘴皮的习惯。想想你是因为紧张而舔嘴唇，还是因为嘴唇干呢？找对原因，才能有的放矢。\\n3.润唇膏使用太过频繁会导致嘴唇自身的屏障能力下降，皮脂腺分泌降低，每天2~3次就可以了；涂口红前可涂抹一层具有修护保湿功能的润唇膏；擦去口红时，最好用唇部专用的卸妆油或橄榄油，以保护唇部皮肤。\\n4.多喝温水，忌吃辛辣、燥热性食物，如辣椒、大蒜、生姜、大葱、洋葱、韭菜、芫荽、胡椒、桂皮等；甜食和肥腻的食物也要少吃，如蛋糕、巧克力、糖、奶油、肥肉、油炸之食等，否则会加重内热。\\n5.多吃一些能生津增液、富含维生素的食物，如梨子、橙子、猕猴桃、西红柿、黄豆芽、油菜、小白菜、白萝卜、豆腐、豆浆、藕、葡萄、甘蔗等，以助滋阴润燥，补充维生素。\\n6.多吃富含维生素B2的食物。食物中以动物肝、肾、心等维生素B2的含量较高，其次是奶及其制品，禽蛋类、豆类及其制品、谷类。尽量做到膳食平衡，荤素搭配。\\n7.可以在家里或办公室里放一个加湿器，可以提高室内空气的湿度，有助于嘴唇保持湿润。\\n8.最好选择天然原料的润唇膏，成分越简单越好，例如凡士林、甘油、各种维生素等。不要选气味太香、颜色比较鲜艳的，因为里面必然含有许多化学添加剂。\"},\"烂嘴角\":{\"name\":\"烂嘴角\",\"type\":\"头面五官\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/lanzuijiao.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/lanzuijiao.png\",\"intro\":\"天气一干燥，嘴巴先知道。美食当前，张大嘴巴……啊！好疼！嘴角不知何时，被寒风剌出了小口子。\\n●在口角部位易出现潮红、起疱，发生糜烂、裂口、结痂等症状。\\n●多伴有烧灼和疼痛感，张口时容易引发出血。\",\"reason\":\"1)口角炎与缺乏铁、维生素B2、叶酸等微量元素，以及感染有关。\\n2)由于嘴唇不适而反复舔会加重症状。因为唾液中有蛋白质、淀粉酶等物质，水分蒸发后，淀粉酶粘在嘴唇上，反而使干燥程度更严重。\",\"whenDoctor\":\"自感不适加重，请随时就医。\",\"advice\":\"如果唇部不适，切忌舔嘴唇！请使用唇油、润唇膏等护唇缓解。\"},\"眼睛干涩\":{\"name\":\"眼睛干涩\",\"type\":\"头面五官\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/yanjinggance.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/yanjinggance.png\",\"intro\":\"“四十四，眼生刺”，意思是说人一般到了40岁左右，眼睛容易干涩、视物疲劳。这是因为随着年龄的增长，体内激素水平改变、身体机能退化导致的。但现在从学生到办公室白领，大多数人都发生过：眼睛不红肿，只是感觉干涩不爽，容易疲劳，有时候还有刺痒感。\\n即便现在只是一个小症状，也别不当回事，长期如此可能会诱发别的眼疾。\",\"reason\":\"1)最常见的原因就是长时间地看电脑、手机、驾车、读书时，眼睛一眨都不眨。因为眨眼有助于泪水的分泌和分布，眨眼次数少了，直接导致泪水的量减少，而暴露在空气中的泪膜会快速蒸发，失去对眼球的保护力。\\n2)中医认为眼睛干涩是由于阴亏血虚造成的。可能是读书用目太过，久视伤血；或嗜酒纵欲，阴精亏损；或悲哀哭泣，久而耗液；或忧思伤脾，营养吸收来源不足。\\n3)在早春、秋冬等干燥多风季节多发，因燥邪侵袭人体，机体阴精受损，不能滋养眼目。\\n4)长时间戴隐形眼镜会使泪液分泌减少，因此戴隐形眼镜的人总会感觉眼睛干干的。\\n5)空调除了调节温度之外，还会抽湿，减少了空气里水分的含量。在这种干燥的环境中，泪膜蒸发率增加，容易使眼睛发干、发涩。气候干燥的秋冬季，为眼睛干涩的高发季节。\\n6)长期使用一些药物，如抗组胺药、降血压药、镇静剂和抗抑郁药，也会引起或加重干眼症状；长期使用某类眼药水(如某些治疗视疲劳、青光眼的眼药水)，也可能造成泪液分泌不足而眼干不适。\",\"whenDoctor\":\"经过用眼习惯的改变，及家庭护理方法，还是没有改善，最好到医院，请医生帮忙查找原因。\",\"advice\":\"1)热敷眼睛，用毛巾沾高于体温但不烫的水，闭眼敷于眼睛上，每次10分钟左右，早上最好能做一次，可帮助潴留于眼睛的分泌物排出。\\n2)平时经常吃富含维生素A、E的食物，有护眼的作用，如动物肝脏、胡萝卜、苋菜、菠菜、韭菜等。维生素B族是视神经的营养来源之一，宜常吃芝麻、大豆、鲜奶、小麦胚芽等食物。\\n3)用眼一小时休息一会儿，闭目养神，或瞭望远处，或看看绿色植物、图片。养成多眨眼的习惯。正常人每分钟眨眼约20次，而在睁眼凝视变动快速的电脑屏幕时，眨眼次数会减少到每分钟4~5次，造成泪液分泌严重不足。\\n4)眼睛近视者，提倡戴有形眼镜；电脑工作者，与电脑距离尽量保持在60厘米以上，使视线能保持向下约30度，可以使颈部肌肉放松，并且使眼球表面暴露于空气中的面积减到最低。\\n5)眼睛干痒时，要注意用眼卫生，勤洗手，不要用手揉搓眼睛，特别是流感、急性眼结膜炎等传染病高发季节。\\n6)使用不含防腐剂的人工泪液，点人工泪液不要过频，一天最好不超过6次。因为如果一天超过6次以上，就会把正常的泪膜冲走，从而加重症状。某些非处方的滴眼药，不要连续使用超过3~5天。\\n7)尽量少使用空调，使用空调要定时开窗通风，至少每2小时通风30分钟。空气干燥时，用加湿器使室内湿度保持在60%左右。\"},\"迎风流泪\":{\"name\":\"迎风流泪\",\"type\":\"头面五官\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/yingfengliulei.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/yingfengliulei.png\",\"intro\":\"平时眼睛不红不肿痛，也不流泪，但遇风眼泪就会不由自主地流出来，无风则止。或仅在冬季或春初时遇寒风刺激才流眼泪。\\n这种状况出现的时间久了，也会对眼睛不好，视线变模糊，眼睛会发红发炎，让你非常难受。\",\"reason\":\"1)西医一般认为是泪道附近有慢性炎症，引起泪道狭窄，或因老年人眼轮匝肌、泪小管和泪囊自身弹性扩张功能减退引起的。\\n2)中医认为，目为肝窍，泪为肝液，肝肾同源，故迎风流泪多与肝肾两经有关。若肝肾两虚、气血不足，则易出现；若肝经郁热，肝火旺也容易出现。\\n3)现代人由于过度使用电子产品，又经常熬夜，休息时间不足，眼睛长期处于疲劳状态，对冷风敏感。\",\"whenDoctor\":\"如果症状延续时间长，且导致眼角红肿、发痒、眼屎增多，应及时就医。\",\"advice\":\"1)风沙天气外出时应佩戴墨镜或眼镜，以防止眼睛受冷空气刺激或异物落入眼内。\\n2)流泪时，用手绢或纸巾向上并偏向鼻侧轻轻擦拭。\\n3)注意用眼卫生，勤洗手，不用脏手揉眼睛、擦眼泪；不与别人共用毛巾和脸盆。\\n4)女性朋友，在流泪期间，慎用睫毛膏、眼影等化妆品。\\n5)平时常吃富含维生素A、B的食物，如胡萝卜、菠菜、红枣、芝麻、大豆、鲜奶、麦芽等。\\n6)尽量不要熬夜，避免用眼过度。减少看电子屏幕的时间，每小时闭目养神5分钟。经常吃些桑椹、酸枣仁，以养肝阴。\"},\"掉头发\":{\"name\":\"掉头发\",\"type\":\"头面五官\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/diaotoufa.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/diaotoufa.png\",\"intro\":\"头发在偷偷的减少，尤其是秋、冬季更为常见。洗澡后、梳头时，发现头发掉得有点多? 99%的人都会有掉头发的问题，但是如果超过每天50~100根的正常数量，下水道口甚至会被堵上，梳头时一抓一把，一段时间后你才惊觉扎辫子都变细了，也可能会表现为明显的发际线后退，就值得你关注了。\",\"reason\":\"头发和指甲、皮肤一样是有一定的生长、死亡周期的，正常人每天50~100根头发脱落。《黄帝内经》云:“女子……五七阳明脉衰，面始焦，发始堕。”中西医都认为在健康的情况下头发越来越少是人体正常老化的一部分。\\n但营养不良、药物、精神压力、减肥、妊娠、不正确的头发护理、疾病等都可能让头发加速脱落。\\n中医认为，头发是人体的树叶，当树根(人体)营养不佳或旱(肾精不足、血虚风燥)或涝(湿浊内盛、头面出油)时，掉头发会加重。细细道来，原因一般如下:\\n1)发为血之余，而肾藏精，精化血，因此，肾精不足，阴血耗伤，不能濡养毛根，因而毛根干涸或发虚脱落；\\n2)肥甘厚味伤胃损脾，脾胃不运，湿浊内盛，侵蚀发根，黏腻而脱落。具体可表现为皮脂腺分泌过盛，头面出油多。\",\"whenDoctor\":\"脱发可能是某种疾病的讯号，如伴有其他身体不适，请联络医生检查。头发突然成斑型脱落可能由压力或遗传因素导致，一般会在几周到几个月内自然长出，无需治疗。\",\"advice\":\"1.对于阴血亏虚的脱发，保证营养，补精养血的食物对改善掉头发有帮助:黑芝麻、核桃、枸杞、黑豆、大枣、山药等都可益气养血。减肥、疲劳导致的掉头发尤其要注意饮食。\\n2.对于湿浊内盛的掉头发，则需少吃油腻、甜食、奶酪等生湿的食物，尤其是头发容易出油的人。平时可以多吃一些香菜、花椒、藿香、薄荷等芳香祛湿的食物。\\n3.调整心态，压力会影响荷尔蒙平衡，加重脱发。\\n4.梳头，像按摩一样梳到头皮，梳至头皮微微发热为度。长期坚持，能促进头发生长。\\n5.避免发型太紧，影响头皮气血循环。\\n6.洗头发的水温不要过高，温暖不烫为好。洗完头发一定要吹干再出门或睡觉。\\n7.养成每天泡脚的习惯。用热水泡脚，有助于排除体内湿气，尤其适合头发容易出油的人。\"},\"解酒\":{\"name\":\"解酒\",\"type\":\"救急\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/jiejiu.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/jiejiu.png\",\"intro\":\"酒喝多了，感到头晕脑胀、心跳加速，甚至恶心呕吐。第二天醒来还晕乎乎地宿醉。以下这些方法目的在帮你舒服一些，并不试图增加你的酒量。\",\"reason\":\"过量的酒精在体内会对中枢神经系统造成抑制。最初会影响人的思维、情绪和判断；达到一定量后，会影响语言和肌肉协调能力。\\n中医认为“其味辛甘，升阳发散，其气燥热”，多饮则损人，动肝火生湿热。常见的醉酒反应:头晕、恶心、呕吐、心慌，与湿热蕴蓄、困阻上焦(心肺)、中焦(脾胃)有关。\",\"whenDoctor\":\"\",\"advice\":\"1.喝酒前吃些食物，一是对胃黏膜形成保护，二是减缓酒精吸收速度，三是增加帮助酒精代谢的营养物质。如蛋白含量高的饮品:豆浆、酸奶、牛奶。富含果胶的果蔬:南瓜、苹果、菜花、山楂。淀粉类食物:五谷、豆类，据说饮酒前嚼些馒头，能让你酒量增大。富含维生素B的食物:蘑菇、蛋黄、粗粮、内脏等。\\n2.维生素B，维生素C，酒前、酒后都吃几片。帮助加速酒精分解。\\n3.不要混饮，虽然原因不明，但实践证实，不同种类、度数、品牌的酒混合饮用，人更容易醉。\\n4.醉酒后，可以喝温水，但不要喝加重肝脏负担的咖啡、可乐、提神饮料。\\n5.喝白酒后，可喝性凉的鲜榨果汁或水果煮汤，解酒醒酒:萝卜、荸荠、甘蔗、梨、橙、柚子。喝冰镇啤酒后，脾胃不适，可以喝些温热的红糖水。\\n6.第二天饮食清淡，不宜再吃油腻的食物，可以吃些稀粥、软面。\\n7.“甘则缓”喝酒前后可以喝些甜汤，旧法用龙眼肉煮汤，可以醒酒。古时新郎预防被灌醉，先用龙眼肉煲汤代饮用。也可以用葡萄糖、蜂蜜冲水饮用。\"},\"流鼻血\":{\"name\":\"流鼻血\",\"type\":\"救急\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/liubixue.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/liubixue.png\",\"intro\":\"那股温热的、带着铁锈味的液体，出来了……它就要流出来了！流鼻血尽管看上去很吓人，但多数情况并非严重原因所导致，一般血量不大，也可迅速止住。超过90%的鼻血发生在鼻腔前方，较易控制，在2岁~10岁的儿童中间常见，秋季或干燥天气时易发。\",\"reason\":\"呼吸道非常容易被燥气侵扰，儿童尤其易表现为咳嗽、喉咙干、流鼻血等。这是由于鼻腔黏膜干燥，鼻内微细血管破裂而流鼻血的情况。\",\"whenDoctor\":\"如果频繁发生或流血量大，则需要重视，请就医以排除其他疾病的可能。\\n此外，以下情况应及时就医:\\n1)正在服用具有抗凝血功能的药物(例如阿司匹林或华法令)的人。\\n2)流鼻血反复发生，或身上易有瘀血、瘀斑的人。\\n3)同时伴有其他出血(如尿中带血或大便中带血)。\\n4)同时出现其他贫血征兆，如心慌、气短、面色苍白等。\\n5)2岁以内的幼儿。\\n6)老年人流鼻血，则多见鼻子后方出血，情况可能较复杂，建议就医检查、治疗。\",\"advice\":\"干燥季节，请多饮水，或使用加湿器，保持空气湿润，使鼻粘膜不至太过干燥。\"},\"晕车\":{\"name\":\"晕车\",\"type\":\"救急\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/yunche.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/yunche.png\",\"intro\":\"是不是一听到要乘车就发愁?有些人还没上车，闻到汽油味儿就想吐；或者刚上车船或飞机时还好，过不了多久，特别是颠簸或刹车时，就头晕、恶心、呕吐、面色苍白、出冷汗，总之不舒服极了！\\n虽然晕车不是什么大病，但还是希望有办法让自己好受点！\",\"reason\":\"1)西医说晕车是人乘坐交通工具时，内耳前庭平衡感受到过度运动刺激，影响到神经中枢才出现的。\\n2)中医说:胃肠功能差、或胃肠有积食、或气不下行而胃气上逆，乘车时就特别容易头晕、恶心、呕吐！\",\"whenDoctor\":\"如果出现皮肤弹性差，皮肤黏膜干燥，四肢凉，尿减少等脱水症状时，请及时就医。\",\"advice\":\"1)乘坐交通工具时，睡觉是防晕车最好的办法。平卧，闭目养神；没条件平卧者尽量限制头部运动，可将头靠在背椅上固定不动，双目注视远处，尽量少看近处物体。\\n2)不在车上、船上看书、看手机等。\\n3)空腹或者刚吃完饭后不要乘车。乘车前或乘车时不要吃油腻和甜腻的食物，以免诱发或加重恶心呕吐症状。\\n4)乘坐巴土，避免坐在轮胎上方座位，尽量选择前后轮胎之间的座位；坐飞机，尽量选择机翼附近座位；船、快艇，尽量中间位置；小车，尽量前排座位；地铁、火车，面向前进方向坐，多望远方景物。\\n5)保持愉快情绪、心平气和心理暗示不会晕车，回忆美好的时光和高兴的事儿，听听音乐、和人聊天。\"},\"落枕\":{\"name\":\"落枕\",\"type\":\"救急\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/laozhen.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/laozhen.png\",\"intro\":\"美好的清晨，阳光鸟鸣，早餐的香气，妈妈的呼唤or爱人的拥抱or久违的闹铃……啊呀，完蛋！脖子不能动了——脖颈和肩膀僵硬、疼痛，完全失控。\\n城门失火，殃及池鱼。你才发现，原来没有了舒适灵活的脖子，再充满智慧的脑袋也只会塞满焦躁，再温柔的面庞也只能写满龇牙咧嘴。\\n不转动身体，你就没法儿左右乱看找袜子；仰头漱口，低头洗脸，对你的能力来说都叫“开玩笑”。还等着上班去打电脑、写文件、查资料吗?是不是恨不得换个备用脖子再出门！\\n落枕，是的，如果不管它，轻者四五天可自愈，重者迁延数周。你，撑得住吗?\",\"reason\":\"落枕，又叫失枕。“失枕”之名首见于《素问·骨空论》。\\n1)睡眠姿势不正确，或枕头高低软硬不合适。\\n2)白天颈部姿势不当或保持同一姿势过久，导致颈肩肌肉、软组织、筋腱疲劳。\\n3)睡前头发潮湿。\\n4)睡眠时对着门窗或吹冷风令颈肩感受风寒，致气血流通不畅而引发落枕。\",\"whenDoctor\":\"自感疼痛加重，持续日久，请及时就医。\",\"advice\":\"1)颈肩保暖，特别是在睡觉的时候，避免有冷风直吹。尤其是开小缝的门窗要避开，俗话说，“钉大的洞，斗大的风”。\\n2)白天常做颈肩活动，避免一个姿势工作太久。\\n3)更换合格舒适的枕头。\\n4)头发吹干再睡觉。\"},\"突发嗓子痛\":{\"name\":\"突发嗓子痛\",\"type\":\"救急\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/tufasangzitong.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/tufasangzitong.png\",\"intro\":\"这嗓子疼也太折磨人了，可能刚开始就只有一点点痛，偶尔觉得喉咙有点干、痒；可能一两天，也许是几个小时，明显感觉喉咙痛，肿痛是在两腮之下，用手摸脖子周围还有胀痛感；或者在喉咙上下，咽唾沫的时候会感觉明显，就在喉结那里疼痛。\\n估计你也知道自己快要生病了，嗓子疼是很多病的早期征兆，最常见的有:普通感冒、流感、扁桃体炎、腮腺炎、咽喉炎。\",\"reason\":\"1)大多数喉咙痛是由病毒和细菌感染引起的，但也能是因干燥和过敏诱发。肿痛在两腮之下，一般是细菌感染；肿痛在喉咙上下，有细菌感染，但病毒感染居多。\\n2)被冷风吹了、受寒了。最近是不是有过身体发冷感觉，也许是几个小时前，也可能是几天前，没及时驱寒，导致寒气深入体内，有清痰、嘴里发咸的症状。\\n3)也许你是熬夜了，或者过度疲劳(劳心、劳力)；或者生气，大动肝火了；也许是看到自己喜欢的美食(辛辣煎烤等烹调方式)，没把控住；还有可能是最近进补，如进补过多羊肉、膏方或补药等，补大发了。总之一句话:上火了。\",\"whenDoctor\":\"1)如果喉咙已经溃烂发炎，发烧了，或者吞咽困难比较严重，还是要及时就医，以免耽误病情。\\n2)自我感觉近一段时间出现咽喉处的痰吐之不出，咽之不下(梅核气)，请及时就医。\",\"advice\":\"1)多喝水，饮水量比平时大一倍。充分饮水能保持呼吸道黏膜湿润，使病毒细菌难以迅速繁殖，同时对去除内热大有帮助。如果不想喝水，可以用果汁代替，如梨汁、西瓜汁，清热效果更好。\\n2)温盐水漱喉。一杯温开水混合一茶匙盐，含在喉咙处，漱喉后再吐出，一天可进行多次，可以冲洗咽喉部位的黏膜，起到杀菌、缓解炎症的作用。\\n3)嘴里含咽喉糖(薄荷糖、枇杷糖、蜂蜜糖等)或嚼无糖口香糖。咀嚼、吮吸可刺激唾液的分泌，有助于清理咽喉。\\n4)少说话。咽喉痛若引起声带发炎，说话会刺激声带，造成暂时性失声。\\n5)保持空气湿润，以免黏膜变干，刺激咽喉部。可以用加湿器，也可以频繁用湿拖把拖地，也可以在室内养鱼，最好让空气湿度保持在60%左右。\\n6)尽量不接触烟雾和其他的空气污染，如吸烟、二手烟、灰尘多、装修污染等，如果无法避免，可以戴上口罩。\\n7)经常洗手，大多数咽喉部病菌是通过直接接触传染的，患者的鼻涕、唾液所到之处，再经你的手引起感染。手脏时不要用手去接触面部，以防细菌及病毒带入口腔或鼻腔。\\n8)不吃辛辣、油炸、油腻、烤制食品，以防刺激咽部，加重症状。\\n9)勤换牙刷；也可经常用生理盐水或白醋浸泡牙刷，以防细菌滋生。\\n10)避免过度疲劳，保证充足的休息和睡眠，睡眠不足会导致免疫力下降。\"},\"小腿抽筋\":{\"name\":\"小腿抽筋\",\"type\":\"救急\",\"icon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xiaotuichoujin.png\",\"detailIcon\":\"https://7niu-article.galaxyeye-tech.com/jiankang/tiaoyang/icon/xiaotuichoujin.png\",\"intro\":\"经常在夜晚睡着时，小腿肌肉突然不由自主地收缩，膝关节弯曲，无法控制，同时伴有难以忍受的酸痛，肌肉硬得像木疙瘩或铁砣砣。一般持续时间很短，一两分钟可能就自动停下来了，但一两天后仍会感到抽筋过的小腿酸胀。\\n不少人认为腿抽筋就是缺钙，这只是其中一个原因而已，多了解它的形成原因，应对起来也会从容很多。\",\"reason\":\"1)肌肉疲劳。白天站立、活动多，产生过多的代谢产物未完全清除，到夜间继续紧张，可刺激引起小腿抽筋，多发生在运动员、体力劳动者、平时体力运动少的人活动量突然加大后。\\n2)脾主肌肉，脾胃功能差或不好好吃饭(节食、偏食)，气血虚，肌肉缺乏营养，也容易引起腿抽筋。\\n3)身体缺钙。当血液中钙水平低于正常，肌肉兴奋性即增高，轻度刺激即可引起收缩，发生抽筋。孕妇、老年人及很少晒太阳的人，容易缺钙而发生小腿抽筋。\\n4)游泳时，腿部突然浸入凉水，肌肉受到冷刺激，也可能发生抽筋。\",\"whenDoctor\":\"\",\"advice\":\"1)防寒保暖。衣着适度，及时按需添减衣服，睡觉时被子松暖舒适。\\n2)适度运动。平时多锻炼身体，但要充分做好准备活动，让身体活动开来；同时避免过于疲劳，走路时间或运动时间不要过长，最好控制在1小时以内。\\n3)补钙。可多吃一些富含钙的食物，如干酪、酸奶酪、牛奶、深绿色蔬菜、大豆、豆腐、芝麻等；也可以服用补钙的产品。多晒太阳，有助于人体合成维生素D，帮助钙吸收，冬天可酌情补充维生素D。\\n4)喝酒、吸烟、喝碳酸饮料，都可增加人体钙的流失，尽量避免。\\n5)不适当的减肥，如节食、水果减肥等，均可导致营养不良。平时要三餐规律、吃饭七八分饱，不可暴饮暴食，把脾胃养好了，气血自然充足。\"}}");
            record6.setEditor(null);
            record6.setEnable(1);
            record6.setUpdatedAt(DateTool.parseDateStr("2020-02-28 14:46:23",DateTool.TIME_PATTERN));
            record6.setVer("5.0.0");
            record6.setVerify(0);
            List<ManualConfig> insertList=new ArrayList<>();
            insertList.add(record1);
            insertList.add(record2);
            insertList.add(record3);
            insertList.add(record4);
            insertList.add(record5);
            insertList.add(record6);
            manualConfigRepository.insertBatch(insertList);
        }
    }


    @Override
    public void destroyData() {

    }



    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }




    /**
     * 获取重复的配置详情，参数RemoveDup设置为0,结果查询到数据为空
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByRepaetAndReturnEmptyData() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("90005.0.0");
            getDetailListBO.setRemoveDup(0);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取重复的配置详情，参数RemoveDup设置为0,结果查询到数据为空");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"env\":\"dev\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"version\":\"90005.0.0\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"config\":[]"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"confAppid\":\"4.00090\""),true);
        }
    }


    /**
     * 获取重复的配置详情，参数RemoveDup设置为0,结果返回重复数据
     * sql：SELECT m1.id,m1.conf_key as `key`, m1.data, m1.created_at as createdAt,
     * m1.comment, m1.enable, m1.verify, m1.enable, m1.editor FROM manual_config
     * m1 WHERE m1.appid='4.00090' AND m1.conf_env='dev' AND m1.ver='5.0.0' AND
     * m1.updated_at=(SELECT MAX(m2.updated_at) FROM manual_config m2 WHERE
     * m2.appid=m1.appid AND m2.conf_key=m1.conf_key AND m2.ver=m1.ver AND
     * m2.conf_env=m1.conf_env) ORDER BY createdAt DESC
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByRepaet() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("5.0.0");
            getDetailListBO.setRemoveDup(0);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取重复的配置详情，参数RemoveDup设置为0,结果返回重复数据");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"env\":\"dev\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"version\":\"5.0.0\""),true);
            Assert.assertEquals(getdetaillistResult.contains("config"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"confAppid\":\"4.00090\""),true);
            //主要检查存在数据
            Assert.assertEquals(getdetaillistResult.contains("comment"),true);
            Assert.assertEquals(getdetaillistResult.contains("createdAt"),true);
            Assert.assertEquals(getdetaillistResult.contains("data"),true);
            Assert.assertEquals(getdetaillistResult.contains("editor"),true);
            Assert.assertEquals(getdetaillistResult.contains("enable"),true);
            Assert.assertEquals(getdetaillistResult.contains("key"),true);
            Assert.assertEquals(getdetaillistResult.contains("verify"),true);
        }
    }

    /**
     * 获取重复的配置详情，参数RemoveDup设置为1
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByNotRepaet() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("5.0.0");
            getDetailListBO.setRemoveDup(1);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取重复的配置详情，参数RemoveDup设置为1");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getdetaillistResult.contains("confAppid"),true);
            Assert.assertEquals(getdetaillistResult.contains("env"),true);
            Assert.assertEquals(getdetaillistResult.contains("config"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"version\":\"5.0.0\""),true);
            //主要检查存在数据
            Assert.assertEquals(getdetaillistResult.contains("comment"),true);
            Assert.assertEquals(getdetaillistResult.contains("createdAt"),true);
            Assert.assertEquals(getdetaillistResult.contains("data"),true);
            Assert.assertEquals(getdetaillistResult.contains("editor"),true);
            Assert.assertEquals(getdetaillistResult.contains("enable"),true);
            Assert.assertEquals(getdetaillistResult.contains("key"),true);
            Assert.assertEquals(getdetaillistResult.contains("verify"),true);
        }

    }

    /**
     * 获取重复的配置详情，参数RemoveDup设置为非0也非1，是设置为10
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByParameterRemoveDupValue() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("5.0.0");
            getDetailListBO.setRemoveDup(10);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取重复的配置详情，参数RemoveDup设置为非0也非1，是设置为10");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"removeDup is out of range\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 参数RemoveDup必填校验
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByNotExistParameterRemoveDup() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("5.0.0");
//        getDetailListBO.setRemoveDup(10);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数RemoveDup必填校验");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"removeDup is missing\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 根据uid与Token获取配置详情列表
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByUidAndToken() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("5.0.0");
            getDetailListBO.setRemoveDup(1);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据uid与Token获取配置详情列表");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"confAppid\":\"4.00090\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"env\":\"dev\""),true);
            Assert.assertEquals(getdetaillistResult.contains("config"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"version\":\"5.0.0\""),true);
            //主要检查存在数据
            Assert.assertEquals(getdetaillistResult.contains("comment"),true);
            Assert.assertEquals(getdetaillistResult.contains("createdAt"),true);
            Assert.assertEquals(getdetaillistResult.contains("data"),true);
            Assert.assertEquals(getdetaillistResult.contains("editor"),true);
            Assert.assertEquals(getdetaillistResult.contains("enable"),true);
            Assert.assertEquals(getdetaillistResult.contains("key"),true);
            Assert.assertEquals(getdetaillistResult.contains("verify"),true);
        }

    }

    /**
     * 根据bmAppid与accessToken获取配置详情列表
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByBmAppidAndAccessToken() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            getDetailListBO.setAccessToken("d4c430ebc03debd8a00e15c1fef36110368c314c03cc9a7aa586af7f9e55da87");
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("5.0.0");
            getDetailListBO.setRemoveDup(1);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据bmAppid与accessToken获取配置详情列表");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"confAppid\":\"4.00090\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"env\":\"dev\""),true);
            Assert.assertEquals(getdetaillistResult.contains("config"),true);
            Assert.assertEquals(getdetaillistResult.contains("\"version\":\"5.0.0\""),true);
            //主要检查存在数据
            Assert.assertEquals(getdetaillistResult.contains("comment"),true);
            Assert.assertEquals(getdetaillistResult.contains("createdAt"),true);
            Assert.assertEquals(getdetaillistResult.contains("data"),true);
            Assert.assertEquals(getdetaillistResult.contains("editor"),true);
            Assert.assertEquals(getdetaillistResult.contains("enable"),true);
            Assert.assertEquals(getdetaillistResult.contains("key"),true);
            Assert.assertEquals(getdetaillistResult.contains("verify"),true);
        }
    }

    /**
     * 参数Appid必填校验
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByNotExistParameterAppid() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
//        getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("5.0.0");
            getDetailListBO.setRemoveDup(1);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Appid必填校验");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"access_deny\""),true);
        }

    }

    /**
     * 参数ConfAppid必填校验
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByNotExistParameterConfAppid() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
//        getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
//        getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("5.0.0");
            getDetailListBO.setRemoveDup(1);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数ConfAppid必填校验");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"confAppid is missing\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"result\":101"),true);
        }

    }

    /**
     * 参数Env必填校验
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByNotExistParameterEnv() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
//        getDetailListBO.setEnv("dev");
            getDetailListBO.setVersion("5.0.0");
            getDetailListBO.setRemoveDup(1);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Env必填校验");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"env is missing\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"result\":101"),true);
        }

    }

    /**
     * 参数version必填校验
     * @throws Exception
     */
    @Test
    public void getdetaillistTestByNotExistParameterVersion() throws Exception {
        String getdetaillistUrl =null;
        GetDetailListBO getDetailListBO =null;
        String getdetaillistResult ="";
        try{
            getdetaillistUrl = url+"/BusinessService/config/getdetaillist";
            getDetailListBO = new GetDetailListBO();
            HashMap<String,String> userLogin=userLoginTest();
            getDetailListBO.setToken(userLogin.get("token"));
            getDetailListBO.setUid(userLogin.get("uid"));
            getDetailListBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getDetailListBO.setAppid("4.00090");
            //参数Key=manual_config.conf_key
            getDetailListBO.setConfAppid("4.00090");
            getDetailListBO.setEnv("dev");
//        getDetailListBO.setVersion("5.0.0");
            getDetailListBO.setRemoveDup(1);
            log.info("getdetaillistUrl 请求的参数=" + getdetaillistUrl);
            log.info("getDetailListBO 请求的参数=" + JSON.toJSONString(getDetailListBO));
            getdetaillistResult = HttpUtil.postGeneralUrl(getdetaillistUrl, "application/json", JSON.toJSONString(getDetailListBO), "UTF-8");
            log.info("getdetaillistResult 返回结果=" + JSON.parseObject(getdetaillistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数version必填校验");
            recordhttp.setUrl(getdetaillistUrl);
            recordhttp.setRequest(JSON.toJSONString(getDetailListBO));
            recordhttp.setResponse(getdetaillistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getdetaillistResult.contains("\"msg\":\"version is missing\""),true);
            Assert.assertEquals(getdetaillistResult.contains("\"result\":101"),true);
        }

    }



}
