package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.PrGewayCodeModel;

/**
 * @Description 通道码表：关于一些通道表配置的部署的Dao层
 * @Author yoko
 * @Date 2020/9/18 19:06
 * @Version 1.0
 */
public interface PrGewayCodeDao<T> extends BaseDao<T> {

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
