package com.xn.manager.controller.template;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.PrGewayCodeModel;
import com.xn.manager.model.strategy.StrategyData;
import com.xn.manager.model.strategy.StrategyModel;
import com.xn.manager.model.template.SendFieldModel;
import com.xn.manager.model.template.SendTemplateModel;
import com.xn.manager.service.PrGewayCodeService;
import com.xn.manager.service.SendFieldService;
import com.xn.manager.service.SendTemplateService;
import com.xn.manager.service.StrategyService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
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
 * @Description: 请求模板的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/sendtemplate")
public class SendTemplateController extends BaseController {

    private static Logger log = Logger.getLogger(SendTemplateController.class);

    @Autowired
    private SendTemplateService<SendTemplateModel> sendTemplateService;

    @Autowired
    private SendFieldService<SendFieldModel> sendFieldService;

    @Autowired
    private StrategyService<StrategyModel> strategyService;

    @Autowired
    private PrGewayCodeService<PrGewayCodeModel> prGewayCodeService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/sendtemplate/sendtemplateIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, SendTemplateModel model) throws Exception {
        List<SendTemplateModel> dataList = new ArrayList<SendTemplateModel>();
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("");
                return;
            }
            dataList = sendTemplateService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, SendTemplateModel model) throws Exception {
        List<SendTemplateModel> dataList = new ArrayList<SendTemplateModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("1");
                return;
            }
            dataList = sendTemplateService.queryAllList(model);
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
            // 查询请求模板数据
            SendTemplateModel sendTemplateQuery = new SendTemplateModel();
            sendTemplateQuery.setId(id);
            SendTemplateModel sendTemplateModel = sendTemplateService.queryById(sendTemplateQuery);
            model.addAttribute("sendTemplate", sendTemplateModel);

            // 通道码数据
            model.addAttribute("prGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));

            // 查询请求字段数据
            SendFieldModel sendFieldQuery = new SendFieldModel();
            sendFieldQuery.setSendTemplateId(sendTemplateModel.getId());
            List<SendFieldModel> sendFieldList = sendFieldService.queryAllList(sendFieldQuery);
            model.addAttribute("sendFieldList", sendFieldList);

            return "manager/sendtemplate/sendtemplateCopy";
        }else {
            // 直接跳转到新增页面
            model.addAttribute("prGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));
            return "manager/sendtemplate/sendtemplateAdd";
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
                SendTemplateModel sendTemplateModel = PublicMethod.assembleSendTemplateByJson(dataOne);
                if (sendTemplateModel == null){
                    sendFailureMessage(response,"模板数据错误,请您重试!");
                    return;
                }

                // check模板数据
                Map<String , Object> templateMap = PublicMethod.checkSendTemplate(sendTemplateModel);
                boolean flag_template = (boolean) templateMap.get("flag_check");
                if (!flag_template){
                    sendFailureMessage(response, templateMap.get("msg").toString());
                    return;
                }

                // 判断是否有重复录入通道码的模板
                SendTemplateModel sendTemplateQuery = new SendTemplateModel();
                sendTemplateQuery.setGewayCodeId(sendTemplateModel.getGewayCodeId());
                SendTemplateModel sendTemplateData = sendTemplateService.queryByCondition(sendTemplateQuery);
                if (sendTemplateData != null && sendTemplateData.getId() > 0){
                    sendFailureMessage(response,"该通道码已有请求模板的存在,属于重复录入,请您重新选择通道码!");
                    return;
                }

                // 解析请求字段
                List<SendFieldModel> sendFieldList = PublicMethod.assembleSendFieldByJson(dataTwo);
                if (sendFieldList == null){
                    sendFailureMessage(response,"请求字段数据错误,请您重试!");
                    return;
                }

                // check请求字段数据
                Map<String, Object> sendFieldMap = PublicMethod.checkSendField(sendFieldList);
                boolean flag_sendField = (boolean) sendFieldMap.get("flag_check");
                if (!flag_sendField){
                    sendFailureMessage(response, sendFieldMap.get("msg").toString());
                    return;
                }

                // 添加模板数据
                sendTemplateService.add(sendTemplateModel);
                if (sendTemplateModel.getId() > 0){
                    // 循环添加请求字段数据
                    for (SendFieldModel sendFieldData : sendFieldList){
                        sendFieldData.setSendTemplateId(sendTemplateModel.getId());
                        sendFieldService.add(sendFieldData);
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
        // 查询请求模板数据
        SendTemplateModel sendTemplateQuery = new SendTemplateModel();
        sendTemplateQuery.setId(id);
        SendTemplateModel sendTemplateModel = sendTemplateService.queryById(sendTemplateQuery);
        model.addAttribute("sendTemplate", sendTemplateModel);

        // 通道码数据
        model.addAttribute("prGewayCode", prGewayCodeService.queryAllList(new PrGewayCodeModel()));

        // 查询请求字段数据
        SendFieldModel sendFieldQuery = new SendFieldModel();
        sendFieldQuery.setSendTemplateId(sendTemplateModel.getId());
        List<SendFieldModel> sendFieldList = sendFieldService.queryAllList(sendFieldQuery);
        model.addAttribute("sendFieldList", sendFieldList);

        return "manager/sendtemplate/sendtemplateEdit";
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
            SendTemplateModel sendTemplateModel = PublicMethod.assembleSendTemplateByJson(dataOne);
            if (sendTemplateModel == null){
                sendFailureMessage(response,"模板数据错误,请您重试!");
                return;
            }

            // check模板数据
            Map<String , Object> templateMap = PublicMethod.checkSendTemplate(sendTemplateModel);
            boolean flag_template = (boolean) templateMap.get("flag_check");
            if (!flag_template){
                sendFailureMessage(response, templateMap.get("msg").toString());
                return;
            }

            // 判断是否有重复录入通道码的模板
            SendTemplateModel sendTemplateQuery = new SendTemplateModel();
            sendTemplateQuery.setGewayCodeId(sendTemplateModel.getGewayCodeId());
            SendTemplateModel sendTemplateData = sendTemplateService.queryByCondition(sendTemplateQuery);
            if (sendTemplateData != null && sendTemplateData.getId() > 0){
                if (sendTemplateData.getId() != id){
                    // 这里就是怕查询到此模板自身，所以得判断查询的模板ID不等于要更新的的模板ID
                    sendFailureMessage(response,"该通道码已有请求模板的存在,属于重复录入,请您重新选择通道码!");
                    return;
                }
            }

            // 解析请求字段
            List<SendFieldModel> sendFieldList = PublicMethod.assembleSendFieldByJson(dataTwo);
            if (sendFieldList == null){
                sendFailureMessage(response,"请求字段数据错误,请您重试!");
                return;
            }

            // check请求字段数据
            Map<String, Object> sendFieldMap = PublicMethod.checkSendField(sendFieldList);
            boolean flag_sendField = (boolean) sendFieldMap.get("flag_check");
            if (!flag_sendField){
                sendFailureMessage(response, sendFieldMap.get("msg").toString());
                return;
            }


            // 更新请求模板数据
            sendTemplateModel.setId(id);// 赋值主键ID
            sendTemplateService.update(sendTemplateModel);


            // 更新请求字段数据
            for (SendFieldModel sendFieldModel : sendFieldList){
                if (sendFieldModel.getId() > 0){
                    // 请求字段的ID大于0则是更新请求字段数据
                    sendFieldService.update(sendFieldModel);
                }else {
                    // 请求字段的ID等于0则是新增请求字段数据
                    sendFieldModel.setSendTemplateId(id);
                    sendFieldService.add(sendFieldModel);
                    log.info("");
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
    public void delete(HttpServletRequest request, HttpServletResponse response, SendTemplateModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            sendTemplateService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, SendTemplateModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            sendTemplateService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * @Description: 获取请求模板里面的请求字段
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getFieldById")
    public void getFieldById(Long id, HttpServletResponse response) throws Exception {
        SendFieldModel query = new SendFieldModel();
        query.setSendTemplateId(id);
        List<SendFieldModel> dataList = new ArrayList<SendFieldModel>();
        dataList = sendFieldService.queryAllList(query);

        // 查询字段类型
        List<StrategyData> fieldTypeList = new ArrayList<StrategyData>();
        StrategyModel strategyQuery = new StrategyModel();
        strategyQuery.setStgType(5);
        StrategyModel strategyModel = strategyService.queryByCondition(strategyQuery);
        if (strategyModel != null){
            fieldTypeList = JSON.parseArray(strategyModel.getStgBigValue(), StrategyData.class);
        }
        if (dataList == null) {
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
     * 更新请求字段里面的数据
     */
    @RequestMapping("/updateSendField")
    public void updateSendField(HttpServletRequest request, HttpServletResponse response,@RequestParam String strArr) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (StringUtils.isBlank(strArr) || strArr.equals("\"\"")){
                sendFailureMessage(response,"请求字段数据为空,请填写请求字段信息!");
                return;
            }
            strArr = strArr.replace("\"", "");
            strArr = URLDecoder.decode(strArr, "UTF-8");
            String [] dataArr = strArr.split("&yc=`");
//            String [] dataArr = data.split("`");
            List<SendFieldModel> sendFieldList = PublicMethod.assembleSendFieldListByJson(dataArr);

            // check请求字段数据
            Map<String, Object> sendFieldMap = PublicMethod.checkSendField(sendFieldList);
            boolean flag_sendField = (boolean) sendFieldMap.get("flag_check");
            if (!flag_sendField){
                sendFailureMessage(response, sendFieldMap.get("msg").toString());
                return;
            }

            long sendTemplateId = 0;// 请求模板ID
            if (sendFieldList != null && sendFieldList.size() > 0){
                // 根据请求字段的ID获取请求模板的ID
                long sendFieldId = sendFieldList.get(0).getId();
                SendFieldModel sendFieldQuery = new SendFieldModel();
                sendFieldQuery.setId(sendFieldId);
                SendFieldModel sendFieldModel = sendFieldService.queryById(sendFieldQuery);
                if (sendFieldModel == null || sendFieldModel.getId() == 0){
                    sendFailureMessage(response, "根据请求字段ID查询请求字段数据为空,请联系管理员核实问题!");
                    return;
                }
                sendTemplateId = sendFieldModel.getSendTemplateId();
            }

            for (SendFieldModel sendFieldModel : sendFieldList){
                if (sendFieldModel.getId() > 0){
                    // 请求字段的ID大于0则是更新请求字段数据
                    sendFieldService.update(sendFieldModel);
                }else {
                    // 请求字段的ID等于0则是新增请求字段数据
                    sendFieldModel.setSendTemplateId(sendTemplateId);
                    sendFieldService.add(sendFieldModel);
                }
            }

            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }


}
