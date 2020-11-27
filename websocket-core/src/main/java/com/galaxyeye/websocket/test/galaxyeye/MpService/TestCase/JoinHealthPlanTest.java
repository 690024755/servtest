package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:16
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.galaxyeye.websocket.application.repository.ManualConfigRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.ManualConfig;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ManualConfigExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.InsertGeneralBatchBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.InsertGeneralBatchConfigBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.JoinHealthPlanBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.GetGeneralDTO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.HealthPlanAbsConfigSubDTO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.HealthPlanAbsHealthPlanDTO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
public class JoinHealthPlanTest extends BaseTest {

    @Autowired
    private InsertGeneralBatchTest insertGeneralBatchTest;

    @Autowired
    private ManualConfigRepository manualConfigRepository;

    @Autowired
    private GetGeneralTest getGeneralTest;


    @Test
    public void joinhealthplanTest() throws Exception {
        String joinhealthplanUrl = null;
        JoinHealthPlanBO joinHealthPlanBO = null;
        String joinhealthplanResult = "";
        try {
            joinhealthplanUrl = url + "/BusinessService/health/joinhealthplan";
            joinHealthPlanBO = new JoinHealthPlanBO();
            HashMap<String, String> userLogin = userLoginTest();
            joinHealthPlanBO.setToken(userLogin.get("token"));
            joinHealthPlanBO.setUid(userLogin.get("uid"));
            joinHealthPlanBO.setBmAppid("4.00090");
            joinHealthPlanBO.setAppid("4.00090");
            GetGeneralDTO getGeneralDTO = JSONObject.parseObject(getGeneralTest.getgeneralTest("healthPlanAbs"), GetGeneralDTO.class);
            String data = getGeneralDTO.getConfig().getData();
            HealthPlanAbsConfigSubDTO configSubDTO = JSONObject.parseObject(data, HealthPlanAbsConfigSubDTO.class);
            List<HealthPlanAbsHealthPlanDTO> list = configSubDTO.getHealthPlan();
            HealthPlanAbsHealthPlanDTO healthPlanDTO = list.get(0);
            joinHealthPlanBO.setId(healthPlanDTO.getId());
            log.info("joinhealthplanUrl 请求的参数=" + joinhealthplanUrl);
            log.info("joinHealthPlanBO 请求的参数=" + JSON.toJSONString(joinHealthPlanBO));
            joinhealthplanResult = HttpUtil.postGeneralUrl(joinhealthplanUrl, "application/json", JSON.toJSONString(joinHealthPlanBO), "UTF-8");
            log.info("joinhealthplanResult 返回结果=" + JSON.parseObject(joinhealthplanResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("加入健康计划");
            recordhttp.setUrl(joinhealthplanUrl);
            recordhttp.setRequest(JSON.toJSONString(joinHealthPlanBO));
            recordhttp.setResponse(joinhealthplanResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(joinhealthplanResult.contains("\"result\":1"), true);
            Assert.assertEquals(joinhealthplanResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(joinhealthplanResult.contains("config"), true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {
        ManualConfigExample selectExample = new ManualConfigExample();
        ManualConfigExample.Criteria selectCr = selectExample.createCriteria();
        List<String> selectValues = new ArrayList<>();
        selectValues.add("healthPlanAbs");
        selectCr.andConfKeyIn(selectValues);
        ManualConfigExample deleteExample = new ManualConfigExample();
        ManualConfigExample.Criteria deleteCr = deleteExample.createCriteria();
        List<String> deleteValues = new ArrayList<>();
        deleteValues.add("getgeneralstruct_bool");
        deleteValues.add("getgeneralstruct_float");
        deleteValues.add("getgeneralstruct_int");
        deleteValues.add("getgeneralstruct_exact1");
        deleteValues.add("getgeneralstruct_exact2");
        deleteValues.add("getgeneralstruct_test");
        deleteValues.add("getgeneralstruct_object");
        deleteValues.add("getgeneralstruct_MultiRecord1");
        deleteValues.add("getgeneralstruct_MultiRecord2");
        deleteValues.add("healthPlanAbs");
        deleteCr.andConfKeyIn(deleteValues);
        manualConfigRepository.deleteByExample(deleteExample);
        InsertGeneralBatchBO insertGeneralBatchBO = new InsertGeneralBatchBO();
        HashMap<String, String> userLogin = userLoginTest();
        insertGeneralBatchBO.setToken(userLogin.get("token"));
        insertGeneralBatchBO.setUid(userLogin.get("uid"));
        insertGeneralBatchBO.setAppid("4.00090");
        //参数Key=manual_config.conf_key
        insertGeneralBatchBO.setConfAppid("4.00090");
        insertGeneralBatchBO.setEnv("dev");
        insertGeneralBatchBO.setVersion("5.0.0");
        insertGeneralBatchBO.setNewApp(1);
        List<InsertGeneralBatchConfigBO> config = new ArrayList<>();
        InsertGeneralBatchConfigBO insertGeneralBatchConfigBO1 = new InsertGeneralBatchConfigBO();
        insertGeneralBatchConfigBO1.setComment("健康小程序健康计划列表（待修改）");
        insertGeneralBatchConfigBO1.setKey("healthPlanAbs");
        String data1 = "{\"healthPlan\":[{\"id\":\"1\",\"name\":\"7天压力释放计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309798804361216/613607293698707456.png\"},{\"id\":\"2\",\"name\":\"7天元气恢复计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309803191603200/613605459055611904.jpg\"},{\"id\":\"3\",\"name\":\"7天补水保湿计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"},{\"id\":\"4\",\"name\":\"14天晚安好眠计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"},{\"id\":\"5\",\"name\":\"14天发量拯救计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"},{\"id\":\"6\",\"name\":\"21天脾胃养护计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915824144384.jpg\"},{\"id\":\"7\",\"name\":\"21天颈椎保护计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"}],\"defaultHealthPlanId\":[\"1\",\"2\",\"3\"]}";
        insertGeneralBatchConfigBO1.setData(data1);
        insertGeneralBatchConfigBO1.setEditor(null);
        insertGeneralBatchConfigBO1.setEnable(1);
        insertGeneralBatchConfigBO1.setVerify(0);
        InsertGeneralBatchConfigBO insertGeneralBatchConfigBO2 = new InsertGeneralBatchConfigBO();
        insertGeneralBatchConfigBO2.setComment("健康小程序健康计划列表（测试专用）");
        insertGeneralBatchConfigBO2.setKey("healthPlanAbs");
        String data2 = "[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]],[{},[[{},[[{},[[{},[[{},[],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]";
        insertGeneralBatchConfigBO2.setData(data2);
        insertGeneralBatchConfigBO2.setEditor(null);
        insertGeneralBatchConfigBO2.setEnable(1);
        insertGeneralBatchConfigBO2.setVerify(1);
        config.add(insertGeneralBatchConfigBO1);
        config.add(insertGeneralBatchConfigBO2);
        insertGeneralBatchBO.setConfig(config);
        insertGeneralBatchTest.insertgeneralbatchTestByGernal(insertGeneralBatchBO, config);
        List<ManualConfig> listTmp = manualConfigRepository.selectByExample(selectExample);
        for (int i = 0; i < listTmp.size(); i++) {
            ManualConfig updateRecord = new ManualConfig();
            updateRecord.setUpdatedAt(new Date());
            ManualConfigExample updateExample = new ManualConfigExample();
            ManualConfigExample.Criteria updateCr = updateExample.createCriteria();
            if (i == 0) {
                Date date = listTmp.get(i).getUpdatedAt();
                date.setTime(date.getTime());
                listTmp.get(i).setUpdatedAt(date);
                BeanUtils.copyProperties(listTmp.get(i), updateRecord);
                updateCr.andIdEqualTo(listTmp.get(i).getId());

            } else {
                Date date = listTmp.get(i).getUpdatedAt();
                date.setTime(date.getTime());
                listTmp.get(i).setUpdatedAt(date);
                BeanUtils.copyProperties(listTmp.get(i), updateRecord);
                updateCr.andIdEqualTo(listTmp.get(i).getId());
            }
            manualConfigRepository.updateByExampleSelective(updateRecord, updateExample);
        }
    }

    @Override
    public void destroyData() {

    }
}
