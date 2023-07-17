package com.trans.tms.authority.api.hystrix;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.tms.authority.api.OrgApi;
import com.trans.tms.base.R;
import org.springframework.stereotype.Component;
import com.trans.tms.authority.entity.core.Org;
import com.trans.tms.authority.dto.core.OrgTreeDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrgApiFallback implements OrgApi {
    public OrgApiFallback() {
    }

    public R<Org> get(Long id) {
        return R.fail("未获取到组织");
    }

    public R<List<Org>> list(Integer orgType, List<Long> ids, Long countyId, Long pid, List<Long> pids) {
        return R.success(new ArrayList());
    }

    public R<List<OrgTreeDTO>> tree(String name, Boolean status) {
        return R.success(new ArrayList());
    }

    public R<Page> pageLike(Integer size, Integer current, String keyword, Long cityId, String latitude, String longitude) {
        return R.timeout();
    }

    public R<List<Org>> listByCountyIds(Integer orgType, List<Long> countyIds) {
        return R.timeout();
    }
}
