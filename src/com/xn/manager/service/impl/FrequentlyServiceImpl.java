package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AgentDao;
import com.xn.manager.dao.FrequentlyDao;
import com.xn.manager.service.AgentService;
import com.xn.manager.service.FrequentlyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 频繁请求：被抓取到的过于频繁的数据的Service层的实现层
 * @Author yoko
 * @Date 2020/4/29 17:21
 * @Version 1.0
 */
@Service("frequentlyService")
public class FrequentlyServiceImpl<T> extends BaseServiceImpl<T> implements FrequentlyService<T> {

    private static Logger log= Logger.getLogger(FrequentlyServiceImpl.class);

    @Autowired
    private FrequentlyDao<T> frequentlyDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return frequentlyDao;
    }
}
