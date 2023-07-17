package com.trans.tms.future;

import com.trans.tms.DTO.TaskTransportDTO;
import com.trans.tms.dto.transportline.TransportTripsDto;
import com.trans.tms.authority.api.AreaApi;
import com.trans.tms.authority.api.OrgApi;
import com.trans.tms.authority.api.UserApi;
import com.trans.tms.authority.entity.auth.User;
import com.trans.tms.authority.entity.common.Area;
import com.trans.tms.authority.entity.core.Org;
import com.trans.tms.authority.enumeration.common.StaticStation;
import com.trans.tms.base.R;
import com.trans.tms.common.utils.Constant;
import com.trans.tms.feign.TransportTaskFeign;
import com.trans.tms.feign.transportline.TransportTripsFeign;
import com.trans.tms.util.BeanUtil;
import com.trans.tms.vo.AreaSimpleVo;
import com.trans.tms.vo.SysUserVo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class PdCompletableFuture {
    /**
     * 获取map类型用户数据集合
     *
     * @param api     数据接口
     * @param userSet 用户id列表
     * @return 执行结果
     */
    public static final CompletableFuture<Map> userMapFuture(UserApi api, Set<Long> userSet, Integer station, String name, String agencyId) {
        return CompletableFuture.supplyAsync(() -> {
            //查询创建者信息列表
            Long stationId = null;
            if (station != null && station == Constant.UserStation.COURIER.getStation()) {
                stationId = StaticStation.COURIER_ID;
            } else if (station != null && station == Constant.UserStation.DRIVER.getStation()) {
                stationId = StaticStation.DRIVER_ID;
            }
            List<User> userList = new ArrayList<>();
            R<List<User>> result = api.list(new ArrayList<>(userSet), stationId, name, StringUtils.isNotEmpty(agencyId) ? Long.valueOf(agencyId) : null);
            if (result.getIsSuccess() && result.getData() != null) {
                userList.addAll(result.getData());
            }
            return userList.stream().map(user -> BeanUtil.parseUser2Vo(user, null, null)).collect(Collectors.toMap(SysUserVo::getUserId, vo -> vo));
        });
    }

    /**
     * 获取机构数据列表
     *
     * @param api        数据接口
     * @param agencyType 机构类型
     * @param ids        机构id列表
     * @return 执行结果
     */
    public static final CompletableFuture<List<Org>> agencyListFuture(OrgApi api, Integer agencyType, Set<String> ids, Long countyId) {
        return CompletableFuture.supplyAsync(() -> {
            R<List<Org>> result = api.list(agencyType, ids.stream().mapToLong(id -> Long.valueOf(id)).boxed().collect(Collectors.toList()), countyId, null, null);
            if (result.getIsSuccess()) {
                return result.getData();
            }
            return new ArrayList<>();
        });
    }

    public static final CompletableFuture<Map> areaMapFuture(AreaApi api, Long parentId, Set<Long> areaSet) {
        R<List<Area>> result = api.findAll(parentId, new ArrayList<>(areaSet));
        return CompletableFuture.supplyAsync(() -> result.getData().stream().map(area -> BeanUtil.parseArea2Vo(area)).collect(Collectors.toMap(AreaSimpleVo::getId, vo -> vo)));
    }

    public static CompletableFuture<Map> tripMapFuture(TransportTripsFeign feign, Set<String> tripSet) {
        return CompletableFuture.supplyAsync(() -> {
            List<TransportTripsDto> transportTripsDtoList = feign.findAll(null, new ArrayList<>(tripSet));
            return transportTripsDtoList.stream().collect(Collectors.toMap(TransportTripsDto::getId, value -> value.getName()));
        });
    }

    public static CompletableFuture<Map<String, TaskTransportDTO>> taskTramsportMapFuture(TransportTaskFeign feign, Set<String> taskTransportSet) {
        return CompletableFuture.supplyAsync(() -> {
            List<TaskTransportDTO> taskTransportDTOList = new ArrayList<>();
            for (String s : taskTransportSet) {
                TaskTransportDTO taskTransportDto = feign.findById(s);
                taskTransportDTOList.add(taskTransportDto);
            }
            return taskTransportDTOList.stream().collect(Collectors.toMap(TaskTransportDTO::getId, item -> item));
        });
    }
}
