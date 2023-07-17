package com.trans.tms.controller;

import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.DTO.TransportOrderDTO;
import com.trans.tms.DTO.webManager.TransportOrderQueryDTO;
import com.trans.tms.authority.api.AreaApi;
import com.trans.tms.authority.api.OrgApi;
import com.trans.tms.authority.api.UserApi;
import com.trans.tms.common.utils.PageResponse;
import com.trans.tms.enums.pickuptask.PickupDispatchTaskType;
import com.trans.tms.feign.OrderFeign;
import com.trans.tms.feign.PickupDispatchTaskFeign;
import com.trans.tms.feign.TransportOrderFeign;
import com.trans.tms.feign.TransportTaskFeign;
import com.trans.tms.feign.transportline.TransportTripsFeign;
import com.trans.tms.feign.truck.TruckFeign;
import com.trans.tms.feign.webManager.WebManagerFeign;
import com.trans.tms.util.BeanUtil;
import com.trans.tms.vo.work.TaskTransportVo;
import com.trans.tms.vo.work.TransportOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 运单表 前端控制器
 * </p>
 *
 * @author jpf
 * @since 2020-01-06
 */
@Slf4j
@Api(tags = "运单相关Api")
@RestController
@RequestMapping("transport-order-manager")
public class TransportOrderController {
    @Autowired
    private TransportOrderFeign transportOrderFeign;
    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private AreaApi areaApi;
    @Autowired
    private PickupDispatchTaskFeign pickupDispatchTaskFeign;
    @Autowired
    private OrgApi orgApi;
    @Autowired
    private UserApi userApi;
    @Autowired
    private TransportTaskFeign transportTaskFeign;
    @Autowired
    private TransportTripsFeign transportTripsFeign;
    @Autowired
    private TruckFeign truckFeign;
    @Autowired
    private WebManagerFeign webManagerFeign;

    @ApiOperation(value = "获取运单分页数据")
    @PostMapping("/page")
    public PageResponse<TransportOrderVo> findByPage(@RequestBody TransportOrderVo vo) {
        TransportOrderQueryDTO dto = new TransportOrderQueryDTO();
        if (vo != null) {
            dto.setPage(vo.getPage());
            dto.setPageSize(vo.getPageSize());
            dto.setStatus(vo.getStatus());
            dto.setId(vo.getId());
            if (vo.getOrder() != null) {
                dto.setSenderName(vo.getOrder().getSenderName());
                dto.setSenderPhone(vo.getOrder().getSenderPhone());
                if (vo.getOrder().getSenderProvince() != null) {
                    dto.setSenderProvinceId(vo.getOrder().getSenderProvince().getId());
                }
                if (vo.getOrder().getSenderCity() != null) {
                    dto.setSenderCityId(vo.getOrder().getSenderCity().getId());
                }
                if (vo.getOrder().getSenderCounty() != null) {
                    dto.setSenderCountyId(vo.getOrder().getSenderCounty().getId());
                }
                dto.setReceiverName(vo.getOrder().getReceiverName());
                dto.setReceiverPhone(vo.getOrder().getReceiverPhone());
                if (vo.getOrder().getReceiverProvince() != null) {
                    dto.setReceiverProvinceId(vo.getOrder().getReceiverProvince().getId());
                }
                if (vo.getOrder().getReceiverCity() != null) {
                    dto.setReceiverCityId(vo.getOrder().getReceiverCity().getId());
                }
                if (vo.getOrder().getReceiverCounty() != null) {
                    dto.setReceiverCountyId(vo.getOrder().getReceiverCounty().getId());
                }
            }
        }
        PageResponse<TransportOrderDTO> dtoPageResponse = webManagerFeign.findTransportOrderByPage(dto);
        List<TransportOrderDTO> dtoList = dtoPageResponse.getItems();
        List<TransportOrderVo> voList = dtoList.stream().map(transportOrderDTO -> BeanUtil.parseTransportOrderDTO2Vo(transportOrderDTO, orderFeign, areaApi)).collect(Collectors.toList());
        return PageResponse.<TransportOrderVo>builder().items(voList).size(vo.getPageSize()).current(vo.getPage()).counts(dtoPageResponse.getCounts()).total(dtoPageResponse.getTotal()).build();
    }

    @ApiOperation(value = "获取运单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "运单id", required = true, example = "1", paramType = "{path}")
    })
    @GetMapping("/{id}")
    public TransportOrderVo findById(@PathVariable(name = "id") String id) {
        TransportOrderDTO dto = transportOrderFeign.findById(id);
        TransportOrderVo vo = BeanUtil.parseTransportOrderDTO2Vo(dto, orderFeign, areaApi);
        if (vo.getOrder() != null && StringUtils.isNotEmpty(vo.getOrder().getId())) {
            //查询取派件任务信息
            TaskPickupDispatchDTO taskPickupDispatchQueryDTO = new TaskPickupDispatchDTO();
            taskPickupDispatchQueryDTO.setOrderId(vo.getOrder().getId());
            List<TaskPickupDispatchDTO> taskPickupDispatchDTOList = pickupDispatchTaskFeign.findAll(taskPickupDispatchQueryDTO);
            if (taskPickupDispatchDTOList != null && taskPickupDispatchDTOList.size() > 0) {
                taskPickupDispatchDTOList.forEach(taskPickupDispatchDTO -> {
                    if (taskPickupDispatchDTO.getOrderId().equals(vo.getOrder().getId()) && taskPickupDispatchDTO.getTaskType() == PickupDispatchTaskType.PICKUP.getCode()) {
                        //取件信息
                        vo.setTaskPickup(BeanUtil.parseTaskPickupDispatchDTO2Vo(taskPickupDispatchDTO, orderFeign, areaApi, orgApi, userApi));
                    }
                    if (taskPickupDispatchDTO.getOrderId().equals(vo.getOrder().getId()) && taskPickupDispatchDTO.getTaskType() == PickupDispatchTaskType.DISPATCH.getCode()) {
                        //派件信息
                        vo.setTaskDispatch(BeanUtil.parseTaskPickupDispatchDTO2Vo(taskPickupDispatchDTO, orderFeign, areaApi, orgApi, userApi));
                    }
                });
            }
        }
        //获取运输信息
        List<TaskTransportVo> taskTransportVoList = new ArrayList<>();
        transportTaskFeign.findAllByOrderIdOrTaskId(vo.getId(), null).forEach(taskTransportDTO -> {
            taskTransportVoList.add(BeanUtil.parseTaskTransportDTO2Vo(taskTransportDTO, transportTripsFeign, orgApi, userApi, truckFeign, transportOrderFeign, orderFeign, areaApi));
        });
        vo.setTaskTransports(taskTransportVoList);
        return vo;
    }
}
