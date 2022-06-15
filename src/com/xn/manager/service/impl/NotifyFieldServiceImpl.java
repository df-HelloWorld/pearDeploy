package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.NotifyFieldDao;
import com.xn.manager.service.NotifyFieldService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @ClassName:
 * @Description: 接收字段的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("notifyFieldService")
public class NotifyFieldServiceImpl<T> extends BaseServiceImpl<T> implements NotifyFieldService<T> {
    private static Logger log= Logger.getLogger(NotifyFieldServiceImpl.class);

    @Autowired
    private NotifyFieldDao notifyFieldDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return notifyFieldDao;
    }
}
