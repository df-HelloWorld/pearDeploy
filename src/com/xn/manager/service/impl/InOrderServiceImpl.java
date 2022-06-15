package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.InOrderDao;
import com.xn.manager.model.inorder.InOrderModel;
import com.xn.manager.service.InOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:
 * @Description: 代收订单的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("inOrderService")
public class InOrderServiceImpl<T> extends BaseServiceImpl<T> implements InOrderService<T> {
    private static Logger log= Logger.getLogger(InOrderServiceImpl.class);

    @Autowired
    private InOrderDao inOrderDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return inOrderDao;
    }

    @Override
    public InOrderModel getTotalData(InOrderModel model) {
        return inOrderDao.getTotalData(model);
    }
}
