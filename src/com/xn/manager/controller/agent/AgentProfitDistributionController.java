package com.xn.manager.controller.agent;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.agent.AgentProfitDistributionModel;
import com.xn.manager.service.AgentProfitDistributionService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
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
 * @Description: 代理利益分配的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/agentprofitdistribution")
public class AgentProfitDistributionController extends BaseController {
    private static Logger log = Logger.getLogger(AgentProfitDistributionController.class);

    @Autowired
    private AgentProfitDistributionService<AgentProfitDistributionModel> agentProfitDistributionService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/agentprofitdistribution/agentprofitdistributionIndex";
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
                if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_AGENT){
                    model.setAgentId(account.getId());
                }else {
                    HtmlUtil.writerJson(response, model.getPage(), dataList);
                    return;
                }
            }
            dataList = agentProfitDistributionService.queryByList(model);
            for (int i = 0; i < dataList.size(); i++){
                if (dataList.get(i).getAgentType() == 1){
                    dataList.get(i).setGewayCodeName("xx通道");
                }
                if (dataList.get(i).getAgentType() == 2){
                    dataList.get(i).setChannelName("xx渠道");
                    dataList.get(i).setCodeName("xx平台");
                }

                if (dataList.get(i).getAgentType() == 3){
                    dataList.get(i).setGewayCodeName("xx通道");
                    if (StringUtils.isBlank(dataList.get(i).getChannelName())){
                        dataList.get(i).setChannelName("");
                    }
                    if (StringUtils.isBlank(dataList.get(i).getGewayCodeName())){
                        dataList.get(i).setGewayCodeName("");
                    }
                }
                dataList.get(i).setAlias("");
            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }




}
