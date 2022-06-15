package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.PrGewayCodeDao;
import com.xn.manager.dao.PrPlatformGewayCodeDao;
import com.xn.manager.service.PrPlatformGewayCodeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 平台通道码的Service层的实现层
 * @Author yoko
 * @Date 2020/9/18 19:10
 * @Version 1.0
 */
@Service("prPlatformGewayCodeService")
public class PrPlatformGewayCodeServiceImpl<T> extends BaseServiceImpl<T> implements PrPlatformGewayCodeService<T> {
    private static Logger log= Logger.getLogger(PrPlatformGewayCodeServiceImpl.class);

    @Autowired
    private PrPlatformGewayCodeDao prPlatformGewayCodeDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return prPlatformGewayCodeDao;
    }
}
