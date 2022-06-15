package com.xn.manager.controller.statistics;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.statistics.StatisticsOftenModel;
import com.xn.manager.service.StatisticsOftenService;
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

/**
 * @ClassName:
 * @Description: 统计：时时的统计的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/statisticsOften")
public class StatisticsOftenController extends BaseController {
    private static Logger log = Logger.getLogger(StatisticsOftenController.class);

    @Autowired
    private StatisticsOftenService<StatisticsOftenModel> statisticsOftenService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/statistics/statisticsOftenIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, StatisticsOftenModel model) throws Exception {
        List<StatisticsOftenModel> dataList = new ArrayList<StatisticsOftenModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("");
                return;
            }
            if (StringUtils.isBlank(model.getStartCreateTime()) && StringUtils.isBlank(model.getEndCreateTime())){
                // 默认查询系统当前时间以及系统时间的前半小时的数据
                model.setStartCreateTime(DateUtil.addDateMinute(-30));
                model.setEndCreateTime(DateUtil.getNowPlusTime());
            }else{
                if (!StringUtils.isBlank(model.getStartCreateTime()) && StringUtils.isBlank(model.getEndCreateTime())){
                    model.setEndCreateTime(DateUtil.addDateMinuteByTime(30, model.getStartCreateTime()));
                }

                if (StringUtils.isBlank(model.getStartCreateTime()) && !StringUtils.isBlank(model.getEndCreateTime())){
                    model.setStartCreateTime(DateUtil.addDateMinuteByTime(-30, model.getEndCreateTime()));
                }
            }
            dataList = statisticsOftenService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }



    /**
     *
     * 获取汇总数据
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, StatisticsOftenModel model) throws Exception {
        StatisticsOftenModel data = new StatisticsOftenModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
                HtmlUtil.writerJson(response, data);
                log.info("");
                return;
            }
            if (StringUtils.isBlank(model.getStartCreateTime()) && StringUtils.isBlank(model.getEndCreateTime())){
                // 默认查询系统当前时间以及系统时间的前半小时的数据
                model.setStartCreateTime(DateUtil.addDateMinute(-30));
                model.setEndCreateTime(DateUtil.getNowPlusTime());
            }else{
                if (StringUtils.isBlank(model.getStartCreateTime()) && !StringUtils.isBlank(model.getEndCreateTime())){
                    model.setStartCreateTime(DateUtil.addDateMinuteByTime(-30, model.getEndCreateTime()));
                }
                if (!StringUtils.isBlank(model.getStartCreateTime()) && StringUtils.isBlank(model.getEndCreateTime())){
                    model.setEndCreateTime(DateUtil.addDateMinuteByTime(30, model.getStartCreateTime()));
                }


            }
            data = statisticsOftenService.getTotalData(model);
        }
        HtmlUtil.writerJson(response, data);
    }

}
