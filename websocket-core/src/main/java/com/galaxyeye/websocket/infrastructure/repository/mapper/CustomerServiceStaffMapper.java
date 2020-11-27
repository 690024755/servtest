package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.CustomerServiceStaff;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.CustomerServiceStaffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerServiceStaffMapper {
    long countByExample(CustomerServiceStaffExample example);

    int deleteByExample(CustomerServiceStaffExample example);

    int insert(CustomerServiceStaff record);

    int insertSelective(CustomerServiceStaff record);

    List<CustomerServiceStaff> selectByExample(CustomerServiceStaffExample example);

    int updateByExampleSelective(@Param("record") CustomerServiceStaff record, @Param("example") CustomerServiceStaffExample example);

    int updateByExample(@Param("record") CustomerServiceStaff record, @Param("example") CustomerServiceStaffExample example);
}