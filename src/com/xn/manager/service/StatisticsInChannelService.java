package com.xn.manager.service;


import com.xn.common.service.BaseService;
import com.xn.manager.model.statistics.StatisticsInChannelModel;

/**
 * @Description 统计代收渠道的每日记录的Service层
 * @Author yoko
 * @Date 2020/3/2 17:29
 * @Version 1.0
 */
public interface StatisticsInChannelService<T> extends BaseService<T> {

    /**
     * @Description: 获取total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public StatisticsInChannelModel getTotalData(StatisticsInChannelModel model);

}
