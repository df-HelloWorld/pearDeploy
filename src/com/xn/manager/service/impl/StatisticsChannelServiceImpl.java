package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.StatisticsChannelDao;
import com.xn.manager.model.statistics.StatisticsChannelModel;
import com.xn.manager.service.StatisticsChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 统计：渠道金额的统计的Service层的实现层
 * @Author yoko
 * @Date 2021/1/20 16:23
 * @Version 1.0
 */
@Service("statisticsChannelService")
public class StatisticsChannelServiceImpl<T> extends BaseServiceImpl<T> implements StatisticsChannelService<T> {
    private static Logger log= Logger.getLogger(StatisticsChannelServiceImpl.class);

    @Autowired
    private StatisticsChannelDao statisticsChannelDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return statisticsChannelDao;
    }

    @Override
    public StatisticsChannelModel getTotalData(StatisticsChannelModel model) {
        return statisticsChannelDao.getTotalData(model);
    }
}
