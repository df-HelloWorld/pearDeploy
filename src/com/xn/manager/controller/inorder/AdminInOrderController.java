package com.xn.manager.controller.inorder;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.inorder.InOrderModel;
import com.xn.manager.service.InOrderService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description: 管理员-代收订单的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/admininorder")
public class AdminInOrderController extends BaseController {
    private static Logger log = Logger.getLogger(AdminInOrderController.class);

    @Autowired
    private InOrderService<InOrderModel> inOrderService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/admininorder/admininorderIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, InOrderModel model) throws Exception {
        List<InOrderModel> dataList = new ArrayList<InOrderModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                sendFailureMessage(response, "您无权查看数据!");
                return;
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                if (StringUtils.isBlank(model.getCreateTimeStart()) && StringUtils.isBlank(model.getCreateTimeEnd())){
                    model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                    model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
                }

            }
            dataList = inOrderService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }





    /**
     * 重发
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, InOrderModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            inOrderService.manyOperation(bean);
            sendSuccessMessage(response, "更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * 按照Excel报表导出数据
     */
    @RequestMapping("/exportData")
    public void exportDataNew(HttpServletRequest request, HttpServletResponse response, InOrderModel model) throws Exception {
        exportData(request,response,model);
    }


    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    public void exportData(HttpServletRequest request, HttpServletResponse response, InOrderModel model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                sendFailureMessage(response, "您无权操作!");
                return;
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                if (StringUtils.isBlank(model.getCreateTimeStart()) && StringUtils.isBlank(model.getCreateTimeEnd())){
                    model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                    model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
                }
            }
            List<InOrderModel> dataList = new ArrayList<InOrderModel>();
            dataList = inOrderService.queryAllList(model);
            // 导出数据
            String[] titles = new String[10];
            String[] titleCode = new String[10];
            String filename = "代收订单";
            titles = new String[]{"平台订单",      "商家订单",   "上游订单", "渠道名称",    "平台通道编码", "平台通道码名称", "通道名称",  "通道码",    "通道码名称",     "订单金额",     "手续费",       "实际金额",   "订单状态",       "拉单状态",          "拉单码类型",           "回执时间",  "是否是补单",      "是否计算收益","同步状态",     "创建时间"};
            titleCode = new String[]{"myTradeNo", "outTradeNo", "tradeNo", "channelName", "pfGewayCode", "codeName",      "gewayName", "gewayCode", "gewayCodeName", "totalAmount", "serviceCharge","actualMoney","orderStatusStr","pullOrderStatusStr","pullOrderCodeTypeStr","tradeTime","replenishTypeStr","isProfitStr","sendStatusStr","createTime"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            for(InOrderModel paramO : dataList){

                if (paramO.getOrderStatus() == 1){
                    paramO.setOrderStatusStr("初始化");
                }else if (paramO.getOrderStatus() == 2){
                    paramO.setOrderStatusStr("超时/失败");
                }else if (paramO.getOrderStatus() == 3){
                    paramO.setOrderStatusStr("有质疑");
                }else if (paramO.getOrderStatus() == 4){
                    paramO.setOrderStatusStr("成功");
                }

                if (paramO.getPullOrderStatus() == 1){
                    paramO.setPullOrderStatusStr("拉单成功");
                }else if (paramO.getPullOrderStatus() == 2){
                    paramO.setPullOrderStatusStr("拉单失败");
                }

                if (paramO.getPullOrderCodeType() == 1){
                    paramO.setPullOrderCodeTypeStr("平台通道码拉单");
                }else if (paramO.getPullOrderCodeType() == 2){
                    paramO.setPullOrderCodeTypeStr("通道码拉单");
                }

                if (paramO.getReplenishType() == 0){
                    paramO.setReplenishTypeStr("不是补单");
                }else if (paramO.getReplenishType() == 1){
                    paramO.setReplenishTypeStr("不是补单");
                }else if (paramO.getReplenishType() == 2){
                    paramO.setReplenishTypeStr("是补单");
                }

                if (paramO.getIsProfit() == 1){
                    paramO.setIsProfitStr("不计算收益");
                }else if (paramO.getIsProfit() == 2){
                    paramO.setIsProfitStr("计算收益");
                }


                if (paramO.getSendStatus() == 0){
                    paramO.setSendStatusStr("初始化");
                }else if (paramO.getSendStatus() == 1){
                    paramO.setSendStatusStr("锁定");
                }else if (paramO.getSendStatus() == 2){
                    paramO.setSendStatusStr("失败");
                }else if (paramO.getSendStatus() == 3){
                    paramO.setSendStatusStr("成功");
                }
                Map<String,Object> map = BeanUtils.transBeanToMap(paramO);
                paramList.add(map);
            }
            ExportData.exportExcel(paramList, titles, titleCode, filename, response);
        }
    }

    /**
     *
     * 获取汇总数据
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, InOrderModel model) throws Exception {
        InOrderModel data = new InOrderModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                sendFailureMessage(response, "您无权查看数据!");
                return;
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            data = inOrderService.getTotalData(model);
        }
        HtmlUtil.writerJson(response, data);
    }
}
