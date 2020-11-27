package com.galaxyeye.websocket.test.galaxyeye.Article.Service; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.Service
 * @Date Create on 16:46
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/10日galaxyeye All Rights Reserved.
 */
import com.galaxyeye.websocket.application.repository.AssLabRepository;
import com.galaxyeye.websocket.application.repository.MainTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssLabExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewMainTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.TestCase.NewMainTypeTest;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class LabServiceCon extends BaseTest {

    @Autowired
    private MainTypeRepository mainTypeRepository;

    @Autowired
    private NewMainTypeTest newMainTypeTest;

    @Autowired
    private AssLabRepository assLabRepository;

    public   List<MainType> mainTypeList= null;

    public  HashMap<String, String> hs= null;

    @Override
    public final String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    public final void initData(){
        destroyData();
        try{
            hs=userLoginTest();
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            String newMainTypeResult=newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);
            Assert.assertEquals(newMainTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newMainTypeResult.contains("\"result\":1"), true);
            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            mainTypeList= mainTypeRepository.selectByExample(example);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public final void destroyData() {
        AssLabExample example=new AssLabExample();
        example.createCriteria().andAssLabNameIn(new ArrayList(){{
            add("yy");
            add("y y");
            add("😂✌");
            add("y");
        }});
        assLabRepository.deleteByExample(example);
        newMainTypeTest.destroyData();
    }

    public HashMap<String, String> getHs() {
        return hs;
    }
    public List<MainType> getMainTypeList() {
        return mainTypeList;
    }

}
