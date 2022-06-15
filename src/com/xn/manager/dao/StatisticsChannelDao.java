package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.statistics.StatisticsChannelModel;

/**
 * @author yoko
 * @desc 统计：渠道金额的统计的Dao层
 * @create 2021-12-27 12:16
 **/
public interface StatisticsChannelDao<T> extends BaseDao<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public StatisticsChannelModel getTotalData(StatisticsChannelModel model);
}
