package com.galaxyeye.websocket.shell;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
public class articleShell {

    public static void restartArticlePid() throws IOException, InterruptedException {
        String str1=" ps -ef|grep 'article' | grep -v grep |awk '{print $2}'";
        String articlePid1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge",str1);
        log.info("articlePid1="+articlePid1);
        String result1_1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","kill -9 "+articlePid1.replace("\n","") );
        log.info("杀死进程 articlePid="+articlePid1+" ;执行结果="+result1_1);
        String articleName=ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","ls /usr/local/code/article/bin" ).replace("\n","");
        String result1_2= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","daemonize /usr/local/code/article/bin/".concat(articleName) );
        log.info("restart article service, 执行结果="+result1_2);
        Thread.sleep(5000);
    }
}

