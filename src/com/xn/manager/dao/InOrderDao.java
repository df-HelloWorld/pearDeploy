package com.xn.manager.dao;


import com.xn.common.dao.BaseDao;
import com.xn.manager.model.inorder.InOrderModel;

/**
 * @ClassName:
 * @Description: 代收订单的Dao层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface InOrderDao<T> extends BaseDao<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public InOrderModel getTotalData(InOrderModel model);
}
