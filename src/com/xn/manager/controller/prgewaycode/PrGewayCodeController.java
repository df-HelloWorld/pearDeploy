package com.xn.manager.controller.prgewaycode;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.PrGewayCodeModel;
import com.xn.manager.model.PrGewayModel;
import com.xn.manager.model.PrPlatformGewayCodeLinkModel;
import com.xn.manager.model.channel.ChannelPlatformGewayCodeLinkModel;
import com.xn.manager.service.ChannelPlatformGewayCodeLinkService;
import com.xn.manager.service.PrGewayCodeService;
import com.xn.manager.service.PrGewayService;
import com.xn.manager.service.PrPlatformGewayCodeLinkService;
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
 * @Description 通道码的Controller层
 * @Author yoko
 * @Date 2020/9/18 19:28
 * @Version 1.0
 */
@Controller
@RequestMapping("/prgewaycode")
public class PrGewayCodeController extends BaseController {

    private static Logger log = Logger.getLogger(PrGewayCodeController.class);

    @Autowired
    private PrGewayCodeService<PrGewayCodeModel> prGewayCodeService;



    @Autowired
    private PrGewayService<PrGewayModel> prGewayService;

    @Autowired
    private PrPlatformGewayCodeLinkService<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkService;


    @Autowired
    private ChannelPlatformGewayCodeLinkService<ChannelPlatformGewayCodeLinkModel> channelPlatformGewayCodeLinkService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/prgewaycode/prgewaycodeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, PrGewayCodeModel model) throws Exception {
        List<PrGewayCodeModel> dataList = new ArrayList<PrGewayCodeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
//                log.info("7");
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            dataList = prGewayCodeService.queryByList(model);

        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, PrGewayCodeModel model) throws Exception {
        List<PrGewayCodeModel> dataList = new ArrayList<PrGewayCodeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                log.info("7");
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            dataList = prGewayCodeService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataRelationTypeList")
    public void dataRelationTypeList(HttpServletRequest request, HttpServletResponse response, PrGewayCodeModel model) throws Exception {
        List<PrGewayCodeModel> dataList = new ArrayList<PrGewayCodeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                log.info("8");
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            dataList = prGewayCodeService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response,Model model) {
        model.addAttribute("geway", prGewayService.queryAllList());
        return "manager/prgewaycode/prgewaycodeAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, PrGewayCodeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                // check我方通道码是否有重复的
                if (!StringUtils.isBlank(bean.getMyGewayCode())){
                    PrGewayCodeModel query = new PrGewayCodeModel();
                    query.setMyGewayCode(bean.getMyGewayCode());
                    PrGewayCodeModel prGewayCodeModel = prGewayCodeService.queryByCondition(query);
                    if (prGewayCodeModel != null && prGewayCodeModel.getId() > 0){
                        sendFailureMessage(response,"我方通道码有重复,请重新换一个!");
                        return;
                    }
                }else {
                    sendFailureMessage(response,"我方通道码不能为空!");
                    return;
                }
                if (StringUtils.isBlank(bean.getUpServiceCharge())){
                    sendFailureMessage(response,"上游费率不能为空!");
                    return;
                }
                bean.setIdentityKey(MD5.parseMD5(DateUtil.getNowPlusTimeMill()));
                prGewayCodeService.add(bean);
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
        PrGewayCodeModel atModel = new PrGewayCodeModel();
        atModel.setId(id);
        model.addAttribute("geway", prGewayService.queryAllList());
        model.addAttribute("account", prGewayCodeService.queryById(atModel));
        return "manager/prgewaycode/prgewaycodeEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, PrGewayCodeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                // check我方通道码是否有重复的
                if (!StringUtils.isBlank(bean.getMyGewayCode())){
                    PrGewayCodeModel query = new PrGewayCodeModel();
                    query.setMyGewayCode(bean.getMyGewayCode());
                    List<Long> idList = new ArrayList<>();
                    idList.add(bean.getId());
                    query.setIdList(idList);
                    PrGewayCodeModel prGewayCodeModel = prGewayCodeService.queryByCondition(query);
                    if (prGewayCodeModel != null && prGewayCodeModel.getId() > 0){
                        sendFailureMessage(response,"我方通道码有重复,请重新换一个!");
                        return;
                    }
                }else {
                    sendFailureMessage(response,"我方通道码不能为空!");
                    return;
                }

                if (!StringUtils.isBlank(bean.getUpServiceCharge())){
                    if (!bean.getUpServiceCharge().matches("[+-]?[0-9]+(\\.[0-9]+)?")){
                        sendFailureMessage(response,"请输入正确的上游费率!");
                        return;
                    }else {
                        if (Double.parseDouble(bean.getUpServiceCharge()) >= 1){
                            sendFailureMessage(response,"上游费率不能大于等于1!");
                            return;
                        }
                    }
                }else {
                    sendFailureMessage(response,"上游费率不能为空!");
                    return;
                }

                // 校验上游费率是否属于亏本运营 -start

                // 查询所有包含次通道码的所有平台通道码
                PrPlatformGewayCodeLinkModel prPlatformGewayCodeLinkQuery = PublicMethod.assemblePrPlatformGewayCodeLinkQueryByGewayCodeId(bean.getId());
                List<PrPlatformGewayCodeLinkModel> prPlatformGewayCodeLinkList = prPlatformGewayCodeLinkService.queryAllList(prPlatformGewayCodeLinkQuery);
                if (prPlatformGewayCodeLinkList != null && prPlatformGewayCodeLinkList.size() > 0){
                    ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkQuery = PublicMethod.assembleChannelPlatformGewayCodeLinkQueryByPlatformGewayCodeIdList(prPlatformGewayCodeLinkList, bean.getUpServiceCharge());

                    List<ChannelPlatformGewayCodeLinkModel> channelPlatformGewayCodeLinkList = channelPlatformGewayCodeLinkService.getServiceChargeDeficitListByPlatformGewayCodeIdList(channelPlatformGewayCodeLinkQuery);
                    if (channelPlatformGewayCodeLinkList != null && channelPlatformGewayCodeLinkList.size() > 0){
                        String errMsg = "";
                        for (ChannelPlatformGewayCodeLinkModel channelPlatformGewayCodeLinkModel : channelPlatformGewayCodeLinkList){
                            errMsg += "《 渠道：" + channelPlatformGewayCodeLinkModel.getChannelName() + "， 平台通道：" + channelPlatformGewayCodeLinkModel.getCodeName()
                                    + "， 渠道费率：" + channelPlatformGewayCodeLinkModel.getServiceCharge() + " 》 <br>";
                        }
                        errMsg += "上游费率《"+ bean.getUpServiceCharge() + "》；更改费率会早上以上配置属于亏本运营，请您先更新以上渠道与平台通道的费率之后，在更新通道的上游费率！";

                        sendFailureMessage(response, errMsg);
                        return;

                    }
                }

                // 校验上游费率是否属于亏本运营 -end

                prGewayCodeService.update(bean);
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
    public void delete(HttpServletRequest request, HttpServletResponse response, PrGewayCodeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                prGewayCodeService.delete(bean);
                sendSuccessMessage(response, "删除成功");
                return;
            }else {
                log.info("9");
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
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, PrGewayCodeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                prGewayCodeService.manyOperation(bean);
                sendSuccessMessage(response, "状态更新成功");
                return;
            }else {
                log.info("9");
                sendFailureMessage(response,"您无权操作!");
                return;
            }

        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }
    }



    /**
     * @Description: 获取请求模板里面的请求字段
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getById")
    public void getFieldById(Long id, HttpServletResponse response) throws Exception {
        PrGewayCodeModel query = new PrGewayCodeModel();
        query.setId(id);
        PrGewayCodeModel prGewayCodeModel = prGewayCodeService.queryById(query);
        if (prGewayCodeModel == null) {
            sendFailureMessage(response, "没有找到对应的记录!");
            return;
        }
        sendSuccessMessage(response, "", prGewayCodeModel);
    }
}
