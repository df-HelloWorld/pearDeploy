package com.xn.manager.controller.agent;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.StringUtil;
import com.xn.manager.model.PrGewayCodeModel;
import com.xn.manager.model.PrPlatformGewayCodeLinkModel;
import com.xn.manager.model.agent.AgentModel;
import com.xn.manager.model.agent.AgentProfitDistributionModel;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.model.channel.ChannelPlatformGewayCodeLinkModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description: 管理员-代理利益分配的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/adminagentprofitdistribution")
public class AdminAgentProfitDistributionController extends BaseController {
    private static Logger log = Logger.getLogger(AdminAgentProfitDistributionController.class);

    @Autowired
    private AgentProfitDistributionService<AgentProfitDistributionModel> agentProfitDistributionService;

    @Autowired
    private AgentService<AgentModel> agentService;

    @Autowired
    private PrGewayCodeService<PrGewayCodeModel> prGewayCodeService;

    @Autowired
    private ChannelService<ChannelModel> channelService;


    @Autowired
    private ChannelPlatformGewayCodeLinkService<ChannelPlatformGewayCodeLinkModel> channelPlatformGewayCodeLinkService;

    @Autowired
    private PrPlatformGewayCodeLinkService<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/adminagentprofitdistribution/adminagentprofitdistributionIndex";
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
                log.info("1");
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

