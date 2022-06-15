package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.channel.ChannelModel;

/**
 * @author df
 * @Description:渠道的Dao层
 * @create 2018-07-27 16:45
 **/
public interface ChannelDao<T> extends BaseDao<T> {

    /**
     * @Description: 更新用户的密码
     * <p>
     *     更新登录密码，提现密码
     * </p>
     * @param model
     * @return
     * @author yoko
     * @date 2020/11/15 17:53
     */
    public int updatePassWd(ChannelModel model);
}
