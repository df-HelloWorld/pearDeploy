package com.xn.manager.controller.template;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.PrGewayCodeModel;
import com.xn.manager.model.strategy.StrategyData;
import com.xn.manager.model.strategy.StrategyModel;
import com.xn.manager.model.template.NotifyFieldModel;
import com.xn.manager.model.template.NotifyTemplateModel;
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
 * @Description: 接收模板的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/notifytemplate")
public class NotifyTemplateController extends BaseController {

    private static Logger log = Logger.getLogger(NotifyTemplateController.class);

    @Autowired
    private NotifyTemplateService<NotifyTemplateModel> notifyTemplateService;

    @Autowired
    private NotifyFieldService<NotifyFieldModel> notifyFieldService;

    @Autowired
    private StrategyService<StrategyModel> strategyService;

    @Autowired
    private PrGewayCodeService<PrGewayCodeModel> prGewayCodeService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/notifytemplate/notifytemplateIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, NotifyTemplateModel model) throws Exception {
        List<NotifyTemplateModel> dataList = new ArrayList<NotifyTemplateModel>();
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("5");
                return;
            }
            dataList = notifyTemplateService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, NotifyTemplateModel model) throws Exception {
        List<NotifyTemplateModel> dataList = new ArrayList<NotifyTemplateModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("3");
                return;
            }
            dataList = notifyTemplateService.queryAllList(model);
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
            // 查询接收模板数据
            NotifyTemplateModel notifyTemplateQuery = new NotifyTemplateModel();
            notifyTemplateQuery.setId(id);
            NotifyTemplateModel notifyTemplateModel = notifyTemplateService.queryById(notifyTemplateQuery);
            model.addAttribute("notifyTemplate", notifyTemplateModel);

            // 通道码数据
            model.addAttribute("prGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));

            // 查询接收字段数据
            NotifyFieldModel notifyFieldQuery = new NotifyFieldModel();
            notifyFieldQuery.setNotifyTemplateId(notifyTemplateModel.getId());
            List<NotifyFieldModel> notifyFieldList = notifyFieldService.queryAllList(notifyFieldQuery);
            model.addAttribute("notifyFieldList", notifyFieldList);

            return "manager/notifytemplate/notifytemplateCopy";
        }else {
            // 直接跳转到新增页面
            model.addAttribute("prGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));
            return "manager/notifytemplate/notifytemplateAdd";
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
                NotifyTemplateModel notifyTemplateModel = PublicMethod.assembleNotifyTemplateByJson(dataOne);
                if (notifyTemplateModel == null){
                    sendFailureMessage(response,"模板数据错误,请您重试!");
                    return;
                }

                // check模板数据
                Map<String , Object> templateMap = PublicMethod.checkNotifyTemplate(notifyTemplateModel);
                boolean flag_template = (boolean) templateMap.get("flag_check");
                if (!flag_template){
                    sendFailureMessage(response, templateMap.get("msg").toString());
                    return;
                }

                // 判断是否有重复录入通道码的模板
                NotifyTemplateModel notifyTemplateQuery = new NotifyTemplateModel();
                notifyTemplateQuery.setGewayCodeId(notifyTemplateModel.getGewayCodeId());
                NotifyTemplateModel notifyTemplateData = notifyTemplateService.queryByCondition(notifyTemplateQuery);
                if (notifyTemplateData != null && notifyTemplateData.getId() > 0){
                    sendFailureMessage(response,"该通道码已有接收模板的存在,属于重复录入,请您重新选择通道码!");
                    return;
                }

                // 解析接收字段
                List<NotifyFieldModel> notifyFieldList = PublicMethod.assembleNotifyFieldByJson(dataTwo);
                if (notifyFieldList == null){
                    sendFailureMessage(response,"接收字段数据错误,请您重试!");
                    return;
                }

                // check接收字段数据
                Map<String, Object> notifyFieldMap = PublicMethod.checkNotifyField(notifyFieldList);
                boolean flag_notifyField = (boolean) notifyFieldMap.get("flag_check");
                if (!flag_notifyField){
                    sendFailureMessage(response, notifyFieldMap.get("msg").toString());
                    return;
                }

                // 添加模板数据
                notifyTemplateService.add(notifyTemplateModel);
                if (notifyTemplateModel.getId() > 0){
                    // 循环添加接收字段数据
                    for (NotifyFieldModel notifyFieldData : notifyFieldList){
                        notifyFieldData.setNotifyTemplateId(notifyTemplateModel.getId());
                        notifyFieldService.add(notifyFieldData);
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
        // 查询接收模板数据
        NotifyTemplateModel notifyTemplateQuery = new NotifyTemplateModel();
        notifyTemplateQuery.setId(id);
        NotifyTemplateModel notifyTemplateModel = notifyTemplateService.queryById(notifyTemplateQuery);
        model.addAttribute("notifyTemplate", notifyTemplateModel);

        // 通道码数据
        model.addAttribute("prGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));

        // 查询接收字段数据
        NotifyFieldModel notifyFieldQuery = new NotifyFieldModel();
        notifyFieldQuery.setNotifyTemplateId(notifyTemplateModel.getId());
        List<NotifyFieldModel> notifyFieldList = notifyFieldService.queryAllList(notifyFieldQuery);
        model.addAttribute("notifyFieldList", notifyFieldList);

        return "manager/notifytemplate/notifytemplateEdit";
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
            NotifyTemplateModel notifyTemplateModel = PublicMethod.assembleNotifyTemplateByJson(dataOne);
            if (notifyTemplateModel == null){
                sendFailureMessage(response,"模板数据错误,请您重试!");
                return;
            }

            // check模板数据
            Map<String , Object> templateMap = PublicMethod.checkNotifyTemplate(notifyTemplateModel);
            boolean flag_template = (boolean) templateMap.get("flag_check");
            if (!flag_template){
                sendFailureMessage(response, templateMap.get("msg").toString());
                return;
            }

            // 判断是否有重复录入通道码的模板
            NotifyTemplateModel notifyTemplateQuery = new NotifyTemplateModel();
            notifyTemplateQuery.setGewayCodeId(notifyTemplateModel.getGewayCodeId());
            NotifyTemplateModel notifyTemplateData = notifyTemplateService.queryByCondition(notifyTemplateQuery);
            if (notifyTemplateData != null && notifyTemplateData.getId() > 0){
                if (notifyTemplateData.getId() != id){
                    // 这里就是怕查询到此模板自身，所以得判断查询的模板ID不等于要更新的的模板ID
                    sendFailureMessage(response,"该通道码已有接收模板的存在,属于重复录入,请您重新选择通道码!");
                    return;
                }
            }

            // 解析接收字段
            List<NotifyFieldModel> notifyFieldList = PublicMethod.assembleNotifyFieldByJson(dataTwo);
            if (notifyFieldList == null){
                sendFailureMessage(response,"接收字段数据错误,请您重试!");
                return;
            }

            // check接收字段数据
            Map<String, Object> notifyFieldMap = PublicMethod.checkNotifyField(notifyFieldList);
            boolean flag_notifyField = (boolean) notifyFieldMap.get("flag_check");
            if (!flag_notifyField){
                sendFailureMessage(response, notifyFieldMap.get("msg").toString());
                return;
            }


            // 更新接收模板数据
            notifyTemplateModel.setId(id);// 赋值主键ID
            notifyTemplateService.update(notifyTemplateModel);


            // 更新接收字段数据
            for (NotifyFieldModel notifyFieldModel : notifyFieldList){
                if (notifyFieldModel.getId() > 0){
                    // 接收字段的ID大于0则是更新接收字段数据
                    notifyFieldService.update(notifyFieldModel);
                }else {
                    // 接收字段的ID等于0则是新增接收字段数据
                    notifyFieldModel.setNotifyTemplateId(id);
                    notifyFieldService.add(notifyFieldModel);
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
    public void delete(HttpServletRequest request, HttpServletResponse response, NotifyTemplateModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            notifyTemplateService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, NotifyTemplateModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            notifyTemplateService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * @Description: 获取接收模板里面的接收字段
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getFieldById")
    public void getFieldById(Long id, HttpServletResponse response) throws Exception {
        NotifyFieldModel query = new NotifyFieldModel();
        query.setNotifyTemplateId(id);
        List<NotifyFieldModel> dataList = new ArrayList<NotifyFieldModel>();
        dataList = notifyFieldService.queryAllList(query);

        // 查询字段类型
        List<StrategyData> fieldTypeList = new ArrayList<StrategyData>();
        StrategyModel strategyQuery = new StrategyModel();
        strategyQuery.setStgType(7);
        StrategyModel strategyModel = strategyService.queryByCondition(strategyQuery);
        if (strategyModel != null){
            fieldTypeList = JSON.parseArray(strategyModel.getStgBigValue(), StrategyData.class);
        }

        if (dataList == null) {
            log.info("1");
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
     * 更新接收字段里面的数据
     */
    @RequestMapping("/updateNotifyField")
    public void updateReturnField(HttpServletRequest request, HttpServletResponse response,@RequestParam String strArr) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (StringUtils.isBlank(strArr) || strArr.equals("\"\"")){
                sendFailureMessage(response,"接收字段数据为空,请填写接收字段信息!");
                return;
            }
            strArr = strArr.replace("\"", "");
            strArr = URLDecoder.decode(strArr, "UTF-8");
            String [] dataArr = strArr.split("&yc=`");
//            String [] dataArr = data.split("`");
            List<NotifyFieldModel> notifyFieldList = PublicMethod.assembleNotifyFieldListByJson(dataArr);

            // check接收字段数据
            Map<String, Object> notifyFieldMap = PublicMethod.checkNotifyField(notifyFieldList);
            boolean flag_notifyField = (boolean) notifyFieldMap.get("flag_check");
            if (!flag_notifyField){
                sendFailureMessage(response, notifyFieldMap.get("msg").toString());
                return;
            }

            long notifyTemplateId = 0;// 接收模板ID
            if (notifyFieldList != null && notifyFieldList.size() > 0){
                // 根据接收字段的ID获取接收模板的ID
                long notifyFieldId = notifyFieldList.get(0).getId();
                NotifyFieldModel notifyFieldQuery = new NotifyFieldModel();
                notifyFieldQuery.setId(notifyFieldId);
                NotifyFieldModel notifyFieldModel = notifyFieldService.queryById(notifyFieldQuery);
                if (notifyFieldModel == null || notifyFieldModel.getId() == 0){
                    sendFailureMessage(response, "根据接收字段ID查询接收字段数据为空,请联系管理员核实问题!");
                    return;
                }
                notifyTemplateId = notifyFieldModel.getNotifyTemplateId();
            }

            for (NotifyFieldModel notifyFieldModel : notifyFieldList){
                if (notifyFieldModel.getId() > 0){
                    log.info("");
                    // 接收字段的ID大于0则是更新接收字段数据
                    notifyFieldService.update(notifyFieldModel);
                }else {
                    // 接收字段的ID等于0则是新增接收字段数据
                    notifyFieldModel.setNotifyTemplateId(notifyTemplateId);
                    notifyFieldService.add(notifyFieldModel);
                }
            }

            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }


}
