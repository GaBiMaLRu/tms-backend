package com.trans.tms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trans.tms.DTO.DriverJobDTO;
import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.DTO.TaskTransportDTO;
import com.trans.tms.DTO.TransportOrderDTO;
import com.trans.tms.DTO.webManager.DriverJobQueryDTO;
import com.trans.tms.DTO.webManager.TaskPickupDispatchQueryDTO;
import com.trans.tms.DTO.webManager.TaskTransportQueryDTO;
import com.trans.tms.DTO.webManager.TransportOrderQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WebManagerMapper extends BaseMapper {
    IPage<DriverJobDTO> findDriverJobByPage(IPage<DriverJobDTO> iPage, @Param("params") DriverJobQueryDTO dto);

    IPage<TaskPickupDispatchDTO> findTaskPickupDispatchJobByPage(IPage<TaskPickupDispatchDTO> iPage, @Param("params") TaskPickupDispatchQueryDTO dto);

    IPage<TransportOrderDTO> findTransportOrderByPage(IPage<TransportOrderDTO> iPage, @Param("params") TransportOrderQueryDTO dto);

    IPage<TaskTransportDTO> findTaskTransportByPage(IPage<TaskTransportDTO> iPage, @Param("params") TaskTransportQueryDTO dto);
}
