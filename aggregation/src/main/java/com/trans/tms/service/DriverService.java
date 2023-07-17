package com.trans.tms.service;

import com.trans.tms.DTO.AppDriverQueryDTO;
import com.trans.tms.DTO.DriverJobDTO;
import com.trans.tms.common.utils.PageResponse;

public interface DriverService {
    PageResponse<DriverJobDTO> findByPage(AppDriverQueryDTO dto);
}
