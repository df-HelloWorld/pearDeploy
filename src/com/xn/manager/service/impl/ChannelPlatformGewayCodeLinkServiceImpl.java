package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ChannelPlatformGewayCodeLinkDao;
import com.xn.manager.service.ChannelPlatformGewayCodeLinkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:
 * @Description: 渠道与平台通道码关联的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("channelPlatformGewayCodeLinkService")
public class ChannelPlatformGewayCodeLinkServiceImpl<T> extends BaseServiceImpl<T> implements ChannelPlatformGewayCodeLinkService<T> {
    private static Logger log= Logger.getLogger(ChannelPlatformGewayCodeLinkServiceImpl.class);

    @Autowired
    private ChannelPlatformGewayCodeLinkDao channelPlatformGewayCodeLinkDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelPlatformGewayCodeLinkDao;
    }
}
