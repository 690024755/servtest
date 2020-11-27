package com.galaxyeye.websocket.tool.File;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


@Slf4j
public class FileUtil {

    public static InputStream readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("not find "+filePath+" in "+file.getAbsolutePath());
        }
        InputStream fis = new FileInputStream(filePath);
        return fis;
    }


    public static InputStream readJarFile(String filePath) throws IOException {
        URL url=new URL(filePath);
        URLConnection URLConnection=url.openConnection();
        return URLConnection.getInputStream();
    }


    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } 

        if (file.length() > 1024 * 1024 * 1024) {
            throw new IOException("File is too large");
        } 

        StringBuilder sb = new StringBuilder((int) (file.length()));
        FileInputStream fis = new FileInputStream(filePath);
        byte[] bbuf = new byte[10240];
        int hasRead = 0;
        while ( (hasRead = fis.read(bbuf)) > 0 ) {  
            sb.append(new String(bbuf, 0, hasRead));  
        }  
        fis.close();  
        return sb.toString();
    }


    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }


    public static ArrayList<String> readFileLines(String filePath, String keyWords) throws IOException{
        File file = new File(filePath);
        BufferedReader reader = null;
        ArrayList<String> list=new ArrayList<String>();
        try {
            log.info("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                log.info("line " + line + ": " + tempString);
                if(tempString.contains(keyWords)){
                    list.add(tempString);
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }


    public static ArrayList<String> readFileLines(String filePath) throws IOException{
        File file = new File(filePath);
        BufferedReader reader = null;
        ArrayList<String> list=new ArrayList<String>();
        try {
            log.info("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                list.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }


    public static void writeFileLines(String filePath,String content,boolean append) throws IOException {

        FileWriter filew = new FileWriter(filePath,append);
        filew.write(content);
        filew.close();
    }




}
