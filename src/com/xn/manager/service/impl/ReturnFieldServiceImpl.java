package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ReturnFieldDao;
import com.xn.manager.service.ReturnFieldService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @ClassName:
 * @Description: 返回字段的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("returnFieldService")
public class ReturnFieldServiceImpl<T> extends BaseServiceImpl<T> implements ReturnFieldService<T> {
    private static Logger log= Logger.getLogger(ReturnFieldServiceImpl.class);

    @Autowired
    private ReturnFieldDao returnFieldDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return returnFieldDao;
    }
}
