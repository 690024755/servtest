package com.galaxyeye.websocket.tool.domain; /*
 * Description:com.galaxyeye.websocket
 * @Date Create on 10:40
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/11日galaxyeye All Rights Reserved.
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result<T> extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -3375529792850475062L;

    private Boolean success;
    private String errorMessage;
    private Exception e;
    private T data;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public static  Result instance() {
        return new Result();
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String,Object> toJsonMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("data",this.data);
        map.put("success",this.success);
        map.put("errorMessage",this.errorMessage);
        return  map;
    }

}
