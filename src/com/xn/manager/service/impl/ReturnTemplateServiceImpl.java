package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ReturnTemplateDao;
import com.xn.manager.service.ReturnTemplateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:
 * @Description: 返回模板的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("returnTemplateService")
public class ReturnTemplateServiceImpl<T> extends BaseServiceImpl<T> implements ReturnTemplateService<T> {
    private static Logger log= Logger.getLogger(ReturnTemplateServiceImpl.class);

    @Autowired
    private ReturnTemplateDao returnTemplateDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return returnTemplateDao;
    }
}
