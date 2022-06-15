package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.SendTemplateDao;
import com.xn.manager.service.SendTemplateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:
 * @Description: 请求模板的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("sendTemplateService")
public class SendTemplateServiceImpl <T> extends BaseServiceImpl<T> implements SendTemplateService<T> {
    private static Logger log= Logger.getLogger(SendTemplateServiceImpl.class);

    @Autowired
    private SendTemplateDao sendTemplateDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return sendTemplateDao;
    }


}
