package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AccountChannelDao;
import com.xn.manager.service.AccountChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:
 * @Description: 渠道账号的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("accountChannelService")
public class AccountChannelServiceImpl<T> extends BaseServiceImpl<T> implements AccountChannelService<T> {
    private static Logger log= Logger.getLogger(AccountChannelServiceImpl.class);

    @Autowired
    private AccountChannelDao accountChannelDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return accountChannelDao;
    }
}
