package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.XmlCase;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.XmlCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmlCaseMapper {
    long countByExample(XmlCaseExample example);

    int deleteByExample(XmlCaseExample example);

    int deleteAll();

    int insert(XmlCase record);

    int insertSelective(XmlCase record);

    List<XmlCase> selectByExample(XmlCaseExample example);

    List<XmlCase> selectAllModuleName();

    int updateByExampleSelective(@Param("record") XmlCase record, @Param("example") XmlCaseExample example);

    int updateByExample(@Param("record") XmlCase record, @Param("example") XmlCaseExample example);
}