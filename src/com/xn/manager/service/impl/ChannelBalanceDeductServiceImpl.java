package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ChannelBalanceDeductDao;
import com.xn.manager.service.ChannelBalanceDeductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道扣减余额流水的Service层的实现层
 * @Author yoko
 * @Date 2020/10/31 16:26
 * @Version 1.0
 */
@Service("channelBalanceDeductService")
public class ChannelBalanceDeductServiceImpl<T> extends BaseServiceImpl<T> implements ChannelBalanceDeductService<T> {
    private static Logger log= Logger.getLogger(ChannelBalanceDeductServiceImpl.class);

    @Autowired
    private ChannelBalanceDeductDao channelBalanceDeductDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelBalanceDeductDao;
    }

}
