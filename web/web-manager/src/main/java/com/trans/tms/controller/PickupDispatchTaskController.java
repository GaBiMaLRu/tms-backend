package com.trans.tms.controller;

import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.DTO.webManager.TaskPickupDispatchQueryDTO;
import com.trans.tms.authority.api.AreaApi;
import com.trans.tms.authority.api.OrgApi;
import com.trans.tms.authority.api.UserApi;
import com.trans.tms.common.utils.PageResponse;
import com.trans.tms.feign.OrderFeign;
import com.trans.tms.feign.PickupDispatchTaskFeign;
import com.trans.tms.feign.webManager.WebManagerFeign;
import com.trans.tms.util.BeanUtil;
import com.trans.tms.vo.work.TaskPickupDispatchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 取件、派件任务信息表 前端控制器
 * </p>
 *
 * @author jpf
 * @since 2019-12-29
 */
@Api(tags = "派件、取件任务相关API")
@Slf4j
@RestController
@RequestMapping("pickup-dispatch-task-manager")
public class PickupDispatchTaskController {
    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private AreaApi areaApi;
    @Autowired
    private OrgApi orgApi;
    @Autowired
    private PickupDispatchTaskFeign pickupDispatchTaskFeign;
    @Autowired
    private UserApi userApi;
    @Autowired
    private WebManagerFeign webManagerFeign;

    @ApiOperation(value = "获取取派件分页数据")
    @PostMapping("/page")
    public PageResponse<TaskPickupDispatchVo> findByPage(@RequestBody TaskPickupDispatchVo vo) {
        TaskPickupDispatchQueryDTO dto = new TaskPickupDispatchQueryDTO();
        if (vo != null) {
            dto.setPage(vo.getPage());
            dto.setPageSize(vo.getPageSize());
            if (vo.getTransportOrder() != null) {
                dto.setTransportOrderId(vo.getTransportOrder().getId());
            }
            if (vo.getCourier() != null) {
                dto.setCourierName(vo.getCourier().getName());
            }
            dto.setTaskType(vo.getTaskType());
            dto.setStatus(vo.getStatus());
            if (vo.getOrder() != null) {
                dto.setSenderName(vo.getOrder().getSenderName());
                if (vo.getOrder().getSenderProvince() != null) {
                    dto.setSenderProvinceId(vo.getOrder().getSenderProvince().getId());
                }
                if (vo.getOrder().getSenderCity() != null) {
                    dto.setSenderCityId(vo.getOrder().getSenderCity().getId());
                }
                dto.setReceiverName(vo.getOrder().getReceiverName());
                if (vo.getOrder().getReceiverProvince() != null) {
                    dto.setReceiverProvinceId(vo.getOrder().getReceiverProvince().getId());
                }
                if (vo.getOrder().getReceiverCity() != null) {
                    dto.setReceiverCityId(vo.getOrder().getReceiverCity().getId());
                }
            }
        }
        PageResponse<TaskPickupDispatchDTO> dtoPageResponse = webManagerFeign.findTaskPickupDispatchJobByPage(dto);
        List<TaskPickupDispatchDTO> dtoList = dtoPageResponse.getItems();
        List<TaskPickupDispatchVo> voList = dtoList.stream().map(taskPickupDispatchDTO -> BeanUtil.parseTaskPickupDispatchDTO2Vo(taskPickupDispatchDTO, orderFeign, areaApi, orgApi, userApi)).collect(Collectors.toList());
        return PageResponse.<TaskPickupDispatchVo>builder().items(voList).size(vo.getPageSize()).current(vo.getPage()).counts(dtoPageResponse.getCounts()).total(dtoPageResponse.getTotal()).build();
    }

    @ApiOperation(value = "更新取派件任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "取派件任务id", required = true, example = "1", paramType = "{path}")
    })
    @PutMapping("/{id}")
    public TaskPickupDispatchVo update(@PathVariable(name = "id") String id, @RequestBody TaskPickupDispatchVo vo) {
        TaskPickupDispatchDTO dto = pickupDispatchTaskFeign.updateById(id, BeanUtil.parseTaskPickupDispatchVo2DTO(vo));
        return BeanUtil.parseTaskPickupDispatchDTO2Vo(dto, orderFeign, areaApi, orgApi, userApi);
    }
}
