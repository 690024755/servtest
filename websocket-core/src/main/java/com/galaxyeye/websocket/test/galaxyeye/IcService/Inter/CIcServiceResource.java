package com.galaxyeye.websocket.test.galaxyeye.IcService.Inter; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.IcService.Inter
 * @Date Create on 16:38
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/2日galaxyeye All Rights Reserved.
 */
import com.galaxyeye.websocket.application.repository.TbProAccountRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.TbProAccount;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TbProAccountExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 人保的专家与业务员新增初始化数据：
 * INSERT INTO `icservicedb`.`tb_pro_account`(`id`, `cid`, `cname`, `cpwd`, `euid`, `appid`, `status`, `uid`, `sendNum`, `type`, `create_time`, `update_time`) VALUES (628, '123458', '专家', '123458', '1273860839975161856', '4.00099', 1, 239556, 0, 1, '2020-08-12 17:06:28', '2020-08-13 10:30:34');
 * INSERT INTO `icservicedb`.`tb_pro_account`(`id`, `cid`, `cname`, `cpwd`, `euid`, `appid`, `status`, `uid`, `sendNum`, `type`, `create_time`, `update_time`) VALUES (627, 'putong3', '业务员', 'putong3', '1273860839975161856', '4.00099', 1, 239555, 0, 0, '2020-08-12 17:06:02', '2020-08-12 20:29:26');
 *
 */
public abstract class CIcServiceResource extends BaseTest implements IIcServiceResource<TbProAccountRepository>{
    //查询expertAppid下所有专家
    private String expertAppid;
    //专家工号
    private String expertCid;
    //专家uid
    private Long expertUid;
    //所欲专家工号默认登录密码
    private String expertPwd=expertCid;

    private String expertNickname;

    private String expertContent="专家Content问题";

    //普通用户的工号
    private String commonCid;
    //普通用户uid
    private Long commonUid;
    //普通用户默认登录密码
    private String commonPwd;

    private String commonNickname;

    private String commonContent="咨询者Content问题";

    @Autowired
    private TbProAccountRepository tbProAccountRepository;


    @Override
    public void initData() {
        initPara();
    }


    @Override
    public void destroyData() {
        TbProAccountExample example=new TbProAccountExample();
        TbProAccountExample.Criteria cr=example.createCriteria();
        List<String> cids=new ArrayList<>();
        cids.add("123458");
        cids.add("putong3");
        cr.andCidIn(cids);
        tbProAccountRepository.deleteByExample(example);
    }

    private void initPara(){
        TbProAccountExample Example=new TbProAccountExample();
        TbProAccountExample.Criteria Cr=Example.createCriteria();
        List<String> cids=new ArrayList<>();
        //查询工号，工号=123458为测试专家
        //查询工号，工号=putong3为普通账号
        cids.add("123458");
        cids.add("putong3");
        Cr.andCidIn(cids);
        List<TbProAccount> list=tbProAccountRepository.selectByExample(Example);
        Integer i=0;
        for (TbProAccount tbProAccount:list
        ) {
            //uid=239192L使用杨逸微信
            if(tbProAccount.getType().equals(1) && tbProAccount.getUid().equals(239192L)){
                //专家工号
                expertCid=tbProAccount.getCid();
                expertUid=tbProAccount.getUid();
                //expertAppid
                expertAppid=tbProAccount.getAppid();
                expertPwd=expertCid;
                expertNickname=expertCid;
                i++;
            }else if(tbProAccount.getType().equals(0) && tbProAccount.getUid().equals(239192L)){ //uid=239192L使用杨逸微信
                //普通账号即业务员，一般是业务员向专家提问题
                commonUid=tbProAccount.getUid();
                commonCid=tbProAccount.getCid();
                commonPwd=commonCid;
                commonNickname=commonCid;
                i++;
            }
        }
        if(i!=2) {
            destroyData();
            TbProAccount expertRecord=new TbProAccount();
            expertRecord.setAppid("4.00099");
            expertRecord.setCid("123458");
            expertRecord.setCname("专家");
            expertRecord.setCpwd("123458");
            expertRecord.setEuid("1273860839975161856");
            expertRecord.setStatus(1);
            expertRecord.setUid(239192L);
            expertRecord.setSendnum(-1000L);
            expertRecord.setType(1);
            expertRecord.setCreateTime(new Date());
            expertRecord.setUpdateTime(new Date());
            tbProAccountRepository.insertSelective(expertRecord);
            TbProAccount commonRecord=new TbProAccount();
            commonRecord.setAppid("4.00099");
            commonRecord.setCid("putong3");
            commonRecord.setCname("业务员");
            commonRecord.setCpwd("putong3");
            commonRecord.setEuid("1273860839975161856");
            commonRecord.setStatus(1);
            commonRecord.setUid(239192L);
            commonRecord.setSendnum(0L);
            commonRecord.setType(0);
            commonRecord.setCreateTime(new Date());
            commonRecord.setUpdateTime(new Date());
            tbProAccountRepository.insertSelective(commonRecord);
            //专家工号
            expertCid=expertRecord.getCid();
            expertUid=expertRecord.getUid();
            //expertAppid
            expertAppid=expertRecord.getAppid();
            expertPwd=expertCid;
            expertNickname=expertCid;

            //普通账号即业务员，一般是业务员向专家提问题
            commonUid=commonRecord.getUid();
            commonCid=commonRecord.getCid();
            commonPwd=commonCid;
            commonNickname=commonCid;
        }
    }

    /**
     * 禁用专家
     * Status=0禁用
     * Status=1启用
     */
    public void disableOrEnableExpert(Integer status){
        TbProAccount record=new TbProAccount();
        record.setStatus(0);
        TbProAccountExample example=new TbProAccountExample();
        TbProAccountExample.Criteria cr=example.createCriteria();
        cr.andCidEqualTo("123458");
        tbProAccountRepository.updateByExampleSelective(record,example);
    }

    @Override
    public TbProAccountRepository getT() {
        return tbProAccountRepository;
    }

    @Override
    public String getExpertAppid() {
        return this.expertAppid;
    }

    @Override
    public String getExpertCid() {
        return this.expertCid;
    }

    @Override
    public String getExpertPwd() {
        return this.expertPwd;
    }

    @Override
    public Long getExpertUid() {
        return this.expertUid;
    }

    @Override
    public String getExpertNickname() {
        return this.expertNickname;
    }

    @Override
    public String getExpertContent() {
        return this.expertContent;
    }

    @Override
    public String getCommonCid() {
        return this.commonCid;
    }

    @Override
    public Long getCommonUid() {
        return this.commonUid;
    }

    @Override
    public String getCommonPwd() {
        return this.commonPwd;
    }

    @Override
    public String getCommonNickname() {
        return this.commonNickname;
    }



    @Override
    public String getCommonContent() {
        return this.commonContent;
    }

}
