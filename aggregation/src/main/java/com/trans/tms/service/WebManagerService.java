package com.trans.tms.service;

import com.trans.tms.DTO.DriverJobDTO;
import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.DTO.TaskTransportDTO;
import com.trans.tms.DTO.TransportOrderDTO;
import com.trans.tms.DTO.webManager.DriverJobQueryDTO;
import com.trans.tms.DTO.webManager.TaskPickupDispatchQueryDTO;
import com.trans.tms.DTO.webManager.TaskTransportQueryDTO;
import com.trans.tms.DTO.webManager.TransportOrderQueryDTO;
import com.trans.tms.common.utils.PageResponse;

public interface WebManagerService {
    /**
     * 获取司机作业单分页数据
     *
     * @param dto 查询参数
     * @return 司机作业单分页数据
     */
    PageResponse<DriverJobDTO> findDriverJobByPage(DriverJobQueryDTO dto);

    /**
     * 获取取派件任务分页数据
     *
     * @param dto 查询参数
     * @return 取派件分页数据
     */
    PageResponse<TaskPickupDispatchDTO> findTaskPickupDispatchJobByPage(TaskPickupDispatchQueryDTO dto);

    /**
     * 获取运单分页数据
     *
     * @param dto 查询参数
     * @return 运单分页数据
     */
    PageResponse<TransportOrderDTO> findTransportOrderByPage(TransportOrderQueryDTO dto);

    /**
     * 获取运输任务分页数据
     *
     * @param dto 查询参数
     * @return 运输任务分页数据
     */
    PageResponse<TaskTransportDTO> findTaskTransportByPage(TaskTransportQueryDTO dto);
}
