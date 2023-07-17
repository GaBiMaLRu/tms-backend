package com.trans.tms.authority.api.hystrix;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.tms.authority.api.UserApi;
import com.trans.tms.base.R;
import org.springframework.stereotype.Component;
import com.trans.tms.authority.entity.auth.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserApiFallback implements UserApi {
    public UserApiFallback() {
    }

    public Map<String, Object> getDataScopeById(Long id) {
        Map<String, Object> map = new HashMap(2);
        map.put("dsType", 5);
        map.put("orgIds", Collections.emptyList());
        return map;
    }

    public R<List<Long>> findAllUserId() {
        return R.timeout();
    }

    public R<User> get(Long id) {
        return R.timeout();
    }

    public R<Page<User>> page(Long current, Long size, Long orgId, Long stationId, String name, String account, String mobile) {
        return R.timeout();
    }

    public R<List<User>> list(List<Long> ids, Long stationId, String name, Long orgId) {
        return R.timeout();
    }
}
