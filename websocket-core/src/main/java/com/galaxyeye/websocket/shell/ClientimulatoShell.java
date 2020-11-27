package com.galaxyeye.websocket.shell;

import java.io.IOException;

public class ClientimulatoShell {

    public static void exec() throws IOException {

        String result= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","/usr/local/tes_test/client_simulator.simulator" );
        System.out.println("exec result="+result);
    }
}
