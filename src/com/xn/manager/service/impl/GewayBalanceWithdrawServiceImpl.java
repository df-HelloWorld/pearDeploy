package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.GewayBalanceDao;
import com.xn.manager.dao.GewayBalanceWithdrawDao;
import com.xn.manager.service.GewayBalanceService;
import com.xn.manager.service.GewayBalanceWithdrawService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 通道余额的Service层的实现层
 * @Author yoko
 * @Date 2020/4/29 17:21
 * @Version 1.0
 */
@Service("gewayBalanceWithdraw")
public class GewayBalanceWithdrawServiceImpl<T> extends BaseServiceImpl<T> implements GewayBalanceWithdrawService<T> {

    private static Logger log= Logger.getLogger(GewayBalanceWithdrawServiceImpl.class);

    @Autowired
    private GewayBalanceWithdrawDao<T> gewayBalanceWithdrawDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return gewayBalanceWithdrawDao;
    }
}
