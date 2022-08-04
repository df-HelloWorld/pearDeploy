package com.xn.manager.service;


import com.xn.common.service.BaseService;
import com.xn.manager.model.channel.ChannelWithdrawModel;

/**
 * @Description 渠道提现记录的Service层
 * @Author yoko
 * @Date 2020/10/31 15:35
 * @Version 1.0
 */
public interface ChannelWithdrawService<T> extends BaseService<T> {


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
