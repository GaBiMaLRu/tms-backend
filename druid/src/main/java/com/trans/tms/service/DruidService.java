package com.trans.tms.service;

import com.trans.tms.common.utils.Result;

import java.util.List;
import java.util.Map;

public interface DruidService {

    Result queryAllTruckLast(Map<String, Object> params);

    Result queryOneTruck(Map<String, Object> params);

    Result queryAll(List<Map<String, Object>> params);
}
