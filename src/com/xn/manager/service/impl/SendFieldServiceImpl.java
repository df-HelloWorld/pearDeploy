package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.SendFieldDao;
import com.xn.manager.service.SendFieldService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @ClassName:
 * @Description: 请求字段的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("sendFieldService")
public class SendFieldServiceImpl<T> extends BaseServiceImpl<T> implements SendFieldService<T> {
    private static Logger log= Logger.getLogger(SendFieldServiceImpl.class);

    @Autowired
    private SendFieldDao sendFieldDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return sendFieldDao;
    }
}
