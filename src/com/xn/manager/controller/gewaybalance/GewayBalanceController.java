package com.xn.manager.controller.gewaybalance;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.enums.ManagerEnum;
import com.xn.common.util.DateUtil;
import com.xn.common.util.GoogleAuthenticator;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.manager.model.GewayBalanceModel;
import com.xn.manager.model.PrGewayModel;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.service.ChannelService;
import com.xn.manager.service.GewayBalanceService;
import com.xn.manager.service.PrGewayService;
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
@RequestMapping("/gewaybalance")
public class GewayBalanceController extends BaseController {

    private static Logger log = Logger.getLogger(GewayBalanceController.class);

    @Autowired
    private GewayBalanceService<GewayBalanceModel> gewayBalanceService;

    @Autowired
    private PrGewayService<PrGewayModel> prGewayService;

    @Autowired
    private ChannelService<ChannelModel> channelService;
    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/gewaybalance/gewaybalanceIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, GewayBalanceModel model) throws Exception {
        List<GewayBalanceModel> dataList = new ArrayList<GewayBalanceModel>();
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = gewayBalanceService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, GewayBalanceModel model) throws Exception {
        List<GewayBalanceModel> dataList = new ArrayList<GewayBalanceModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = gewayBalanceService.queryAllList(model);
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
            model.addAttribute("gewayList",prGewayService.queryAllList(new PrGewayModel()));

        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/gewaybalance/gewaybalanceAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, GewayBalanceModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //check是否有重复的账号
                GewayBalanceModel query =new GewayBalanceModel();
                query.setGewayId(bean.getGewayId());
                GewayBalanceModel  rs =gewayBalanceService.queryByCondition(query);
                if(rs!=null){
                    sendFailureMessage(response, "该通道已被添加，不能进行添加！");
                }else{
                    gewayBalanceService.add(bean);
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
        GewayBalanceModel atModel = new GewayBalanceModel();
        atModel.setId(id);

        model.addAttribute("gewayId", id);
        model.addAttribute("geway", prGewayService.queryAllList(new PrGewayModel()));
        model.addAttribute("channel", channelService.queryAllList(new ChannelModel()));
        return "manager/gewaybalance/gewaybalanceEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,GewayBalanceModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                gewayBalanceService.update(bean);
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
    public void delete(HttpServletRequest request, HttpServletResponse response, GewayBalanceModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                gewayBalanceService.delete(bean);
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
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, GewayBalanceModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                gewayBalanceService.manyOperation(bean);
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
}
