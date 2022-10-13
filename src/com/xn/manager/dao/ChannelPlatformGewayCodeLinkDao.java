package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.channel.ChannelPlatformGewayCodeLinkModel;

import java.util.List;

/**
 * @ClassName:
 * @Description: 渠道与平台通道码关联的Dao层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface ChannelPlatformGewayCodeLinkDao<T> extends BaseDao<T> {

    /**
     * @Description:根据上游费率（成本）查询渠道与平台通道会亏本运营的数据
     * <p>
     *     平台码绑定通道码时查询上游费率高出渠道给平台通道码配置的费率的数据集合，
     *     用来校验亏本运营的
     * </p>
     * @param model
     * @author: yoko
     * @date: 2022/10/12 15:18
     * @version 1.0.0
     */
    public List<ChannelPlatformGewayCodeLinkModel> getServiceChargeDeficitList(ChannelPlatformGewayCodeLinkModel model);


    /**
     * @Description:根据平台通道码ID集合以及费率查出亏本运营的数据
     * <p>
     *     在更新通道码的时候（费率时）
     * </p>
     * @param model
     * @return:
     * @author: yoko
     * @date: 2022/10/13 9:24
     * @version 1.0.0
     */
    public List<ChannelPlatformGewayCodeLinkModel> getServiceChargeDeficitListByPlatformGewayCodeIdList(ChannelPlatformGewayCodeLinkModel model);
}
