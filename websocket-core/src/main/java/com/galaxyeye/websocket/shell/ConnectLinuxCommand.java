package com.galaxyeye.websocket.shell;

import ch.ethz.ssh2.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.util.*;

@Slf4j
public class ConnectLinuxCommand {

    private static String DEFAULTCHARTSET = "UTF-8";
    private static Connection conn;


    public static Boolean login(RemoteConnect remoteConnect) {
        boolean flag = false;
        try {
            conn = new Connection(remoteConnect.getIp());
            conn.connect();// 连接
            flag = conn.authenticateWithPassword(remoteConnect.getUserName(), remoteConnect.getPassword());// 认证
            if (flag) {
                log.info("认证成功！");
            } else {
                log.info("认证失败！");
                conn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Boolean loginByFileKey(RemoteConnect remoteConnect, File keyFile, String keyfilePass) {
        boolean flag = false;
        try {
            conn = new Connection(remoteConnect.getIp());
            conn.connect();
            flag = conn.authenticateWithPublicKey(remoteConnect.getUserName(), keyFile, keyfilePass);
            if (flag) {
                log.info("认证成功！");
            } else {
                log.info("认证失败！");
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static Boolean loginByCharsKey(RemoteConnect remoteConnect, char[] keys, String keyPass) {

        boolean flag = false;
        try {
            conn = new Connection(remoteConnect.getIp());
            conn.connect();
            flag = conn.authenticateWithPublicKey(remoteConnect.getUserName(), keys, keyPass);
            if (flag) {
                log.info("认证成功！");
            } else {
                log.info("认证失败！");
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String execute(String cmd){
        String result = "";
        try {
            Session session = conn.openSession();
            session.execCommand(cmd);
            result = processStdout(session.getStdout(), DEFAULTCHARTSET);
            if (StringUtils.isBlank(result)) {
                result = processStdout(session.getStderr(), DEFAULTCHARTSET);
            }
            conn.close();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String executeKeepConnection(Session session,String cmd){
        String result = "";
        try {
            session.execCommand(cmd);
            result = processStdout(session.getStdout(), DEFAULTCHARTSET);
            if (StringUtils.isBlank(result)) {
                result = processStdout(session.getStderr(), DEFAULTCHARTSET);
            }
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static Session getSession(){
        Session session = null;
        try {
            if(session !=null){
                return session;
            }
             session = conn.openSession();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

    public static Connection getConnection(){
        return conn;
    }



    public static String executeSuccess(String cmd){
        String result = "";
        try {
            Session session = conn.openSession();
            session.execCommand(cmd);
            result = processStdout(session.getStdout(), DEFAULTCHARTSET);
            conn.close();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String processStdout(InputStream in, String charset){
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }


    public static boolean connectLinux(String ip,String userName,String password,String commandStr) {

        log.info("ConnectLinuxCommand  scpGet===" + "ip:" + ip + "  userName:" + userName + "  commandStr:"
                + commandStr);

        String returnStr = "";
        boolean result = true;

        RemoteConnect remoteConnect = new RemoteConnect();
        remoteConnect.setIp(ip);
        remoteConnect.setUserName(userName);
        remoteConnect.setPassword(password);
        try {
            if (login(remoteConnect)) {
                returnStr = execute(commandStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isBlank(returnStr)) {
            result = false;
        }
        return result;
    }

    public synchronized static String connectLinuxResult(String ip,String userName,String password,String commandStr) {
        String returnStr = "";
        RemoteConnect remoteConnect = new RemoteConnect();
        remoteConnect.setIp(ip);
        remoteConnect.setUserName(userName);
        remoteConnect.setPassword(password);
        try {
            if (login(remoteConnect)) {
                returnStr = execute(commandStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(returnStr);
        return returnStr;
    }


    public static void scpGet(String ip, String userName, String password, String remoteFile, String localDir)
            throws IOException {

        log.info("ConnectLinuxCommand  scpGet===" + "ip:" + ip + "  userName:" + userName + "  remoteFile:"
                + remoteFile + "  localDir:" + localDir);
        RemoteConnect remoteConnect = new RemoteConnect();
        remoteConnect.setIp(ip);
        remoteConnect.setUserName(userName);
        remoteConnect.setPassword(password);

        if (login(remoteConnect)) {
            SCPClient client = new SCPClient(conn);
            client.get(remoteFile, localDir);
            conn.close();
        }
    }

    public static void scpPut(String ip, String userName, String password, String localFile, String remoteDir)
            throws IOException {
        log.info("ConnectLinuxCommand  scpPut===" + "ip:" + ip + "  userName:" + userName + "  localFile:"
                + localFile + "  remoteDir:" + remoteDir);

        RemoteConnect remoteConnect = new RemoteConnect();
        remoteConnect.setIp(ip);
        remoteConnect.setUserName(userName);
        remoteConnect.setPassword(password);

        if (login(remoteConnect)) {
            SCPClient client = new SCPClient(conn);
            client.put(localFile, remoteDir);
            conn.close();
        }
    }


}
