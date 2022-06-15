package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.PrGewayCodeDao;
import com.xn.manager.dao.PrGewayDao;
import com.xn.manager.service.PrGewayCodeService;
import com.xn.manager.service.PrGewayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 通道表的Service层的实现层
 * @Date 2020/9/18 19:10
 * @Version 1.0
 */
@Service("prGewayCodeService")
public class PrGewayCodeServiceImpl<T> extends BaseServiceImpl<T> implements PrGewayCodeService<T> {
    private static Logger log= Logger.getLogger(PrGewayCodeServiceImpl.class);

    @Autowired
    private PrGewayCodeDao prGewayCodeDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return prGewayCodeDao;
    }
}
