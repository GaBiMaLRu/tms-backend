package com.trans.tms.controller;

import com.trans.tms.DTO.AppCourierQueryDTO;
import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.common.utils.PageResponse;
import com.trans.tms.service.CourierService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "快递员聚合平台")
@RestController
@RequestMapping("appCourier")
public class AppCourierController {

    @Autowired
    private CourierService courierService;

    @PostMapping("/page")
    PageResponse<TaskPickupDispatchDTO> findByPage(@RequestBody AppCourierQueryDTO dto) {
        return courierService.findByPage(dto);
    }
}
