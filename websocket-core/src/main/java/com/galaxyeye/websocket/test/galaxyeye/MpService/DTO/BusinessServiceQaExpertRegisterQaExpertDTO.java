package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 13:22
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/29日galaxyeye All Rights Reserved.
 */
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BusinessServiceQaExpertRegisterQaExpertDTO implements Serializable{
    private static final long serialVersionUID = -203277220154296217L;
    /**
     * data : {"uid":237671,"expert_no":"123456"}
     * pageNum : 0
     * pageSize : 0
     * ret_code : 1
     * ret_msg : OK
     * total : 0
     */
    private Expert data;
    private int pageNum;
    private int pageSize;
    private int ret_code;
    private String ret_msg;
    private int total;
    @Data
    @ToString
    public static class Expert implements Serializable {
        private static final long serialVersionUID = 6242739997395856716L;
        /**
         * uid : 237671
         * expert_no : 123456
         */
        private int uid;
        private String expert_no;
    }
}
