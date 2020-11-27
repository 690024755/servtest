package com.galaxyeye.websocket.test.galaxyeye.CustomReport; /*
 * @Date Create on 9:12
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/19日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.XmlCaseRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.XmlCase;
import com.galaxyeye.websocket.service.FindStrInJar;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Component
@Slf4j
public class TestngXMLConfig extends BaseTest implements InitializingBean {
    public static String TotalDir = null;
    public static String DetailDir = null;
    public static String ThreadDir = null;

    @Autowired
    private XmlCaseRepository xmlCaseRepository;


    public static Map<String, String> xmlCase = new HashMap() {{
        put(ModuleNameEnum.getName(0), "AccTotal.xml");
        put(ModuleNameEnum.getName(1), "ArticleTotal.xml");
        put(ModuleNameEnum.getName(2), "AudioToWordTotal.xml");
        put(ModuleNameEnum.getName(3), "MPServerJavaTotal.xml");
        put(ModuleNameEnum.getName(4), "MpServiceTotal.xml");
        put(ModuleNameEnum.getName(5), "OperationManagementTotal.xml");
        put(ModuleNameEnum.getName(6), "SaasTotal.xml");
        put(ModuleNameEnum.getName(7), "TesTesting.xml");
        put(ModuleNameEnum.getName(8), "WxPayTotal.xml");
        put(ModuleNameEnum.getName(9), "WxServiceTotal.xml");
        put(ModuleNameEnum.getName(10), "YunGuanWebTotal.xml");
        put(ModuleNameEnum.getName(11), "IcServiceTotal.xml");
        put(ModuleNameEnum.getName(12), "WebSocketTestingTotal.xml");
        put(ModuleNameEnum.getName(13), "TesDaTestTotal.xml");
        put(ModuleNameEnum.getName(14), "JjServerTotal.xml");
    }};


    private void setTotalXml(List<String> TotalPaths) {
        for (String path : TotalPaths) {
            FindStrInJar findInJar = new FindStrInJar(path);
            List<String> jarFiles = findInJar.find(TotalDir, true);
        }
    }

    private void setDetailXml(List<String> DetailPaths) {
        for (String path : DetailPaths) {
            FindStrInJar findInJar = new FindStrInJar(path);
            List<String> jarFiles = findInJar.find(DetailDir, true);
        }
    }

    private void initXmlCase() {

        try {
            xmlCaseRepository.deleteAll();
            List<String> totalXmlList = new ArrayList<>();
            List<String> detailXmlList = new ArrayList<>();
            for (ModuleNameEnum moduleNameEnum : ModuleNameEnum.values()
            ) {

                String ThreadDir = Thread.currentThread().getContextClassLoader().getResource("/").getFile().replace("/classes", "").replace("lib", "");
                DetailDir = ThreadDir + "xmlCase/";
                TotalDir = DetailDir + "Total/";
                File detailFile = new File(DetailDir);
                if (!detailFile.exists()) {
                    detailFile.mkdir();
                }
                File totalFile = new File(TotalDir);
                if (!totalFile.exists()) {
                    totalFile.mkdir();
                }
                String caseName = xmlCase.get(moduleNameEnum.getName());
                log.info("初始化测试用例，moduleNameEnum: " + moduleNameEnum + " find caseName: " + caseName + " Jar  ！！！");
                FindStrInJar findInJar = new FindStrInJar(caseName);
                List<String> jarFiles = findInJar.find(TotalDir, true);
                File f = new File(TotalDir + caseName);
                if (f.exists()) {
                    totalXmlList.add(caseName);
                    XmlCase totalRecord = new XmlCase();
                    totalRecord.setModuleName(moduleNameEnum.getName());
                    totalRecord.setCreateTime(new Date());
                    totalRecord.setCaseName(caseName);
                    totalRecord.setType(0);
                    xmlCaseRepository.insertSelective(totalRecord);
                    SAXReader reader = new SAXReader();
                    log.info("===3 字节的 UTF-8 序列的字节 3 无效===,檢查哪个xml文件解析出错" + f.getAbsolutePath());
                    Document doc = reader.read(f);
                    Element root = doc.getRootElement();
                    Element lement = root.element("suite-files");
                    List<Element> list = lement.elements("suite-file");
                    for (Element element : list
                    ) {
                        XmlCase detailRecord = new XmlCase();
                        detailRecord.setCaseName(element.attribute("path").getValue().replace("../", ""));
                        detailRecord.setCreateTime(new Date());
                        detailRecord.setModuleName(moduleNameEnum.getName());
                        detailRecord.setType(1);
                        xmlCaseRepository.insertSelective(detailRecord);
                        detailXmlList.add(element.attribute("path").getValue().replace("../", ""));
                    }
                    log.info("detailRecord=" + JSON.toJSONString(detailXmlList));
                }
                setDetailXml(detailXmlList);
            }
            log.info("ThreadDir路径=" + ThreadDir);
            log.info("DetailDir路径=" + DetailDir);
            log.info("TotalDir路径=" + TotalDir);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public String initUrl() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String file = this.getClass().getResource("TestngXMLConfig.class").toString();
        if (file.contains("jar:file") && file.startsWith("jar")) {
        }
    }


}
