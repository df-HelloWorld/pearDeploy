package com.xn.manager.controller.inorder;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.inorder.InOrderModel;
import com.xn.manager.model.inorder.vo.InOrderVo;
import com.xn.manager.service.InOrderService;
import com.xn.system.entity.Account;
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
 * @Description: 代收订单的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/inorder")
public class InOrderController extends BaseController {
    private static Logger log = Logger.getLogger(InOrderController.class);

    @Autowired
    private InOrderService<InOrderModel> inOrderService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/inorder/inorderIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, InOrderModel model) throws Exception {
        List<InOrderVo> dataList = new ArrayList<InOrderVo>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                model.setChannelId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            List<InOrderModel> resList = new ArrayList<InOrderModel>();
            resList = inOrderService.queryByList(model);
            if (resList != null && resList.size() > 0){
                dataList = BeanUtils.copyList(resList, InOrderVo.class);
            }
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
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                model.setChannelId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            List<InOrderModel> dataList = new ArrayList<InOrderModel>();
            dataList = inOrderService.queryAllList(model);
            // 导出数据
            String[] titles = new String[10];
            String[] titleCode = new String[10];
            String filename = "代收订单";
            titles = new String[]{"平台订单",      "商家订单",  "渠道名称",    "平台通道编码", "平台通道码名称", "订单金额",     "手续费",       "实际金额",   "订单状态",        "拉单状态",              "回执时间",   "同步状态",     "创建时间"};
            titleCode = new String[]{"myTradeNo", "outTradeNo","channelName", "pfGewayCode", "codeName",      "totalAmount", "serviceCharge","actualMoney","orderStatusStr", "pullOrderStatusStr",    "tradeTime", "sendStatusStr","createTime"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            for(InOrderModel paramO : dataList){

                if (paramO.getOrderStatus() == 1){
                    paramO.setOrderStatusStr("初始化");
                }else if (paramO.getOrderStatus() == 2){
                    paramO.setOrderStatusStr("超时/失败");
                }else if (paramO.getOrderStatus() == 3){
                    log.info("");
                    paramO.setOrderStatusStr("有质疑");
                }else if (paramO.getOrderStatus() == 4){
                    paramO.setOrderStatusStr("成功");
                }

                if (paramO.getPullOrderStatus() == 1){
                    paramO.setPullOrderStatusStr("拉单成功");
                }else if (paramO.getPullOrderStatus() == 2){
                    paramO.setPullOrderStatusStr("拉单失败");
                }


                if (paramO.getSendStatus() == 0){
                    paramO.setSendStatusStr("初始化");
                }else if (paramO.getSendStatus() == 1){
                    paramO.setSendStatusStr("锁定");
                    log.info("");
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
        InOrderVo data = new InOrderVo();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                model.setChannelId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            InOrderModel resData = new InOrderModel();
            resData = inOrderService.getTotalData(model);
            if (resData != null){
                data = BeanUtils.copy(resData, InOrderVo.class);
            }
        }
        HtmlUtil.writerJson(response, data);
    }
}
