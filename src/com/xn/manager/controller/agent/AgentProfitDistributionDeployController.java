package com.xn.manager.controller.agent;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.*;
import com.xn.manager.model.agent.AgentModel;
import com.xn.manager.model.agent.AgentProfitDistributionModel;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.service.*;
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
 * @Description 通道码的Controller层
 * @Author yoko
 * @Date 2020/9/18 19:28
 * @Version 1.0
 */
@Controller
@RequestMapping("/agentprofitdistributiondeploy")
public class AgentProfitDistributionDeployController extends BaseController {

    private static Logger log = Logger.getLogger(AgentProfitDistributionDeployController.class);

    @Autowired
    private AgentProfitDistributionDeployService<AgentProfitDistributionDeployModel> agentProfitDistributionDeployService;
    @Autowired
    private AgentProfitDistributionService<AgentProfitDistributionModel> agentProfitDistributionService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/agentprofitdistributiondeploy/agentprofitdistributiondeployIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionDeployModel model) throws Exception {
        List<AgentProfitDistributionDeployModel> dataList = new ArrayList<AgentProfitDistributionDeployModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = agentProfitDistributionDeployService.queryByList(model);


            for(AgentProfitDistributionDeployModel agentProfitDistributionDeployModel:dataList){
                String  channelName  ="";
                String  gewayCodeName  ="";
                String  agentNameOrServiceCharge ="";
                AgentProfitDistributionModel   agentProfitDistributionModel =new  AgentProfitDistributionModel();
                agentProfitDistributionModel.setDeployId(agentProfitDistributionDeployModel.getId());
                List<AgentProfitDistributionModel>   list =agentProfitDistributionService.queryAllList(agentProfitDistributionModel);
                for(AgentProfitDistributionModel  agentProfitDistributionModel1:list){
                    if(agentProfitDistributionModel1.getBindingType()==1){
                        gewayCodeName=agentProfitDistributionModel1.getGewayCodeName();
                        agentNameOrServiceCharge+=agentProfitDistributionModel1.getAgentName()+"==《"+agentProfitDistributionModel1.getServiceCharge()+"》||"+"<br>";
                    }else if(agentProfitDistributionModel1.getBindingType()==2){
                        channelName=agentProfitDistributionModel1.getChannelName();
                        agentNameOrServiceCharge+=agentProfitDistributionModel1.getAgentName()+"==《"+agentProfitDistributionModel1.getServiceCharge()+"》||"+"<br>";
                    }else if(agentProfitDistributionModel1.getBindingType()==3){
                        channelName=agentProfitDistributionModel1.getChannelName();
                        gewayCodeName=agentProfitDistributionModel1.getGewayCodeName();
                        agentNameOrServiceCharge+=agentProfitDistributionModel1.getAgentName()+"==《"+agentProfitDistributionModel1.getServiceCharge()+"》||"+"<br>";
                    }
                }
                agentProfitDistributionDeployModel.setChannelName(channelName);
                agentProfitDistributionDeployModel.setAgentNameOrServiceCharge(agentNameOrServiceCharge);
                agentProfitDistributionDeployModel.setGewayCodeNames(gewayCodeName);
            }

        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionDeployModel model) throws Exception {
        List<AgentProfitDistributionDeployModel> dataList = new ArrayList<AgentProfitDistributionDeployModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = agentProfitDistributionDeployService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
//    @RequestMapping("/dataSelGewayCode")
//    public void dataSelGewayCode(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel model) throws Exception {
//        List<PrPlatformGewayCodeLinkModel> dataList = new ArrayList<PrPlatformGewayCodeLinkModel>();
//        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
//        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//            }else {
//                HtmlUtil.writerJson(response, model.getPage(), dataList);
//            }
//            dataList = agentProfitDistributionDeployService.queryGewayCodeId(model);
//        }
//        HtmlUtil.writerJson(response, dataList);
//    }


    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAddInfo")
    public String jumpAddInfo(Model model, long id) {
        model.addAttribute("deployId", id);
        AgentProfitDistributionDeployModel   agentProfitDistributionDeployModel=new AgentProfitDistributionDeployModel();
        agentProfitDistributionDeployModel.setId(id);
        AgentProfitDistributionDeployModel  query=agentProfitDistributionDeployService.queryByCondition(agentProfitDistributionDeployModel);
        model.addAttribute("bindingType",query.getBindingType());
        model.addAttribute("channelId",0);
        model.addAttribute("gewayCodeId",0);

        AgentProfitDistributionModel   agentProfitDistributionModel = new AgentProfitDistributionModel();
        agentProfitDistributionModel.setDeployId(id);
        List<AgentProfitDistributionModel>   queryList =agentProfitDistributionService.queryAllList(agentProfitDistributionModel);
        for(AgentProfitDistributionModel agentProfitDistributionModel1:queryList){
            if(query.getBindingType()==1){
                model.addAttribute("gewayCodeId",agentProfitDistributionModel1.getGewayCodeId());
            }else if(query.getBindingType()==2){
                model.addAttribute("channelId",agentProfitDistributionModel1.getChannelId());
            }else if(query.getBindingType()==3){
                model.addAttribute("gewayCodeId",agentProfitDistributionModel1.getGewayCodeId());
                model.addAttribute("channelId",agentProfitDistributionModel1.getChannelId());
            }
        }


        return "manager/agentprofitdistributiondeploy/agentprofitdistributiondeployUpdate";
    }





    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
//        model.addAttribute("rloeMenu", roleService.queryList());
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                sendFailureMessage(response,"您无权操作!");
                return null;
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/agentprofitdistributiondeploy/agentprofitdistributiondeployAdd";
    }

    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionDeployModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                agentProfitDistributionDeployService.add(bean);

                sendSuccessMessage(response, "保存成功~");
                return;
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
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionDeployModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                agentProfitDistributionDeployService.update(bean);
                sendSuccessMessage(response, "保存成功~");
                return;
            }else {
                log.info("1");
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
    public void delete(HttpServletRequest request, HttpServletResponse response, PrRelationTypeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                agentProfitDistributionDeployService.delete(bean);
                agentProfitDistributionService.deleteDeployId(bean.getId());
                sendSuccessMessage(response, "删除成功");
                return;
            }else {
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
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionDeployModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                agentProfitDistributionDeployService.manyOperation(bean);
                sendSuccessMessage(response, "状态更新成功");
                return;
            }else {
                log.info("1");
                sendFailureMessage(response,"您无权操作!");
                return;
            }

        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }
    }
}
