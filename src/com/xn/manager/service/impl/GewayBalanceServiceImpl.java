package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AgentDao;
import com.xn.manager.dao.GewayBalanceDao;
import com.xn.manager.service.AgentService;
import com.xn.manager.service.GewayBalanceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 通道余额的Service层的实现层
 * @Author yoko
 * @Date 2020/4/29 17:21
 * @Version 1.0
 */
@Service("gewayBalanceService")
public class GewayBalanceServiceImpl<T> extends BaseServiceImpl<T> implements GewayBalanceService<T> {

    private static Logger log= Logger.getLogger(GewayBalanceServiceImpl.class);

    @Autowired
    private GewayBalanceDao<T> gewayBalanceDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return gewayBalanceDao;
    }
}
