package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ChannelWithdrawDao;
import com.xn.manager.model.channel.ChannelWithdrawModel;
import com.xn.manager.service.ChannelWithdrawService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道提现记录的Service层的实现层
 * @Author yoko
 * @Date 2020/10/31 15:36
 * @Version 1.0
 */
@Service("channelWithdrawService")
public class ChannelWithdrawServiceImpl<T> extends BaseServiceImpl<T> implements ChannelWithdrawService<T> {
    private static Logger log= Logger.getLogger(ChannelWithdrawServiceImpl.class);

    @Autowired
    private ChannelWithdrawDao channelWithdrawDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelWithdrawDao;
    }


    @Override
    public int checkChannelWithdraw(ChannelWithdrawModel model) {
        return channelWithdrawDao.checkChannelWithdraw(model);
    }

    @Override
    public String getTotalData(ChannelWithdrawModel model) {
        return channelWithdrawDao.getTotalData(model);
    }
}
