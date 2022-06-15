package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.StrategyDao;
import com.xn.manager.service.StrategyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 策略的Service层的实现层
 * @Author yoko
 * @Date 2020/9/18 19:10
 * @Version 1.0
 */
@Service("strategyService")
public class StrategyServiceImpl<T> extends BaseServiceImpl<T> implements StrategyService<T> {
    private static Logger log= Logger.getLogger(StrategyServiceImpl.class);

    @Autowired
    private StrategyDao strategyDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return strategyDao;
    }
}
