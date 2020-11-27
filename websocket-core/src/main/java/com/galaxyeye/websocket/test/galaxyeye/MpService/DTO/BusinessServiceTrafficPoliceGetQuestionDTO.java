package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 16:29
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/7日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class BusinessServiceTrafficPoliceGetQuestionDTO implements Serializable {
    private static final long serialVersionUID = 6892751068235745435L;
    /**
     * msg : ok
     * question : {"id":1,"idStr":"1","type":"判断","statement":"安装防抱死制动装置（ABS）的机动车制动时，制动距离会大大缩短。（  ）","image":[],"option":[{"text":"A、正确","img":[]},{"text":"B、错误","img":[]}],"qcount":0,"right":0,"complete":0,"isFinished":false,"beat":0,"rank":0}
     * result : 1
     * seq : null
     */
    private String msg;
    private QuestionBean question;
    private Integer result;
    private Object seq;

    @Data
    @ToString
    public static class QuestionBean {
        /**
         * id : 1
         * idStr : 1
         * type : 判断
         * statement : 安装防抱死制动装置（ABS）的机动车制动时，制动距离会大大缩短。（  ）
         * image : []
         * option : [{"text":"A、正确","img":[]},{"text":"B、错误","img":[]}]
         * qcount : 0
         * right : 0
         * complete : 0
         * isFinished : false
         * beat : 0
         * rank : 0
         */
        private Integer id;
        private String idStr;
        private String type;
        private String statement;
        private Integer qcount;
        private Integer right;
        private Integer complete;
        private Boolean isFinished;
        private Integer beat;
        private Integer rank;
        private List<String> image;
        private List<OptionBean> option;

        @Data
        @ToString
        public static class OptionBean {
            /**
             * text : A、正确
             * img : []
             */
            private String text;
            private List<String> img;
        }
    }
}
