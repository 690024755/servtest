package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 17:45
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/21日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class HealthPlanAbsConfigParentDTO implements Serializable {
    private static final long serialVersionUID = 5607284826273665423L;
    /**
     * appid :
     * env :
     * version :
     * key :
     * data : {"healthPlan":[{"id":"1","name":"7天压力释放计划","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309798804361216/613607293698707456.png"},{"id":"2","name":"7天元气恢复计划","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309803191603200/613605459055611904.jpg"},{"id":"3","name":"7天补水保湿计划","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg"},{"id":"4","name":"14天晚安好眠计划","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg"},{"id":"5","name":"14天发量拯救计划","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg"},{"id":"6","name":"21天脾胃养护计划","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915824144384.jpg"},{"id":"7","name":"21天颈椎保护计划","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg"}],"defaultHealthPlanId":["1","2","3"]}
     * config : {"defaultHealthPlanId":["1","2","3"],"healthPlan":[{"id":"1","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309798804361216/613607293698707456.png","name":"7天压力释放计划"},{"id":"2","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309803191603200/613605459055611904.jpg","name":"7天元气恢复计划"},{"id":"3","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg","name":"7天补水保湿计划"},{"id":"4","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg","name":"14天晚安好眠计划"},{"id":"5","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg","name":"14天发量拯救计划"},{"id":"6","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915824144384.jpg","name":"21天脾胃养护计划"},{"id":"7","image":"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg","name":"21天颈椎保护计划"}]}
     * createdAt :
     * updatedAt : 2020-03-02 17:35:40
     * comment :
     * enable : 0
     * verify : 0
     * editor :
     */
    private String appid;
    private String env;
    private String version;
    private String key;
    private String data;
    private HealthPlanAbsConfigSubDTO config;
    private String createdAt;
    private String updatedAt;
    private String comment;
    private int enable;
    private int verify;
    private String editor;
}
