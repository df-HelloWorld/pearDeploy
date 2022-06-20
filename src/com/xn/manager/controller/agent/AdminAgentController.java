package com.xn.manager.controller.agent;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.enums.ManagerEnum;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.agent.AgentModel;
import com.xn.manager.model.strategy.StrategyData;
import com.xn.manager.model.strategy.StrategyModel;
import com.xn.manager.service.AgentService;
import com.xn.manager.service.StrategyService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
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
 * @ClassName:
 * @Description: 管理员-代理的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/adminagent")
public class AdminAgentController extends BaseController {

    private static Logger log = Logger.getLogger(AdminAgentController.class);

    @Autowired
    private AgentService<AgentModel> agentService;

    @Autowired
    private StrategyService<StrategyModel> strategyService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/adminagent/adminagentIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AgentModel model) throws Exception {
        List<AgentModel> dataList = new ArrayList<AgentModel>();
        StrategyModel strategyQuery = new StrategyModel();
        strategyQuery.setStgKey("agentTypeList");
        StrategyModel strategyData = strategyService.queryByCondition(strategyQuery);
        List<StrategyData> strategyDataList = new ArrayList<>();
        if (strategyData != null ){
            if (!StringUtils.isBlank(strategyData.getStgBigValue())){
                strategyDataList = JSON.parseArray(strategyData.getStgBigValue(), StrategyData.class);
            }
        }
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = agentService.queryByList(model);
            if (dataList != null && dataList.size() > 0){
                for (int i = 0; i < dataList.size(); i++){
                    for (int j = 0; j < strategyDataList.size(); j++){
                        if (dataList.get(i).getAgentType() == strategyDataList.get(j).getStgKey()){
                            dataList.get(i).setAgentTypeName(strategyDataList.get(j).getStgValueOne());
                        }
                    }
                }
            }

        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, AgentModel model) throws Exception {
        List<AgentModel> dataList = new ArrayList<AgentModel>();
        StrategyModel strategyQuery = new StrategyModel();
        strategyQuery.setStgKey("agentTypeList");
        StrategyModel strategyData = strategyService.queryByCondition(strategyQuery);
        List<StrategyData> strategyDataList = new ArrayList<>();
        if (strategyData != null ){
            if (!StringUtils.isBlank(strategyData.getStgBigValue())){
                strategyDataList = JSON.parseArray(strategyData.getStgBigValue(), StrategyData.class);
            }
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = agentService.queryAllList(model);
            if (dataList != null && dataList.size() > 0){
                for (int i = 0; i < dataList.size(); i++){
                    for (int j = 0; j < strategyDataList.size(); j++){
                        if (dataList.get(i).getAgentType() == strategyDataList.get(j).getStgKey()){
                            dataList.get(i).setAgentTypeName(strategyDataList.get(j).getStgValueOne());
                            log.info("");
                        }
                    }
                }
            }
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(Model model, HttpServletRequest request, HttpServletResponse response) {

        StrategyModel strategyQuery = new StrategyModel();
        strategyQuery.setStgKey("agentTypeList");
        StrategyModel strategyData = strategyService.queryByCondition(strategyQuery);
        List<StrategyData> strategyDataList = new ArrayList<>();
        if (strategyData != null ){
            if (!StringUtils.isBlank(strategyData.getStgBigValue())){
                strategyDataList = JSON.parseArray(strategyData.getStgBigValue(), StrategyData.class);
            }
        }
        model.addAttribute("agentTypeList", strategyDataList);
        return "manager/adminagent/adminagentAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, AgentModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //check是否有重复的账号
                AgentModel queryBean = new AgentModel();
                queryBean.setAccountNum(bean.getAccountNum());
                queryBean = agentService.queryByCondition(queryBean);
                if (queryBean != null && queryBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                    sendFailureMessage(response,"有重复的账号,请重新输入其它账号!");
                    return;
                }else{
                    //check是否有重复的平台类型的代理
                    if (bean.getAgentType() == 4){
                        AgentModel queryAgentType = new AgentModel();
                        queryAgentType.setAgentType(bean.getAgentType());
                        queryAgentType = agentService.queryByCondition(queryAgentType);
                        if (queryAgentType != null && queryAgentType.getId() > 0){
                            sendFailureMessage(response,"平台类型代理只允许设置一个!");
                            return;
                        }
                    }

                    bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
                    bean.setRoleId(ManagerEnum.RoleTypeEnum.AGENT.getRoleType());
                    bean.setWithdrawType(1);
                    agentService.add(bean);
                    sendSuccessMessage(response, "保存成功~");
                    return;
                }
            }else {
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
    public String jumpUpdate(Model model, long id, Integer op) {
        AgentModel atModel = new AgentModel();
        atModel.setId(id);
        model.addAttribute("account", agentService.queryById(atModel));
        model.addAttribute("op", op);

        StrategyModel strategyQuery = new StrategyModel();
        strategyQuery.setStgKey("agentTypeList");
        StrategyModel strategyData = strategyService.queryByCondition(strategyQuery);
        List<StrategyData> strategyDataList = new ArrayList<>();
        if (strategyData != null ){
            if (!StringUtils.isBlank(strategyData.getStgBigValue())){
                strategyDataList = JSON.parseArray(strategyData.getStgBigValue(), StrategyData.class);
            }
        }
        model.addAttribute("agentTypeList", strategyDataList);

        return "manager/adminagent/adminagentEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,AgentModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if ("2".equals(op)) {
                    bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
                }

                //check是否有重复的账号
                AgentModel queryBean = new AgentModel();
                queryBean.setNotId(bean.getId());
                queryBean.setAccountNum(bean.getAccountNum());
                queryBean = agentService.queryByCondition(queryBean);
                if (queryBean != null && queryBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                    sendFailureMessage(response,"有重复的账号,请重新输入其它账号!");
                    return;
                }


                //check是否有重复的平台类型的代理
                if (bean.getAgentType() == 4){
                    AgentModel queryAgentType = new AgentModel();
                    queryAgentType.setNotId(bean.getId());
                    queryAgentType.setAgentType(bean.getAgentType());
                    queryAgentType = agentService.queryByCondition(queryAgentType);
                    if (queryAgentType != null && queryAgentType.getId() > 0){
                        sendFailureMessage(response,"平台类型代理只允许设置一个!");
                        return;
                    }
                }


                agentService.update(bean);
                sendSuccessMessage(response, "保存成功~");
                return;
            }else {
                sendFailureMessage(response,"您无权操作!");
                return;
            }

        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, AgentModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                agentService.delete(bean);
                sendSuccessMessage(response, "删除成功");
                return;
            }else {
                log.info("");
                sendFailureMessage(response,"您无权操作!");
                return;
            }
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, AgentModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                agentService.manyOperation(bean);
                sendSuccessMessage(response, "状态更新成功");
                return;
            }else {
                log.info("5");
                sendFailureMessage(response,"您无权操作!");
                return;
            }

        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
