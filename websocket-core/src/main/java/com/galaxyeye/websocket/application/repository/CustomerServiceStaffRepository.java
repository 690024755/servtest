package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.CustomerServiceStaff;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.CustomerServiceStaffExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerServiceStaffRepository {
    Long countByExample(CustomerServiceStaffExample example);

    Integer deleteByExample(CustomerServiceStaffExample example);

    Integer insert(CustomerServiceStaff record);

    Integer insertSelective(CustomerServiceStaff record);

    List<CustomerServiceStaff> selectByExample(CustomerServiceStaffExample example);

    Integer updateByExampleSelective(@Param("record") CustomerServiceStaff record, @Param("example") CustomerServiceStaffExample example);

    Integer updateByExample(@Param("record") CustomerServiceStaff record, @Param("example") CustomerServiceStaffExample example);

}
