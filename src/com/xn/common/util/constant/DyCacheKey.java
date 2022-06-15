package com.xn.common.util.constant;

/**
 * @ClassName:
 * @Description: redis的key-deploy
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface DyCacheKey {

    /**
     * 锁住此渠道的主键ID：主要不让渠道短时间内重复点击
     */
    String CHANNEL_WITHDRAW = "-1";

    /**
     * 锁住此渠道提现审核的主键ID：主要不让这条数据短时间内重复点击
     * <p>
     *     渠道提现审核
     * </p>
     */
    String CHECK_CHANNEL_WITHDRAW = "-2";

    /**
     * 锁住此代理的主键ID：主要不让代理短时间内重复点击
     */
    String AGENT_WITHDRAW = "-3";

    /**
     * 锁住此代理提现审核的主键ID：主要不让这条数据短时间内重复点击
     * <p>
     *     渠道提现审核
     * </p>
     */
    String CHECK_AGENT_WITHDRAW = "-4";


    /**
     * 锁住此补单的单号：主要不让这条数据短时间内重复点击补单
     * <p>
     *     补单
     * </p>
     */
    String REPLENISH = "-5";

}
