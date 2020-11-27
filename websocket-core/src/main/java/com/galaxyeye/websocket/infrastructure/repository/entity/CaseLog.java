package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;
import org.glassfish.grizzly.http.util.TimeStamp;

import java.io.Serializable;
import java.sql.Timestamp;
@ToString
@Data
public class CaseLog implements Serializable {
    private static final long serialVersionUID = 8827107487708343038L;
    private Integer id;

    private Integer httpid;

    private String projectname;

    private String modulename;

    private String classname;

    private String methodname;

    private Timestamp begintime;

    private Timestamp endtime;

    private Double lasttime;

    private String status;

    private String description;

    private String batchno;

}