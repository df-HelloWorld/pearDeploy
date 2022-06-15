package com.xn.manager.service;


import com.xn.common.service.BaseService;
import com.xn.manager.model.agent.AgentWithdrawModel;

/**
 * @ClassName:
 * @Description: 代理提现记录的Service层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface AgentWithdrawService<T> extends BaseService<T> {

    /**
     * @Description: 提现审核
     * @param model
     * @return
     * @Author: yoko
     * @Date 2021/9/7 13:32
     */
    public int checkAgentWithdraw(AgentWithdrawModel model);
}
