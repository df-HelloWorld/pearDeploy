package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ChannelDao;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.service.ChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:渠道的Service层的实现层
 * @create 2018-07-27 16:33
 **/
@Service("channelService")
public class ChannelServiceImpl<T> extends BaseServiceImpl<T> implements ChannelService<T> {
    private final static Logger log = Logger.getLogger(ChannelServiceImpl.class);
    @Autowired
    private ChannelDao channelDao;

    @Override
    public BaseDao<T> getDao() {
        return channelDao;
    }

    @Override
    public int updatePassWd(ChannelModel model) {
        return channelDao.updatePassWd(model);
    }

    @Override
    public ChannelModel getTotalData(ChannelModel model) {
        return channelDao.getTotalData(model);
    }

    @Override
    public int updateSecretKey(ChannelModel model) {
        return channelDao.updateSecretKey(model);
    }
}
