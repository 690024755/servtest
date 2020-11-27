package com.galaxyeye.websocket.shell;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@Slf4j
public class RemoteConnect {
    private String ip;

    private String userName;

    private String password;
}
