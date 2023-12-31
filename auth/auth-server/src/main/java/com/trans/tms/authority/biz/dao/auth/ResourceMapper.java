package com.trans.tms.authority.biz.dao.auth;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trans.tms.authority.dto.auth.ResourceQueryDTO;
import com.trans.tms.authority.entity.auth.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 *
 */
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询用户拥有的资源
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    List<Long> findMenuIdByResourceId(@Param("resourceIdList") List<Long> resourceIdList);
}
