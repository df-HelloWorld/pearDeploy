package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.channel.ChannelWithdrawModel;

/**
 * @ClassName:
 * @Description: 渠道提现记录的Dao层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface ChannelWithdrawDao<T> extends BaseDao<T> {

    /**
     * @Description: 提现审核
     * @param model
     * @return
     * @Author: yoko
     * @Date 2021/9/7 13:32
    */
    public int checkChannelWithdraw(ChannelWithdrawModel model);


    /**
     * @Description:提现总金额
     * @param model
     * @author: yoko
     * @date: 2022/7/10 8:17
     * @version 1.0.0
     */
    public String getTotalData(ChannelWithdrawModel model);

}
