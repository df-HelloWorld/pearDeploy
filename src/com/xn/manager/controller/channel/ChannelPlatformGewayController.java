package com.xn.manager.controller.channel;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.PrPlatformGewayCodeModel;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.model.channel.ChannelPlatformGewayCodeLinkModel;
import com.xn.manager.model.channel.vo.ChannelPlatformGewayCodeLinkVo;
import com.xn.manager.service.ChannelPlatformGewayCodeLinkService;
import com.xn.manager.service.ChannelService;
import com.xn.manager.service.PrPlatformGewayCodeService;
import com.xn.system.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description: 渠道：渠道与平台通道码关联的Controller层
 * <p>
 *     渠道查看可使用的通道码
 * </p>
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/channelplatformgeway")
public class ChannelPlatformGewayController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelPlatformGewayController.class);

    @Autowired
    private ChannelPlatformGewayCodeLinkService<ChannelPlatformGewayCodeLinkModel> channelPlatformGewayCodeLinkService;

    @Autowired
    private ChannelService<ChannelModel> channelService;

    @Autowired
    private PrPlatformGewayCodeService<PrPlatformGewayCodeModel> prPlatformGewayCodeService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelplatformgeway/channelplatformgewayIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelPlatformGewayCodeLinkModel model) throws Exception {
        List<ChannelPlatformGewayCodeLinkVo> dataList = new ArrayList<ChannelPlatformGewayCodeLinkVo>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                model.setChannelId(account.getId());
            }
            List<ChannelPlatformGewayCodeLinkModel> resList = new ArrayList<ChannelPlatformGewayCodeLinkModel>();
            resList = channelPlatformGewayCodeLinkService.queryByList(model);
            if (resList != null && resList.size() > 0){
                dataList = BeanUtils.copyList(resList, ChannelPlatformGewayCodeLinkVo.class);
            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }




}
