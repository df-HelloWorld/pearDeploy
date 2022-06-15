package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AgentChangeDao;
import com.xn.manager.service.AgentChangeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代理金额变更纪录的Service层的实现层
 * @Author yoko
 * @Date 2020/3/24 16:01
 * @Version 1.0
 */
@Service("agentChangeService")
public class AgentChangeServiceImpl<T> extends BaseServiceImpl<T> implements AgentChangeService<T> {
    private static Logger log= Logger.getLogger(AgentChangeServiceImpl.class);

    @Autowired
    private AgentChangeDao agentChangeDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return agentChangeDao;
    }
}
