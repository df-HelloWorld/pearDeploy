package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ChannelChangeDao;
import com.xn.manager.service.ChannelChangeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道金额变更纪录的Service层的实现层
 * @Author yoko
 * @Date 2020/11/4 10:49
 * @Version 1.0
 */
@Service("channelChangeService")
public class ChannelChangeServiceImpl<T> extends BaseServiceImpl<T> implements ChannelChangeService<T> {
    private static Logger log= Logger.getLogger(ChannelChangeServiceImpl.class);

    @Autowired
    private ChannelChangeDao channelChangeDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelChangeDao;
    }
}
