package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ReplenishDao;
import com.xn.manager.service.ReplenishService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 运营人员补单表的Service层的实现层
 * @Author yoko
 * @Date 2020/3/2 17:30
 * @Version 1.0
 */
@Service("replenishService")
public class ReplenishServiceImpl<T> extends BaseServiceImpl<T> implements ReplenishService<T> {
    private static Logger log= Logger.getLogger(ReplenishServiceImpl.class);

    @Autowired
    private ReplenishDao replenishDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return replenishDao;
    }

}
