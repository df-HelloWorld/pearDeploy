package com.xn.manager.dao;


import com.xn.common.dao.BaseDao;
import com.xn.manager.model.agent.AgentProfitModel;

/**
 * @Description 代理收益数据：收益的详情，成功订单要进行余额累加的流水表的Dao层
 * @Author yoko
 * @Date 2020/10/31 16:22
 * @Version 1.0
 */
public interface AgentProfitDao<T> extends BaseDao<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public AgentProfitModel getTotalData(AgentProfitModel model);

}
