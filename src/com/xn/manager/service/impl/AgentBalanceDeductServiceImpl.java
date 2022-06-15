package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AgentBalanceDeductDao;
import com.xn.manager.service.AgentBalanceDeductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代理扣减余额流水表的Service层的实现层
 * @Author yoko
 * @Date 2021/1/20 16:23
 * @Version 1.0
 */
@Service("agentBalanceDeductService")
public class AgentBalanceDeductServiceImpl<T> extends BaseServiceImpl<T> implements AgentBalanceDeductService<T> {
    private static Logger log= Logger.getLogger(AgentBalanceDeductServiceImpl.class);

    @Autowired
    private AgentBalanceDeductDao agentBalanceDeductDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return agentBalanceDeductDao;
    }
}
