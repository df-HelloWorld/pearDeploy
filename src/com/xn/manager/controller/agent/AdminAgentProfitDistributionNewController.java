package com.xn.manager.controller.agent;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.PrGewayCodeModel;
import com.xn.manager.model.agent.AgentModel;
import com.xn.manager.model.agent.AgentProfitDistributionModel;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.service.AgentProfitDistributionService;
import com.xn.manager.service.AgentService;
import com.xn.manager.service.ChannelService;
import com.xn.manager.service.PrGewayCodeService;
import com.xn.system.entity.Account;
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
 * @Description: 管理员-代理利益分配的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/adminagentprofitdistributionnew")
public class AdminAgentProfitDistributionNewController extends BaseController {
    private static Logger log = Logger.getLogger(AdminAgentProfitDistributionNewController.class);

    @Autowired
    private AgentProfitDistributionService<AgentProfitDistributionModel> agentProfitDistributionService;

    @Autowired
    private AgentService<AgentModel> agentService;

    @Autowired
    private PrGewayCodeService<PrGewayCodeModel> prGewayCodeService;

    @Autowired
    private ChannelService<ChannelModel> channelService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/adminagentprofitdistributionnew/adminagentprofitdistributionIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionModel model) throws Exception {
        List<AgentProfitDistributionModel> dataList = new ArrayList<AgentProfitDistributionModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
                log.info("");
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            dataList = agentProfitDistributionService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionModel model) throws Exception {
        List<AgentProfitDistributionModel> dataList = new ArrayList<AgentProfitDistributionModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("");
                return;
            }
            dataList = agentProfitDistributionService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
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
                model.addAttribute("agent", agentService.queryAllList(new AgentModel()));
                model.addAttribute("pfGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));
                model.addAttribute("channel", channelService.queryAllList(new ChannelModel()));
            }else{
                sendFailureMessage(response,"您无权操作!");
                return null;
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/adminagentprofitdistribution/adminagentprofitdistributionAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            // check数据是否有问题

            if (bean.getAgentId() == 0){
                sendFailureMessage(response,"请选择代理!");
                return;
            }

            if (bean.getGewayCodeId() == 0 && bean.getChannelId() == 0){
                sendFailureMessage(response,"通道码与渠道必须至少选择一个!");
                return;
            }

            if (bean.getBindingType() == 1){
                if (bean.getGewayCodeId() == 0 || bean.getChannelId() > 0){
                    sendFailureMessage(response,"与通道码绑定,渠道不用填写!");
                    return;
                }
            }

            if (bean.getBindingType() == 2){
                if (bean.getGewayCodeId() > 0 || bean.getChannelId() == 0){
                    sendFailureMessage(response,"与渠道绑定,通道码不用填写!");
                    return;
                }
            }

            if (bean.getBindingType() == 3){
                if (bean.getGewayCodeId() == 0 || bean.getChannelId() == 0){
                    sendFailureMessage(response,"与两者绑定,通道码跟渠道都要填写!");
                    return;
                }
            }

            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                // check是否有重复纪录
                AgentProfitDistributionModel agentProfitDistributionQuery = new AgentProfitDistributionModel();
                if (bean.getAgentId() > 0){
                    agentProfitDistributionQuery.setAgentId(bean.getAgentId());
                }
                if (bean.getGewayCodeId() > 0){
                    agentProfitDistributionQuery.setGewayCodeId(bean.getGewayCodeId());
                }
                if (bean.getChannelId() > 0){
                    agentProfitDistributionQuery.setChannelId(bean.getChannelId());
                }


                agentProfitDistributionQuery.setBindingType(bean.getBindingType());
                AgentProfitDistributionModel agentProfitDistributionModel = agentProfitDistributionService.queryByCondition(agentProfitDistributionQuery);
                if (agentProfitDistributionModel != null && agentProfitDistributionModel.getId() > 0){
                    sendFailureMessage(response,"有重复纪录,请重新选择!");
                    return;
                }


                agentProfitDistributionService.add(bean);
                sendSuccessMessage(response, "保存成功~");
                return;
            }else {
                sendFailureMessage(response,"您无权操作!");
                return;
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        AgentProfitDistributionModel atModel = new AgentProfitDistributionModel();
        atModel.setId(id);
        model.addAttribute("account", agentProfitDistributionService.queryById(atModel));
        model.addAttribute("agent", agentService.queryAllList(new AgentModel()));
        model.addAttribute("pfGewayCode", prGewayCodeService.queryAllList());
        model.addAttribute("channel", channelService.queryAllList());
        return "manager/adminagentprofitdistribution/adminagentprofitdistributionEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,AgentProfitDistributionModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            // check数据是否有问题

            if (bean.getAgentId() == 0){
                sendFailureMessage(response,"请选择代理!");
                return;
            }

            if (bean.getGewayCodeId() == 0 && bean.getChannelId() == 0){
                sendFailureMessage(response,"通道码与渠道必须至少选择一个!");
                return;
            }

            if (bean.getBindingType() == 1){
                if (bean.getGewayCodeId() == 0 || bean.getChannelId() > 0){
                    sendFailureMessage(response,"与通道码绑定,渠道不用填写!");
                    return;
                }
            }

            if (bean.getBindingType() == 2){
                if (bean.getGewayCodeId() > 0 || bean.getChannelId() == 0){
                    sendFailureMessage(response,"与渠道绑定,通道码不用填写!");
                    return;
                }
            }

            if (bean.getBindingType() == 3){
                if (bean.getGewayCodeId() == 0 || bean.getChannelId() == 0){
                    sendFailureMessage(response,"与两者绑定,通道码跟渠道都要填写!");
                    return;
                }
            }

            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){

                // check是否有重复纪录
                AgentProfitDistributionModel agentProfitDistributionQuery = new AgentProfitDistributionModel();
                if (bean.getAgentId() > 0){
                    agentProfitDistributionQuery.setAgentId(bean.getAgentId());
                }
                if (bean.getGewayCodeId() > 0){
                    agentProfitDistributionQuery.setGewayCodeId(bean.getGewayCodeId());
                }
                if (bean.getChannelId() > 0){
                    agentProfitDistributionQuery.setChannelId(bean.getChannelId());
                }
                agentProfitDistributionQuery.setBindingType(bean.getBindingType());
                AgentProfitDistributionModel agentProfitDistributionModel = agentProfitDistributionService.queryByCondition(agentProfitDistributionQuery);
                if (agentProfitDistributionModel != null && agentProfitDistributionModel.getId() > 0){
                    sendFailureMessage(response,"有重复纪录,请重新选择!");
                    return;
                }

                agentProfitDistributionService.update(bean);
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
    public void delete(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                agentProfitDistributionService.delete(bean);
                sendSuccessMessage(response, "删除成功");
                return;
            }else {
                log.info("");
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
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                agentProfitDistributionService.manyOperation(bean);
                sendSuccessMessage(response, "状态更新成功");
                return;
            }else {
                log.info("");
                sendFailureMessage(response,"您无权操作!");
                return;
            }

        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }
    }
}
