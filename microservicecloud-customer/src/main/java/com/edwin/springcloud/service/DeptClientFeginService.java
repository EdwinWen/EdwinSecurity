package com.edwin.springcloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by wenpuzhao on 2019/3/26.
 */
@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallback=DeptClientServiceFallbackFactory.class) //,fallbackFactory=DeptClientServiceFallbackFactory.class
public interface DeptClientFeginService extends DeptClientService {
}
