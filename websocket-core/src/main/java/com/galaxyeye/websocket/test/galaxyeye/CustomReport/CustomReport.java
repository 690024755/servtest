package com.galaxyeye.websocket.test.galaxyeye.CustomReport; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.CustomReport
 * @Date Create on 19:36
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/24日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.CaseLogRepository;
import com.galaxyeye.websocket.application.repository.HttpLogRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.CaseLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.tool.SpringContext.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.testng.*;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
@Component
public class CustomReport implements IReporter , ApplicationContextAware  {
    public static ApplicationContext contextHolder;

//    static String mySqlUrl= GlobalSettings.MySqlUrl;
//    static String mySqlUserName=GlobalSettings.mySqlUserName;
//    static String mySqlPassword=GlobalSettings.mySqlPassword;

    static String mySqlUrl="jdbc:mysql://172.16.0.25:3306/testngdb?serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf-8&useSSL=false" ;
    static String mySqlUserName="root";
    static String mySqlPassword="root";
    static String projectName = "websocket";

    public static ConcurrentHashMap<String,Long> httpidMap=new ConcurrentHashMap<>();
    public static String key =null;

    public static void setHttpidMap(ConcurrentHashMap<String, Long> httpidMapTMP) {
        httpidMap.putAll(httpidMapTMP);
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        List<ITestResult> list = new ArrayList<ITestResult>();
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                IResultMap passedTests = testContext.getPassedTests();
                IResultMap failedTests = testContext.getFailedTests();
                IResultMap skippedTests = testContext.getSkippedTests();
                IResultMap failedConfig = testContext.getFailedConfigurations();
                list.addAll(this.listTestResult(passedTests));
                list.addAll(this.listTestResult(failedTests));
                list.addAll(this.listTestResult(skippedTests));
                list.addAll(this.listTestResult(failedConfig));
            }
        }
        this.sort(list);
        setPrjName();
        this.outputResult(list, outputDirectory+"/test.txt");
    }

    public void setPrjName() {
//        GlobalSettings.REPORT_PRJ_NAME = "test";
    }

    private ArrayList<ITestResult> listTestResult(IResultMap resultMap){
        Set<ITestResult> results = resultMap.getAllResults();
        return new ArrayList<ITestResult>(results);
    }

    private void sort(List<ITestResult> list){
        Collections.sort(list, new Comparator<ITestResult>() {
            @Override
            public int compare(ITestResult r1, ITestResult r2) {
                if(r1.getStartMillis()>r2.getStartMillis()){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
    }


    private void outputResult(List<ITestResult> list, String path){
        CaseLogRepository caseLogRepository=contextHolder.getBean(CaseLogRepository.class);
        CaseLog record=new CaseLog();
        try {
            String batchNo = UUID.randomUUID().toString();
            for (int i = 0; i < list.size(); i++) {
                ITestResult result=list.get(i);
                StringBuilder sb=new StringBuilder();
                if(key ==null){
                     key=sb.append(result.getInstanceName()).append(".").append(result.getMethod().getMethodName()).toString();
                }
                if(httpidMap.size()>0 && ! httpidMap.isEmpty() && httpidMap !=null && httpidMap.get(key)!=null){
                    record.setHttpid(Integer.valueOf(String.valueOf(httpidMap.get(key))));
                }else {
                    continue;
                }
                record.setProjectname(projectName);
                record.setModulename(getClassName(result.getTestClass().getName()));
                record.setClassname(result.getInstanceName());
                record.setMethodname(result.getMethod().getMethodName());
                Timestamp startTime=new Timestamp(result.getStartMillis());
                Timestamp endTime=new Timestamp(result.getEndMillis());
                record.setBegintime(startTime);
                record.setEndtime(endTime);
                Float tempLast=(float) (result.getEndMillis()-result.getStartMillis());
                Float lastTime=tempLast/1000;
                record.setLasttime(Double.valueOf(lastTime.toString()));
                record.setStatus(this.getStatus(result.getStatus()));
                record.setDescription(result.getMethod().getDescription());
                record.setBatchno(batchNo);
                caseLogRepository.insert(record);
                key=null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{

        }
    }


    private void outputResult1(List<ITestResult> list, String path){
        HttpLog recordhttp=new HttpLog();
        recordhttp.setCreateTime(new Date());
        recordhttp.setCaseDescribe("新增一条不存在的记录");
        recordhttp.setUrl("addappidUrl");
        recordhttp.setRequest(JSON.toJSONString("addAppidBO"));
        recordhttp.setResponse("addappidResult");
        CaseLogRepository caseLogRepository=contextHolder.getBean(CaseLogRepository.class);

        Connection conn=getConn();
        //TODO
//        String projectName = GlobalSettings.REPORT_PRJ_NAME;
        String strInsert="";
        strInsert = "INSERT INTO case_log(httpid,ProjectName, ModuleName, ClassName, MethodName, BeginTime, EndTime, LastTime, Status, Description, BatchNo) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            String batchNo = UUID.randomUUID().toString();
            PreparedStatement prest = (PreparedStatement) conn.prepareStatement(strInsert);
            for (int i = 0; i < list.size(); i++) {
                ITestResult result=list.get(i);
                StringBuilder sb=new StringBuilder();
                if(key ==null){
                    key=sb.append(result.getInstanceName()).append(".").append(result.getMethod().getMethodName()).toString();
                }
                if(httpidMap.size()>0 && ! httpidMap.isEmpty() && httpidMap !=null && httpidMap.get(key)!=null){
                    prest.setString(1, String.valueOf(httpidMap.get(key)));
                }else {
                    continue;
                }
                prest.setString(2, projectName);
                prest.setString(3, getClassName(result.getTestClass().getName()));
                prest.setString(4, result.getInstanceName());
                prest.setString(5, result.getMethod().getMethodName());
                Timestamp startTime=new Timestamp(result.getStartMillis());
                Timestamp endTime=new Timestamp(result.getEndMillis());
                prest.setTimestamp(6, startTime);
                prest.setTimestamp(7, endTime);
                Float tempLast=(float) (result.getEndMillis()-result.getStartMillis());
                Float lastTime=tempLast/1000;
                prest.setString(8,lastTime.toString());
                prest.setString(9, this.getStatus(result.getStatus()));
                prest.setString(10, result.getMethod().getDescription());
                prest.setString(11, batchNo);
                prest.execute();
                conn.commit();
                key=null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            close(conn);

        }
    }


    public String getStatus(int status){
        String statusString = null;
        switch (status) {
            case 1:
                statusString = "SUCCESS";
                break;
            case 2:
                statusString = "FAILURE";
                break;
            case 3:
                statusString = "SKIP";
                break;
            default:
                break;
        }
        return statusString;
    }


    public String getModuleName(String moduleName){
        String moduleNameString = null;
        switch (moduleName) {
            case "Acc":
                moduleNameString = "Acc";
                break;
            case "Article":
                moduleNameString = "Article";
                break;
            case "AudioToWord":
                moduleNameString = "AudioToWord";
                break;
            case "MPServerJava":
                moduleNameString = "MPServerJava";
                break;
            case "MPService":
                moduleNameString = "MPService";
                break;
            case "OperationManagement":
                moduleNameString = "OperationManagement";
                break;
            case "Saas":
                moduleNameString = "Saas";
                break;
            case "TesTesting":
                moduleNameString = "TesTesting";
                break;
            case "WxPay":
                moduleNameString = "WxPay";
                break;
            case "WxService":
                moduleNameString = "WxService";
                break;
            default:
                break;
        }
        return moduleNameString;
    }


    public static String getClassName(String className){
        Pattern p= Pattern.compile("galaxyeye.(\\w+).TestCase");
        Matcher m= p.matcher(className);
        StringBuilder sb=new StringBuilder();
        while (m.find()){
            String find= m.group(1).toString();
            sb.append(find);
        }
        return sb.toString();
    }


    /**
     * @return
     */
    public static Connection getConn(){
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(mySqlUrl, mySqlUserName, mySqlPassword);
            conn.setAutoCommit(false);
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * @return
     */
    public static void close(Connection conn){
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String addZeroToIntStr(Integer num, int zeroNum) {
        if(num==null) {
            return null;
        }else if(zeroNum<=0) {
            return num+"";
        }else {
            String numStr = num + "";
            int numLength  = numStr.length();
            if(zeroNum>numLength) {
                for(int i=0; i<(zeroNum-numLength); i++) {
                    numStr = "0" + numStr;
                }
            }
            return numStr;
        }


    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        contextHolder=applicationContext;
    }


}

