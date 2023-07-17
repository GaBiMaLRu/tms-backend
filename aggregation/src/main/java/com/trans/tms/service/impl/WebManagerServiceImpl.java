package com.trans.tms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.tms.DTO.DriverJobDTO;
import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.DTO.TaskTransportDTO;
import com.trans.tms.DTO.TransportOrderDTO;
import com.trans.tms.DTO.webManager.DriverJobQueryDTO;
import com.trans.tms.DTO.webManager.TaskPickupDispatchQueryDTO;
import com.trans.tms.DTO.webManager.TaskTransportQueryDTO;
import com.trans.tms.DTO.webManager.TransportOrderQueryDTO;
import com.trans.tms.common.utils.PageResponse;
import com.trans.tms.mapper.WebManagerMapper;
import com.trans.tms.service.WebManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebManagerServiceImpl implements WebManagerService {

    @Autowired
    private WebManagerMapper webManagerMapper;

    @Override
    public PageResponse<DriverJobDTO> findDriverJobByPage(DriverJobQueryDTO dto) {
        IPage<DriverJobDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        webManagerMapper.findDriverJobByPage(iPage, dto);
        return PageResponse.<DriverJobDTO>builder()
                .counts(iPage.getTotal())
                .total(iPage.getPages())
                .size(dto.getPageSize())
                .current(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }

    @Override
    public PageResponse<TaskPickupDispatchDTO> findTaskPickupDispatchJobByPage(TaskPickupDispatchQueryDTO dto) {
        IPage<TaskPickupDispatchDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        webManagerMapper.findTaskPickupDispatchJobByPage(iPage, dto);
        return PageResponse.<TaskPickupDispatchDTO>builder()
                .counts(iPage.getTotal())
                .total(iPage.getPages())
                .size(dto.getPageSize())
                .current(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }

    @Override
    public PageResponse<TransportOrderDTO> findTransportOrderByPage(TransportOrderQueryDTO dto) {
        IPage<TransportOrderDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        webManagerMapper.findTransportOrderByPage(iPage, dto);
        return PageResponse.<TransportOrderDTO>builder()
                .counts(iPage.getTotal())
                .total(iPage.getPages())
                .size(dto.getPageSize())
                .current(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }

    @Override
    public PageResponse<TaskTransportDTO> findTaskTransportByPage(TaskTransportQueryDTO dto) {
        IPage<TaskTransportDTO> iPage = new Page();
        iPage.setSize(dto.getPageSize());
        iPage.setCurrent(dto.getPage());
        webManagerMapper.findTaskTransportByPage(iPage, dto);
        return PageResponse.<TaskTransportDTO>builder()
                .counts(iPage.getTotal())
                .total(iPage.getPages())
                .size(dto.getPageSize())
                .current(dto.getPage())
                .items(iPage.getRecords())
                .build();
    }
}
