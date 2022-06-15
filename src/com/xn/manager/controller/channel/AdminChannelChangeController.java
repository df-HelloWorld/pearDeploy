package com.xn.manager.controller.channel;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.channel.ChannelChangeModel;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.service.ChannelChangeService;
import com.xn.manager.service.ChannelService;
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
import java.util.Date;
import java.util.List;

/**
 * @ClassName:
 * @Description: 管理员-渠道金额变更纪录的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/adminchannelchange")
public class AdminChannelChangeController extends BaseController {
    private static Logger log = Logger.getLogger(AdminChannelChangeController.class);

    @Autowired
    private ChannelChangeService<ChannelChangeModel> channelChangeService;

    @Autowired
    private ChannelService<ChannelModel> channelService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/adminchannelchange/adminchannelchangeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelChangeModel model) throws Exception {
        List<ChannelChangeModel> dataList = new ArrayList<ChannelChangeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                    model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                    model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
                }
                dataList = channelChangeService.queryByList(model);
            }else{
                //不是管理员，只能查询自己的数据
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("tp", channelService.queryAllList(new ChannelModel()));
        return "manager/adminchannelchange/adminchannelchangeAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChannelChangeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                sendFailureMessage(response, "您无权操作!~");
                return;
            }
            bean.setCreateRoleId(account.getRoleId());
            bean.setCreateUserId(account.getId());
            bean.setCurday(DateUtil.getDayNumber(new Date()));
            bean.setCurhour(DateUtil.getHour(new Date()));
            bean.setCurminute(DateUtil.getCurminute(new Date()));
            channelChangeService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }



}
