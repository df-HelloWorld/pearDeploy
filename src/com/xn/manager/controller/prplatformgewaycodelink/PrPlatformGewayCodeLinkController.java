package com.xn.manager.controller.prplatformgewaycodelink;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.*;
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
 * @Description 平台通道码的Controller层
 * @Author yoko
 * @Date 2020/9/18 19:28
 * @Version 1.0
 */
@Controller
@RequestMapping("/prplatformgewaycodelink")
public class PrPlatformGewayCodeLinkController extends BaseController {

    private static Logger log = Logger.getLogger(PrPlatformGewayCodeLinkController.class);

    @Autowired
    private PrPlatformGewayCodeLinkService<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkService;

    @Autowired
    private PrPlatformGewayCodeService<PrPlatformGewayCodeModel> prPlatformGewayCodeService;

    @Autowired
    private PrGewayCodeService<PrGewayCodeModel> prGewayCodeService;

    @Autowired
    private PrGewayService<PrGewayModel> prGewayService;

    @Autowired
    private ChannelPlatformGewayCodeLinkService<ChannelPlatformGewayCodeLinkModel> channelPlatformGewayCodeLinkService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/prplatformgewaycodelink/prplatformgewaycodelinkIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel model) throws Exception {
        List<PrPlatformGewayCodeLinkModel> dataList = new ArrayList<PrPlatformGewayCodeLinkModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = prPlatformGewayCodeLinkService.queryByList(model);

        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel model) throws Exception {
        List<PrPlatformGewayCodeLinkModel> dataList = new ArrayList<PrPlatformGewayCodeLinkModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = prPlatformGewayCodeLinkService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response,Model model) {
//        model.addAttribute("geway", prPlatformGewayCodeService.queryAllList());
        return "manager/prplatformgewaycodelink/prplatformgewaycodelinkAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                prPlatformGewayCodeLinkService.add(bean);
                sendSuccessMessage(response, "保存成功~");
                return;
            }else {
                log.info("11");
                sendFailureMessage(response,"您无权操作!");
                return;
            }

        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
            return;
        }
    }


    @RequestMapping("/addLink")
    public void addLink(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if (bean == null || bean.getPfGewayCodeId() <= 0){
                    sendFailureMessage(response, "错误,请您重试!");
                    return;
                }
                if(bean.getRelationType().equals("")){
                    sendFailureMessage(response, "请选择数据进行添加!");
                    return;
                }else{
                    String [] relationType = bean.getRelationType().split(",");
                    if (relationType == null ){
                        sendFailureMessage(response, "请选择通道进行添加!");
                        return;
                    }

                    // 查询选择的通道当中上游费率最大的费率值（最大成本）
                    PrGewayCodeModel prGewayCodeQuery = PublicMethod.assemblePrGewayCodeQueryByIdList(relationType);
                    String maxUpServiceCharge = prGewayCodeService.getMaxUpServiceCharge(prGewayCodeQuery);
                    if (!StringUtils.isBlank(maxUpServiceCharge)){
                        if (Double.parseDouble(maxUpServiceCharge) <= 0){
                            sendFailureMessage(response, "被选中的通道码的上游费率小于0，请您重新配置通道码的上游费率!");
                            return;
                        }
                    }else {
                        sendFailureMessage(response, "被选中的通道码的上游费率为空,请您先配置通道码的上游费率!");
                        return;
                    }

//                    // 查询被选中的通道码是否亏本运营的数据集合
//                    ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkQuery = PublicMethod.assembleChannelPlatformGewayCodeLinkQueryByServiceCharge(bean.getPfGewayCodeId(), maxUpServiceCharge);
//                    List<ChannelPlatformGewayCodeLinkModel> channelPlatformGewayCodeLinkList = channelPlatformGewayCodeLinkService.getServiceChargeDeficitList(channelPlatformGewayCodeLinkQuery);
//                    if (channelPlatformGewayCodeLinkList != null && channelPlatformGewayCodeLinkList.size() > 0){
//                        // 被选中的通道码会导致有亏本运营的数据存在
//                        String errMsg = "";
//                        for (ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkModel : channelPlatformGewayCodeLinkList){
//                            errMsg += "《 渠道：" + channelPlatformGewayCodeLinkModel.getChannelName() + "， 平台通道：" + channelPlatformGewayCodeLinkModel.getCodeName()
//                                    + "， 渠道费率：" + channelPlatformGewayCodeLinkModel.getServiceCharge() + " 》 <br>";
//                        }
//
//                        errMsg += "被选中的里面最大的上游费率《"+ maxUpServiceCharge + "》；被选中会导致以上渠道跑量会亏本运营，请您重新配置以上渠道与平台通道的费率，再来绑定新通道码！";
//
//                        sendFailureMessage(response, errMsg);
//                        return;
//
//                    }


//                    PrPlatformGewayCodeLinkModel  query =new  PrPlatformGewayCodeLinkModel();
//                    query.setPfGewayCodeId(bean.getPfGewayCodeId());

                    for(int i=0;i<relationType.length;i++){
                        PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkModel =new PrPlatformGewayCodeLinkModel();
                        prPlatformGewayCodeLinkModel.setPfGewayCodeId(bean.getPfGewayCodeId());
                        prPlatformGewayCodeLinkModel.setGewayCodeId(Long.parseLong(relationType[i]));
                        PrPlatformGewayCodeLinkModel  queryByCondition=prPlatformGewayCodeLinkService.queryByCondition(prPlatformGewayCodeLinkModel);
                        prPlatformGewayCodeLinkModel.setPfGewayCodeId(bean.getPfGewayCodeId());
                        if(queryByCondition==null){
                            prPlatformGewayCodeLinkService.add(prPlatformGewayCodeLinkModel);
                        }
                    }
                }
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


    @RequestMapping("/addGewayCodeLink")
    public void addGewayCodeLink(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if(bean.getRelationType().equals("")){
                    sendSuccessMessage(response, "请选择数据进行添加!");
                }else{
                    String [] relationType=bean.getRelationType().split(",");

                    PrPlatformGewayCodeLinkModel  query =new  PrPlatformGewayCodeLinkModel();
                    query.setRelationTypeId(bean.getRelationTypeId());

                    Long   ptGewayCodeId=null;
                    List<PrPlatformGewayCodeLinkModel> cbeanList=prPlatformGewayCodeLinkService.queryPfGewayCodeId(query);
                    if(cbeanList.size()!=0){
                        ptGewayCodeId=cbeanList.get(0).getPfGewayCodeId();
                    }

                    for(int i=0;i<relationType.length;i++){
                        PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkModel =new PrPlatformGewayCodeLinkModel();
                        prPlatformGewayCodeLinkModel.setRelationTypeId(bean.getRelationTypeId());
                        prPlatformGewayCodeLinkModel.setGewayCodeId(Long.parseLong(relationType[i]));
                        PrPlatformGewayCodeLinkModel  queryByCondition=prPlatformGewayCodeLinkService.queryByCondition(prPlatformGewayCodeLinkModel);
                        prPlatformGewayCodeLinkModel.setPfGewayCodeId(ptGewayCodeId);
                        if(queryByCondition==null){
                            prPlatformGewayCodeLinkService.add(prPlatformGewayCodeLinkModel);
                        }

                    }
                }
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
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        PrPlatformGewayCodeLinkModel atModel = new PrPlatformGewayCodeLinkModel();
        atModel.setId(id);
//        model.addAttribute("geway", prGewayService.queryAllList());
        model.addAttribute("account", prPlatformGewayCodeLinkService.queryById(atModel));
        return "manager/prplatformgewaycodelink/prplatformgewaycodelinkEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                prPlatformGewayCodeLinkService.update(bean);
                sendSuccessMessage(response, "保存成功~");
                return;
            }else {
                log.info("10");
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
    public void delete(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                prPlatformGewayCodeLinkService.delete(bean);
                sendSuccessMessage(response, "删除成功");
                return;
            }else {
                sendFailureMessage(response,"您无权操作!");
                return;
            }
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                prPlatformGewayCodeLinkService.manyOperation(bean);
                sendSuccessMessage(response, "状态更新成功");
                return;
            }else {
                log.info("11");
                sendFailureMessage(response,"您无权操作!");
                return;
            }
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }
    }
}
