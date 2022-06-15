package com.xn.manager.service;


import com.xn.common.service.BaseService;
import com.xn.manager.model.agent.AgentProfitModel;

/**
 * @Description 代理收益数据：收益的详情，成功订单要进行余额累加的流水表的Service层
 * @Author yoko
 * @Date 2021/1/20 16:21
 * @Version 1.0
 */
public interface AgentProfitService<T> extends BaseService<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public AgentProfitModel getTotalData(AgentProfitModel model);
}
