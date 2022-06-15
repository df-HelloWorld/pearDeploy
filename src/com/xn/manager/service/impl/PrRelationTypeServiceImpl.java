package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.PrGewayDao;
import com.xn.manager.dao.PrRelationTypeDao;
import com.xn.manager.service.PrGewayService;
import com.xn.manager.service.PrRelationTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 通道表的Service层的实现层
 * @Author yoko
 * @Date 2020/9/18 19:10
 * @Version 1.0
 */
@Service("prRelationTypeService")
public class PrRelationTypeServiceImpl<T> extends BaseServiceImpl<T> implements PrRelationTypeService<T> {
    private static Logger log= Logger.getLogger(PrRelationTypeServiceImpl.class);

    @Autowired
    private PrRelationTypeDao prRelationTypeDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return prRelationTypeDao;
    }
}
