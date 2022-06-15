package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AgentBankDao;
import com.xn.manager.service.AgentBankService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代理银行卡的Service层的实现层
 * @Author yoko
 * @Date 2020/3/24 16:01
 * @Version 1.0
 */
@Service("agentBankService")
public class AgentBankServiceImpl<T> extends BaseServiceImpl<T> implements AgentBankService<T> {
    private static Logger log= Logger.getLogger(AgentBankServiceImpl.class);

    @Autowired
    private AgentBankDao agentBankDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return agentBankDao;
    }
}
