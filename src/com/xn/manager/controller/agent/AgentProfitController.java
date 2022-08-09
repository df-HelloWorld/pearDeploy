package com.xn.manager.controller.agent;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.agent.AgentProfitModel;
import com.xn.manager.service.AgentProfitService;
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
 * @Description: 管理员-代理利益明细的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/agentprofit")
public class AgentProfitController extends BaseController {
    private static Logger log = Logger.getLogger(AgentProfitController.class);

    @Autowired
    private AgentProfitService<AgentProfitModel> agentProfitService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/agentprofit/agentprofitIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AgentProfitModel model) throws Exception {
        List<AgentProfitModel> dataList = new ArrayList<AgentProfitModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                model.setAgentId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            dataList = agentProfitService.queryByList(model);
            for (int i = 0; i < dataList.size(); i++){
                if (dataList.get(i).getAgentType() == 1){
                    dataList.get(i).setGewayCodeName("xx通道");
                }
                if (dataList.get(i).getAgentType() == 2){
                    dataList.get(i).setChannelName("xx渠道");
                    dataList.get(i).setCodeName("xx平台");
                }
                if (dataList.get(i).getAgentType() == 3){
                    dataList.get(i).setGewayCodeName("xx通道");
                }
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
            return;
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }



    /**
     * 按照Excel报表导出数据
     */
    @RequestMapping("/exportData")
    public void exportDataNew(HttpServletRequest request, HttpServletResponse response, AgentProfitModel model) throws Exception {
        exportData(request,response,model);
    }


    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    public void exportData(HttpServletRequest request, HttpServletResponse response, AgentProfitModel model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                model.setAgentId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            List<AgentProfitModel> dataList = new ArrayList<AgentProfitModel>();
            dataList = agentProfitService.queryAllList(model);
            // 导出数据
            String[] titles = new String[10];
            String[] titleCode = new String[10];
            String filename = "代理收益信息";
//            titles = new String[]{"代理名称", "渠道名称", "平台订单", "订单金额", "实际支付金额", "手续费", "收益分成", "收益", "创建时间"};
//            titleCode = new String[]{"agentName", "channelName", "myTradeNo", "totalAmount", "payAmount", "serviceCharge", "profitRatio", "profit", "createTime"};
            titles = new String[]{"代理",         "渠道名称",     "平台通道", "通道码名",       "平台订单",  "订单金额",     "订单类型",     "收益类型",        "收益",   "创建时间"};
            titleCode = new String[]{"agentName", "channelName", "codeName", "gewayCodeName", "myTradeNo", "totalAmount", "orderTypeStr", "profitTypeStr",  "profit", "createTime"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            for(AgentProfitModel paramO : dataList){
                if (paramO.getProfitType() == 1){
                    paramO.setProfitTypeStr("固定收益");
                }else if (paramO.getProfitType() == 2){
                    paramO.setProfitTypeStr("额外收益");
                }
                if (paramO.getOrderType() == 1){
                    paramO.setOrderTypeStr("代收");
                }else if (paramO.getOrderType() == 2){
                    paramO.setOrderTypeStr("代付");
                }
                if (paramO.getAgentType() == 1){
                    paramO.setGewayCodeName("xx通道");
                }
                if (paramO.getAgentType() == 2){
                    paramO.setChannelName("xx渠道");
                    paramO.setCodeName("xx平台");
                }
                if (paramO.getAgentType() == 3){
                    paramO.setGewayCodeName("xx通道");
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
    public void totalData(HttpServletRequest request, HttpServletResponse response, AgentProfitModel model) throws Exception {
        AgentProfitModel data = new AgentProfitModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                // 不是管理员
                model.setAgentId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            data = agentProfitService.getTotalData(model);
        }
        HtmlUtil.writerJson(response, data);
    }

}
