package com.trans.tms.service;

import com.trans.tms.dto.OrderLineSimpleDTO;
import com.trans.tms.dto.OrderLineTripsTruckDriverDTO;
import com.trans.tms.DTO.TaskTransportDTO;

import java.util.List;
import java.util.Map;

public interface IBusinessOperationService {
    /**
     * 创建运输任务 更新运单
     *
     * @param orderLineSimpleDTOS
     * @return 运输任务id
     */
    Map<String, TaskTransportDTO> createTransportOrderTask(List<OrderLineSimpleDTO> orderLineSimpleDTOS);

    /**
     * 更新运输任务 创建司机作业单
     *
     * @param orderLineTripsTruckDriverDTOS
     * @param transportTaskMap
     */
    void updateTransportTask(List<OrderLineTripsTruckDriverDTO> orderLineTripsTruckDriverDTOS, Map<String, TaskTransportDTO> transportTaskMap);
}
