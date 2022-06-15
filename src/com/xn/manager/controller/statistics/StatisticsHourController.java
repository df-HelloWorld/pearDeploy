package com.xn.manager.controller.statistics;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.statistics.StatisticsHourModel;
import com.xn.manager.service.StatisticsHourService;
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

/**
 * @ClassName:
 * @Description: 统计：每小时的统计的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/statisticsHour")
public class StatisticsHourController extends BaseController {
    private static Logger log = Logger.getLogger(StatisticsHourController.class);

    @Autowired
    private StatisticsHourService<StatisticsHourModel> statisticsHourService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/statistics/statisticsHourIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, StatisticsHourModel model) throws Exception {
        List<StatisticsHourModel> dataList = new ArrayList<StatisticsHourModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("");
                return;
            }
            if (model.getCurday() ==0){
                model.setCurday(DateUtil.getDayNumber(new Date()));
            }
            dataList = statisticsHourService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }



    /**
     *
     * 获取汇总数据
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, StatisticsHourModel model) throws Exception {
        StatisticsHourModel data = new StatisticsHourModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
                HtmlUtil.writerJson(response, data);
                log.info("");
                return;
            }
            if (model.getCurday() == 0){
                model.setCurday(DateUtil.getDayNumber(new Date()));
            }
            data = statisticsHourService.getTotalData(model);
        }
        HtmlUtil.writerJson(response, data);
    }

}
