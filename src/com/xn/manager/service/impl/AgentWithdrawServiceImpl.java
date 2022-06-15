package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AgentWithdrawDao;
import com.xn.manager.model.agent.AgentWithdrawModel;
import com.xn.manager.service.AgentWithdrawService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代理提现记录的Service层的实现层
 * @Author yoko
 * @Date 2020/3/24 16:01
 * @Version 1.0
 */
@Service("agentWithdrawService")
public class AgentWithdrawServiceImpl<T> extends BaseServiceImpl<T> implements AgentWithdrawService<T> {
    private static Logger log= Logger.getLogger(AgentWithdrawServiceImpl.class);

    @Autowired
    private AgentWithdrawDao agentWithdrawDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return agentWithdrawDao;
    }

    @Override
    public int checkAgentWithdraw(AgentWithdrawModel model) {
        return agentWithdrawDao.checkAgentWithdraw(model);
    }
}
