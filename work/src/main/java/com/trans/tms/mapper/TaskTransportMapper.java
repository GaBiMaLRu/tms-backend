package com.trans.tms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trans.tms.entity.TaskTransport;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 运输任务表 Mapper 接口
 * </p>
 *
 * @author jpf
 * @since 2020-01-08
 */
@Mapper
public interface TaskTransportMapper extends BaseMapper<TaskTransport> {
}
