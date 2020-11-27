package com.galaxyeye.websocket.test.galaxyeye.Acc.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.DTO
 * @Date Create on 10:30
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/18日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.DelUserBindBO;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@ToString
public class DelUserBindDTO {
    /**
     * keys : [{"cacc":"Cacc_yy"}]
     * result : 1
     * seq : null
     * uname : 225825
     */
    private int result;
    private Object seq;
    private int uname;
    private List<KeysBean> keys;

    @Data
    @ToString
    public static class KeysBean {
        private String unionid;
        private String deviceid;
        private String openid;
        private String cacc;
        private String nickname;
        private String email;
        private String mobile;

        public String get(KeysBean keysBean){
            String tmp="";
            if(keysBean.getCacc()!=null){
                tmp= keysBean.getCacc();
            }else if(keysBean.getDeviceid()!=null){
                tmp= keysBean.getDeviceid();
            }else if(keysBean.getEmail()!=null){
                tmp= keysBean.getEmail();
            }else if(keysBean.getMobile()!=null){
                tmp= keysBean.getMobile();
            }else if(keysBean.getNickname()!=null){
                tmp= keysBean.getNickname();
            }else if(keysBean.getOpenid()!=null){
                tmp= keysBean.getOpenid();
            }else if(keysBean.getUnionid()!=null){
                tmp= keysBean.getUnionid();
            }
            return tmp;
        }


    }
}
