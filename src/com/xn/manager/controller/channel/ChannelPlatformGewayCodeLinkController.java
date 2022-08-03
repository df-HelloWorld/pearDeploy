package com.xn.manager.controller.channel;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.PrGewayCodeModel;
import com.xn.manager.model.PrPlatformGewayCodeLinkModel;
import com.xn.manager.model.PrPlatformGewayCodeModel;
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
import java.util.List;

/**
 * @ClassName:
 * @Description: 管理员：渠道与平台通道码关联的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/channelplatformgewaycodelink")
public class ChannelPlatformGewayCodeLinkController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelPlatformGewayCodeLinkController.class);

    @Autowired
    private ChannelPlatformGewayCodeLinkService<ChannelPlatformGewayCodeLinkModel> channelPlatformGewayCodeLinkService;

    @Autowired
    private ChannelService<ChannelModel> channelService;

    @Autowired
    private PrPlatformGewayCodeService<PrPlatformGewayCodeModel> prPlatformGewayCodeService;

    @Autowired
    private PrGewayCodeService<PrGewayCodeModel> prGewayCodeService;

    @Autowired
    private PrPlatformGewayCodeLinkService<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelplatformgewaycodelink/channelplatformgewaycodelinkIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelPlatformGewayCodeLinkModel model) throws Exception {
        List<ChannelPlatformGewayCodeLinkModel> dataList = new ArrayList<ChannelPlatformGewayCodeLinkModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
//                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            dataList = channelPlatformGewayCodeLinkService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ChannelPlatformGewayCodeLinkModel model) throws Exception {
        List<ChannelPlatformGewayCodeLinkModel> dataList = new ArrayList<ChannelPlatformGewayCodeLinkModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
//                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            dataList = channelPlatformGewayCodeLinkService.queryAllList(model);
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
                model.addAttribute("channel", channelService.queryAllList(new ChannelModel()));
                model.addAttribute("pfGeway", prPlatformGewayCodeService.queryAllList(new PrPlatformGewayCodeModel()));
            }else{
                sendFailureMessage(response,"您无权操作!");
                return null;
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/channelplatformgewaycodelink/channelplatformgewaycodelinkAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChannelPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                // check是否有重复纪录
                ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkQuery = new ChannelPlatformGewayCodeLinkModel();
                channelPlatformGewayCodeLinkQuery.setChannelId(bean.getChannelId());
                channelPlatformGewayCodeLinkQuery.setPfGewayCodeId(bean.getPfGewayCodeId());
                ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkModel = channelPlatformGewayCodeLinkService.queryByCondition(channelPlatformGewayCodeLinkQuery);
                if (channelPlatformGewayCodeLinkModel != null && channelPlatformGewayCodeLinkModel.getId() > 0){
                    sendFailureMessage(response,"有重复纪录,请重新选择关联!");
                    return;
                }

                channelPlatformGewayCodeLinkService.add(bean);
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
        ChannelPlatformGewayCodeLinkModel atModel = new ChannelPlatformGewayCodeLinkModel();
        atModel.setId(id);
        model.addAttribute("account", channelPlatformGewayCodeLinkService.queryById(atModel));
        model.addAttribute("channel", channelService.queryAllList());
        model.addAttribute("pfGeway", prPlatformGewayCodeService.queryAllList());
        return "manager/channelplatformgewaycodelink/channelplatformgewaycodelinkEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,ChannelPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){

                // check是否有重复纪录
                ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkQuery = new ChannelPlatformGewayCodeLinkModel();
                List<Long> idList = new ArrayList<>();
                idList.add(bean.getId());
                channelPlatformGewayCodeLinkQuery.setIdList(idList);
                channelPlatformGewayCodeLinkQuery.setChannelId(bean.getChannelId());
                channelPlatformGewayCodeLinkQuery.setPfGewayCodeId(bean.getPfGewayCodeId());
                ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkModel = channelPlatformGewayCodeLinkService.queryByCondition(channelPlatformGewayCodeLinkQuery);
                if (channelPlatformGewayCodeLinkModel != null && channelPlatformGewayCodeLinkModel.getId() > 0){
                    sendFailureMessage(response,"有重复纪录,请重新选择关联!");
                    return;
                }


                channelPlatformGewayCodeLinkService.update(bean);
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

    @RequestMapping("/updateServiceCharge")
    public void updateServiceCharge(HttpServletRequest request, HttpServletResponse response,ChannelPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                // check判断费率格式
                if (StringUtils.isBlank(bean.getServiceCharge())){
                    sendFailureMessage(response,"请填写费率值!");
                    return;
                }else{
                    if (!bean.getServiceCharge().matches("[+-]?[0-9]+(\\.[0-9]+)?")){
                        sendFailureMessage(response,"请输入正确的费率值!");
                        return;
                    }else {
                        if (Double.parseDouble(bean.getServiceCharge()) >= 1){
                            sendFailureMessage(response,"费率值不能大于等于1!");
                            return;
                        }
                    }
                }

                // check判断是否是亏本运营
                // 根据平台通道查询次平台通道下价格最贵的一个通道的费率
                PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkQuery = new PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkQuery.setPfGewayCodeId(bean.getPfGewayCodeId());
                String up_service_charge = prPlatformGewayCodeLinkService.queryMaxUpServiceChargeByPfGewayCode(prPlatformGewayCodeLinkQuery);
                if (Double.parseDouble(bean.getServiceCharge()) <= Double.parseDouble(up_service_charge)){
                    sendFailureMessage(response,"配置次费率属于亏本运营，此通道包含最贵的一条通道的成本价格是：" + up_service_charge + "，请您重新配置费率！");
                    return;
                }


                channelPlatformGewayCodeLinkService.update(bean);
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
    public void delete(HttpServletRequest request, HttpServletResponse response, ChannelPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                channelPlatformGewayCodeLinkService.manyOperation(bean);
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
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ChannelPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                channelPlatformGewayCodeLinkService.manyOperation(bean);
                sendSuccessMessage(response, "状态更新成功");
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
     *
     * 获取渠道与平台通道码的详情
     * <p>
     *     这里包含平台通道码旗下的通道信息
     * </p>
     */
    @RequestMapping("/dataAllInfoList")
    public void dataAllInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ChannelPlatformGewayCodeLinkModel> dataList = new ArrayList<ChannelPlatformGewayCodeLinkModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
