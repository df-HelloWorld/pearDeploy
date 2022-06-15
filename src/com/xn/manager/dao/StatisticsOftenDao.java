package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.statistics.StatisticsOftenModel;

/**
 * @author yoko
 * @desc 统计：时时的统计的Dao层
 * @create 2021-12-27 12:16
 **/
public interface StatisticsOftenDao<T> extends BaseDao<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public StatisticsOftenModel getTotalData(StatisticsOftenModel model);
}
