package com.xn.manager.controller.frequently;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.frequently.FrequentlyModel;
import com.xn.manager.service.FrequentlyService;
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
import java.util.Date;
import java.util.List;

/**
 * @author yoko
 * @desc 频繁请求：被抓取到的过于频繁的数据的Controller层
 * @create 2022-04-15 15:53
 **/
@Controller
@RequestMapping("/frequently")
public class FrequentlyController extends BaseController {

    private static Logger log = Logger.getLogger(FrequentlyController.class);

    @Autowired
    private RedisIdService redisIdService;

    @Autowired
    private FrequentlyService<FrequentlyModel> frequentlyService;




    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/frequently/frequentlyIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, FrequentlyModel model) throws Exception {
        List<FrequentlyModel> dataList = new ArrayList<FrequentlyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if (model.getInvalidStatus() == 0){
                    model.setInvalidStatus(1);
                }
                if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                    model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                    model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
                }
                dataList = frequentlyService.queryByList(model);
            }else {
                sendFailureMessage(response,"不是管理员,无法查看!");
            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     * @Description: 获取频繁请求详情
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getId")
    public void getId(Long id, HttpServletResponse response) throws Exception {
        FrequentlyModel query = new FrequentlyModel();
        query.setId(id);
        FrequentlyModel bean = frequentlyService.queryById(query);
        if (bean == null) {
            sendFailureMessage(response, "没有找到对应的记录!");
            return;
        }
        sendSuccessMessage(response, "", bean);
    }


    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, FrequentlyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            FrequentlyModel query = new FrequentlyModel();
            query.setId(bean.getId());
            FrequentlyModel frequentlyModel = frequentlyService.queryById(query);
            if (frequentlyModel == null) {
                sendFailureMessage(response, "没有找到对应的记录!");
                return;
            }

            redisIdService.delLock(frequentlyModel.getLockRedisKey());

            frequentlyService.manyOperation(bean);

            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }





}
