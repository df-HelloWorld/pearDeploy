package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.StatisticsInChannelDao;
import com.xn.manager.model.statistics.StatisticsInChannelModel;
import com.xn.manager.service.StatisticsInChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 统计代收渠道的每日记录的Service层的实现层
 * @Author yoko
 * @Date 2020/3/2 17:30
 * @Version 1.0
 */
@Service("statisticsInChannelService")
public class StatisticsInChannelServiceImpl<T> extends BaseServiceImpl<T> implements StatisticsInChannelService<T> {
    private static Logger log= Logger.getLogger(StatisticsHourServiceImpl.class);

    @Autowired
    private StatisticsInChannelDao statisticsInChannelDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return statisticsInChannelDao;
    }

    @Override
    public StatisticsInChannelModel getTotalData(StatisticsInChannelModel model) {
        return statisticsInChannelDao.getTotalData(model);
    }

}
