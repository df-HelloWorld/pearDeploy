package com.xn.manager.controller.channel;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.enums.ManagerEnum;
import com.xn.common.util.DateUtil;
import com.xn.common.util.GoogleAuthenticator;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.manager.model.PrPlatformGewayCodeLinkModel;
import com.xn.manager.model.PrPlatformGewayCodeModel;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.model.channel.ChannelPlatformGewayCodeLinkModel;
import com.xn.manager.service.ChannelPlatformGewayCodeLinkService;
import com.xn.manager.service.ChannelService;
import com.xn.manager.service.PrPlatformGewayCodeLinkService;
import com.xn.manager.service.PrPlatformGewayCodeService;
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
 * @Description: 渠道的Controller层-admin管理员使用的
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/adminchannel")
public class AdminChannelController extends BaseController {

    private static Logger log = Logger.getLogger(AdminChannelController.class);

    @Autowired
    private ChannelService<ChannelModel> channelService;

    @Autowired
    PrPlatformGewayCodeService<PrPlatformGewayCodeModel> prPlatformGewayCodeService;

    @Autowired
    PrPlatformGewayCodeLinkService<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkService;


    @Autowired
    ChannelPlatformGewayCodeLinkService<ChannelPlatformGewayCodeLinkModel> channelPlatformGewayCodeLinkService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/adminchannel/adminchannelIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelModel model) throws Exception {
        List<ChannelModel> dataList = new ArrayList<ChannelModel>();
        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = channelService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ChannelModel model) throws Exception {
        List<ChannelModel> dataList = new ArrayList<ChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = channelService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                sendFailureMessage(response,"只允许管理员操作!");
            }
//            else {
//                model.addAttribute("agent", channelService.queryAllList());
//            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/adminchannel/adminchannelAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);

        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //check是否有重复的账号
                ChannelModel queryBean = new ChannelModel();
                queryBean.setAccountNum(bean.getAccountNum());
                queryBean = channelService.queryByCondition(queryBean);
                List<PrPlatformGewayCodeModel> list=prPlatformGewayCodeService.queryAllList();
                if (queryBean != null && queryBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                    sendFailureMessage(response,"有重复的账号,请重新输入其它账号!");
                }else{
                    bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
                    bean.setRoleId(ManagerEnum.RoleTypeEnum.TP.getRoleType());
                    bean.setSecretKey(MD5.parseMD5(bean.getAccountNum() + DateUtil.getNowLongTime()));
                    bean.setGoogleKey(GoogleAuthenticator.generateSecretKey());
                    channelService.add(bean);
                    Long    channerlId =   bean.getId();

                    for(PrPlatformGewayCodeModel  prPlatformGewayCodeModel:list){
                        ChannelPlatformGewayCodeLinkModel query =new ChannelPlatformGewayCodeLinkModel();
                        query.setChannelId(channerlId);
                        query.setPfGewayCodeId(prPlatformGewayCodeModel.getId());
                        ChannelPlatformGewayCodeLinkModel pf = channelPlatformGewayCodeLinkService.queryByCondition(query);
                        if (pf != null && pf.getId() > 0){
                             continue;
//                            sendFailureMessage(response,"平台通道码有重复,请重新换一个!");
//                            return;
                        }

                        channelPlatformGewayCodeLinkService.add(query);
                    }

//                if (bean.getAgentId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//                    AgentChannelModel agentChannelModel = new AgentChannelModel();
//                    agentChannelModel.setAgentId(bean.getAgentId());
//                    agentChannelModel.setChannelId(bean.getId());
//                    agentChannelService.add(agentChannelModel);
//                }
                    sendSuccessMessage(response, "保存成功~");
                }
            }else {
                sendFailureMessage(response,"您没权限操作!");
            }

        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id, Integer op) {
        ChannelModel atModel = new ChannelModel();
        atModel.setId(id);
        model.addAttribute("account", channelService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/adminchannel/adminchannelEdit";
    }


    @RequestMapping("/jumpServiceCharge")
    public String jumpServiceCharge(Model model, long id) {
        ChannelModel atModel = new ChannelModel();
        atModel.setId(id);
        model.addAttribute("channelId", id);
        return "manager/adminchannel/adminchannelservicechargeEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,ChannelModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if ("2".equals(op)) {
                    bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
                }
                channelService.update(bean);
                sendSuccessMessage(response, "保存成功~");
                return;
            }else {
                sendFailureMessage(response, "您无权操作!");
                log.info("");
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
    public void delete(HttpServletRequest request, HttpServletResponse response, ChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                channelService.delete(bean);
                sendSuccessMessage(response, "删除成功");
                return;
            }else {
                log.info("6");
                sendFailureMessage(response, "您无权操作!");
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
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                channelService.manyOperation(bean);
                sendSuccessMessage(response, "状态更新成功");
                return;
            }else {
                log.info("6");
                sendFailureMessage(response, "您无权操作!");
                return;
            }

        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }
    }


    /**
     *
     * 获取汇总数据
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, ChannelModel bean) throws Exception {
        ChannelModel data = new ChannelModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            data = channelService.getTotalData(bean);
        }
        HtmlUtil.writerJson(response, data);
    }
}
