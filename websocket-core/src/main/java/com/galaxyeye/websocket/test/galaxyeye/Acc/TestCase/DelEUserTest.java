package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 13:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/18日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.application.repository.EuserRepository;
import com.galaxyeye.websocket.application.repository.TUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Euser;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.EuserExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddeUserBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.DelEUserBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.DelUserBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.DTO.AddUserDTO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;


@Slf4j
@Component
public class DelEUserTest extends BaseTest {

    @Autowired
    private AddeUserTest addeUserTest;

    @Autowired
    private UpdEuserTest updEuserTest;

    @Autowired
    private EuserRepository euserRepository;

    /**
     * 通用删除企业用户的信息
     * @throws Exception
     */
    public String delEUserTestByGeneral(DelEUserBO delEUserBO) throws Exception {
        String delEUserUrl = null;
        String delEUserResult = "";
            try {
                delEUserUrl = url + "/AccService/deleuser";
                log.info("delEUserUrl 请求的参数=" + delEUserUrl);
                log.info("delEUserBO 请求的参数=" + JSON.toJSONString(delEUserBO));
                delEUserResult = HttpUtil.postGeneralUrl(delEUserUrl, "application/json", JSON.toJSONString(delEUserBO), "UTF-8");
                log.info("delEUserResult 返回结果=" + delEUserResult);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("通用删除企业用户的信息");
                recordhttp.setUrl(delEUserUrl);
                recordhttp.setRequest(JSON.toJSONString(delEUserBO));
                recordhttp.setResponse(delEUserResult);
                initLog(recordhttp, new Object() {
                });
                return delEUserResult;
            }
    }


    /**
     * 删除未启用角色的企业账号
     * @throws Exception
     */
    @Test
    public void delEUserTestByExistUser() throws Exception {
        destroyData();
        addeUserTest.modifySysRoleEnable();
        AddeUserBO addeUserBO = new AddeUserBO();
        //设置企业名称
        addeUserBO.setEuname("test_yy0");
        //设置企业密码
        addeUserBO.setPasswd("test_yy0");
        addeUserBO.setEmail("test_yy0@qq.com");
        addeUserBO.setMobile("13093863588");
        addeUserBO.setContactNumber("13093863588");
        addeUserBO.setEnterpriseName("test_yy0");
        addeUserBO.setAddr("test_yy0");
        addeUserBO.setAcctype("test");
        addeUserBO.setFeuid(1115813755523960832L);
        addeUserBO.setAppid("1.00002");
        addeUserBO.setSeq("test_yy0");
        String addeuserResult=addeUserTest.addeuserTestByGernal(addeUserBO);
        Long euid= JsonPath.read(addeuserResult,"$.euid");
        Assert.assertEquals(addeuserResult.contains("\"result\":1"),true);
        Assert.assertEquals(addeuserResult.contains("euid"),true);
        Assert.assertEquals(addeuserResult.contains("\"msg\":\"\""),true);
        addeUserTest.modifySysRoleDisable();

        String delEUserUrl =null;
        DelEUserBO delEUserBO =null;
        String delEUserResult ="";
        try{
            delEUserUrl = url+"/AccService/deleuser";
            delEUserBO = new DelEUserBO();
            delEUserBO.setAppid(addeUserBO.getAppid());
            delEUserBO.setEuid(euid);
            log.info("delEUserUrl 请求的参数=" + delEUserUrl);
            log.info("delEUserBO 请求的参数=" + JSON.toJSONString(delEUserBO));
            delEUserResult = HttpUtil.postGeneralUrl(delEUserUrl, "application/json", JSON.toJSONString(delEUserBO), "UTF-8");
            log.info("delEUserResult 返回结果=" + delEUserResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除未启用角色的企业账号");
            recordhttp.setUrl(delEUserUrl);
            recordhttp.setRequest(JSON.toJSONString(delEUserBO));
            recordhttp.setResponse(delEUserResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(delEUserResult.contains("euid"),true);
            Assert.assertEquals(delEUserResult.contains("\"result\":1"),true);
            EuserExample euserExample=new EuserExample();
            euserExample.createCriteria().andEuidEqualTo(euid);
            List<Euser> list=euserRepository.selectByExample(euserExample);
            Assert.assertNotNull(list.get(0).getDeletedAt());
        }
    }



    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {
        addeUserTest.destroyData();
    }
}
