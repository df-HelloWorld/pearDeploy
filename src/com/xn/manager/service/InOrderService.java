package com.xn.manager.service;


import com.xn.common.service.BaseService;
import com.xn.manager.model.inorder.InOrderModel;

/**
 * @ClassName:
 * @Description: 代收订单的Service层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface InOrderService<T> extends BaseService<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public InOrderModel getTotalData(InOrderModel model);
}
