package com.xn.manager.service;


import com.xn.common.service.BaseService;

/**
 * @ClassName:
 * @Description: 代理利益分配的Service层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface AgentProfitDistributionService<T> extends BaseService<T> {
    public  int deleteDeployId(Long id);
}
