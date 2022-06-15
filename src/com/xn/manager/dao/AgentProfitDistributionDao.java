package com.xn.manager.dao;


import com.xn.common.dao.BaseDao;

/**
 * @ClassName:
 * @Description: 代理利益分配的Dao层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface AgentProfitDistributionDao<T> extends BaseDao<T> {
    public int deleteDeployId(Long id);
}
