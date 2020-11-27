package com.galaxyeye.websocket.tool.http;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Log4j
public class HttpUtil {

    public static String post(String requestUrl, String accessToken, String params)
            throws Exception {
        String contentType = "application/x-www-form-urlencoded";
        return HttpUtil.post(requestUrl, accessToken, contentType, params);
    }

    public static String post(String requestUrl, String accessToken, String contentType, String params)
            throws Exception {
        String encoding = "UTF-8";
        if (requestUrl.contains("nlp")) {
            encoding = "GBK";
        }
        return HttpUtil.post(requestUrl, accessToken, contentType, params, encoding);
    }

    public static String post(String requestUrl, String accessToken, String contentType, String params, String encoding)
            throws Exception {
        String url = requestUrl + "?access_token=" + accessToken;
        return HttpUtil.postGeneralUrl(url, contentType, params, encoding);
    }


    public static String postGeneralUrl(String generalUrl, String contentType, String params, String encoding)
            throws Exception {
        URL url = new URL(generalUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(params.getBytes(encoding));
        out.flush();
        out.close();
        connection.connect();
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String key : headers.keySet()) {
            log.error(key + "--->" + headers.get(key));
        }
        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), encoding));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        log.error("result:" + result);
        return result;
    }


    public static String postGeneralUrlByCustomHeader(String generalUrl,Map<String ,String > headerMaps,String contentType, String params, String encoding)
            throws Exception {
        URL url = new URL(generalUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("Connection", "Keep-Alive");
        Set<Map.Entry<String, String>> multiParaSet=headerMaps.entrySet();
        for (Map.Entry<String, String> multiPara:multiParaSet
        ) {
            connection.setRequestProperty(multiPara.getKey(),multiPara.getValue());
        }
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(params.getBytes(encoding));
        out.flush();
        out.close();
        connection.connect();
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String key : headers.keySet()) {
            log.error(key + "--->" + headers.get(key));
        }
        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), encoding));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        log.error("result:" + result);
        return result;
    }


    public static String getGeneralUrlByCustomHeader(String generalUrl,Map<String ,String > headerMaps,String contentType, String params, String encoding)
            throws Exception {
        URL url = new URL(generalUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("Connection", "Keep-Alive");
        Set<Map.Entry<String, String>> multiParaSet=headerMaps.entrySet();
        for (Map.Entry<String, String> multiPara:multiParaSet
        ) {
            connection.setRequestProperty(multiPara.getKey(),multiPara.getValue());
        }
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(params.getBytes(encoding));
        out.flush();
        out.close();
        connection.connect();
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String key : headers.keySet()) {
            log.error(key + "--->" + headers.get(key));
        }
        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), encoding));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        log.error("result:" + result);
        return result;
    }



    public static String postGeneralUrlByHeaders(String generalUrl, Map<String, String> multiParaMap, byte[] fileByte, String encoding)
            throws Exception {
        URL url = new URL(generalUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        Set<Map.Entry<String, String>> multiParaSet=multiParaMap.entrySet();
        for (Map.Entry<String, String> multiPara:multiParaSet
        ) {
            connection.setRequestProperty(multiPara.getKey(),multiPara.getValue());
        }
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(fileByte);
        out.flush();
        out.close();
        connection.connect();
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String key : headers.keySet()) {
            log.error(key + "--->" + headers.get(key));
        }
        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), encoding));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        log.error("result:" + result);
        return result;
    }

    public static String getGeneralUrl(String generalUrl, String params, String encoding)
            throws Exception {
        URL url = new URL(generalUrl+"?"+params);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String key : headers.keySet()) {
            log.error(key + "--->" + headers.get(key));
        }
        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), encoding));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        log.error("result:" + result);
        return result;
    }




    public static String formUpload(String generalUrl,Map<String,String> multiParaMap,Map<String,String> multiFile) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        HttpPost httpPost = new HttpPost(generalUrl);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        Set<Map.Entry<String, String>> multiParaSet=multiParaMap.entrySet();
        for (Map.Entry<String, String> multiPara:multiParaSet
             ) {
            multipartEntityBuilder.addPart(multiPara.getKey(), new StringBody(multiPara.getValue(), Charset.forName("utf-8")));
        }

        Set<Map.Entry<String, String>> multiFileSet=multiFile.entrySet();
        for (Map.Entry<String, String> multiFil:multiFileSet
        ) {
            multipartEntityBuilder.addPart(multiFil.getKey(), new StringBody(multiFil.getValue(), Charset.forName("utf-8")));
            File file = new File(multiFil.getValue());
            multipartEntityBuilder.addPart(multiFil.getKey(), new FileBody(file));
        }
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);
        httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        int statusCode= httpResponse.getStatusLine().getStatusCode();
        StringBuffer buffer =null;
        if(statusCode == 200){
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            buffer = new StringBuffer();
            String str = "";
            while((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            System.out.println();
        }
        httpClient.close();
        if(httpResponse!=null){
            httpResponse.close();
        }
        return buffer.toString();
    }



    public static String formUploadByHeadersparaAndMultiParaAndMultiFile(String generalUrl, Header[] headers,Map<String, String> multiParaMap, Map<String,String> multiFile) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        HttpPost httpPost = new HttpPost(generalUrl);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        Set<Map.Entry<String, String>> multiParaSet=multiParaMap.entrySet();
        for (Map.Entry<String, String> multiPara:multiParaSet
        ) {
            multipartEntityBuilder.addPart(multiPara.getKey(), new StringBody(multiPara.getValue(), Charset.forName("utf-8")));
        }

        Set<Map.Entry<String, String>> multiFileSet=multiFile.entrySet();
        for (Map.Entry<String, String> multiFil:multiFileSet
        ) {
            multipartEntityBuilder.addPart(multiFil.getKey(), new StringBody(multiFil.getValue(), Charset.forName("utf-8")));
            File file = new File(multiFil.getValue());
            multipartEntityBuilder.addPart(multiFil.getKey(), new FileBody(file));
        }

        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setHeaders(headers);
        httpPost.setEntity(httpEntity);
        httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        int statusCode= httpResponse.getStatusLine().getStatusCode();
        StringBuffer buffer =null;
        if(statusCode == 200){
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            buffer = new StringBuffer();
            String str = "";
            while((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            System.out.println();
        }
        httpClient.close();
        if(httpResponse!=null){
            httpResponse.close();
        }
        return buffer.toString();
    }


    public static String body(Map<String, String> param) throws UnsupportedEncodingException {
        String result="";
        List<Map.Entry<String, String>> itmes = new ArrayList<Map.Entry<String, String>>(param.entrySet());
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> item : itmes) {
            if (StringUtils.isNotBlank(item.getKey())) {
                String key = item.getKey();
                String val = URLEncoder.encode(item.getValue(),"UTF-8");
                    sb.append(key + "=" + val);
                sb.append("&");
            }
        }
        result = sb.toString();
        if (!result.isEmpty()) {
            result = result.substring(0, result.length()- 1);
        }
        result=result.toString();
        return result;
    }

}
