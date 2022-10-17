package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.PrGewayCodeModel;

import java.util.List;

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


    /**
    * @Description: 获取有效的通道码集合
     * <p>
     *     有效：1，通道yn=0并且is_enable=2
     *     2，通道码yn=0并且is_enable=2
     * </p>
    * @param model
    * @return:
    * @author: yoko
    * @date: 2022/10/17 8:24
    * @version 1.0.0
    */
    public List<PrGewayCodeModel> getValidGewayCode(PrGewayCodeModel model);
}
