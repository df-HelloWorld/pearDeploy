package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AccountAgentDao;
import com.xn.manager.dao.AccountChannelDao;
import com.xn.manager.service.AccountAgentService;
import com.xn.manager.service.AccountChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:
 * @Description: 代理账号的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("accountAgentService")
public class AccountAgentServiceImpl<T> extends BaseServiceImpl<T> implements AccountAgentService<T> {
    private static Logger log= Logger.getLogger(AccountAgentServiceImpl.class);

    @Autowired
    private AccountAgentDao accountAgentDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return accountAgentDao;
    }
}
