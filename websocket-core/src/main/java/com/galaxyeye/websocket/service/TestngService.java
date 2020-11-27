package com.galaxyeye.websocket.service; /*
 * Description:com.galaxyeye.websocket.service
 * @Date Create on 10:50
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/11日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.CustomReport;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.TestngXMLConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.uncommons.reportng.HTMLReporter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TestngService {

public final String os=System.getProperty("os.name").toLowerCase();

    public String excuteTotalXml(List<String> paths) throws IOException, URISyntaxException {
        String TestngResultTotalDir=Thread.currentThread().getContextClassLoader().getResource("/").getFile().replace("/classes","");
        String dir=TestngXMLConfig.TotalDir;
        List<String> suites = new ArrayList<>();
        TestNG testNG = new TestNG();
        String outputdir = TestngResultTotalDir+"TestngResult";
        File file=new File(outputdir);
        if(!file.exists()){
            file.mkdir();
        }
        log.info("测试报告的html路径="+outputdir);
        Map<String,Object> hs=new HashMap<>();
        List<String> existXmlCaseList = new ArrayList<>();
        List<String> notExistXmlCaseList = new ArrayList<>();
        for (String path : paths) {
            suites.add(dir+path);
        }
        hs.put("existXmlCaseList",existXmlCaseList);
        hs.put("notExistXmlCaseList",notExistXmlCaseList);
        hs.put("report",outputdir);
        if(!suites.isEmpty() && suites.size()>0){
            testNG.setTestSuites(suites);
            log.info("需要执行的xmlCase用例="+JSON.toJSONString(suites));
            testNG.setOutputDirectory(outputdir);
            List<Class<? extends ITestNGListener>> classes = new ArrayList<>();
            classes.add(HTMLReporter.class);
            classes.add(CustomReport.class);
            testNG.setListenerClasses(classes);
            testNG.run();
        }
        return JSON.toJSONString(hs);
    }

    public String excuteDetailXml(List<String> paths) throws IOException, URISyntaxException {
        String TestngResultDetailDir=Thread.currentThread().getContextClassLoader().getResource("/").getFile().replace("/classes","");
        String dir=TestngXMLConfig.DetailDir;
        List<String> suites = new ArrayList<>();
        TestNG testNG = new TestNG();
        String outputdir = TestngResultDetailDir+"TestngResult";
        File file=new File(outputdir);
        if(!file.exists()){
            file.mkdir();
        }
        log.info("测试报告的html路径="+outputdir);
        Map<String,Object> hs=new HashMap<>();
        List<String> existXmlCaseList = new ArrayList<>();
        List<String> notExistXmlCaseList = new ArrayList<>();
        for (String path : paths) {
            suites.add(dir+path);
        }
        hs.put("existXmlCaseList",existXmlCaseList);
        hs.put("notExistXmlCaseList",notExistXmlCaseList);
        hs.put("report",outputdir);
        if(!suites.isEmpty() && suites.size()>0){
            testNG.setTestSuites(suites);
            log.info("需要执行的xmlCase用例="+JSON.toJSONString(suites));
            testNG.setOutputDirectory(outputdir);
            List<Class<? extends ITestNGListener>> classes = new ArrayList<>();
            classes.add(HTMLReporter.class);
            classes.add(CustomReport.class);
            testNG.setListenerClasses(classes);
            testNG.run();
        }
        return JSON.toJSONString(hs);
    }

}
