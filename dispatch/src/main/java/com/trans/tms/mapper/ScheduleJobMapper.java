package com.trans.tms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trans.tms.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 定时任务  Mapper 接口
 */
@Component
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {

}
