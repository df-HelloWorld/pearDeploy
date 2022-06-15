package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AgentProfitDao;
import com.xn.manager.dao.StatisticsHourDao;
import com.xn.manager.model.agent.AgentProfitModel;
import com.xn.manager.model.statistics.StatisticsHourModel;
import com.xn.manager.service.AgentProfitService;
import com.xn.manager.service.StatisticsHourService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 统计：每小时的统计的Service层的实现层
 * @Author yoko
 * @Date 2021/1/20 16:23
 * @Version 1.0
 */
@Service("statisticsHourService")
public class StatisticsHourServiceImpl<T> extends BaseServiceImpl<T> implements StatisticsHourService<T> {
    private static Logger log= Logger.getLogger(StatisticsHourServiceImpl.class);

    @Autowired
    private StatisticsHourDao statisticsHourDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return statisticsHourDao;
    }

    @Override
    public StatisticsHourModel getTotalData(StatisticsHourModel model) {
        return statisticsHourDao.getTotalData(model);
    }
}
