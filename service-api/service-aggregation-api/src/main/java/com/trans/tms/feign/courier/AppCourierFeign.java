package com.trans.tms.feign.courier;

import com.trans.tms.DTO.AppCourierQueryDTO;
import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.common.utils.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@FeignClient(name = "aggregation")
@RequestMapping("appCourier")
@ApiIgnore
public interface AppCourierFeign {
    /**
     * 分页查询快递员任务
     *
     * @param dto
     * @return
     */
    @PostMapping("page")
    PageResponse<TaskPickupDispatchDTO> findByPage(@RequestBody AppCourierQueryDTO dto);

}
