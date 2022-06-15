package com.xn.manager.controller.template;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.PrGewayCodeModel;
import com.xn.manager.model.strategy.StrategyData;
import com.xn.manager.model.strategy.StrategyModel;
import com.xn.manager.model.template.ReturnFieldModel;
import com.xn.manager.model.template.ReturnTemplateModel;
import com.xn.manager.service.*;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description: 返回模板的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/returntemplate")
public class ReturnTemplateController extends BaseController {

    private static Logger log = Logger.getLogger(ReturnTemplateController.class);

    @Autowired
    private ReturnTemplateService<ReturnTemplateModel> returnTemplateService;

    @Autowired
    private ReturnFieldService<ReturnFieldModel> returnFieldService;

    @Autowired
    private StrategyService<StrategyModel> strategyService;

    @Autowired
    private PrGewayCodeService<PrGewayCodeModel> prGewayCodeService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/returntemplate/returntemplateIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ReturnTemplateModel model) throws Exception {
        List<ReturnTemplateModel> dataList = new ArrayList<ReturnTemplateModel>();
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("1");
                return;
            }
            dataList = returnTemplateService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ReturnTemplateModel model) throws Exception {
        List<ReturnTemplateModel> dataList = new ArrayList<ReturnTemplateModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("2");
                return;
            }
            dataList = returnTemplateService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model, long id) {
        log.info("id:" + id);
        if(id != 0){
            // 这里是复制模板
            // 查询返回模板数据
            ReturnTemplateModel returnTemplateQuery = new ReturnTemplateModel();
            returnTemplateQuery.setId(id);
            ReturnTemplateModel returnTemplateModel = returnTemplateService.queryById(returnTemplateQuery);
            model.addAttribute("returnTemplate", returnTemplateModel);

            // 通道码数据
            model.addAttribute("prGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));

            // 查询返回字段数据
            ReturnFieldModel returnFieldQuery = new ReturnFieldModel();
            returnFieldQuery.setReturnTemplateId(returnTemplateModel.getId());
            List<ReturnFieldModel> returnFieldList = returnFieldService.queryAllList(returnFieldQuery);
            model.addAttribute("returnFieldList", returnFieldList);

            return "manager/returntemplate/returntemplateCopy";
        }else {
            // 直接跳转到新增页面
            model.addAttribute("prGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));
            return "manager/returntemplate/returntemplateAdd";
        }
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, @RequestParam String dataOne, @RequestParam String dataTwo) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if (StringUtils.isBlank(dataOne)){
                    sendFailureMessage(response,"模板数据为空,请填写必要信息!");
                    return;
                }
                if (StringUtils.isBlank(dataTwo) || dataTwo.equals("\"\"")){
                    sendFailureMessage(response,"字段数据为空,请填写必要信息!");
                    return;
                }
                // 解析模板数据
                ReturnTemplateModel returnTemplateModel = PublicMethod.assembleReturnTemplateByJson(dataOne);
                if (returnTemplateModel == null){
                    sendFailureMessage(response,"模板数据错误,请您重试!");
                    return;
                }

                // check模板数据
                Map<String , Object> templateMap = PublicMethod.checkReturnTemplate(returnTemplateModel);
                boolean flag_template = (boolean) templateMap.get("flag_check");
                if (!flag_template){
                    sendFailureMessage(response, templateMap.get("msg").toString());
                    return;
                }

                // 判断是否有重复录入通道码的模板
                ReturnTemplateModel returnTemplateQuery = new ReturnTemplateModel();
                returnTemplateQuery.setGewayCodeId(returnTemplateModel.getGewayCodeId());
                ReturnTemplateModel returnTemplateData = returnTemplateService.queryByCondition(returnTemplateQuery);
                if (returnTemplateData != null && returnTemplateData.getId() > 0){
                    sendFailureMessage(response,"该通道码已有返回模板的存在,属于重复录入,请您重新选择通道码!");
                    return;
                }

                // 解析返回字段
                List<ReturnFieldModel> returnFieldList = PublicMethod.assembleReturnFieldByJson(dataTwo);
                if (returnFieldList == null){
                    sendFailureMessage(response,"返回字段数据错误,请您重试!");
                    return;
                }

                // check返回字段数据
                Map<String, Object> returnFieldMap = PublicMethod.checkReturnField(returnFieldList);
                boolean flag_returnField = (boolean) returnFieldMap.get("flag_check");
                if (!flag_returnField){
                    sendFailureMessage(response, returnFieldMap.get("msg").toString());
                    return;
                }

                // 添加模板数据
                returnTemplateService.add(returnTemplateModel);
                if (returnTemplateModel.getId() > 0){
                    // 循环添加返回字段数据
                    for (ReturnFieldModel returnFieldData : returnFieldList){
                        returnFieldData.setReturnTemplateId(returnTemplateModel.getId());
                        returnFieldService.add(returnFieldData);
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
    public String jumpUpdate(Model model, long id, Integer op) {
        // 查询返回模板数据
        ReturnTemplateModel returnTemplateQuery = new ReturnTemplateModel();
        returnTemplateQuery.setId(id);
        ReturnTemplateModel returnTemplateModel = returnTemplateService.queryById(returnTemplateQuery);
        model.addAttribute("returnTemplate", returnTemplateModel);

        // 通道码数据
        model.addAttribute("prGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));

        // 查询返回字段数据
        ReturnFieldModel returnFieldQuery = new ReturnFieldModel();
        returnFieldQuery.setReturnTemplateId(returnTemplateModel.getId());
        List<ReturnFieldModel> returnFieldList = returnFieldService.queryAllList(returnFieldQuery);
        model.addAttribute("returnFieldList", returnFieldList);

        return "manager/returntemplate/returntemplateEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, @RequestParam long id, @RequestParam String dataOne, @RequestParam String dataTwo) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            if (id <= 0){
                sendFailureMessage(response,"错误,请您重试!");
                return;
            }

            if (StringUtils.isBlank(dataOne)){
                sendFailureMessage(response,"模板数据为空,请填写必要信息!");
                return;
            }
            if (StringUtils.isBlank(dataTwo) || dataTwo.equals("\"\"")){
                sendFailureMessage(response,"字段数据为空,请填写必要信息!");
                return;
            }
            // 解析模板数据
            ReturnTemplateModel returnTemplateModel = PublicMethod.assembleReturnTemplateByJson(dataOne);
            if (returnTemplateModel == null){
                sendFailureMessage(response,"模板数据错误,请您重试!");
                return;
            }

            // check模板数据
            Map<String , Object> templateMap = PublicMethod.checkReturnTemplate(returnTemplateModel);
            boolean flag_template = (boolean) templateMap.get("flag_check");
            if (!flag_template){
                sendFailureMessage(response, templateMap.get("msg").toString());
                return;
            }

            // 判断是否有重复录入通道码的模板
            ReturnTemplateModel returnTemplateQuery = new ReturnTemplateModel();
            returnTemplateQuery.setGewayCodeId(returnTemplateModel.getGewayCodeId());
            ReturnTemplateModel returnTemplateData = returnTemplateService.queryByCondition(returnTemplateQuery);
            if (returnTemplateData != null && returnTemplateData.getId() > 0){
                if (returnTemplateData.getId() != id){
                    // 这里就是怕查询到此模板自身，所以得判断查询的模板ID不等于要更新的的模板ID
                    sendFailureMessage(response,"该通道码已有返回模板的存在,属于重复录入,请您重新选择通道码!");
                    return;
                }
            }

            // 解析返回字段
            List<ReturnFieldModel> returnFieldList = PublicMethod.assembleReturnFieldByJson(dataTwo);
            if (returnFieldList == null){
                sendFailureMessage(response,"返回字段数据错误,请您重试!");
                return;
            }

            // check返回字段数据
            Map<String, Object> returnFieldMap = PublicMethod.checkReturnField(returnFieldList);
            boolean flag_returnField = (boolean) returnFieldMap.get("flag_check");
            if (!flag_returnField){
                sendFailureMessage(response, returnFieldMap.get("msg").toString());
                return;
            }


            // 更新返回模板数据
            returnTemplateModel.setId(id);// 赋值主键ID
            returnTemplateService.update(returnTemplateModel);


            // 更新返回字段数据
            for (ReturnFieldModel returnFieldModel : returnFieldList){
                if (returnFieldModel.getId() > 0){
                    // 返回字段的ID大于0则是更新返回字段数据
                    returnFieldService.update(returnFieldModel);
                }else {
                    // 返回字段的ID等于0则是新增返回字段数据
                    returnFieldModel.setReturnTemplateId(id);
                    returnFieldService.add(returnFieldModel);
                    log.info("1");
                }
            }


            sendSuccessMessage(response, "保存成功~");
            return;
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, ReturnTemplateModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            returnTemplateService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ReturnTemplateModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            returnTemplateService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * @Description: 获取返回模板里面的返回字段
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getFieldById")
    public void getFieldById(Long id, HttpServletResponse response) throws Exception {
        ReturnFieldModel query = new ReturnFieldModel();
        query.setReturnTemplateId(id);
        List<ReturnFieldModel> dataList = new ArrayList<ReturnFieldModel>();
        dataList = returnFieldService.queryAllList(query);

        // 查询字段类型
        List<StrategyData> fieldTypeList = new ArrayList<StrategyData>();
        StrategyModel strategyQuery = new StrategyModel();
        strategyQuery.setStgType(6);
        StrategyModel strategyModel = strategyService.queryByCondition(strategyQuery);
        if (strategyModel != null){
            fieldTypeList = JSON.parseArray(strategyModel.getStgBigValue(), StrategyData.class);
        }

        if (dataList == null) {
            log.info("");
            sendFailureMessage(response, "没有找到对应的记录!");
            return;
        }else {
            for (int i= 0; i < dataList.size(); i++){
                dataList.get(i).setStrategyDataList(fieldTypeList);
            }
        }
        sendSuccessMessage(response, "", dataList);
    }


    /**
     * 更新返回字段里面的数据
     */
    @RequestMapping("/updateReturnField")
    public void updateReturnField(HttpServletRequest request, HttpServletResponse response,@RequestParam String strArr) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (StringUtils.isBlank(strArr) || strArr.equals("\"\"")){
                sendFailureMessage(response,"返回字段数据为空,请填写返回字段信息!");
                return;
            }
            strArr = strArr.replace("\"", "");
            strArr = URLDecoder.decode(strArr, "UTF-8");
            String [] dataArr = strArr.split("&yc=`");
//            String [] dataArr = data.split("`");
            List<ReturnFieldModel> returnFieldList = PublicMethod.assembleReturnFieldListByJson(dataArr);

            // check返回字段数据
            Map<String, Object> returnFieldMap = PublicMethod.checkReturnField(returnFieldList);
            boolean flag_returnField = (boolean) returnFieldMap.get("flag_check");
            if (!flag_returnField){
                sendFailureMessage(response, returnFieldMap.get("msg").toString());
                return;
            }

            long returnTemplateId = 0;// 返回模板ID
            if (returnFieldList != null && returnFieldList.size() > 0){
                // 根据返回字段的ID获取返回模板的ID
                long returnFieldId = returnFieldList.get(0).getId();
                ReturnFieldModel returnFieldQuery = new ReturnFieldModel();
                returnFieldQuery.setId(returnFieldId);
                ReturnFieldModel returnFieldModel = returnFieldService.queryById(returnFieldQuery);
                if (returnFieldModel == null || returnFieldModel.getId() == 0){
                    sendFailureMessage(response, "根据返回字段ID查询返回字段数据为空,请联系管理员核实问题!");
                    return;
                }
                returnTemplateId = returnFieldModel.getReturnTemplateId();
            }

            for (ReturnFieldModel returnFieldModel : returnFieldList){
                if (returnFieldModel.getId() > 0){
                    // 返回字段的ID大于0则是更新返回字段数据
                    returnFieldService.update(returnFieldModel);
                }else {
                    // 返回字段的ID等于0则是新增返回字段数据
                    returnFieldModel.setReturnTemplateId(returnTemplateId);
                    returnFieldService.add(returnFieldModel);
                }
            }

            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }


}
