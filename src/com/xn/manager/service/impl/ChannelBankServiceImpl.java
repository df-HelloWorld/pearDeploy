package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ChannelBankDao;
import com.xn.manager.service.ChannelBankService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道银行卡的Service层的实现层
 * @Author yoko
 * @Date 2020/3/24 16:01
 * @Version 1.0
 */
@Service("channelBankService")
public class ChannelBankServiceImpl<T> extends BaseServiceImpl<T> implements ChannelBankService<T> {
    private static Logger log= Logger.getLogger(ChannelBankServiceImpl.class);

    @Autowired
    private ChannelBankDao channelBankDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelBankDao;
    }
}
