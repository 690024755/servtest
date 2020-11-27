package com.galaxyeye.websocket.tool.sftp;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Vector;

@Slf4j
public class SFTPUtil {


    private ChannelSftp sftp;

    private Session session;
    private String username;
    private String password;
    private String privateKey;
    private String host;
    private int port;



    public SFTPUtil(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }


    public SFTPUtil(String username, String host, int port, String privateKey) {
        this.username = username;
        this.host = host;
        this.port = port;
        this.privateKey = privateKey;
    }

    public SFTPUtil(){}


    public void login(){
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                jsch.addIdentity(privateKey);
            }
            session = jsch.getSession(username, host, port);
            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }


    public void logout(){
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }



    public void upload(String basePath,String directory, String sftpFileName, InputStream input) throws SftpException{
        try {
            sftp.cd(basePath);
            sftp.cd(directory);
        } catch (SftpException e) {
            String [] dirs=directory.split("/");
            String tempPath=basePath;
            for(String dir:dirs){
                if(null== dir || "".equals(dir)) continue;
                tempPath+="/"+dir;
                try{
                    sftp.cd(tempPath);
                }catch(SftpException ex){
                    sftp.mkdir(tempPath);
                    sftp.cd(tempPath);
                }
            }
        }
        sftp.put(input, sftpFileName);  //上传文件
    }



    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        File file = new File(saveFile);
        sftp.get(downloadFile, new FileOutputStream(file));
    }


    public byte[] download(String directory, String downloadFile) throws SftpException, IOException{
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        InputStream is = sftp.get(downloadFile);
        byte[] fileData = IOUtils.toByteArray(is);
        return fileData;
    }



    public void delete(String directory, String deleteFile) throws SftpException{
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }



    public Vector<?> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }


    public int execCmd(String command) throws Exception{
        log.info( "开始执行命令:" + command);
        int returnCode  = -1;
        BufferedReader reader = null;
        Channel channel = null;
        channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        InputStream in = channel.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in));
        channel.connect();
        log.info("The remote command is: " + command);
        String buf ;
        while ((buf = reader.readLine()) != null) {
            log.info(buf);
        }
        reader.close();
        if (channel.isClosed()) {
            returnCode = channel.getExitStatus();
        }
        log.info( "Exit-status:" + returnCode );
        channel.disconnect();
        return returnCode;
    }


    public void execCmd() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        BufferedReader reader = null;
        Channel channel = null;
        try {
            while ((command = br.readLine()) != null) {
                channel = session.openChannel("exec");
                ((ChannelExec) channel).setCommand(command);
                channel.setInputStream(null);
                ((ChannelExec) channel).setErrStream(System.err);

                channel.connect();
                InputStream in = channel.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in,
                        Charset.forName("UTF-8")));
                String buf = null;
                while ((buf = reader.readLine()) != null) {
                    System.out.println(buf);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            channel.disconnect();
        }
    }


    public int shellCmd(String command) throws Exception{
        log.info( "开始执行命令:" + command);
        int returnCode  = -1;
        ChannelShell channel=(ChannelShell) session.openChannel("shell");
        InputStream in = channel.getInputStream();
        channel.setPty(true);
        channel.connect();
        OutputStream os = channel.getOutputStream();
        os.write((command + "\r\n").getBytes());
        os.write("exit\r\n".getBytes());
        os.flush();
        log.info("The remote command is:{}" ,command);
        byte[] tmp=new byte[1024];
        while(true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                log.info(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0) continue;
                returnCode = channel.getExitStatus();
                log.info("exit-status: " + channel.getExitStatus());
                break;
            }
            try{Thread.sleep(1000);}catch(Exception ee){}
        }
        os.close();
        in.close();
        channel.disconnect();
        session.disconnect();
        return returnCode;
    }


}
