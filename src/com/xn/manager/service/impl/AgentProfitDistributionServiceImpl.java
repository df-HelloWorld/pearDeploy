package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AgentProfitDistributionDao;
import com.xn.manager.service.AgentProfitDistributionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代理利益分配的Service层的实现层
 * @Author yoko
 * @Date 2021/1/20 16:23
 * @Version 1.0
 */
@Service("agentProfitDistributionService")
public class AgentProfitDistributionServiceImpl<T> extends BaseServiceImpl<T> implements AgentProfitDistributionService<T> {
    private static Logger log= Logger.getLogger(AgentProfitDistributionServiceImpl.class);

    @Autowired
    private AgentProfitDistributionDao agentProfitDistributionDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return agentProfitDistributionDao;
    }

    @Override
    public int deleteDeployId(Long id) {
        return agentProfitDistributionDao.deleteDeployId(id);
    }
}
