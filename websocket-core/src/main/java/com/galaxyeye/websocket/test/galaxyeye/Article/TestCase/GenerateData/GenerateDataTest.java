package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase.GenerateData; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 15:42
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/10日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.AssLabRepository;
import com.galaxyeye.websocket.application.repository.AssSubTypeRepository;
import com.galaxyeye.websocket.application.repository.MainTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssSubType;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

@Slf4j
@ContextConfiguration(locations = {"/META-INF/spring/websocket-context.xml"})
public class GenerateDataTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AssLabRepository assLabRepository;

    @Autowired
    private AssSubTypeRepository assSubTypeRepository;

    @Autowired
    private MainTypeRepository mainTypeRepository;


    /**
     * 批量生成测试数据
     * @throws Exception
     */
    @Test
    public void generateData() throws Exception {
        for (int i = 0; i < 1000; i++) {
            int maxLegth = 8;
            //mainType表插入数据
            Long idMainType =mainTypeRepository.getId();
            MainType mainType=new MainType();
            mainType.setCreatedAt(new Date());
            mainType.setId(idMainType);

            StringBuffer MainTypeCode = new StringBuffer("01");
            for (int k = 0; k < maxLegth - (String.valueOf(idMainType).length() + 2); k++) {
                MainTypeCode.append("0");
            }
            MainTypeCode.append(idMainType);
            mainType.setMainTypeCode(MainTypeCode.toString());
            mainType.setMainTypeName("mainTypeName"+idMainType);
            mainTypeRepository.insertSelective(mainType);


            Long idAssLab = assLabRepository.getId();
            AssLab recordassLab = new AssLab();
            recordassLab.setId(idAssLab);

            StringBuffer AssLabCode = new StringBuffer("03");
            for (int k = 0; k < maxLegth - (String.valueOf(idAssLab).length() + 2); k++) {
                AssLabCode.append("0");
            }
            recordassLab.setAssLabCode(AssLabCode.append(idAssLab).toString());
            recordassLab.setAssLabName("assLabName" + idAssLab);
            recordassLab.setMainTypeCode(MainTypeCode.toString());
            recordassLab.setCreatedAt(new Date());
            System.out.println("assLabRepository.insertSelective(record)=" + assLabRepository.insertSelective(recordassLab));
            System.out.println(recordassLab);

            Long idAssSubType = assSubTypeRepository.getId();
            AssSubType recordAssSubType = new AssSubType();
            recordAssSubType.setId(idAssSubType);
            StringBuffer assSubTypeCode = new StringBuffer("02");
            for (int k = 0; k < maxLegth - (String.valueOf(idAssSubType).length() + 2); k++) {
                assSubTypeCode.append("0");
            }
            recordAssSubType.setAssSubTypeCode(assSubTypeCode.append(idAssSubType).toString());
            recordAssSubType.setAssSubTypeName("assSubTypeName" + idAssSubType);
            recordAssSubType.setMainTypeCode(MainTypeCode.toString());
            recordAssSubType.setCreatedAt(new Date());
            System.out.println("assSubTypeRepository.insertSelective(record)=" + assSubTypeRepository.insertSelective(recordAssSubType));
            System.out.println(recordAssSubType);
        }

    }

}
