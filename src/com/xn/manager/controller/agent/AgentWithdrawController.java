package com.xn.manager.controller.agent;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.StringUtil;
import com.xn.common.util.constant.CachedKeyUtils;
import com.xn.common.util.constant.DyCacheKey;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.agent.AgentBalanceDeductModel;
import com.xn.manager.model.agent.AgentBankModel;
import com.xn.manager.model.agent.AgentModel;
import com.xn.manager.model.agent.AgentWithdrawModel;
import com.xn.manager.service.*;
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
import java.util.Date;
import java.util.List;

/**
 * @ClassName:
 * @Description: 代理-提现信息
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/agentwithdraw")
public class AgentWithdrawController extends BaseController {

    private static Logger log = Logger.getLogger(AgentWithdrawController.class);

    @Autowired
    private RedisIdService redisIdService;

    @Autowired
    private AgentWithdrawService<AgentWithdrawModel> agentWithdrawService;

    @Autowired
    private AgentService<AgentModel> agentService;

    @Autowired
    private AgentBankService<AgentBankModel> agentBankService;

    @Autowired
    private AgentBalanceDeductService<AgentBalanceDeductModel> agentBalanceDeductService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/agentwithdraw/agentwithdrawIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AgentWithdrawModel model) throws Exception {
        List<AgentWithdrawModel> dataList = new ArrayList<AgentWithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，只能查询自己的数据
                model.setAgentId(account.getId());
            }
            dataList = agentWithdrawService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
//        model.addAttribute("rloeMenu", roleService.queryList());
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                sendFailureMessage(response,"管理员无法提现!");
            }else {
                if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_AGENT){
                    AgentModel agentModel = new AgentModel();
                    agentModel.setId(account.getId());
                    model.addAttribute("tp", agentService.queryById(agentModel));
                }

                AgentBankModel agentBankModel = new AgentBankModel();
                agentBankModel.setAgentId(account.getId());
                agentBankModel.setIsEnable(2);
                model.addAttribute("bank", agentBankService.queryAllList(agentBankModel));
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/agentwithdraw/agentwithdrawAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, AgentWithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                sendFailureMessage(response,"管理员无法提现!");
                log.info("");
                return;
            }
            if (StringUtils.isBlank(bean.getMoney()) || StringUtils.isBlank(bean.getServiceCharge())){
                sendFailureMessage(response,"请填写提现金额!");
                return;
            }
            if (bean.getMoney().indexOf("-") > -1 || bean.getServiceCharge().indexOf("-") > -1){
                sendFailureMessage(response,"错误,请重试!");
                return;
            }
            if (StringUtils.isBlank(bean.getServiceCharge())){
                sendFailureMessage(response,"错误,请重试!");
                return;
            }
            boolean flag_serviceCharge = false;
//            if (bean.getServiceCharge().equals("2")){
            if (bean.getServiceCharge().equals("3")){
                flag_serviceCharge = true;
            }
            if (bean.getServiceCharge().equals("5")){
                flag_serviceCharge = true;
            }
            if (!flag_serviceCharge){
                sendFailureMessage(response,"错误,请重试!");
                return;
            }
            int bankType = 0;// 使用银行的地方：1是通过下拉框选择的，2是直接填写的
            if (bean.getAgentBankId() > 0 && !StringUtils.isBlank(bean.getBankName()) && !StringUtils.isBlank(bean.getAccountName())
                    && !StringUtils.isBlank(bean.getBankCard())){
                sendFailureMessage(response,"选择了下拉框的银行则无需填写临时银行信息,两者只需要选择一种!");
                return;
            }
            if (bean.getAgentBankId() <= 0 && StringUtils.isBlank(bean.getBankName())){
                sendFailureMessage(response,"请选择或者填写要提现的银行信息!");
                return;
            }
            if (bean.getAgentBankId() > 0 && StringUtils.isBlank(bean.getBankName()) && StringUtils.isBlank(bean.getAccountName())
                    && StringUtils.isBlank(bean.getBankCard())){
                bankType = 1;
            }
            if (bean.getAgentBankId() <= 0 && !StringUtils.isBlank(bean.getBankName()) && !StringUtils.isBlank(bean.getAccountName())
                    && !StringUtils.isBlank(bean.getBankCard())){
                bankType = 2;
            }


            boolean flag = false;
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_AGENT){
                if (bankType == 1){
                    // 查询渠道银行信息
                    AgentBankModel agentBankModel = new AgentBankModel();
                    agentBankModel.setId(bean.getAgentBankId());
                    agentBankModel = agentBankService.queryByCondition(agentBankModel);
                    if (agentBankModel == null || agentBankModel.getId() <= 0){
                        sendFailureMessage(response,"没有相关银行信息,请重新选择要提现的银行!");
                        return;
                    }

                    // 银行信息赋值
                    if (agentBankModel != null && agentBankModel.getId() > 0){
                        bean.setBankName(agentBankModel.getBankName());
                        if (!StringUtils.isBlank(agentBankModel.getSubbranchName())){
                            bean.setSubbranchName(agentBankModel.getSubbranchName());
                            log.info("");
                        }
                        bean.setAccountName(agentBankModel.getAccountName());
                        bean.setBankCard(agentBankModel.getBankCard());
                    }
                }

                AgentModel agentModel = new AgentModel();
                agentModel.setId(account.getId());
                agentModel = agentService.queryById(agentModel);
                if (agentModel == null && agentModel.getId() <= 0){
                    sendFailureMessage(response,"错误,请联系管理员!");
                    return;
                }

                String totalMoney = StringUtil.getBigDecimalAdd(bean.getMoney(), bean.getServiceCharge());
                flag = StringUtil.getBigDecimalSubtract(agentModel.getBalance(), totalMoney);

                if (flag){
                    // redis锁住此代理的主键ID：主要不让渠道短时间内重复点击
                    String lockKey = CachedKeyUtils.getDeployCacheKey(DyCacheKey.AGENT_WITHDRAW, account.getId());
                    boolean flagLock = redisIdService.lock(lockKey);
                    if (flagLock){
                        bean.setAgentId(account.getId());
                        String orderNo = "PDLTX" + DateUtil.getNowPlusTimeMill();
                        bean.setOrderNo(orderNo);
                        bean.setCurday(DateUtil.getDayNumber(new Date()));
                        // 添加提现纪录
                        agentWithdrawService.add(bean);

                        // 提现金额 + 手续费
                        String money = StringUtil.getBigDecimalAdd(bean.getMoney(), bean.getServiceCharge());
                        AgentBalanceDeductModel agentBalanceDeductModel = PublicMethod.assembleAgentBalanceDeductAdd(bean.getAgentId(), orderNo,
                                money, 1,1,null,null);
                        // 添加代理扣减余额流水
                        agentBalanceDeductService.add(agentBalanceDeductModel);
                        sendSuccessMessage(response, "保存成功~");
                        return;
                    }else{
                        sendFailureMessage(response,"频繁点击,请过20秒后在重试!");
                        return;
                    }


                }else {
                    sendFailureMessage(response,"提现金额超出余额!");
                    return;
                }
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
            return;
        }
    }

}
