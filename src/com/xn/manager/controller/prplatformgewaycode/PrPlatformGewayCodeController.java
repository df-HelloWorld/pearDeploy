package com.xn.manager.controller.prplatformgewaycode;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.PrGewayCodeModel;
import com.xn.manager.model.PrGewayModel;
import com.xn.manager.model.PrPlatformGewayCodeLinkModel;
import com.xn.manager.model.PrPlatformGewayCodeModel;
import com.xn.manager.model.strategy.StrategyModel;
import com.xn.manager.service.PrGewayService;
import com.xn.manager.service.PrPlatformGewayCodeLinkService;
import com.xn.manager.service.PrPlatformGewayCodeService;
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
@RequestMapping("/prplatformgewaycode")
public class PrPlatformGewayCodeController extends BaseController {

    private static Logger log = Logger.getLogger(PrPlatformGewayCodeController.class);

    @Autowired
    private PrPlatformGewayCodeService<PrPlatformGewayCodeModel> prPlatformGewayCodeService;

    @Autowired
    private PrPlatformGewayCodeLinkService<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkService;

    @Autowired
    private PrGewayService<PrGewayModel> prGewayService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/prplatformgewaycode/prplatformgewaycodeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeModel model) throws Exception {
        List<PrPlatformGewayCodeModel> dataList = new ArrayList<PrPlatformGewayCodeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = prPlatformGewayCodeService.queryByList(model);
            for(PrPlatformGewayCodeModel prGewayCodeModel: dataList){
                String  gewayNameStr ="";
                long  prGewayCodeId =prGewayCodeModel.getId();
                PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel =new PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkModel.setPfGewayCodeId(prGewayCodeId);
                List<PrPlatformGewayCodeLinkModel>   linkList=prPlatformGewayCodeLinkService.queryAllList(prPlatformGewayCodeLinkModel);
                for(PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel1:linkList){
                    gewayNameStr+=prPlatformGewayCodeLinkModel1.getGewayCodeNames()+"--"+prPlatformGewayCodeLinkModel1.getGewayCode()+"<br>";
                }

                prGewayCodeModel.setGewayNameStr(gewayNameStr);
            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeModel model) throws Exception {
        List<PrPlatformGewayCodeModel> dataList = new ArrayList<PrPlatformGewayCodeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = prPlatformGewayCodeService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response,Model model) {
//        model.addAttribute("geway", prPlatformGewayCodeService.queryAllList());
        return "manager/prplatformgewaycode/prplatformgewaycodeAdd";
    }


    /**
     * 保存平台代码
     * @param request
     * @param response
     * @param bean
     * @throws Exception
     */
    @RequestMapping("/insertPfGewayCode")
    public void insertPfGewayCode(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel = new  PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkModel.setPfGewayCodeId(bean.getPfGewayCodeId());

                PrPlatformGewayCodeLinkModel   queryBean=prPlatformGewayCodeLinkService.queryByCondition(prPlatformGewayCodeLinkModel);
                if (queryBean != null && queryBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                    sendSuccessMessage(response, "该平台通道名称已有类型绑定了，请选择别的平台通道名称！");
                    return;
                }
//            PrPlatformGewayCodeLinkModel  queryPr = prPlatformGewayCodeLinkService.queryByCondition(prPlatformGewayCodeLinkModel);
//            if (queryPr != null && queryPr.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
////                bean.setRelationTypeId();
//                PrPlatformGewayCodeLinkModel  uqdate=new PrPlatformGewayCodeLinkModel();
//                uqdate.setRelationTypeId(bean.getRelationTypeId());
//                uqdate.setPfGewayCodeId(bean.getPfGewayCodeId());
//                prPlatformGewayCodeLinkService.update(uqdate);
//            }else{
                prPlatformGewayCodeLinkService.add(bean);
//            }
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
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                // check平台通道码是否有重复的
                if (!StringUtils.isBlank(bean.getPfGewayCode())){
                    PrPlatformGewayCodeModel query = new PrPlatformGewayCodeModel();
                    query.setPfGewayCode(bean.getPfGewayCode());
                    PrPlatformGewayCodeModel prPlatformGewayCodeModel = prPlatformGewayCodeService.queryByCondition(query);
                    if (prPlatformGewayCodeModel != null && prPlatformGewayCodeModel.getId() > 0){
                        sendFailureMessage(response,"平台通道码有重复,请重新换一个!");
                        return;
                    }
                }else {
                    sendFailureMessage(response,"平台通道码不能为空!");
                    return;
                }
                prPlatformGewayCodeService.add(bean);
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
        PrPlatformGewayCodeModel atModel = new PrPlatformGewayCodeModel();
        atModel.setId(id);
//        model.addAttribute("geway", prGewayService.queryAllList());
        model.addAttribute("account", prPlatformGewayCodeService.queryById(atModel));
        return "manager/prplatformgewaycode/prplatformgewaycodeEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                // check平台通道码是否有重复的
                if (!StringUtils.isBlank(bean.getPfGewayCode())){
                    PrPlatformGewayCodeModel query = new PrPlatformGewayCodeModel();
                    query.setPfGewayCode(bean.getPfGewayCode());
                    query.setNotId(bean.getId());
                    PrPlatformGewayCodeModel prPlatformGewayCodeModel = prPlatformGewayCodeService.queryByCondition(query);
                    if (prPlatformGewayCodeModel != null && prPlatformGewayCodeModel.getId() > 0){
                        sendFailureMessage(response,"我方通道码有重复,请重新换一个!");
                        return;
                    }
                }else {
                    sendFailureMessage(response,"平台通道码不能为空!");
                    return;
                }
                prPlatformGewayCodeService.update(bean);
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
    public void delete(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                prPlatformGewayCodeService.delete(bean);
                sendSuccessMessage(response, "删除成功");
                return;
            }else {
                log.info("10");
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
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                prPlatformGewayCodeService.manyOperation(bean);
                sendSuccessMessage(response, "状态更新成功");
                return;
            }else {
                log.info("10");
                sendFailureMessage(response,"您无权操作!");
                return;
            }
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }
    }
}
