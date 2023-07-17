package com.trans.tms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.tms.DTO.AppCourierQueryDTO;
import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.common.utils.PageResponse;
import com.trans.tms.mapper.CourierMapper;
import com.trans.tms.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierMapper courierMapper;

    @Override
    public PageResponse<TaskPickupDispatchDTO> findByPage(AppCourierQueryDTO dto) {
        IPage<TaskPickupDispatchDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        courierMapper.findByPage(iPage, dto);

        return PageResponse.<TaskPickupDispatchDTO>builder()
                .counts(iPage.getTotal())
                .total(iPage.getPages())
                .size(dto.getPageSize())
                .current(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }
}
