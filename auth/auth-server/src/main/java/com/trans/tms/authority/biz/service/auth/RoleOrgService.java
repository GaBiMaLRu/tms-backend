package com.trans.tms.authority.biz.service.auth;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trans.tms.authority.entity.auth.RoleOrg;
/**
 * 业务接口
 * 角色组织关系
 */
public interface RoleOrgService extends IService<RoleOrg> {
    /**
     * 根据角色id查询
     */
    List<Long> listOrgByRoleId(Long id);
}
