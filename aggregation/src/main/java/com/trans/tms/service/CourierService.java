package com.trans.tms.service;

import com.trans.tms.DTO.AppCourierQueryDTO;
import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.common.utils.PageResponse;

public interface CourierService {
    PageResponse<TaskPickupDispatchDTO> findByPage(AppCourierQueryDTO dto);
}
