package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.PrPlatformGewayCodeLinkModel;

import java.util.List;

/**
 * @Description 通道表：关于一些通道表配置的部署的Dao层
 * @Author yoko
 * @Date 2020/9/18 19:06
 * @Version 1.0
 */
public interface PrPlatformGewayCodeLinkDao<T> extends BaseDao<T> {
    public List<PrPlatformGewayCodeLinkModel> queryPfGewayCodeId(PrPlatformGewayCodeLinkModel model);
    public List<PrPlatformGewayCodeLinkModel> queryGewayCodeId(PrPlatformGewayCodeLinkModel model);
    public int  deleteRelationType(PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel);
}
