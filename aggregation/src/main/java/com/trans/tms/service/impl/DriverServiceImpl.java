package com.trans.tms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.tms.DTO.AppDriverQueryDTO;
import com.trans.tms.DTO.DriverJobDTO;
import com.trans.tms.common.utils.PageResponse;
import com.trans.tms.mapper.DriverMapper;
import com.trans.tms.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverMapper driverMapper;

    @Override
    public PageResponse<DriverJobDTO> findByPage(AppDriverQueryDTO dto) {
        IPage<DriverJobDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        driverMapper.findByPage(iPage, dto);

        return PageResponse.<DriverJobDTO>builder()
                .counts(iPage.getTotal())
                .total(iPage.getPages())
                .size(dto.getPageSize())
                .current(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }
}
