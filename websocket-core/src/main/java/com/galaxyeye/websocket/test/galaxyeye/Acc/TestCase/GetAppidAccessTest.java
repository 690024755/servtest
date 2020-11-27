package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.GetAppidAccessBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.DTO.GetAppidAccessDTO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

@Slf4j
@Component
public class GetAppidAccessTest extends BaseTest {


    @Autowired
    private GetAppidTest getAppidTest;

    /**
     * 1、获取表userdb.appid所有记录，给其他服务使用比如mpservice调用更新某个appid下添加的方法，做权限验证使用
     * 2、
     * @throws Exception
     */
    @Test
    public void Test() throws Exception {
        String getappidaccessUrl = url+"/AccService/getappidaccess";
        GetAppidAccessBO getAppidAccessBO = new GetAppidAccessBO();
        String bmAppid="3.00014";
        getAppidAccessBO.setAppid(bmAppid);
        getAppidAccessBO.setOnlyCheckTime(false);
        log.info("getappidaccessUrl 请求的参数=" + getappidaccessUrl);
        log.info("getAppidAccessBO 请求的参数=" + JSON.toJSONString(getAppidAccessBO));
        String getappidaccessResult = HttpUtil.postGeneralUrl(getappidaccessUrl, "application/json", JSON.toJSONString(getAppidAccessBO), "UTF-8");
        log.info("getappidaccessResult 返回结果=" + JSON.parseObject(getappidaccessResult));
        GetAppidAccessDTO getAppidAccessDTO=JSON.parseObject(getappidaccessResult, GetAppidAccessDTO.class);
        String yy=getAppidAccessDTO.getAppidAccess();
        if(JSON.toJSONString(JSON.parseObject(yy).get("1.00002")).contains("1.00002yy1")){
            log.info(JSON.toJSONString(JSON.parseObject(yy).get("1.00002")));
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

    }
}
