package com.trans.tms.authority.api.hystrix;

import com.trans.tms.authority.api.RoleApi;
import com.trans.tms.authority.dto.auth.RoleDTO;
import com.trans.tms.authority.dto.auth.RoleResourceDTO;
import com.trans.tms.base.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleApiFallback implements RoleApi {
    public RoleApiFallback() {
    }

    public R<List<Long>> findUserIdByCode(String[] codes) {
        return R.timeout();
    }

    public R<List<Long>> findRoleByUserId(Long id) {
        return R.timeout();
    }

    public R<List<RoleResourceDTO>> findAllRoles() {
        return R.timeout();
    }

    public R<List<RoleDTO>> list(Long userId) {
        return R.timeout();
    }
}
