package com.xn.manager.service;


import com.xn.common.service.BaseService;
import com.xn.manager.model.statistics.StatisticsChannelModel;

/**
 * @Description 统计：渠道金额的统计的Service层
 * @Author yoko
 * @Date 2021/1/20 16:21
 * @Version 1.0
 */
public interface StatisticsChannelService<T> extends BaseService<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public StatisticsChannelModel getTotalData(StatisticsChannelModel model);
}
