package com.xn.manager.controller.strategy;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.constant.CacheKey;
import com.xn.common.util.constant.CachedKeyUtils;
import com.xn.manager.model.strategy.StrategyData;
import com.xn.system.entity.Account;
import com.xn.manager.model.strategy.StrategyModel;
import com.xn.manager.service.RedisIdService;
import com.xn.manager.service.StrategyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 策略的Controller层
 * @Author yoko
 * @Date 2020/9/18 19:28
 * @Version 1.0
 */
@Controller
@RequestMapping("/strategy")
public class StrategyController extends BaseController {

    private static Logger log = Logger.getLogger(StrategyController.class);

    @Autowired
    private RedisIdService redisIdService;

    @Autowired
    private StrategyService<StrategyModel> strategyService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/strategy/strategyIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, StrategyModel model) throws Exception {
        List<StrategyModel> dataList = new ArrayList<StrategyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = strategyService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, StrategyModel model) throws Exception {
        List<StrategyModel> dataList = new ArrayList<StrategyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = strategyService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("rloeMenu", roleService.queryList());
        return "manager/strategy/strategyAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, StrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                strategyService.add(bean);
                sendSuccessMessage(response, "保存成功~");
                return;
            }else {
                log.info("4");
                sendFailureMessage(response,"您无权操作!");
                return;
            }

        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
            return;
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        StrategyModel atModel = new StrategyModel();
        atModel.setId(id);
        model.addAttribute("account", strategyService.queryById(atModel));
        return "manager/strategy/strategyEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, StrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                strategyService.update(bean);
                String strKeyCache = CachedKeyUtils.getCacheKey(CacheKey.STRATEGY, bean.getId());
                redisIdService.delLock(strKeyCache);
                sendSuccessMessage(response, "保存成功~");
                return;
            }else {
                sendFailureMessage(response,"您无权操作!");
                return;
            }
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, StrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                strategyService.delete(bean);
                sendSuccessMessage(response, "删除成功");
                return;
            }else {
                log.info("3");
                sendFailureMessage(response,"您无权操作!");
                return;
            }
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, StrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                strategyService.manyOperation(bean);
                sendSuccessMessage(response, "状态更新成功");
                return;
            }else {
                log.info("8");
                sendFailureMessage(response,"您无权操作!");
                return;
            }

        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }
    }


    /**
     *
     * 获取表格数据列表-无分页
     * <p>
     *     拆解策略里面的JSON数据，返回list集合数据
     * </p>
     */
    @RequestMapping("/dataJsonList")
    public void dataJsonList(HttpServletRequest request, HttpServletResponse response, StrategyModel model) throws Exception {
        List<StrategyData> dataList = new ArrayList<StrategyData>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            StrategyModel strategyModel = strategyService.queryByCondition(model);
            if (strategyModel != null){
                dataList = JSON.parseArray(strategyModel.getStgBigValue(), StrategyData.class);
            }else {
                HtmlUtil.writerJson(response, model.getPage(), null);
            }
        }
        HtmlUtil.writerJson(response, dataList);
    }
}
