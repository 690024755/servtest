package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 19:18
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/26日galaxyeye All Rights Reserved.
 */
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class BusinessServiceWzhrssBankListDTO implements Serializable {
    private static final long serialVersionUID = -8132580253469960676L;
    /**
     * list : [{"bank":"中国工商银行","no":"1001"},{"bank":"中国农业银行","no":"1002"},{"bank":"中国建设银行","no":"1003"},{"bank":"中国银行","no":"1004"},{"bank":"交通银行","no":"1005"},{"bank":"温州银行","no":"1006"},{"bank":"浙江省农信联社","no":"1007"},{"bank":"中国邮政储蓄银行","no":"1008"}]
     * msg : ok
     * result : 1
     * seq : null
     */
    private String msg;
    private int result;
    private Object seq;
    private List<Bank> list;

    @Data
    @ToString
    public static class Bank implements Serializable{
        private static final long serialVersionUID = 1694624626302762390L;
        /**
         * bank : 中国工商银行
         * no : 1001
         */
        private String bank;
        private String no;
    }

}