//                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
                HtmlUtil.writerJson(response, dataList);
                return;
            }
            dataList = channelPlatformGewayCodeLinkService.queryAllList();
            for (int i = 0; i < dataList.size(); i++){
                String splicing = "";
                PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkQuery = new PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkQuery.setPfGewayCodeId(dataList.get(i).getPfGewayCodeId());
                List<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkList = new ArrayList<>();
                prPlatformGewayCodeLinkList = prPlatformGewayCodeLinkService.queryAllList(prPlatformGewayCodeLinkQuery);
                for (PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkModel : prPlatformGewayCodeLinkList){
                    PrGewayCodeModel query = new PrGewayCodeModel();
                    query.setId(prPlatformGewayCodeLinkModel.getGewayCodeId());
                    PrGewayCodeModel prGewayCodeModel = prGewayCodeService.queryByCondition(query);
                    if (prGewayCodeModel != null && prGewayCodeModel.getId() > 0){
                        String serviceCharge = "";
                        if (!StringUtils.isBlank(prGewayCodeModel.getUpServiceCharge())){
                            serviceCharge = prGewayCodeModel.getUpServiceCharge();
                        }else {
                            serviceCharge = "0.0000";
                        }
                        splicing += prGewayCodeModel.getGewayName() + "|" + prGewayCodeModel.getGewayCodeName() + "|" + prGewayCodeModel.getGewayCode() + "|" + serviceCharge + "<br />";
                    }
                }

                if (!StringUtils.isBlank(splicing)){
                    dataList.get(i).setSplicing(splicing);
                }else{
                    dataList.get(i).setSplicing("");
                }



            }
        }
        HtmlUtil.writerJson(response, dataList);
//        sendSuccessMessage(response, "", dataList);
    }




    /**
     *
     * 获取渠道与平台通道码的详情
     * <p>
     *     这里包含平台通道码旗下的通道信息
     * </p>
     */
    @RequestMapping("/dataAllInfoJsonList")
    public void dataAllInfoJsonList(HttpServletRequest request, HttpServletResponse response, ChannelPlatformGewayCodeLinkModel bean) throws Exception {
        List<ChannelPlatformGewayCodeLinkModel> dataList = new ArrayList<ChannelPlatformGewayCodeLinkModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
//                HtmlUtil.writerJson(response, dataList);
                return;
            }
            ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkQuery = new ChannelPlatformGewayCodeLinkModel();
            channelPlatformGewayCodeLinkQuery.setChannelId(bean.getChannelId());
            dataList = channelPlatformGewayCodeLinkService.queryAllList(channelPlatformGewayCodeLinkQuery);
            for (int i = 0; i < dataList.size(); i++){
                String splicing = "";
                PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkQuery = new PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkQuery.setPfGewayCodeId(dataList.get(i).getPfGewayCodeId());
                List<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkList = new ArrayList<>();
                prPlatformGewayCodeLinkList = prPlatformGewayCodeLinkService.queryAllList(prPlatformGewayCodeLinkQuery);
                for (PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkModel : prPlatformGewayCodeLinkList){
                    PrGewayCodeModel query = new PrGewayCodeModel();
                    query.setId(prPlatformGewayCodeLinkModel.getGewayCodeId());
                    PrGewayCodeModel prGewayCodeModel = prGewayCodeService.queryByCondition(query);
                    if (prGewayCodeModel != null && prGewayCodeModel.getId() > 0){
                        String serviceCharge = "";
                        if (!StringUtils.isBlank(prGewayCodeModel.getUpServiceCharge())){
                            serviceCharge = prGewayCodeModel.getUpServiceCharge();
                        }else {
                            serviceCharge = "0.0000";
                        }
                        splicing += prGewayCodeModel.getGewayName() + "|" + prGewayCodeModel.getGewayCodeName() + "|" + prGewayCodeModel.getGewayCode() + "|" + serviceCharge + "<br />";
                    }
                }

                if (!StringUtils.isBlank(splicing)){
                    dataList.get(i).setSplicing(splicing);
                }else{
                    dataList.get(i).setSplicing("");
                }



            }
        }
        sendSuccessMessage(response, "", dataList);
        return;
    }





}
