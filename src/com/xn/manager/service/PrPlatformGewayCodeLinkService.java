package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.PrPlatformGewayCodeLinkModel;

import java.util.List;

/**
 * @Description 通道的Service层
 * @Author yoko
 * @Date 2020/9/18 19:09
 * @Version 1.0
 */
public interface PrPlatformGewayCodeLinkService<T> extends BaseService<T> {
    public List<PrPlatformGewayCodeLinkModel>  queryPfGewayCodeId(PrPlatformGewayCodeLinkModel model);
    public List<PrPlatformGewayCodeLinkModel>  queryGewayCodeId(PrPlatformGewayCodeLinkModel model);
    public int  deleteRelationType(PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel);
}
