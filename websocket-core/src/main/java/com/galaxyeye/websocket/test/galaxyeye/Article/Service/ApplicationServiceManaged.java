package com.galaxyeye.websocket.test.galaxyeye.Article.Service; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.Service
 * @Date Create on 16:06
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/15日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.shell.ConnectLinuxCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ApplicationServiceManaged {

    /**
     * 提取数字
     * @param str
     * @return
     */
    public Integer getNum(String str){
        Pattern p= Pattern.compile("(\\d+)");
        Matcher m= p.matcher(str);
        StringBuilder sb=new StringBuilder();
        List<String> list=new ArrayList<>();
        while (m.find()){
            String find= m.group(1).toString();
            sb.append(find);
            list.add(find);
        }
        return Integer.valueOf(sb.toString());
    }


    /**
     * 提取数字
     * @param str
     * @return
     */
    public List<String> getNumList(String str){
        Pattern p= Pattern.compile("(\\d+)");
        Matcher m= p.matcher(str);
        List<String> list=new ArrayList<>();
        while (m.find()){
            String find= m.group(1).toString();
            list.add(find);
        }
        return list;
    }


    public void deleteArticleApplicationService() throws IOException, InterruptedException {
        String deleteArticle= "rm -rf /usr/local/code/article/bin/article";
        String deleteArticle1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge",deleteArticle);
        log.info("deleteArticle1="+deleteArticle1);
    }



    /**
     * 启动文章单台的服务
     * @throws IOException
     * @throws InterruptedException
     */
    public void restartArticlePid() throws IOException, InterruptedException {
        String str1=" ps -ef|grep 'article' | grep -v grep |awk '{print $2}'";
        String articlePid1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge",str1);
        log.info("articlePid1="+articlePid1);
        if(!articlePid1.isEmpty() && articlePid1.trim().length()>0){
            List<String> list=getNumList(articlePid1);
            for (String pid:list
                 ) {
                String result1_1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","kill -9 "+Integer.valueOf(pid));
                log.info("杀死进程 articlePid="+articlePid1+" ;执行结果="+result1_1);
            }
        }
        String articleName=ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","ls /usr/local/code/article/bin" ).replace("\n","");
        String result1_2= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","daemonize /usr/local/code/article/bin/".concat(articleName) );
        log.info("restart article service, 执行结果="+result1_2);
        Thread.sleep(5000);//等待程序启动完成后，在执行后续业务
    }

    /**
     * 重启Mtsr服务
     * @param host
     * @param uName
     * @param passwd
     * @throws IOException
     * @throws InterruptedException
     */
    public void restartMtsPid(String host,String uName,String passwd) throws IOException, InterruptedException {
        String str1="sh /usr/bin/serviceRestart.sh";
        String MtsrPid1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge",str1);
        log.info("MtsrPid1="+MtsrPid1);
        Thread.sleep(5000);//等待程序启动完成后，在执行后续业务
    }

    /**
     * 启动负载均衡服务
     * @throws IOException
     * @throws InterruptedException
     */
    public void loadBalanceRestartArticlePid() throws IOException, InterruptedException {
        String str1=" ps -ef|grep 'article' | grep -v grep |awk '{print $2}'";
        String articlePid1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge",str1);
        log.info("articlePid1="+articlePid1);
        String result1_1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","kill -9 "+getNum(articlePid1) );
        log.info("杀死进程 articlePid="+articlePid1+" ;执行结果="+result1_1);
        String result1_2= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","daemonize /usr/local/code/article/bin/article" );
        log.info("restart article service, 执行结果="+result1_2);

        String str2=" ps -ef|grep 'article' | grep -v grep |awk '{print $2}'";
        String articlePid2= ConnectLinuxCommand.connectLinuxResult("172.16.3.34","root","root",str2);
        log.info("articlePid2="+articlePid2);
        String result2_1= ConnectLinuxCommand.connectLinuxResult("172.16.3.34","root","root","kill -9 "+getNum(articlePid2) );
        log.info("杀死进程 articlePid="+articlePid2+" ;执行结果="+result2_1);
        String result2_2= ConnectLinuxCommand.connectLinuxResult("172.16.3.34","root","root","daemonize /usr/local/project/article/bin/article" );
        log.info("restart article service, 执行结果="+result2_2);
        Thread.sleep(5000);//等待程序启动完成后，在执行后续业务
    }

    /**
     * 启动账号服务
     * @throws IOException
     * @throws InterruptedException
     */
    public void restartAccPid() throws IOException, InterruptedException {
        String str1=" ps -ef|grep 'acc' | grep -v grep |awk '{print $2}'";
        String accPid1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge",str1);
        log.info("accPid1="+accPid1);
        if(!accPid1.isEmpty() && accPid1.trim().length()>0){
            List<String> list=getNumList(accPid1);
            for (String pid:list
            ) {
                String result1_1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","kill -9 "+Integer.valueOf(pid));
                log.info("杀死进程 accPid="+accPid1+" ;执行结果="+result1_1);
            }
        }
        String accName=ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","ls /usr/local/code/acc/bin" ).replace("\n","");
        String result1_2= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","daemonize /usr/local/code/acc/bin/".concat(accName) );
        log.info("restart acc service, 执行结果="+result1_2);
        Thread.sleep(5000);//等待程序启动完成后，在执行后续业务
    }


    /**
     * 启动mpService服务
     * @throws IOException
     * @throws InterruptedException
     */
    public void restartMpServicePid() throws IOException, InterruptedException {
        String str1=" ps -ef|grep 'mpService' | grep -v grep |awk '{print $2}'";
        String mpServicePid1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge",str1);
        log.info("mpServicePid1="+mpServicePid1);
        if(!mpServicePid1.isEmpty() && mpServicePid1.trim().length()>0){
            List<String> list=getNumList(mpServicePid1);
            for (String pid:list
            ) {
                String result1_1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","kill -9 "+Integer.valueOf(pid));
                log.info("杀死进程 mpServicePid1="+mpServicePid1+" ;执行结果="+result1_1);
            }
        }
        String mpServiceName=ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","ls /usr/local/code/mpService/bin" ).replace("\n","");
        String result1_2= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","daemonize /usr/local/code/mpService/bin/".concat(mpServiceName) );
        log.info("restart mpService service, 执行结果="+result1_2);
        Thread.sleep(5000);//等待程序启动完成后，在执行后续业务
    }

}
