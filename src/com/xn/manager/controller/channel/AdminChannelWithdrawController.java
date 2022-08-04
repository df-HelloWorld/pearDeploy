package com.xn.manager.controller.channel;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.StringUtil;
import com.xn.common.util.constant.CachedKeyUtils;
import com.xn.common.util.constant.DyCacheKey;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.channel.ChannelBalanceDeductModel;
import com.xn.manager.model.channel.ChannelWithdrawModel;
import com.xn.manager.service.ChannelBalanceDeductService;
import com.xn.manager.service.ChannelWithdrawService;
import com.xn.manager.service.RedisIdService;
import com.xn.system.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description: 管理员-渠道提现审核
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/adminchannelwithdraw")
public class AdminChannelWithdrawController extends BaseController {

    private static Logger log = Logger.getLogger(AdminChannelWithdrawController.class);

    @Autowired
    private RedisIdService redisIdService;

    @Autowired
    private ChannelWithdrawService<ChannelWithdrawModel> channelWithdrawService;

    @Autowired
    private ChannelBalanceDeductService<ChannelBalanceDeductModel> channelBalanceDeductService;




    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/adminchannelwithdraw/adminchannelwithdrawIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelWithdrawModel model) throws Exception {
        List<ChannelWithdrawModel> dataList = new ArrayList<ChannelWithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                dataList = channelWithdrawService.queryByList(model);
            }else {
                sendFailureMessage(response,"不是管理员,无法查看!");
            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     * @Description: 获取提现详情
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getId")
    public void getId(Long id, HttpServletResponse response) throws Exception {
        ChannelWithdrawModel query = new ChannelWithdrawModel();
        query.setId(id);
        ChannelWithdrawModel bean = channelWithdrawService.queryById(query);
        if (bean == null) {
            sendFailureMessage(response, "没有找到对应的记录!");
            return;
        }
        sendSuccessMessage(response, "", bean);
    }





    /**
     * 审核提现订单
     */
    @RequestMapping("/check")
    public void check(HttpServletRequest request, HttpServletResponse response, ChannelWithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                sendFailureMessage(response, "您无权审核提现订单!");
                return;
            }

            if (bean.getCheckWithdrawStatus() == 0){
                sendFailureMessage(response, "请选择提现状态!");
                return;
            }


            if (bean.getCheckWithdrawStatus() == 1){
                sendFailureMessage(response, "提现状态选择提现中无效,请选择其它提现状态!");
                return;
            }

            // redis锁住此提现审核的主键ID：主要不让这条数据短时间内重复点击
            String lockKey = CachedKeyUtils.getDeployCacheKey(DyCacheKey.CHECK_CHANNEL_WITHDRAW, bean.getId());
            boolean flagLock = redisIdService.lock(lockKey);
            if (!flagLock){
                sendFailureMessage(response, "操作过于频繁,请稍后再试!");
                return;
            }




            int withdrawStatus = bean.getCheckWithdrawStatus();
            bean.setWithdrawStatus(withdrawStatus);
            // 更新提现状态
            channelWithdrawService.checkChannelWithdraw(bean);

            if (withdrawStatus == 2){
                // 提现驳回，需要添加渠道扣减余额流水：并且是加金额类型的

                // 获取提现订单信息：这里直接从数据库查，这样安全
                ChannelWithdrawModel channelWithdrawQuery = new ChannelWithdrawModel();
                channelWithdrawQuery.setId(bean.getId());
                ChannelWithdrawModel channelWithdrawModel = channelWithdrawService.queryById(channelWithdrawQuery);
                if (channelWithdrawModel == null || channelWithdrawModel.getId() <= 0){
                    sendFailureMessage(response, "错误,请联系管理员!");
                    return;
                }

                // 提现金额 + 手续费
                String money = StringUtil.getBigDecimalAdd(channelWithdrawModel.getMoney(), channelWithdrawModel.getServiceCharge());
                ChannelBalanceDeductModel channelBalanceDeductModel = PublicMethod.assembleChannelBalanceDeductAdd(channelWithdrawModel.getChannelId(), channelWithdrawModel.getOrderNo(),
                        money, 3,2,null,null);
                // 添加渠道扣减余额流水
                channelBalanceDeductService.add(channelBalanceDeductModel);

            }


            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }

    /**
     *
     * 获取汇总数据
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, ChannelWithdrawModel bean) throws Exception {
        ChannelWithdrawModel data = new ChannelWithdrawModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setWithdrawStatus(1);//提现中
            String ing_money = channelWithdrawService.getTotalData(bean);// 提现中

            bean.setWithdrawStatus(2);//提现失败/驳回
            String fail_money = channelWithdrawService.getTotalData(bean);// 提现失败/驳回

            bean.setWithdrawStatus(3);//提现成功
            String suc_money = channelWithdrawService.getTotalData(bean);// 提现成功

            data.setTotalIngMoney(ing_money);
            data.setTotalFailMoney(fail_money);
            data.setTotalSucMoney(suc_money);
        }
        HtmlUtil.writerJson(response, data);
    }




    /**
     * 按照Excel报表导出数据
     */
    @RequestMapping("/exportData")
    public void exportDataNew(HttpServletRequest request, HttpServletResponse response, ChannelWithdrawModel model) throws Exception {
        exportData(request,response,model);
    }


    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    public void exportData(HttpServletRequest request, HttpServletResponse response, ChannelWithdrawModel model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                return;
            }

            List<ChannelWithdrawModel> dataList = new ArrayList<ChannelWithdrawModel>();
            dataList = channelWithdrawService.queryAllList(model);
            // 导出数据
            String[] titles = new String[10];
            String[] titleCode = new String[10];
            String filename = "渠道提现信息";
//            titles = new String[]{"代理名称", "渠道名称", "平台订单", "订单金额", "实际支付金额", "手续费", "收益分成", "收益", "创建时间"};
//            titleCode = new String[]{"agentName", "channelName", "myTradeNo", "totalAmount", "payAmount", "serviceCharge", "profitRatio", "profit", "createTime"};
            titles = new String[]{"渠道名称",        "提现金额", "手续费",         "银行",     "开户名",      "银行卡号",  "支行",          "备注",    "提现状态",           "创建时间"};
            titleCode = new String[]{"channelName", "money",    "serviceCharge", "bankName", "accountName", "bankCard", "subbranchName", "remark",  "withdrawStatusStr", "createTime"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            for(ChannelWithdrawModel paramO : dataList){
                if (paramO.getWithdrawStatus() == 1){
                    paramO.setWithdrawStatusStr("提现中");
                }else if (paramO.getWithdrawStatus() == 2){
                    paramO.setWithdrawStatusStr("提现驳回");
                }else if (paramO.getWithdrawStatus() == 3){
                    paramO.setWithdrawStatusStr("提现成功");
                }
                Map<String,Object> map = BeanUtils.transBeanToMap(paramO);
                paramList.add(map);
            }
            ExportData.exportExcel(paramList, titles, titleCode, filename, response);
        }
    }



}
