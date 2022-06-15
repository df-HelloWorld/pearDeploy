package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.NotifyTemplateDao;
import com.xn.manager.service.NotifyTemplateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:
 * @Description: 接收模板的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("notifyTemplateService")
public class NotifyTemplateServiceImpl<T> extends BaseServiceImpl<T> implements NotifyTemplateService<T> {
    private static Logger log= Logger.getLogger(NotifyTemplateServiceImpl.class);

    @Autowired
    private NotifyTemplateDao notifyTemplateDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return notifyTemplateDao;
    }
}
