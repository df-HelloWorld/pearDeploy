package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.PrChannelDao;
import com.xn.manager.dao.PrGewayDao;
import com.xn.manager.service.PrChannelService;
import com.xn.manager.service.PrGewayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 通道表的Service层的实现层
 * @Author yoko
 * @Date 2020/9/18 19:10
 * @Version 1.0
 */
@Service("prGewayService")
public class PrGewayServiceImpl<T> extends BaseServiceImpl<T> implements PrGewayService<T> {
    private static Logger log= Logger.getLogger(PrGewayServiceImpl.class);

    @Autowired
    private PrGewayDao prGewayDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return prGewayDao;
    }
}
