package com.galaxyeye.websocket.service; /*
 * Description:com.galaxyeye.websocket.service
 * @Date Create on 14:54
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/11日galaxyeye All Rights Reserved.
 */

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
@Slf4j
public class FindStrInJar {
    public String condition;

    public static String xmlCasePath;


    public ArrayList<String> jarFiles = new ArrayList<String>();


    public FindStrInJar() {
    }

    public FindStrInJar(String condition) {
        this.condition = condition;
    }

    public FindStrInJar(String condition, String exclude) {
        this.condition = condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<String> find(String dir, boolean recurse) {
        searchDir(dir, recurse);
        return this.jarFiles;
    }

    public List<String> getFilenames() {
        return this.jarFiles;
    }

    protected String getClassName(ZipEntry entry) {
        StringBuffer className = new StringBuffer(entry.getName().replace("/", "."));
        return className.toString();
    }

    protected void searchDir(String dir, boolean recurse) {
        try {
            String sys=this.getClass().getResource("/").getFile().replace("/classes","/lib");
            File d = new File(sys);
            if (!d.isDirectory()) {
                return;
            }
            File[] files = d.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (recurse && files[i].isDirectory()) {
                    searchDir(files[i].getAbsolutePath(), true);
                } else {
                    String filename = files[i].getAbsolutePath();
                    if (filename.endsWith(".jar") || filename.endsWith(".zip")) {
                        ZipFile zip = new ZipFile(filename);
                        Enumeration<? extends ZipEntry> entries = zip.entries();
                        while (entries.hasMoreElements()) {
                            ZipEntry entry = entries.nextElement();
                            String thisClassName = getClassName(entry);
                            if (thisClassName.contains(condition) && thisClassName.endsWith(".xml")) {
                                BufferedWriter bWriter = null;
                                String str = "";
                                BufferedReader bReader = new BufferedReader(new InputStreamReader(zip.getInputStream(entry)));
                                File file = new File(dir);
                                if (file.exists()) {
                                    bWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath() + "/" + condition));
                                } else {
                                    file.mkdir();
                                    bWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath() + "/" + condition));
                                }
                                while ((str = bReader.readLine()) != null) {
                                    bWriter.write(str);

                                }
                                bWriter.flush();
                                this.jarFiles.add(filename + "" + thisClassName);
                            }
                        }
                        zip.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        FindStrInJar findInJar = new FindStrInJar("GetDetailListTest.xml");
        List<String> jarFiles = findInJar.find("D:/tomcat7/webapps/ROOT/WEB-INF/lib/", true);
        if (jarFiles.size() == 0) {
            System.out.println("Not Found");
        } else {
            for (int i = 0; i < jarFiles.size(); i++) {
                System.out.println(jarFiles.get(i));
            }
        }
    }


}