            if (bean.getBindingType() == 0){
                sendFailureMessage(response,"请填写绑定类型!");
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

            if (StringUtils.isBlank(bean.getServiceCharge())){
                sendFailureMessage(response,"请填写分润值!");
                return;
            }else{
                if (!bean.getServiceCharge().matches("[+-]?[0-9]+(\\.[0-9]+)?")){
                    sendFailureMessage(response,"请输入正确的分润值!");
                    return;
                }else {
                    if (Double.parseDouble(bean.getServiceCharge()) >= 1){
                        sendFailureMessage(response,"分润值不能大于等于1!");
                        return;
                    }
                }
            }

            // 查询代理的类型
            AgentModel agentQuery = new AgentModel();
            agentQuery.setId(bean.getAgentId());
            AgentModel agentModel = agentService.queryByCondition(agentQuery);
            // check数据
            if (agentModel.getAgentType() == 1){
                // 针对渠道的
                if (bean.getBindingType() != 2){
                    sendFailureMessage(response,"此代理只能绑定渠道!");
                    return;
                }
            }else if (agentModel.getAgentType() == 2){
                // 针对通道码
                if (bean.getBindingType() != 1){
                    sendFailureMessage(response,"此代理只能绑定通道码!");
                    return;
                }
            }else if (agentModel.getAgentType() == 3){
                // 两者针对
                if (bean.getBindingType() != 3){
                    sendFailureMessage(response,"此代理只能绑定两者针对的!");
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

            if (bean.getBindingType() == 0){
                sendFailureMessage(response,"请填写绑定类型!");
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

            if (StringUtils.isBlank(bean.getServiceCharge())){
                sendFailureMessage(response,"请填写分润值!");
                return;
            }else{
                if (!bean.getServiceCharge().matches("[+-]?[0-9]+(\\.[0-9]+)?")){
                    sendFailureMessage(response,"请输入正确的分润值!");
                    return;
                }else {
                    if (Double.parseDouble(bean.getServiceCharge()) >= 1){
                        log.info("");
                        sendFailureMessage(response,"分润值不能大于等于1!");
                        return;
                    }
                }
            }


            // 查询代理的类型
            AgentModel agentQuery = new AgentModel();
            agentQuery.setId(bean.getAgentId());
            AgentModel agentModel = agentService.queryByCondition(agentQuery);
            // check数据
            if (agentModel.getAgentType() == 1){
                // 针对渠道的
                if (bean.getBindingType() != 2){
                    sendFailureMessage(response,"此代理只能绑定渠道!");
                    return;
                }
            }else if (agentModel.getAgentType() == 2){
                // 针对通道码
                if (bean.getBindingType() != 1){
                    sendFailureMessage(response,"此代理只能绑定通道码!");
                    return;
                }
            }else if (agentModel.getAgentType() == 3){
                // 两者针对
                if (bean.getBindingType() != 3){
                    sendFailureMessage(response,"此代理只能绑定两者针对的!");
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
                List<Long> idList = new ArrayList<>();
                idList.add(bean.getId());
                agentProfitDistributionQuery.setIdList(idList);// 自己自身的ID除外
                AgentProfitDistributionModel agentProfitDistributionModel = agentProfitDistributionService.queryByCondition(agentProfitDistributionQuery);
                if (agentProfitDistributionModel != null && agentProfitDistributionModel.getId() > 0){
                    sendFailureMessage(response,"有重复纪录,请重新选择!");
                    return;
                }

                if (StringUtils.isBlank(bean.getExtraServiceCharge())){
                    bean.setExtraServiceCharge("");
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
                agentProfitDistributionService.manyOperation(bean);
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
                log.info("23");
                sendFailureMessage(response,"您无权操作!");
                return;
            }

        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }
    }



    /**
     * @Description: 获取代理的所有利益分配数据
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getAgentProfitDistributionList")
    public void getAgentProfitDistributionList(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                AgentProfitDistributionModel query = new AgentProfitDistributionModel();
                query.setAgentId(id);
                List<AgentProfitDistributionModel> dataList = new ArrayList<AgentProfitDistributionModel>();
                dataList = agentProfitDistributionService.queryAllList(query);
                sendSuccessMessage(response, "", dataList);
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
     * @Description: 获取渠道的所有利益分配数据-两者针对
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getAgentProfitDistributionByChannelList")
    public void getAgentProfitDistributionByChannelList(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                AgentProfitDistributionModel query = new AgentProfitDistributionModel();
                query.setChannelId(id);
                query.setBindingType(3);
                List<AgentProfitDistributionModel> dataList = new ArrayList<AgentProfitDistributionModel>();
                dataList = agentProfitDistributionService.queryAllList(query);
                for (int i = 0; i < dataList.size(); i++){
                    // 赋值平台码的费率
                    String pfGewayServiceCharge = "0.0000";
                    ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkQuery = new ChannelPlatformGewayCodeLinkModel();
                    channelPlatformGewayCodeLinkQuery.setChannelId(dataList.get(i).getChannelId());
                    channelPlatformGewayCodeLinkQuery.setPfGewayCodeId(dataList.get(i).getPfGewayCodeId());
                    ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkModel = channelPlatformGewayCodeLinkService.queryByCondition(channelPlatformGewayCodeLinkQuery);
                    if (channelPlatformGewayCodeLinkModel != null && channelPlatformGewayCodeLinkModel.getId() > 0){
                        if (!StringUtils.isBlank(channelPlatformGewayCodeLinkModel.getServiceCharge())){
                            dataList.get(i).setPfGewayServiceCharge(channelPlatformGewayCodeLinkModel.getServiceCharge());
                            pfGewayServiceCharge = channelPlatformGewayCodeLinkModel.getServiceCharge();
                        }else {
                            dataList.get(i).setPfGewayServiceCharge(pfGewayServiceCharge);
                        }
                    }else {
                        dataList.get(i).setPfGewayServiceCharge(pfGewayServiceCharge);
                    }

                    // 赋值-分润 -start
                    String splicing = "";
                    PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkQuery = new PrPlatformGewayCodeLinkModel();
                    prPlatformGewayCodeLinkQuery.setPfGewayCodeId(dataList.get(i).getPfGewayCodeId());
                    List<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkList = new ArrayList<>();
                    prPlatformGewayCodeLinkList = prPlatformGewayCodeLinkService.queryAllList(prPlatformGewayCodeLinkQuery);
                    for (PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkModel : prPlatformGewayCodeLinkList){
                        PrGewayCodeModel prGewayCodeQuery = new PrGewayCodeModel();
                        prGewayCodeQuery.setId(prPlatformGewayCodeLinkModel.getGewayCodeId());
                        PrGewayCodeModel prGewayCodeModel = prGewayCodeService.queryByCondition(prGewayCodeQuery);
                        if (prGewayCodeModel != null && prGewayCodeModel.getId() > 0){
                            String serviceCharge = "";
                            if (!StringUtils.isBlank(prGewayCodeModel.getUpServiceCharge())){
                                serviceCharge = prGewayCodeModel.getUpServiceCharge();
                            }else {
                                serviceCharge = "0.0000";
                            }
                            //
                            String ptAgent = StringUtil.getBigDecimalSubtractStr(pfGewayServiceCharge, serviceCharge);// 平台费率-通道费率
                            ptAgent = StringUtil.getBigDecimalSubtractStr(ptAgent, dataList.get(i).getServiceCharge());// 剩余费率-代理费率

                            splicing += "平台分润:" + ptAgent + "|" + prGewayCodeModel.getGewayName() + "|" + prGewayCodeModel.getGewayCodeName() + "|" + prGewayCodeModel.getGewayCode() + "|" + serviceCharge + "<br />";
                        }
                    }

                    if (!StringUtils.isBlank(splicing)){
                        dataList.get(i).setSplicing(splicing);
                    }else{
                        dataList.get(i).setSplicing("");
                    }

                    // 赋值-分润 -end
                }

                sendSuccessMessage(response, "", dataList);
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
     * 添加数据-添加两者针对的数据
     */
    @RequestMapping("/addDataByBindingType")
    public void addDataByBindingType(HttpServletRequest request, HttpServletResponse response, AgentProfitDistributionModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){


            List<AgentProfitDistributionModel> pfGwewayCodeServiceChargeList = new ArrayList<>();// 平台通道添加分润的集合
            // check数据是否有问题

            if (bean == null){
                sendFailureMessage(response,"请填写数据!");
                return;
            }

            if (bean.getBindingType() <= 0){
                sendFailureMessage(response,"请填写绑定的类型!");
                return;
            }else {
                if (bean.getBindingType() != 3){
                    sendFailureMessage(response,"绑定类型错误,请重试!");
                    return;
                }
            }

            if (StringUtils.isBlank(bean.getAgentArr())){
                sendFailureMessage(response,"请选择要分润的代理!");
                return;
            }

            if (bean.getChannelId() <= 0){
                sendFailureMessage(response,"渠道数据为空,请重试!");
                return;
            }

            if (StringUtils.isBlank(bean.getPfGewayServiceChargeArr())){
                sendFailureMessage(response,"请填写平台码的分润值!");
                return;
            }else {
                String [] pfGewayServiceChargeArr_fg_one = bean.getPfGewayServiceChargeArr().split("#");
                for (String str : pfGewayServiceChargeArr_fg_one){
                    String [] pfGewayServiceChargeArr_fg_two = str.split(",");
                    // check数据是否为空
                    if (!StringUtils.isBlank(pfGewayServiceChargeArr_fg_two[0]) && !StringUtils.isBlank(pfGewayServiceChargeArr_fg_two[1])){
                        // check分润值是否填写正确
                        if (!pfGewayServiceChargeArr_fg_two[1].matches("[+-]?[0-9]+(\\.[0-9]+)?")){
                            sendFailureMessage(response,"请输入正确的分润值!");
                            return;
                        }else {
                            if (Double.parseDouble(pfGewayServiceChargeArr_fg_two[1]) >= 1){
                                sendFailureMessage(response,"分润值不能大于等于1!");
                                return;
                            }
                        }
                        AgentProfitDistributionModel pfGwewayCodeServiceCharge = new AgentProfitDistributionModel();
                        pfGwewayCodeServiceCharge.setChannelId(bean.getChannelId());
                        pfGwewayCodeServiceCharge.setPfGewayCodeId(Long.parseLong(pfGewayServiceChargeArr_fg_two[0]));
                        pfGwewayCodeServiceCharge.setServiceCharge(pfGewayServiceChargeArr_fg_two[1]);
                        pfGwewayCodeServiceChargeList.add(pfGwewayCodeServiceCharge);
                    }else {
                        sendFailureMessage(response,"平台通道或者分润值为空,请重试!");
                        return;
                    }
                }
            }



            List<Long> agentIdList = new ArrayList<>();// 代理数据集合
            String [] agent_fg = bean.getAgentArr().split(",");
            for (String str : agent_fg){
                agentIdList.add(Long.parseLong(str));
            }

            // 组装分润：准备分润的数据（前端配置的A），预计要分出的分润（预计分出的B）=A*代理个数，之前已分配的分润
            for (int i = 0; i < pfGwewayCodeServiceChargeList.size(); i++){
                // 预计要分出的利润
                String pfGwewayCodeServiceCharge_cy = StringUtil.getMultiply(pfGwewayCodeServiceChargeList.get(i).getServiceCharge(), agentIdList.size() + "");
                pfGwewayCodeServiceChargeList.get(i).setExtraServiceCharge(pfGwewayCodeServiceCharge_cy);

                /*// 之前已分配的分润
                AgentProfitDistributionModel agentProfitDistributionByChannelPfGewayQuery = new AgentProfitDistributionModel();
                agentProfitDistributionByChannelPfGewayQuery.setChannelId(pfGwewayCodeServiceChargeList.get(i).getChannelId());
                agentProfitDistributionByChannelPfGewayQuery.setPfGewayCodeId(pfGwewayCodeServiceChargeList.get(i).getPfGewayCodeId());
                agentProfitDistributionByChannelPfGewayQuery.setBindingType(bean.getBindingType());
                AgentProfitDistributionModel agentProfitDistributionByChannelPfGewayData = agentProfitDistributionService.getSunServiceChargeByChannelAndPfGewayCode(agentProfitDistributionByChannelPfGewayQuery);
                if (agentProfitDistributionByChannelPfGewayData != null && !StringUtils.isBlank(agentProfitDistributionByChannelPfGewayData.getServiceCharge())){
                    pfGwewayCodeServiceChargeList.get(i).setPfGewayServiceCharge(agentProfitDistributionByChannelPfGewayData.getServiceCharge());// 之前已分配的利润
                }else {
                    pfGwewayCodeServiceChargeList.get(i).setPfGewayServiceCharge("0.0000");// 之前已分配的利润
                }*/

                // 根据平台通道查询次平台通道下价格最贵的一个通道的费率
                PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkQuery = new PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkQuery.setPfGewayCodeId(pfGwewayCodeServiceChargeList.get(i).getPfGewayCodeId());
                String up_service_charge = prPlatformGewayCodeLinkService.queryMaxUpServiceChargeByPfGewayCode(prPlatformGewayCodeLinkQuery);



                // 查询渠道对应的平台通道分配的具体费率
                ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkQuery = new ChannelPlatformGewayCodeLinkModel();
                channelPlatformGewayCodeLinkQuery.setChannelId(pfGwewayCodeServiceChargeList.get(i).getChannelId());
                channelPlatformGewayCodeLinkQuery.setPfGewayCodeId(pfGwewayCodeServiceChargeList.get(i).getPfGewayCodeId());
                ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkModel = channelPlatformGewayCodeLinkService.queryByCondition(channelPlatformGewayCodeLinkQuery);
                if (channelPlatformGewayCodeLinkModel != null && channelPlatformGewayCodeLinkModel.getId() > 0){
                    if (!StringUtils.isBlank(channelPlatformGewayCodeLinkModel.getServiceCharge())){
                        // 正式进行check是否亏本运营：已设置的费率-预计要分出的利润-之前已分配的分润-平台通道里面费率最高的通道的费率=利润（利润要为正数）
                        String profit = StringUtil.getBigDecimalSubtractStr(channelPlatformGewayCodeLinkModel.getServiceCharge(), pfGwewayCodeServiceChargeList.get(i).getExtraServiceCharge());//预计要分配出去的利润
//                        profit = StringUtil.getBigDecimalSubtractStr(profit, pfGwewayCodeServiceChargeList.get(i).getPfGewayServiceCharge());//之前已分配的利润
                        profit = StringUtil.getBigDecimalSubtractStr(profit, up_service_charge);// 最贵的通道码价格
                        if (Double.parseDouble(profit) <= 0){
                            sendFailureMessage(response,"渠道：" + channelPlatformGewayCodeLinkModel.getChannelName() + ", 通道：" + channelPlatformGewayCodeLinkModel.getCodeName() + ", 费率：" + channelPlatformGewayCodeLinkModel.getServiceCharge()
                            + "，配置代理利润之后属于亏本运营,请您核实!");
                            return;
                        }

                    }else{
                        sendFailureMessage(response,"渠道与平台通道关联关系中没有设置费率,请先设置费率!");
                        return;
                    }
                }else {
                    sendFailureMessage(response,"渠道与平台通道关联关系中没有关联记录,请核实!");
                    return;
                }


            }

            for (long agentId : agentIdList){
                // 查询代理的类型
                AgentModel agentQuery = new AgentModel();
                agentQuery.setId(agentId);
                AgentModel agentModel = agentService.queryByCondition(agentQuery);
                if (agentModel == null || agentModel.getId() <= 0){
                    sendFailureMessage(response,"代理信息查询为空!");
                    return;
                }

                if (agentModel.getAgentType() != 3){
                    sendFailureMessage(response,"代理:" + agentModel.getAgentName() + ", 只允许添加两者针对的分润!");
                    return;
                }

                for (AgentProfitDistributionModel pfGwewayCodeServiceCharge : pfGwewayCodeServiceChargeList){
                    // check是否有重复记录
                    AgentProfitDistributionModel agentProfitDistributionQuery = new AgentProfitDistributionModel();
                    agentProfitDistributionQuery.setAgentId(agentId);
                    agentProfitDistributionQuery.setChannelId(bean.getChannelId());
                    agentProfitDistributionQuery.setPfGewayCodeId(pfGwewayCodeServiceCharge.getPfGewayCodeId());
                    agentProfitDistributionQuery.setBindingType(bean.getBindingType());

                    AgentProfitDistributionModel agentProfitDistributionModel = agentProfitDistributionService.queryByCondition(agentProfitDistributionQuery);
                    if (agentProfitDistributionModel != null && agentProfitDistributionModel.getId() > 0){
                        sendFailureMessage(response,"代理：" + agentModel.getAgentName()+ "，平台通道：" + agentProfitDistributionModel.getCodeName() +"， 有重复纪录,请剔除此代理!");
                        return;
                    }
                }


            }


            for (long agentId : agentIdList){
                if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){

                    // 正式添加分润数据
                    for (AgentProfitDistributionModel pfGwewayCodeServiceCharge : pfGwewayCodeServiceChargeList){
                        AgentProfitDistributionModel agentProfitDistributionAdd = new AgentProfitDistributionModel();
                        agentProfitDistributionAdd.setAgentId(agentId);
                        agentProfitDistributionAdd.setChannelId(pfGwewayCodeServiceCharge.getChannelId());
                        agentProfitDistributionAdd.setPfGewayCodeId(pfGwewayCodeServiceCharge.getPfGewayCodeId());
                        agentProfitDistributionAdd.setServiceCharge(pfGwewayCodeServiceCharge.getServiceCharge());
                        agentProfitDistributionAdd.setBindingType(bean.getBindingType());
                        agentProfitDistributionService.add(agentProfitDistributionAdd);
                    }

                }else {
                    sendFailureMessage(response,"您无权操作!");
                    return;
                }
            }


            sendSuccessMessage(response, "保存成功~");
            return;

        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
            return;
        }
    }





    /**
     * 修改数据-两者针对的
     */
    @RequestMapping("/updateByBindingType")
    public void updateByBindingType(HttpServletRequest request, HttpServletResponse response,AgentProfitDistributionModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if (bean.getId() <= 0){
                    sendFailureMessage(response,"错误,请重试!");
                    return;
                }
                if (StringUtils.isBlank(bean.getServiceCharge())){
                    sendFailureMessage(response,"请填写分润值!");
                    return;
                }else{
                    if (!bean.getServiceCharge().matches("[+-]?[0-9]+(\\.[0-9]+)?")){
                        sendFailureMessage(response,"请输入正确的分润值!");
                        return;
                    }else {
                        if (Double.parseDouble(bean.getServiceCharge()) >= 1){
                            log.info("2");
                            sendFailureMessage(response,"分润值不能大于等于1!");
                            return;
                        }
                    }
                }

                // check是否属于亏本运营

                // 根据平台通道查询次平台通道下价格最贵的一个通道的费率
                PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkQuery = new PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkQuery.setPfGewayCodeId(bean.getPfGewayCodeId());
                String up_service_charge = prPlatformGewayCodeLinkService.queryMaxUpServiceChargeByPfGewayCode(prPlatformGewayCodeLinkQuery);



                // 查询渠道对应的平台通道分配的具体费率
                ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkQuery = new ChannelPlatformGewayCodeLinkModel();
                channelPlatformGewayCodeLinkQuery.setChannelId(bean.getChannelId());
                channelPlatformGewayCodeLinkQuery.setPfGewayCodeId(bean.getPfGewayCodeId());
                ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkModel = channelPlatformGewayCodeLinkService.queryByCondition(channelPlatformGewayCodeLinkQuery);
                if (channelPlatformGewayCodeLinkModel != null && channelPlatformGewayCodeLinkModel.getId() > 0){
                    if (!StringUtils.isBlank(channelPlatformGewayCodeLinkModel.getServiceCharge())){
                        // 正式进行check是否亏本运营：
                        String profit = StringUtil.getBigDecimalSubtractStr(channelPlatformGewayCodeLinkModel.getServiceCharge(), bean.getServiceCharge());//预计要分配出去的利润
                        profit = StringUtil.getBigDecimalSubtractStr(profit, up_service_charge);// 最贵的通道码价格
                        if (Double.parseDouble(profit) <= 0){
                            sendFailureMessage(response,"渠道：" + channelPlatformGewayCodeLinkModel.getChannelName() + ", 通道：" + channelPlatformGewayCodeLinkModel.getCodeName() + ", 费率：" + channelPlatformGewayCodeLinkModel.getServiceCharge()
                                    + "，配置代理利润之后属于亏本运营,请您核实!");
                            return;
                        }

                    }else{
                        sendFailureMessage(response,"渠道与平台通道关联关系中没有设置费率,请先设置费率!");
                        return;
                    }
                }else {
                    sendFailureMessage(response,"渠道与平台通道关联关系中没有关联记录,请联系技术!");
                    return;
                }

                agentProfitDistributionService.updateServiceCharge(bean);
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



}
