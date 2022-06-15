package com.xn.manager.controller.prrelationtype;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.*;
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
@RequestMapping("/prrelationtype")
public class PrRelationTypeController extends BaseController {

    private static Logger log = Logger.getLogger(PrRelationTypeController.class);

    @Autowired
    private PrRelationTypeService<PrRelationTypeModel> relationTypeService;


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
        return "manager/prrelationtype/prrelationtypeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, PrRelationTypeModel model) throws Exception {
        List<PrRelationTypeModel> dataList = new ArrayList<PrRelationTypeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = relationTypeService.queryByList(model);

            List<PrRelationTypeModel> rsDataList = new ArrayList<PrRelationTypeModel>();
            for(PrRelationTypeModel relationTypeModel:dataList){
                PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel =new PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkModel.setRelationTypeId(relationTypeModel.getId());
                List<PrPlatformGewayCodeLinkModel>  list  = prPlatformGewayCodeLinkService.queryAllList(prPlatformGewayCodeLinkModel);

                if(list.size()==0){
                    prPlatformGewayCodeLinkModel.setGewayCodeNames("");
                }else{
                    relationTypeModel.setCodeName(list.get(0).getCodeName());
                    String  gewayCodeNames ="";
                    for(PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel1:list){
                        if(prPlatformGewayCodeLinkModel1.getGewayCodeNames()==null){
                            gewayCodeNames=gewayCodeNames+"";
                        }else{
                            gewayCodeNames=gewayCodeNames+prPlatformGewayCodeLinkModel1.getGewayCodeNames()+"||";
                        }
                    }
                    if(gewayCodeNames.length()>=2){
                        gewayCodeNames=gewayCodeNames.substring(0,gewayCodeNames.length()-2);
                    }
                    relationTypeModel.setGewayCodeNames(gewayCodeNames);
                }
//                rsDataList.add(prPlatformGewayCodeLinkModel);
            }

        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, PrRelationTypeModel model) throws Exception {
        List<PrRelationTypeModel> dataList = new ArrayList<PrRelationTypeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = relationTypeService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataSelGewayCode")
    public void dataSelGewayCode(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel model) throws Exception {
        List<PrPlatformGewayCodeLinkModel> dataList = new ArrayList<PrPlatformGewayCodeLinkModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = prPlatformGewayCodeLinkService.queryGewayCodeId(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response,Model model) {
//        model.addAttribute("platformGewayCode", prPlatformGewayCodeService.queryAllList());
//        model.addAttribute("gewayCode", prGewayCodeService.queryAllList());

//          prPlatformGewayCodeLinkModel  = new  PrPlatformGewayCodeLinkModel();
//        prPlatformGewayCodeLinkService.add(prPlatformGewayCodeLinkModel);
        return "manager/prrelationtype/prrelationtypeAdd";
    }



    /**
     * 获取新增页面
     */




    @RequestMapping("/dataGewayCode")
    public void dataCodeUpdate(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkModel) {
        List<PrPlatformGewayCodeLinkModel> dataList = new ArrayList<PrPlatformGewayCodeLinkModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, dataList);
            }
            dataList = prPlatformGewayCodeLinkService.queryGewayCodeId(prPlatformGewayCodeLinkModel);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    @RequestMapping("/upPtCode")
    public String upPtCode(Model model,long id) {
        System.out.println("============"+id);
        model.addAttribute("platformGewayCode", prPlatformGewayCodeService.queryAllList());

        PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel = new  PrPlatformGewayCodeLinkModel();
        prPlatformGewayCodeLinkModel.setGewayCodeId(id);
        model.addAttribute("selPlatformGewayCode", prPlatformGewayCodeLinkService.queryPfGewayCodeId(prPlatformGewayCodeLinkModel));

//        prPlatformGewayCodeLinkModel  = new  PrPlatformGewayCodeLinkModel();
//        prPlatformGewayCodeLinkService.add(prPlatformGewayCodeLinkModel);
        return "manager/prrelationtype/prrelationtypeCodeEdit";
    }


    @RequestMapping("/upCode")
    public String upCode(Model model,long id) {
        model.addAttribute("platformGewayCode", prPlatformGewayCodeService.queryAllList());

        PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel = new  PrPlatformGewayCodeLinkModel();
        prPlatformGewayCodeLinkModel.setGewayCodeId(id);
        model.addAttribute("selPlatformGewayCode", prPlatformGewayCodeLinkService.queryPfGewayCodeId(prPlatformGewayCodeLinkModel));
        return "manager/prrelationtype/prrelationtypeCodeEdit";
    }


    /**
     * 获取新增页面
     */
//    @RequestMapping("/jumpGewayCodeUpdate")
//    public void jumpGewayCodeUpdate(HttpServletRequest request, HttpServletResponse response, PrPlatformGewayCodeLinkModel bean) {
//
//        PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel = new  PrPlatformGewayCodeLinkModel();
//        prPlatformGewayCodeLinkModel.setRelationTypeId(bean.getRelationTypeId());
//
//        PrPlatformGewayCodeLinkModel  queryPr = prPlatformGewayCodeLinkService.queryByCondition(prPlatformGewayCodeLinkModel);
//        if (queryPr != null && queryPr.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            prPlatformGewayCodeLinkService.add(bean);
//        }else{
//            prPlatformGewayCodeLinkService.update(bean);
//        }
//        sendSuccessMessage(response, "保存成功~");
//    }



    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, PrRelationTypeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                relationTypeService.add(bean);
                Long    relationTypeId =   bean.getId();
                PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkModel = new PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkModel.setRelationTypeId(relationTypeId);

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
    @RequestMapping("/jumpPtGewayCodeUpdate")
    public String jumpPtGewayCodeUpdate(Model model, long id) {
        model.addAttribute("platformGewayCode", prPlatformGewayCodeService.queryAllList());

        PrPlatformGewayCodeLinkModel  prPlatformGewayCodeLinkModel = new  PrPlatformGewayCodeLinkModel();
        prPlatformGewayCodeLinkModel.setRelationTypeId(id);
        Long  gewayCode  =0L;
        List<PrPlatformGewayCodeLinkModel> list=prPlatformGewayCodeLinkService.queryPfGewayCodeId(prPlatformGewayCodeLinkModel);
        if(list.size()!=0){
            gewayCode=list.get(0).getPfGewayCodeId();
        }
        model.addAttribute("selPlatformGewayCode", gewayCode);
        model.addAttribute("relationTypeId", id);
        return "manager/prrelationtype/prrelationtypeCodeEdit";
    }







    @RequestMapping("/jumpGewayCodeUpdate")
    public String jumpGewayCodeUpdate(Model model, long id) {
//        model.addAttribute("platformGewayCode", prGewayCodeService.queryAllList());
        model.addAttribute("relationTypeId", id);
        return "manager/prrelationtype/prrelationtypeAddLink";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, PrRelationTypeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                relationTypeService.update(bean);
                sendSuccessMessage(response, "保存成功~");
                return;
            }else {
                log.info("9");
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
                relationTypeService.delete(bean);
                PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkModel=new PrPlatformGewayCodeLinkModel();
                prPlatformGewayCodeLinkModel.setRelationTypeId(bean.getId());
                prPlatformGewayCodeLinkService.deleteRelationType(prPlatformGewayCodeLinkModel);
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
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, PrRelationTypeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                relationTypeService.manyOperation(bean);
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
