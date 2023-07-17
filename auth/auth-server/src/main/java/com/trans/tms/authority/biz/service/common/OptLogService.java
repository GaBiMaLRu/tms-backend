package com.trans.tms.authority.biz.service.common;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trans.tms.authority.entity.common.OptLog;
import com.trans.tms.log.entity.OptLogDTO;

/**
 * 业务接口
 * 操作日志
 */
public interface OptLogService extends IService<OptLog> {
    /**
     * 保存日志
     */
    boolean save(OptLogDTO entity);
}
