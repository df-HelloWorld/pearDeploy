package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.PrGewayDao;
import com.xn.manager.dao.PrPlatformGewayCodeLinkDao;
import com.xn.manager.model.PrPlatformGewayCodeLinkModel;
import com.xn.manager.service.PrGewayService;
import com.xn.manager.service.PrPlatformGewayCodeLinkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 通道表的Service层的实现层
 * @Author yoko
 * @Date 2020/9/18 19:10
 * @Version 1.0
 */
@Service("prPlatformGewayCodeLinkService")
public class PrPlatformGewayCodeLinkServiceImpl<T> extends BaseServiceImpl<T> implements PrPlatformGewayCodeLinkService<T> {
    private static Logger log= Logger.getLogger(PrPlatformGewayCodeLinkServiceImpl.class);

    @Autowired
    private PrPlatformGewayCodeLinkDao prPlatformGewayCodeLinkDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return prPlatformGewayCodeLinkDao;
    }

    @Override
    public List<PrPlatformGewayCodeLinkModel> queryPfGewayCodeId(PrPlatformGewayCodeLinkModel model) {
        return prPlatformGewayCodeLinkDao.queryPfGewayCodeId(model);
    }

    @Override
    public List<PrPlatformGewayCodeLinkModel> queryGewayCodeId(PrPlatformGewayCodeLinkModel model) {
        return prPlatformGewayCodeLinkDao.queryGewayCodeId(model);
    }

    @Override
    public int deleteRelationType(PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkModel) {
        return prPlatformGewayCodeLinkDao.deleteRelationType(prPlatformGewayCodeLinkModel);
    }

    @Override
    public String queryMaxUpServiceChargeByPfGewayCode(PrPlatformGewayCodeLinkModel model) {
        return prPlatformGewayCodeLinkDao.queryMaxUpServiceChargeByPfGewayCode(model);
    }
}
