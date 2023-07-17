package com.trans.tms.controller;

import com.trans.tms.DTO.OrderDTO;
import com.trans.tms.DTO.TaskPickupDispatchDTO;
import com.trans.tms.DTO.TransportOrderDTO;
import com.trans.tms.authority.api.AreaApi;
import com.trans.tms.authority.api.OrgApi;
import com.trans.tms.authority.api.UserApi;
import com.trans.tms.common.utils.PageResponse;
import com.trans.tms.enums.pickuptask.PickupDispatchTaskType;
import com.trans.tms.feign.OrderFeign;
import com.trans.tms.feign.PickupDispatchTaskFeign;
import com.trans.tms.feign.TransportOrderFeign;
import com.trans.tms.util.BeanUtil;
import com.trans.tms.vo.oms.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单状态  前端控制器
 * </p>
 *
 * @author jpf
 * @since 2019-12-26
 */
@RestController
@Api(tags = "订单相关API")
@RequestMapping("order-manager/order")
public class OrderController {
    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private PickupDispatchTaskFeign pickupDispatchTaskFeign;
    @Autowired
    private AreaApi areaApi;
    @Autowired
    private OrgApi orgApi;
    @Autowired
    private UserApi userApi;
    @Autowired
    private TransportOrderFeign transportOrderFeign;

    @ApiOperation(value = "获取订单分页数据")
    @PostMapping("/page")
    public PageResponse<OrderVo> findByPage(@RequestBody OrderVo vo) {
        PageResponse<OrderDTO> orderPage = orderFeign.findByPage(BeanUtil.parseOrderVo2DTO(vo));
        //加工数据
        List<OrderDTO> orderDTOList = orderPage.getItems();
        List<OrderVo> orderVoList = orderDTOList.stream().map(orderDTO -> BeanUtil.parseOrderDTO2Vo(orderDTO, areaApi)).collect(Collectors.toList());
        return PageResponse.<OrderVo>builder().items(orderVoList).size(vo.getPageSize()).current(vo.getPage()).counts(orderPage.getCounts()).total(orderPage.getTotal()).build();
    }

    @ApiOperation(value = "获取订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, example = "1", paramType = "{path}")
    })
    @GetMapping("/{id}")
    public OrderVo findOrderById(@PathVariable(name = "id") String id) {
        OrderDTO orderDTO = orderFeign.findById(id);
        OrderVo vo = BeanUtil.parseOrderDTO2Vo(orderDTO, areaApi);
        if (StringUtils.isNotEmpty(vo.getId())) {
            //查询取派件任务信息
            TaskPickupDispatchDTO taskPickupDispatchQueryDTO = new TaskPickupDispatchDTO();
            taskPickupDispatchQueryDTO.setOrderId(vo.getId());
            List<TaskPickupDispatchDTO> taskPickupDispatchDTOList = pickupDispatchTaskFeign.findAll(taskPickupDispatchQueryDTO);
            if (taskPickupDispatchDTOList != null && taskPickupDispatchDTOList.size() > 0) {
                taskPickupDispatchDTOList.forEach(taskPickupDispatchDTO -> {
                    if (taskPickupDispatchDTO.getOrderId().equals(vo.getId()) && taskPickupDispatchDTO.getTaskType() == PickupDispatchTaskType.PICKUP.getCode()) {
                        //取件信息
                        vo.setTaskPickup(BeanUtil.parseTaskPickupDispatchDTO2Vo(taskPickupDispatchDTO, orderFeign, areaApi, orgApi, userApi));
                    }
                    if (taskPickupDispatchDTO.getOrderId().equals(vo.getId()) && taskPickupDispatchDTO.getTaskType() == PickupDispatchTaskType.DISPATCH.getCode()) {
                        //派件信息
                        vo.setTaskDispatch(BeanUtil.parseTaskPickupDispatchDTO2Vo(taskPickupDispatchDTO, orderFeign, areaApi, orgApi, userApi));
                    }
                });
            }
            //查询运单信息
            TransportOrderDTO transportOrderDTO = transportOrderFeign.findByOrderId(vo.getId());
            if (transportOrderDTO != null) {
                vo.setTransportOrder(BeanUtil.parseTransportOrderDTO2Vo(transportOrderDTO, null, null));
            }
        }
        return vo;
    }

    @ApiOperation(value = "更新订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, example = "1", paramType = "{path}")
    })
    @PostMapping("/{id}")
    public OrderVo updateOrder(@PathVariable(name = "id") String id, @RequestBody OrderVo vo) {
        OrderDTO dto = orderFeign.updateById(id, BeanUtil.parseOrderVo2DTO(vo));
        return BeanUtil.parseOrderDTO2Vo(dto, null);
    }
}
