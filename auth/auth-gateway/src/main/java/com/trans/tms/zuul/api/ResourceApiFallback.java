package com.trans.tms.zuul.api;

import com.trans.tms.authority.dto.auth.ResourceQueryDTO;
import com.trans.tms.authority.entity.auth.Resource;
import com.trans.tms.base.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceApiFallback implements ResourceApi {
    @Override
    public R<List> list() {
        return null;
    }

    @Override
    public R<List<Resource>> visible(ResourceQueryDTO resource) {
        return null;
    }
}