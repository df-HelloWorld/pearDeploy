package com.xn.manager.controller.channel;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.channel.ChannelFlowingWaterModel;
import com.xn.manager.model.channel.vo.ChannelFlowingWaterVo;
import com.xn.manager.service.ChannelFlowingWaterService;
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
 * @Description: 渠道资金流水的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/channelflowingwater")
public class ChannelFlowingWaterController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelFlowingWaterController.class);

    @Autowired
    private ChannelFlowingWaterService<ChannelFlowingWaterModel> channelFlowingWaterService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelflowingwater/channelflowingwaterIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelFlowingWaterModel model) throws Exception {
        List<ChannelFlowingWaterVo> dataList = new ArrayList<ChannelFlowingWaterVo>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                model.setChannelId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }else{
//                if (model.getCurdayStart() > model.getCurdayEnd()){
//                    sendFailureMessage(response, "开始日期不能大于结束日期!");
//                    return;
//                }
//                int dayNum = DateUtil.differDayNum(String.valueOf(model.getCurdayStart()), String.valueOf(model.getCurdayEnd()));
//                if (dayNum > 5){
//                    sendFailureMessage(response, "查询5天范围的数据!");
//                    return;
//                }
            }
            List<ChannelFlowingWaterModel> resList = new ArrayList<ChannelFlowingWaterModel>();
            resList = channelFlowingWaterService.queryByList(model);
            if (resList != null && resList.size() > 0){
                dataList = BeanUtils.copyList(resList, ChannelFlowingWaterVo.class);
            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }





    /**
     * 按照Excel报表导出数据
     */
    @RequestMapping("/exportData")
    public void exportDataNew(HttpServletRequest request, HttpServletResponse response, ChannelFlowingWaterModel model) throws Exception {
        exportData(request,response,model);
    }


    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    public void exportData(HttpServletRequest request, HttpServletResponse response, ChannelFlowingWaterModel model) throws Exception{
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                model.setChannelId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }else{
                if (model.getCurdayStart() > model.getCurdayEnd()){
                    sendFailureMessage(response, "开始日期不能大于结束日期!");
                    return;
                }
                int dayNum = DateUtil.differDayNum(String.valueOf(model.getCurdayStart()), String.valueOf(model.getCurdayEnd()));
                if (dayNum > 5){
                    sendFailureMessage(response, "只能导出5天范围的数据!");
                    return;
                }
            }
            List<ChannelFlowingWaterModel> dataList = new ArrayList<ChannelFlowingWaterModel>();
            dataList = channelFlowingWaterService.queryAllList(model);
            // 导出数据
            String[] titles = new String[10];
            String[] titleCode = new String[10];
            String filename = "资金流水";
            titles = new String[]{"平台订单",      "商家订单",   "渠道名称",     "平台通道码名称",    "订单金额",     "手续费",       "类型",           "原金额",   "变动金额",   "变动后金额",   "备注",   "变动时间"};
            titleCode = new String[]{"myTradeNo", "outTradeNo",  "channelName",  "codeName",           "totalAmount", "serviceCharge",  "changeTypeStr",  "oldMoney", "changeMoney","newMoney",    "remark",  "createTime"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            for(ChannelFlowingWaterModel paramO : dataList){

                if (paramO.getChangeType() == 1){
                    paramO.setChangeTypeStr("付款订单");
                }else if (paramO.getChangeType() == 2){
                    paramO.setChangeTypeStr("提现");
                }else if (paramO.getChangeType() == 3){
                    paramO.setChangeTypeStr("提现驳回");
                }else if (paramO.getChangeType() == 4){
                    paramO.setChangeTypeStr("手动增加");
                }else if (paramO.getChangeType() == 5){
                    paramO.setChangeTypeStr("手动减少");
                }

                Map<String,Object> map = BeanUtils.transBeanToMap(paramO);
                paramList.add(map);
            }
            ExportData.exportExcel(paramList, titles, titleCode, filename, response);
        }
    }


}
