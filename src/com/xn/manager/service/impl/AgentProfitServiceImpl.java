package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AgentProfitDao;
import com.xn.manager.model.agent.AgentProfitModel;
import com.xn.manager.service.AgentProfitService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代理收益数据：收益的详情，成功订单要进行余额累加的流水表的Service层的实现层
 * @Author yoko
 * @Date 2021/1/20 16:23
 * @Version 1.0
 */
@Service("agentProfitService")
public class AgentProfitServiceImpl<T> extends BaseServiceImpl<T> implements AgentProfitService<T> {
    private static Logger log= Logger.getLogger(AgentProfitServiceImpl.class);

    @Autowired
    private AgentProfitDao agentProfitDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return agentProfitDao;
    }

    @Override
    public AgentProfitModel getTotalData(AgentProfitModel model) {
        return agentProfitDao.getTotalData(model);
    }
}
