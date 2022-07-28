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

    /**
     * @Description:根据平台通道码ID查询旗下最贵的通道码的费率
     * @param model
     * @author: yoko
     * @date: 2022/7/27 16:42
     * @version 1.0.0
     */
    public String queryMaxUpServiceChargeByPfGewayCode(PrPlatformGewayCodeLinkModel model);
}
