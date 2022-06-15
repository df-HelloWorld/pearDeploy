package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.StatisticsAgentDao;
import com.xn.manager.model.statistics.StatisticsAgentModel;
import com.xn.manager.service.StatisticsAgentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 统计代理的每日记录的Service层的实现层
 * @Author yoko
 * @Date 2020/3/2 17:30
 * @Version 1.0
 */
@Service("statisticsAgentService")
public class StatisticsAgentServiceImpl<T> extends BaseServiceImpl<T> implements StatisticsAgentService<T> {
    private static Logger log= Logger.getLogger(StatisticsAgentServiceImpl.class);

    @Autowired
    private StatisticsAgentDao statisticsAgentDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return statisticsAgentDao;
    }

    @Override
    public StatisticsAgentModel getTotalData(StatisticsAgentModel model) {
        return statisticsAgentDao.getTotalData(model);
    }

}
