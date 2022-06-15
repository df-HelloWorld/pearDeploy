package com.xn.manager.dao;


import com.xn.common.dao.BaseDao;
import com.xn.manager.model.statistics.StatisticsInChannelModel;

/**
 * @author yoko
 * @desc 统计代收渠道的每日记录的Dao层
 * @create 2021-12-30 17:33
 **/
public interface StatisticsInChannelDao<T> extends BaseDao<T> {

    /**
     * @Description: 获取total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public StatisticsInChannelModel getTotalData(StatisticsInChannelModel model);
}
