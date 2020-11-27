//package com.galaxyeye.websocket.test;
///*
// * Description:com.galaxyeye.websocket.test
// * @Date Create on 16:04
// * @author <a href="mailto:yangyi@galaxyeye-tech.com">yangyi</a>
// * @Version JDK 1.8
// * @since version 1.0 Copyright 2019-09-02日galaxyeye All Rights Reserved.
// */
//import ch.ethz.ssh2.Connection;
//import com.alibaba.fastjson.JSON;
//import com.galaxyeye.websocket.shell.ConnectLinuxCommand;
//import com.galaxyeye.websocket.shell.RemoteConnect;
//import com.galaxyeye.websocket.test.galaxyeye.WebSocketTesting.Application.WebSocketApplication;
//import com.galaxyeye.websocket.test.galaxyeye.WebSocketTesting.Service.WebSocketConfig;
//import lombok.extern.slf4j.Slf4j;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Properties;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Slf4j
//public class TestShell {
//
//    private static Connection conn;
//    private static RemoteConnect remoteConnect;
//    private static Properties properties;
//
//
//    public void close() {
//        if (conn != null) {
//            conn.close();
//        }
//    }
//
//    public static void initConnection() {
//        Boolean login= ConnectLinuxCommand.login(remoteConnect);
//        if(login){
//             conn=ConnectLinuxCommand.getConnection();
//        }
//    }
//
//
//    public static Long getNum(String str){
//        Pattern p= Pattern.compile("(\\d+)");
//        Matcher m= p.matcher(str);
//        StringBuilder sb=new StringBuilder();
//        if(str.isEmpty() || str.trim().isEmpty()){
//            return null;
//        }
//        while (m.find()){
//            String find= m.group(1).toString();
//            sb.append(find);
//        }
//        return Long.valueOf(sb.toString());
//    }
//
//
//
//    public static String getAlphabet(String str){
//        Pattern p= Pattern.compile("([a-zA-Z]+)");
//        Matcher m= p.matcher(str);
//        StringBuilder sb=new StringBuilder();
//        while (m.find()){
//            String find= m.group(1).toString();
//            sb.append(find);
//        }
//        return sb.toString();
//    }
//
//
//    private static void initConfig(String deBugOrDeploy) throws IOException {
//        if(deBugOrDeploy.equals("Debug")){
//            URL ConfigUrl= WebSocketApplication.class.getResource("/com/galaxyeye/websocket/test/start.properties");
//            properties=WebSocketConfig.initJarConfig(ConfigUrl.toString());
//        }else if (deBugOrDeploy.equals("Deploy")){
//            properties= WebSocketConfig.initConfig("start.properties");
//        }
//        remoteConnect=new RemoteConnect();
//        remoteConnect.setIp(properties.getProperty("programNmae.host"));
//        remoteConnect.setPassword(properties.getProperty("programNmae.Passwd"));
//        remoteConnect.setUserName(properties.getProperty("programNmae.Uname"));
//        initConnection();
//    }
//
//
//
//    private static void generalShell(Properties properties,String shell) throws IOException {
//        String result=ConnectLinuxCommand.connectLinuxResult(properties.getProperty("programNmae.host"),properties.getProperty("programNmae.Uname"),properties.getProperty("programNmae.Passwd"),shell);
//        log.info("result="+result);
//
//    }
//
//    private static void startService(String PidName) throws IOException, InterruptedException {
//        StringBuilder sb=new StringBuilder();
//        sb.append("daemonize -c ").append(properties.getProperty("programNmae.path")).append(
//                properties.getProperty("programNmae.config")).append(" ").append(properties.getProperty("programNmae.path")).append(properties.getProperty("programNmae.bin")).append("/").append(properties.getProperty("program.name"));
//        ConnectLinuxCommand.executeKeepConnection(conn.openSession(),sb.toString());
//        Thread.sleep(Long.valueOf(properties.getProperty("programNmae.StartWaitTime")));
//        StringBuilder sbPidName=new StringBuilder("ps -ef | grep \"").append(PidName).append("\" | grep -v grep |awk '{print $2}'");
//        String result=ConnectLinuxCommand.executeKeepConnection(conn.openSession(),sbPidName.toString());
//        log.info(PidName.concat("Pid=")+result);
//        Long pid=getNum(result);
//        if(String.valueOf(pid).isEmpty() || String.valueOf(pid).length()<0 || pid==null){
//            log.info(PidName.concat("启动失败=")+pid);
//        }else {
//            log.info(PidName.concat("启动成功=")+pid);
//        }
//    }
//
//    private static void stopService() throws IOException, InterruptedException {
//        String progranName=getAlphabet(properties.getProperty("program.name"));
//        String getPid=" ps -ef|grep '"+progranName+"' | grep -v grep |grep -v "+properties.getProperty("exclude.programName")+" |awk '{print $2}'";
//        String[] PidArr=ConnectLinuxCommand.executeKeepConnection(conn.openSession(),getPid).split("\\n");
//        for (String Pid:PidArr
//        ) {
//            ConnectLinuxCommand.executeKeepConnection(conn.openSession(),"kill -9 "+Pid);
//        }
//        log.info(progranName+"进程"+JSON.toJSONString(PidArr)+"已杀死");
//    }
//
//
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        initConfig("Deploy");
//        if(args.length<=0){
//            log.error("not find start program.name");
//            System.exit(1);
//        }
//        properties.setProperty("program.name",args[0]);
//        stopService();
//        startService(getAlphabet(args[0]));
//        conn.close();
//    }
//}
