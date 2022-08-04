package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ChannelFlowingWaterDao;
import com.xn.manager.service.ChannelFlowingWaterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道资金流水的Service层的实现层
 * @Author yoko
 * @Date 2020/3/24 16:01
 * @Version 1.0
 */
@Service("channelFlowingWaterService")
public class ChannelFlowingWaterServiceImpl<T> extends BaseServiceImpl<T> implements ChannelFlowingWaterService<T> {
    private static Logger log= Logger.getLogger(ChannelFlowingWaterServiceImpl.class);

    @Autowired
    private ChannelFlowingWaterDao channelFlowingWaterDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelFlowingWaterDao;
    }
}
