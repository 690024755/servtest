package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@ToString
@Data
public class SysRole implements Serializable {
    private Integer roleId;

    private String roleType;

    private String roleName;

    private Integer parentRole;

    private Boolean status;
}