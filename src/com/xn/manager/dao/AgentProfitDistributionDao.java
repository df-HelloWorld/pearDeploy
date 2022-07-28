package com.xn.manager.dao;


import com.xn.common.dao.BaseDao;
import com.xn.manager.model.agent.AgentProfitDistributionModel;


/**
 * @ClassName:
 * @Description: 代理利益分配的Dao层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface AgentProfitDistributionDao<T> extends BaseDao<T> {
    public int deleteDeployId(Long id);


    /**
     * @Description: 根据渠道，平台通道码获取代理分润的利润和
     * @param model
     * @author: yoko
     * @date: 2022/7/1 16:44
     * @version 1.0.0
     */
    public AgentProfitDistributionModel getSunServiceChargeByChannelAndPfGewayCode(AgentProfitDistributionModel model);

    /**
     * @Description:专门修改代理的分润
     * @param model
     * @return:
     * @author: yoko
     * @date: 2022/7/28 9:52
     * @version 1.0.0
     */
    public int updateServiceCharge(AgentProfitDistributionModel model);

}
