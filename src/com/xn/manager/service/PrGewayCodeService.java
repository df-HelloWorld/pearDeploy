package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.PrGewayCodeModel;

/**
 * @Description 通道的Service层
 * @Date 2020/9/18 19:09
 * @Version 1.0
 */
public interface PrGewayCodeService<T> extends BaseService<T> {

    /**
    * @Description: 根据条件查询通道码最大的费率值
     * <p>
     *     查询上游最大费率值：成本
     * </p>
    * @param model
    * @author: yoko
    * @date: 2022/10/12 14:04
    * @version 1.0.0
    */
    public String getMaxUpServiceCharge(PrGewayCodeModel model);
}
