package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.StatisticsOftenDao;
import com.xn.manager.model.statistics.StatisticsOftenModel;
import com.xn.manager.service.StatisticsOftenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 统计：时时的统计的Service层的实现层
 * @Author yoko
 * @Date 2021/1/20 16:23
 * @Version 1.0
 */
@Service("statisticsOftenService")
public class StatisticsOftenServiceImpl<T> extends BaseServiceImpl<T> implements StatisticsOftenService<T> {
    private static Logger log= Logger.getLogger(StatisticsOftenServiceImpl.class);

    @Autowired
    private StatisticsOftenDao statisticsOftenDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return statisticsOftenDao;
    }

    @Override
    public StatisticsOftenModel getTotalData(StatisticsOftenModel model) {
        return statisticsOftenDao.getTotalData(model);
    }
}
