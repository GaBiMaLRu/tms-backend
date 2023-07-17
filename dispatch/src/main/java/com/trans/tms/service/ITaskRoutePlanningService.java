package com.trans.tms.service;

import com.trans.tms.dto.OrderClassifyGroupDTO;
import com.trans.tms.dto.OrderLineSimpleDTO;

import java.util.List;

/**
 * 路线规划
 */
public interface ITaskRoutePlanningService {
    /**
     * 执行
     * @return
     */
    List<OrderLineSimpleDTO> execute(List<OrderClassifyGroupDTO> orderClassifyGroupDTOS, String agencyId, String jobId, String logId, String params);
}
